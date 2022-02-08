<template>
  <div class="container-lg">
    <div class="column justify-content-md-center">
      <div class="card" style="margin-bottom: 16px">
        <div class="card-body">
          <h5 class="card-title">Movimentação de Estoque</h5>
          <form id="register" @submit.prevent="save" @reset="clear">
            <div class="form-group">
              <label>ID do Produto</label>
              <input
                v-model="stock.product.id"
                class="form-control"
                id="id"
                type="number"
                required
              />
            </div>
            <div class="form-group">
              <label>Tipo</label>
              <select
                v-model="stock.type"
                type="select"
                class="form-control"
                id="type"
                required
              >
                <option selected value="0">Entrada</option>
                <option value="1">Saída</option>
              </select>
            </div>
            <div class="form-group">
              <label>Valor</label>
              <input
                v-model="stock.value"
                class="form-control"
                id="value"
                type="number"
                step="0.01"
                min="0"
                required
              />
            </div>
            <div class="form-group">
              <label>Quantidade</label>
              <input
                v-model="stock.quantity"
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
          <h5 class="card-title">Movimentação</h5>
          <table class="table">
            <thead>
              <tr>
                <th scope="col">ID da movimentação</th>
                <th scope="col">Descrição</th>
                <th scope="col">Tipo</th>
                <th scope="col">Valor</th>
                <th scope="col">Quantidade</th>
              </tr>
            </thead>
            <tbody>
              <template v-for="stock in getStocks">
                <tr :key="stock.id">
                  <th scope="row">{{ stock.id }}</th>
                  <td>{{ stock.product.description }}</td>
                  <td>{{ formatType(stock.type) }}</td>
                  <td>{{ formatMoney(stock.value) }}</td>
                  <td>{{ stock.quantity }}</td>
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
import stockMovementService from "../services/stockMovementService.js";
export default {
  name: "StockMovement",
  data() {
    return {
      stock: {
        product: {
          id: null,
        },
        type: null,
        value: null,
        quantity: null,
      },
      stocks: [],
    };
  },
  computed: {
    getStocks() {
      return this.stocks;
    },
  },
  methods: {
    save() {
      stockMovementService
        .save(this.stock)
        .then((response) => {
          if (response.status === 200) {
            if (response.data.msg) {
              return alert(response.data.msg);
            }
            this.clear();
            this.stocks.unshift(response.data);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    formatType(type) {
      if (type == 0) {
        return "Entrada";
      } else if (type == 1) {
        return "Saída";
      }
    },
    formatMoney(price) {
      return price.toLocaleString("pt-br", {
        style: "currency",
        currency: "BRL",
      });
    },
    clear() {
      this.stock.product.id = null;
      this.stock.product.description = null;
      this.stock.type = null;
      this.stock.value = null;
      this.stock.quantity = null;
    },
  },
};
</script>
