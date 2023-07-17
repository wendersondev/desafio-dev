import React, { useState } from 'react';
import uploadFile from '../api/UploadFile';
import TransactionTable from './TransactionTable';

import transactions from '../api/Transactions';

const UploadComponent = () => {
  const [selectedFile, setSelectedFile] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [transactionsA, setTransactionsA] = useState([]);

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
      const response = getApiTransactionsWithoutName(0,10);
      response.then(response => {
            console.log(response.transactionResponse);
            setTransactionsA(response.transactionResponse.content);
      })
      console.log('Arquivo enviado com sucesso');
    } catch (error) {
      setError('Erro ao enviar o arquivo');
      console.error('Erro:', error);
    }

    setLoading(false);
  };

  const getApiTransactionsWithoutName = async(page, size) => {
    const params = new URLSearchParams();
    params.append('page', page);
    params.append('size', size);
  
    return await transactions(params);
  }

  return (
    <>
    <div className="upload-container">
      <input type="file" onChange={handleFileChange} className="file-input" />
      <button onClick={handleUpload} disabled={loading} className="upload-button">
        Upload
      </button>
      {error && <p className="error-message">{error}</p>}
    </div>
    <TransactionTable data={transactionsA}/>
    </>
  );
};

export default UploadComponent;