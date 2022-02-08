<template>
  <div class="container-lg">
    <div class="column justify-content-md-center">
      <div class="card" style="margin-bottom: 16px">
        <div class="card-body">
          <h5 class="card-title">Cadastro de Produtos</h5>
          <form id="register" @submit.prevent="save" @reset="clear">
            <div class="form-group">
              <label>Descrição</label>
              <input
                v-model="product.description"
                class="form-control"
                id="description"
                placeholder="Digite a descrição"
                required
              />
            </div>
            <div class="form-group">
              <label>Tipo</label>
              <select
                v-model="product.type"
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
            <div class="form-group">
              <label>Preço no fornecedor</label>
              <input
                v-model="product.price"
                class="form-control"
                id="price"
                type="number"
                step="0.01"
                min="0"
                required
              />
            </div>
            <div class="form-group">
              <label>Quantidade em estoque</label>
              <input
                v-model="product.quantity"
                type="number"
                class="form-control"
                id="quantity"
                required
              />
            </div>
            <button
              type="submit"
              class="btn btn-success"
              style="margin-right: 16px"
            >
              <i class="fas fa-save" style="padding-right: 5px"></i> Salvar
            </button>
            <button type="reset" class="btn btn-secondary">
              <i class="fas fa-eraser" style="padding-right: 5px"></i> Limpar
            </button>
          </form>
        </div>
      </div>
      <div class="card">
        <div class="card-body">
          <h5 class="card-title">Produtos Cadastrados</h5>
          <table class="table">
            <thead>
              <tr>
                <th scope="col">ID</th>
                <th scope="col">Descrição</th>
                <th scope="col">Tipo</th>
                <th scope="col">Preço no fornecedor</th>
                <th scope="col">Quantidade em estoque</th>
                <th scope="col">Opções</th>
              </tr>
            </thead>
            <tbody>
              <template v-for="product in getProducts">
                <tr :key="product.id">
                  <th scope="row">{{ product.id }}</th>
                  <td>{{ product.description }}</td>
                  <td>{{ formatType(product.type) }}</td>
                  <td>{{ formatMoney(product.price) }}</td>
                  <td>{{ product.quantity }}</td>
                  <td>
                    <div class="row">
                      <button
                        class="btn btn-primary"
                        style="margin-right: 6px"
                        @click="updateTableProducts(product)"
                      >
                        <i class="fas fa-edit"></i>
                      </button>
                      <button
                        class="btn btn-danger"
                        @click="deleteProduct(product)"
                      >
                        <i class="fas fa-trash-alt"></i>
                      </button>
                    </div>
                  </td>
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
  name: "ProductForm",
  data() {
    return {
      product: {
        id: null,
        description: null,
        type: 0,
        price: null,
        quantity: null,
      },
      products: [],
    };
  },
  computed: {
    getProducts() {
      return this.products;
    },
  },
  async created() {
    this.products = await productService
      .getAllProducts()
      .then((response) => response.data)
      .catch((err) => console.log(err));
  },
  methods: {
    save() {
      if (this.product.id !== null) {
        productService
          .update(this.product)
          .then((response) => {
            if (response.status === 200) {
              this.clear();
              this.updateTable(response.data);
            }
          })
          .catch((err) => {
            console.log(err);
          });
      } else {
        productService
          .save(this.product)
          .then((response) => {
            if (response.status === 200) {
              this.clear();
              this.products.unshift(response.data);
            }
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    updateTable(product) {
      const index = this.products.findIndex((p) => p.id === product.id);
      const keys = Object.keys(product);
      keys.forEach((k) => {
        this.products[index][k] = product[k];
      });
    },
    updateTableProducts(product) {
      const keys = Object.keys(product);
      keys.forEach((k) => {
        this.product[k] = product[k];
      });
    },
    async deleteProduct(product) {
      const res = await productService.delete(product.id).then((response) => {
        return response;
      });
      if (res.status === 200) {
        this.products = this.products.filter((p) => p.id !== product.id);
      }
    },
    formatType(type) {
      if (type == 0) {
        return "Eletrônico";
      } else if (type == 1) {
        return "Eletrodoméstico";
      } else if (type == 2) {
        return "Móvel";
      }
    },
    formatMoney(price) {
      return price.toLocaleString("pt-br", {
        style: "currency",
        currency: "BRL",
      });
    },
    clear() {
      this.product.id = null;
      this.product.description = null;
      this.product.type = null;
      this.product.price = null;
      this.product.quantity = null;
    },
  },
};
</script>
