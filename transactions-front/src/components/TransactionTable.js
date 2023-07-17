import React, { useState, useEffect  } from 'react';

const TransactionTable = ({ data }) => {
    return (
      <>
      <h2>Tabela de Transações</h2>  
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
            <td>{item.date}</td>
            <td>{item.value?.toLocaleString('pt-br', { style: 'currency', currency: 'BRL' })}</td>
            <td>{item.cpf}</td>
            <td>{item.cardNumber}</td>
            <td>{item.hour}</td>
            <td>{item.onwnerStore}</td>
            <td>{item.nameStore}</td>
            <td>{item.typeTransaction}</td>
            <td>{item.operation}</td>
          </tr>
        ))}
      </tbody>
      </table>
      </div>
      </>
    );
}

export default TransactionTable;