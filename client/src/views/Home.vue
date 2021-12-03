<template>
  <div class="container">
    <form v-on:submit.prevent>
      <input v-model="object.data.message">
      <button v-on:click="update">Submit</button>
    </form>
    <h1>{{ response.message }}</h1>
    {{ responseAxios }}
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Home',
  data () {
    return {
      object: {
        data: {
          message: ''
        }
      },
      response: {
        message: ''
      }
    }
  },
  methods: {
    async update() {
      await axios.put("http://152.70.211.106:8080/api/health", this.object)
    },
    async get() {
      await axios.get("http://152.70.211.106:8080/api/health")
      .then(response => this.response = response.data.data.attributes)
    }
  },
  computed: {
    responseAxios: () => {
      return this.response.message
    }
  },
  created () {
    this.get()
  },
  mounted () {
  }
}
</script>

<style scoped>
.container {
  height: 100vw;
}
</style>