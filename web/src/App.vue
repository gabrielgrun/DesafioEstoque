<!--<template>
  <div>
    <ProductForm />
    <ProfitFilter />
  </div>
</template>-->

<script>
import ProductForm from "./components/ProductForm";
import ProfitFilter from "./components/ProfitFilter";
import StockMovement from "./components/StockMovement";
import TypeFilter from "./components/TypeFilter";
import Menu from "./components/Menu";

const routes = {
  "/": Menu,
  "/cadastro": ProductForm,
  "/estoque": StockMovement,
  "/tipo": TypeFilter,
  "/lucro": ProfitFilter,
};

export default {
  name: "App",
  data() {
    return { currentRoute: window.location.pathname };
  },
  computed: {
    ViewComponent() {
      return routes[this.currentRoute];
    },
  },
  render(h) {
    return h(this.ViewComponent);
  },
};
</script>

<style>
</style>
