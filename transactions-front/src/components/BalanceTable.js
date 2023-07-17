import React, { useState, useEffect  } from 'react';


const BalanceTable = ({ totalDebito, totalCredito, saldo }) => {
    return (
      <>
      <h2>Tabela de Totalização</h2>  
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
            <td>{totalDebito?.toLocaleString('pt-br', { style: 'currency', currency: 'BRL' })}</td>
            <td>{totalCredito?.toLocaleString('pt-br', { style: 'currency', currency: 'BRL' })}</td>
            <td>{saldo?.toLocaleString('pt-br', { style: 'currency', currency: 'BRL' })}</td>
          </tr>
        </tbody>
      </table>
      </>
    );
  };

  export default BalanceTable;