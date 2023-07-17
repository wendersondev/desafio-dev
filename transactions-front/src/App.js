import React, { useState } from 'react';
import './styles.css';
import Pagination from './components/Pagination';

const Table = ({ data }) => {

  const [currentPage, setCurrentPage] = useState(1);
  const totalPages = 10;

  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
    // lógica
  };

  return (
    <div className="table-container">
      <table className="my-table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Data</th>
          <th>Valor</th>
          <th>CPF</th>
          <th>Número do Cartão</th>
          <th>Hora</th>
          <th>Nome do Dono</th>
          <th>Nome da Loja</th>
          <th>Tipo de Transação</th>
          <th>Operação</th>
        </tr>
      </thead>
      <tbody>
        {data.map((item) => (
          <tr key={item.id}>
            <td>{item.id}</td>
            <td>{item.data}</td>
            <td>{item.valor}</td>
            <td>{item.cpf}</td>
            <td>{item.numeroCartao}</td>
            <td>{item.hora}</td>
            <td>{item.nomeDono}</td>
            <td>{item.nomeLoja}</td>
            <td>{item.tipoTransacao}</td>
            <td>{item.operacao}</td>
          </tr>
        ))}
      </tbody>
    </table>
    <Pagination
        currentPage={currentPage}
        totalPages={totalPages}
        onPageChange={handlePageChange}
    />
    </div>
  );
};

const TotalTable = ({ totalDebito, totalCredito, saldo }) => {
  return (
    <table>
      <thead>
        <tr>
          <th>Total de Débito</th>
          <th>Total de Crédito</th>
          <th>Saldo</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>{totalDebito}</td>
          <td>{totalCredito}</td>
          <td>{saldo}</td>
        </tr>
      </tbody>
    </table>
  );
};

const App = () => {
  const data = [
    {
      id: 1,
      data: '2023-07-15',
      valor: 100.0,
      cpf: '123.456.789-00',
      numeroCartao: '**** **** **** 1234',
      hora: '14:30',
      nomeDono: 'João da Silva',
      nomeLoja: 'Loja A',
      tipoTransacao: 'Débito',
      operacao: 'Compra',
    },
  ];

  const totalDebito = 500.0;
  const totalCredito = 800.0;
  const saldo = totalCredito - totalDebito;

  return (
    <div>
      <h2>Tabela de Transações</h2>
      <Table data={data} />

      <h2>Tabela de Totalização</h2>
      <TotalTable
        totalDebito={totalDebito}
        totalCredito={totalCredito}
        saldo={saldo}
      />
    </div>
  );
};

export default App;