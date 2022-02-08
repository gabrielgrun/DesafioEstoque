import axios from 'axios';
const BASE_URL = 'http://localhost:8080';
export default {
  save: (product) => axios.post(BASE_URL + '/estoque', product),
};
