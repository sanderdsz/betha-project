<template>
  <main
    class="min-h-screen 
           bg-green-50 text-gray-700 
           dark:bg-gray-900 dark:text-purple-50">
    <ThemeToggler/>
    <slot />
  </main>
</template>

<script>
import { mapGetters } from "vuex";
import ThemeToggler from "../components/ThemeToggler.vue";

export default {
  components: {
    ThemeToggler,
  },
  beforeMount() {
    this.$store.dispatch("initTheme");
  },
  computed: {
    ...mapGetters({ theme: "getTheme" }),
  },
  watch: {
    theme(newTheme) {
      newTheme === "light"
        ? document.querySelector("html").classList.remove("dark")
        : document.querySelector("html").classList.add("dark");
    },
  },
};
</script>