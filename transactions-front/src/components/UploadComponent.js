import React, { useState } from 'react';
import uploadFile from '../api/UploadFile';

const UploadComponent = () => {
  const [selectedFile, setSelectedFile] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

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
      // Upload bem-sucedido, faça qualquer ação necessária após o upload
      console.log('Arquivo enviado com sucesso');
    } catch (error) {
      setError('Erro ao enviar o arquivo');
      console.error('Erro:', error);
    }

    setLoading(false);
  };

  return (
    <div className="upload-container">
      <input type="file" onChange={handleFileChange} className="file-input" />
      <button onClick={handleUpload} disabled={loading} className="upload-button">
        Upload
      </button>
      {error && <p className="error-message">{error}</p>}
    </div>
  );
};

export default UploadComponent;