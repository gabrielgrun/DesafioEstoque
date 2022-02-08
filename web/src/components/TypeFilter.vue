<template>
  <div class="container-lg">
    <div class="column justify-content-md-center">
      <div class="card" style="margin-bottom: 16px">
        <div class="card-body">
          <h5 class="card-title">Filtro por tipo (deve ter movimentação)</h5>
          <form id="search">
            <div class="form-group">
              <label>Tipo</label>
              <select
                v-model="type"
                type="select"
                class="form-control"
                id="type"
                required
              >
                <option selected value="0">Eletrônico</option>
                <option value="1">Eletrodoméstico</option>
                <option value="2">Móvel</option>
              </select>
            </div>

            <button
              type="button"
              class="btn btn-success"
              style="margin-right: 16px"
              @click="getByType()"
            >
              <i class="fas fa-search" style="padding-right: 5px"></i> Pesquisar
            </button>
          </form>
        </div>
      </div>
      <div class="card">
        <div class="card-body">
          <h5 class="card-title">Produtos</h5>
          <table class="table">
            <thead>
              <tr>
                <th scope="col">ID</th>
                <th scope="col">Descrição</th>
                <th scope="col">Quantidade em estoque</th>
                <th scope="col">Quantidade vendida</th>
              </tr>
            </thead>
            <tbody>
              <template v-for="product in getProducts">
                <tr :key="product.id">
                  <th scope="row">{{ product.id }}</th>
                  <td>{{ product.description }}</td>
                  <td>{{ product.quantity }}</td>
                  <td>{{ product.sellQuantity }}</td>
                </tr>
              </template>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import productService from "../services/productService.js";
export default {
  name: "TypeFilter",
  data() {
    return {
      type: null,
      products: [],
    };
  },
  computed: {
    getProducts() {
      return this.products;
    },
  },
  methods: {
    async getByType() {
      this.products = await productService
        .getByType(this.type)
        .then((response) => response.data)
        .catch((err) => console.log(err));
    },
  },
};
</script>
