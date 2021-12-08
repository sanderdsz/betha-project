<template>
  <div class="nav__container">
    <router-link class="nav__item" to="/">
      <div class="nav__children">
        <font-awesome-icon :icon="['fas', 'home']" size="lg" />
        <span class="nav__title">Home</span>
      </div>
    </router-link>
    <br>
    <router-link class="nav__item" to="/incomes" :class="{'active': isActive}">
      <div class="nav__children">
        <font-awesome-icon :icon="['fas', 'wallet']" size="lg" />
        <span class="nav__title" >Incomes</span>
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
  name: 'Navigation',
  computed: {
    isActive() {
     if (this.$route.matched[0].path === '/incomes' ||
        this.$route.matched[0].path === '/incomes/create'
      ) {
       return true
     } if (        
       this.$route.matched[0].path === '/outgoings' ||
       this.$route.matched[0].path === '/outgoings/create'
      ) {
        return true
      } else {
       return console.log(this.$route)
     }
   },
  }
}
</script>

<style scoped>
.nav__container {
  overflow: hidden;
  position: fixed;
  display: flex;
  bottom: 0;
  justify-content: space-around;
  border-top: 1px solid #ffffff;
  box-shadow: 10px 10px 10px 10px rgba(0,0,0,.2);
  z-index: 100;
  background-color: #ffffff;
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
  font-family: gt-pressura-mono-regular;
}
.active > div {
  color: #ffe180;
  /* border-bottom: 2px solid #ffe180; */
}
.active > div > span {
  color: #1e5d84;
}
a.router-link-active > div {
  color: #ffe180;
  /* border-bottom: 2px solid #ffe180; */
}
a.router-link-active > div > span {
  color: #1e5d84;
}
</style>