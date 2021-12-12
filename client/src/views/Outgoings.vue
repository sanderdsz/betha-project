<template>
  <div class='container'>
    <div class='container__header'>
      <h1 class='container__title'>
        Expenses
      </h1>
    </div>
    <div class='spinner' v-if="loading">
      <Spinner />
    </div>
    <div v-else >
      <div class='container__body'>
        <div>
          <ul>
            <li v-for="item in expenses" :key="item.attributes.value">
              <div class='list'>
                <div class='list__icon'>
                  <div v-show='item.attributes.transactionType === "housing"'>
                    <font-awesome-icon :icon="['fas', 'house-user']" size="lg" />
                  </div>
                  <div v-show='item.attributes.transactionType === "leisure"'>
                    <font-awesome-icon :icon="['fas', 'laugh-beam']" size="lg" />
                  </div>
                  <div v-show='item.attributes.transactionType === "feeding"'>
                    <font-awesome-icon :icon="['fas', 'utensils']" size="lg" />
                  </div>
                  <div v-show='item.attributes.transactionType === "transportation"'>
                    <font-awesome-icon :icon="['fas', 'car']" size="lg" />
                  </div>
                  <div v-show='item.attributes.transactionType === "health"'>
                    <font-awesome-icon :icon="['fas', 'heartbeat']" size="lg" />
                  </div>
                  <div v-show='item.attributes.transactionType === "personal"'>
                    <font-awesome-icon :icon="['fas', 'user-lock']" size="lg" />
                  </div>
                  <div v-show='item.attributes.transactionType === "shopping"'>
                    <font-awesome-icon :icon="['fas', 'shopping-cart']" size="lg" />
                  </div>
                </div>
                <div class='list__wrapper'>
                  <div class='list__header'>
                    <span>{{ item.attributes.date }}</span>
                  </div>
                  <span>R$ {{ item.attributes.value }}</span>
                </div>
                <!--
                <div class='list__type'>
                  <span>{{ item.attributes.transactionType }}</span>
                </div>
                -->
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
          <router-link class='button button--red' to='/outgoings/create'>create</router-link>
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
      expenses: {},
      page: 1, 
      pageCount: 0,
      total: 0,
      pageSize: 5,
      loading: false
    }
  },
  computed: {
    icons () {
      return 'money-bill-wave'
    }
  },
  methods: {
   async getIncomes () {
      this.loading = true
      const request = await axios.get(`http://152.70.211.106:8080/api/outgoings?pagination[page]=${this.page}&pagination[pageSize]=${this.pageSize}&sort=date:desc`)
      .then(response => { 
        this.expenses = response.data.data  
        this.total = response.data.meta.pagination.total
        this.pageCount = response.data.meta.pagination.pageCount
        }
      )
      this.loading = false
      return request
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
.container {
  background-color: #F6E4B6;
}
.container__header {
  text-align: center;
}
.container__title {
  width: 100%;
  background-size: 45% 0.3rem;
  background-image: url('../assets/images/blue-underline.svg');
  color: #1E5D84;
  background-position: center bottom;
}
.container__footer {
  background-color: #F6E4B6;
}
.list {
  display: flex;
  background-color: #1E5D84;
  color:#fff;
}
.list__header {
  background-color: #F6E4B6;
  color: #1E5D84;
}
</style>