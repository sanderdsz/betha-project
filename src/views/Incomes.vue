<template>
  <div class='container'>
    <div class='container__header'>
      <h1 class='container__title'>
        Incomes
      </h1>
    </div>
    <div class='spinner' v-if="loading">
      <Spinner />
    </div>
    <div v-else >
      <div class='container__body'>
        <div>
          <ul>
            <li v-for="item in incomes" :key="item.attributes.value">
              <div class='list'>
                <div class='list__icon'>
                  <div v-show='item.attributes.incomeType === "salary"'>
                    <font-awesome-icon :icon="['fas', 'money-bill-wave']" size="lg" />
                  </div>
                  <div v-show='item.attributes.incomeType === "transaction"'>
                    <font-awesome-icon :icon="['fas', 'comment-dollar']" size="lg" />
                  </div>
                </div>
                <div class='list__wrapper'>
                  <div class='list__header'>
                    <span>{{ item.attributes.date }}</span>
                  </div>
                  <span>R$ {{ item.attributes.value }}</span>
                </div>
                <button class='button button--red button__delete' @click.prevent='this.delete(item.id)'>  
                  <font-awesome-icon :icon="['fas', 'trash']" size="lg" />
                </button>
              </div>
            </li>
          </ul>
        </div>
        <div class='container__pagination'>
          <button class='button button--red' @click.prevent='goToPreviousPage'>Previous</button>
          <div class='container__pagination--right'>
            <button class='button button--red' @click.prevent='goToFowardPage'>Forward</button>
          </div>
        </div>
      </div>
      <div class='container__footer'>
        <div class='container__footer--right'>
          <router-link class='button button--red' to='/incomes/create'>create</router-link>
        </div>
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
      incomes: {},
      page: 1, 
      pageCount: 0,
      total: 0,
      pageSize: 5,
      loading: false
    }
  },
  methods: {
   async getIncomes () {
      this.loading = true
      const request = await axios.get(`http://152.70.211.106:8080/api/incomes?pagination[page]=${this.page}&pagination[pageSize]=${this.pageSize}&sort=date:desc`)
      .then(response => { 
        this.incomes = response.data.data  
        this.total = response.data.meta.pagination.total
        this.pageCount = response.data.meta.pagination.pageCount
        }
      )
      this.loading = false
      return request
    },
    async delete (id) {
      this.loading = true
      await axios.delete(`http://152.70.211.106:8080/api/incomes/${id}`).then(
        response => {
          this.getIncomes() 
          this.loading = false 
          return response
        }
      )
    },
    goToPreviousPage () {
      if (this.page !== 1) {
        this.page = this.page - 1
        this.getIncomes()
      }
    },
    goToFowardPage () {
      if (this.page === this.pageCount) {
        return null
      }
      this.page = this.page + 1
      this.getIncomes()
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
  background-size: 35% 0.3rem;
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