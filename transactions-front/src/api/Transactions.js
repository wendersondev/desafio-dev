import axios from 'axios';

const transactions = async (params) => {
    const result = await axios.get('http://localhost:9001/v1/cnab/transactions', {
        headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*"
        },
        params: params
    });

    return result.data;
}

export default transactions;