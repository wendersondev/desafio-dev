import axios from 'axios';

const uploadFile = async (formData) => {
    try {
      const response = await axios.post('http://localhost:9001/v1/cnab/transactions/load', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
          'Access-Control-Allow-Origin': '*'
        }
      });
  
      console.log('Arquivo carregado com sucesso');
      return response.data;
    } catch (error) {
      console.error('Erro ao carregar o arquivo:', error);
      throw error;
    }
};

export default uploadFile;