<template>
  <div class='container'>
    <div class='container__header'>
      <h1 class='container__title'>
        Incomes
      </h1>
    </div>
    <div class='container__body'>
      <div class='spinner' v-if="loading">
        <Spinner />
      </div>
      <div v-else>
        <ul>
          <li v-for="item in response" :key="item.attributes.value">
            <div class='list'>
              <div class='list__icon'>
                <font-awesome-icon :icon="['fas', 'money-bill-wave']" size="lg" />
              </div>
              <div class='list__wrapper'>
                <div class='list__header'>
                  <span>{{ item.attributes.date }}</span>
                </div>
                <span>R$ {{ item.attributes.value }}</span>
              </div>
            </div>
          </li>
        </ul>
      </div>
    </div>
    <div class='container__footer'>
      <div class='container__footer--right'>
        <router-link class='button button--red' to='/incomes/create'>create</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import Spinner from '../components/Spinner.vue'

export default {
  name: 'Incomes',
  components: {
    Spinner
  },
  data () {
    return {
      response: {},
      loading: false
    }
  },
  methods: {
   async getIncomes () {
      this.loading = true
      const request = await axios.get("http://152.70.211.106:8080/api/incomes")
      .then(response => this.response = response.data.data)
      this.loading = false
      console.log(this.response)
      return request
    }
  },
  created () {
    this.getIncomes()
  }
}
</script>

<style scoped>
span {
  padding-top: .5rem;
}
.container {
  height: 100vh;
  padding-bottom: 3rem;
  background-color: #1E5D84;
}
.container__header {
  text-align: center;
}
.container__title {
  width: 100%;
  background-image: url('../assets/images/yellow-underline.svg');
  background-size: 45% 0.3rem;
  color: #fff;
  background-position: center bottom;
}
.container__footer {
  background-color: #1E5D84;
}
.list {
  display: flex;
}
</style>