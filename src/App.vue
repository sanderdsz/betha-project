<template>
  <div class='force-view'> 
    <router-view class="main"></router-view>
    <router-view class="navbar" name="navbar"></router-view>  
  </div>
</template>

<script>
export default {
  name: 'App',
}
</script>

<style>
:root {
  --navbar-height: 3.5rem;
}
.main {
  height: calc(100vh - var(--navbar-height));
}
.navbar {
  position: fixed;
  bottom: 0;
  margin: 0;
  padding: .25rem 0 0 0;
  width: 100%;
  height: var(--navbar-height);
}
</style>
