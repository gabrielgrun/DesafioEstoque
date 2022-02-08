import axios from 'axios';
const BASE_URL = 'http://localhost:8080';
export default {
  save: (product) => axios.post(BASE_URL + '/produto', product),
  update: (product) => axios.put(BASE_URL + '/produto/' + product.id, product),
  getAllProducts: () => axios.get(BASE_URL + '/produto/list'),
  delete: (idProduct) => axios.delete(BASE_URL + '/produto/' + idProduct),
  getByType: (type) => axios.get(BASE_URL + '/produto/tipo/' + type),
  getProfit: (id) => axios.get(BASE_URL + '/produto/lucro/' + id),
};
