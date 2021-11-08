<template>
    <button 
     @click="toggleTheme"
     class="dark:text-red-400 text-cyan-200">
      Theme Toggle
    </button>
</template>

<script>
import { mapGetters } from 'vuex';
  export default {
    computed: {
      ...mapGetters({ theme: 'getTheme' }),
    },
    methods: {
      toggleTheme() {
        this.$store.dispatch('toggleTheme');
      },
    },
  };
</script>