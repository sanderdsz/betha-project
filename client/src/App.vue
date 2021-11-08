<template>
  <router-view class="content"></router-view>
  <router-view class="navbar" name="navbar"></router-view>
</template>

<script>
export default {
  name: 'App',
  beforeMount() {
    this.$store.dispatch("initTheme");
  },
}
</script>

<style>
.content {
  height: 100vh;
  z-index: 0;
}
.navbar {
  position: absolute;
  width: 100%;
  bottom: 0;
  z-index: 10;
}
</style>
