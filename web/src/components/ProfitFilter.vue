<template>
  <div class="container-lg">
    <div class="column justify-content-md-center">
      <div class="card" style="margin-bottom: 16px">
        <div class="card-body">
          <h5 class="card-title">Lucro por produto</h5>
          <form id="search">
            <div class="form-group">
              <label>ID do Produto</label>
              <input
                v-model="id"
                class="form-control"
                id="id"
                type="number"
                required
              />
            </div>

            <button
              type="button"
              class="btn btn-success"
              style="margin-right: 16px"
              @click="getProfit()"
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
                <th scope="col">Quantidade vendida</th>
                <th scope="col">Lucro</th>
              </tr>
            </thead>
            <tbody>
              <template v-for="product in getProducts">
                <tr :key="product.id">
                  <th scope="row">{{ product.id }}</th>
                  <td>{{ product.description }}</td>
                  <td>{{ product.sellQuantity }}</td>
                  <td>{{ product.profit }}</td>
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
  name: "ProfitFilter",
  data() {
    return {
      id: null,
      products: [],
    };
  },
  computed: {
    getProducts() {
      return this.products;
    },
  },
  methods: {
    async getProfit() {
      this.products = await productService
        .getProfit(this.id)
        .then((response) => response.data)
        .catch((err) => console.log(err));
    },
  },
};
</script>
