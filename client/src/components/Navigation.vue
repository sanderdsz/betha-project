<template>
  <div class="nav__container">
    <router-link class="nav__item" to="/">
      <div class="nav__children">
        <font-awesome-icon :icon="['fas', 'home']" size="lg" />
        <span class="nav__title">Home</span>
      </div>
    </router-link>
    <br>
    <router-link class="nav__item" to="/incomes">
      <div class="nav__children">
        <font-awesome-icon :icon="['fas', 'wallet']" size="lg" />
        <span class="nav__title">Incomes</span>
      </div>
    </router-link>
    <br>
    <router-link class="nav__item" to="/outgoings">
      <div class="nav__children">
        <font-awesome-icon :icon="['fas', 'hand-holding-usd']" size="lg" />
        <span class="nav__title">Outgoings</span>
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
  border-top: 1px solid #ffffff;
  box-shadow: 10px 10px 10px 10px rgba(0,0,0,.2);
}
.nav__item {
  padding: 0.25rem;
  color: #999;
  text-decoration: none;
}
.nav__children {
  display: grid;
  justify-items: center;
}
.nav__children > span {
  padding-top: .5rem;
}
.nav__title {
  font-size: .85rem;
}
a.router-link-exact-active > div {
  color: #ffe180;
  border-bottom: 2px solid #ffe180;
}
a.router-link-exact-active > div > span {
  color: #1e5d84;
}
</style>