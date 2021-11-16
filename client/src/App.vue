<template>
  <div> 
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
.main {
}

.navbar {
  position: fixed;
  bottom: 0;
  width: 100%;
  margin: 0;
}
</style>
