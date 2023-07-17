import React, { useState, useEffect  } from 'react';
import './styles.css';
import Principal from './components/Principal';

const Table = () => {

  return (
    <>
    <div className="table-container">
      <Principal />
    </div>
    </>
  );
};

const App = () => {
  return (
    <div>
      <Table />
    </div>
  );
};

export default App;