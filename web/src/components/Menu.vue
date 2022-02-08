<template>
  <div class="container-lg">
    <div class="column justify-content-md-center">
      <div class="card" style="margin-bottom: 16px">
        <div class="card-body">
          <h5 class="card-title">Menu</h5>
          <div class="list-group">
            <a class="list-group-item list-group-item-action" href="/cadastro"
              >Cadastro de Produtos</a
            >
            <a class="list-group-item list-group-item-action" href="/estoque"
              >Registro de Movimentação</a
            >
            <a class="list-group-item list-group-item-action" href="/tipo"
              >Filtro por tipo (deve ter movimentação)</a
            >
            <a class="list-group-item list-group-item-action" href="/lucro"
              >Lucro por produto</a
            >
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "Menu",
};
</script>
