<template>
  <div class="container">
    <!--
    <form v-on:submit.prevent>
      <input v-model="object.data.message">
      <button v-on:click="update">Submit</button>
    </form>
    -->
    <div v-if="loading">
      <Spinner />
    </div>
    <div v-else>
      <h1>{{ response.message }}</h1>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import Spinner from '../components/Spinner.vue'

export default {
  name: 'Home',
  components: {
    Spinner
  },
  data () {
    return {
      object: {
        data: {
          message: ''
        }
      },
      response: {
        message: ''
      },
      loading: false
    }
  },
  methods: {
    async update() {
      await axios.put("http://152.70.211.106:8080/api/health", this.object)
    },
    async get() {
      this.loading = true
      const request = await axios.get("http://152.70.211.106:8080/api/health")
      .then(response => this.response = response.data.data.attributes)
      this.loading = false
      return request
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
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>