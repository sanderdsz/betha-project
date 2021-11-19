<template>
  <div class="container">
    <h1>hi from home</h1>
    <router-link to="/world">to world</router-link>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'Home'
}
</script>

<style scoped>
.container {
  height: 100vw;
}
</style>