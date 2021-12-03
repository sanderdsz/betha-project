<template>
  <div class="nav__container">
    <router-link class="nav__item" to="/">
      <div class="nav__children">
        <font-awesome-icon :icon="['fas', 'home']" size="lg" />
        <span>Home</span>
      </div>
    </router-link>
    <br>
    <router-link class="nav__item" to="/incomes">
      <div class="nav__children">
        <font-awesome-icon :icon="['fas', 'wallet']" size="lg" />
        <span>Incomes</span>
      </div>
    </router-link>
    <br>
    <router-link class="nav__item" to="/outgoings">
      <div class="nav__children">
        <font-awesome-icon :icon="['fas', 'hand-holding-usd']" size="lg" />
        <span>Outgoings</span>
      </div>
    </router-link>
  </div>
</template>

<script>
export default {
  name: 'Navigation'
}
</script>

<style scoped>
.nav__container {
  display: flex;
  justify-content: space-around;  
}
.nav__item {
  padding: 0.25rem;
  color: #000000;
  text-decoration: none;
}
.nav__children {
  display: grid;
  justify-items: center;
}
.nav__children > span {
  padding-top: .5rem;
}

a.router-link-exact-active {
  color: #1e5d84;
}
</style>