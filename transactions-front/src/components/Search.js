import React, { useState } from 'react';

const Search = ({ onSearch }) => {
  const [searchTerm, setSearchTerm] = useState('');

  const handleSearchTermChange = (event) => {
    setSearchTerm(event.target.value);
  };

  const handleSearch = () => {
    onSearch(searchTerm);
  };

  return (
    <div className="search-container">
      <input type="text" value={searchTerm} onChange={handleSearchTermChange} className="search-input" />
      <button onClick={handleSearch} className="search-button">Buscar</button>
    </div>
  );
};

export default Search;