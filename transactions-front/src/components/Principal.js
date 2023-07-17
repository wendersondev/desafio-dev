import React, { useState, useEffect } from 'react';
import uploadFile from '../api/UploadFile';
import TransactionTable from './TransactionTable';
import BalanceTable from './BalanceTable';
import Pagination from './Pagination';
import Search from './Search';

import transactions from '../api/Transactions';


const Principal = () => {
  const [selectedFile, setSelectedFile] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [transactionsA, setTransactionsA] = useState([]);
  const [amountBalance, setBalance] = useState(null);

  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(0);
  

  const getApiTransactionsWithoutName = async(page, size, name) => {
    const params = new URLSearchParams();
    params.append('page', page);
    params.append('size', size);
    if(name != null){
      params.append('name', name);
    }
  
    return await transactions(params);
  }

  const buildDataTable = async(totalPages, name) => {
    const response = getApiTransactionsWithoutName(totalPages,10, name);
      
    response.then(response => {
      setTransactionsA(response.transactionResponse.content);
      setBalance(response.balanceResponse);
      setTotalPages(response.transactionResponse.totalPages);
    });
  }

  const handleSearch = (searchTerm) => {
      buildDataTable(0,searchTerm);
  };

  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
    buildDataTable(pageNumber-1,null);
  };

  const handleFileChange = (event) => {
    setSelectedFile(event.target.files[0]);
  };

  const handleUpload = async () => {
    if (!selectedFile) {
      setError('Nenhum arquivo selecionado');
      return;
    }
    setError(null);
    setLoading(true);

    const formData = new FormData();
    formData.append('file', selectedFile);

    try {
      await uploadFile(formData);
      buildDataTable(0, null);
      console.log('Arquivo enviado com sucesso');
    } catch (error) {
      setError('Erro ao enviar o arquivo');
      console.error('Erro:', error);
    }

    setLoading(false);
  };

 
  useEffect(() => {
    if (transactionsA.length < 1) {
      buildDataTable(0, null);
    }
  });


  return (
    <>
    <div className="upload-container">
      <input type="file" onChange={handleFileChange} className="file-input" />
      <button onClick={handleUpload} disabled={loading} className="upload-button">
        Upload
      </button>
      {error && <p className="error-message">{error}</p>}
    </div>
    <div>
      <Search onSearch={handleSearch} />
    </div>
    <TransactionTable data={transactionsA}/>
    <Pagination
        currentPage={currentPage}
        totalPages={totalPages}
        onPageChange={handlePageChange}
    />
    <BalanceTable saldo={amountBalance ? amountBalance.valueTotal : 0 } 
                  totalCredito={amountBalance ? amountBalance.valueCredit : 0 } 
                  totalDebito={amountBalance ? amountBalance.valueDebit : 0} />
    </>
  );
};

export default Principal;