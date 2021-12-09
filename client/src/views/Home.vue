<template>
  <div class='container'>
   <div class='container__header'>
     <div class='container__title'>
       <h1>Welcome</h1>
     </div>
   </div>
    <div class='spinner' v-if="loading">
      <Spinner />
    </div>
    <div v-else>
      <div class='container__body'>
        <div class='container__wrapper'>
          <div class='container__wrapper--center'>
            <h2 class='text--blue'>Your month now is...</h2>
          </div>
        </div>
        <div class='container__card'>
          <div class='card card--left text--red'>
            R$ {{ incomesSum }}
          </div>
          <div>
            <h2 class='text--blue'>Incomes</h2>
          </div>
        </div>
        <div class='container__card'>
          <div>
            <h2 class='text--blue'>Expenses</h2>
          </div>
          <div class='card card--right text--red'>
            R$ {{ expensesSum }}
          </div>
        </div>
      </div>
      <div class='total'>
        <div class='total--left'>
          <div class='total__dollar'>R$</div>
          <span class='total__sum'>{{ balance }}</span>
        </div>
        <div class='total--right'>
          <h3 class='total__label'>your balance</h3>
        </div>
      </div>
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
      incomesResponse: {},
      incomesRefactor: {},
      incomesSum: 0,
      expensesResponse: {},
      expensesRefactor: {},
      expensesSum: 0,
      balance: 0,
      loading: false,
      month: ''
    }
  },
  methods: {
    async getIncomes () {
      this.loading = true
      const dt = new Date()
      const currentMonth = dt.getMonth() + 1
      const request = await axios.get("http://152.70.211.106:8080/api/incomes")
      .then(response => this.incomesResponse = response.data.data)
      this.incomesRefactor = this.incomesResponse.map(
        function(item) {
          let months = item.attributes.date.split('-')
          if (months[1] == currentMonth) {
            return item.attributes.value
          } else {
            return null
          }
        }
      )
      for (let i = 0; i < this.incomesRefactor.length; i++) {
        this.incomesSum += this.incomesRefactor[i]
      }
      this.loading = false
      return request
    },
    async getExpenses () {
      this.loading = true
      const dt = new Date()
      const currentMonth = dt.getMonth() + 1
      const request = await axios.get("http://152.70.211.106:8080/api/outgoings")
      .then(response => this.expensesResponse = response.data.data)
      this.expensesRefactor = this.expensesResponse.map(
        function(item) {
          let months = item.attributes.date.split('-')
          if (months[1] == currentMonth) {
            return item.attributes.value
          } else {
            return null
          }
        }
      )
      for (let i = 0; i < this.expensesRefactor.length; i++) {
        this.expensesSum += this.expensesRefactor[i]
      }
      this.balance = this.expensesSum - this.incomesSum
      this.loading = false
      return request
    }
  },
  computed: {
    responseAxios: () => {
      return this.response.message
    }
  },
  async created () {
    await this.getIncomes()
    await this.getExpenses()
  },
  mounted () {
  }
}
</script>

<style scoped>
.container {
  background-color: #F7F5F3;
}
.container__header {
  background-color: #FCE180;;
}
.container__title {
  width: 9rem;
  padding: .75rem 0 0 .5rem;
  background-color: #fff;
  background-image: none;
}
.container__body {
  background-color: #F7F5F3;
}
.container__wrapper--center {
  margin: 1rem 0 0 0;
  text-align: center;
}
.total {
  padding-top: 1.5rem;
  padding-bottom: 4rem;
  bottom: 0;
  width: 100%;
  margin: 3rem 0 0 0;
  height: 5rem;
  background: linear-gradient(-60deg, #f2c4c4 50%, #e66c55 50%);
}
.total--left {
  padding: 0 0 0 1rem;
  display: flex;
}
.total--right {
  padding: 0 0 0 13rem;
}
.total__dollar {
  font-family: cooper-hewitt-medium;
  color: #fff;
  font-size: 1rem;
  text-shadow: 0.2rem 0.2rem 0 #1e5d84;
}
.total__sum {
  width: 5rem;
  font-family: cooper-hewitt-medium;
  color: #fff;
  font-size: 2.5rem;
  text-shadow: 0.2rem 0.2rem 0 #1e5d84;
}
.total__label {
  margin-top: 1rem;
  font-size: 2rem;
  color: #1e5d84;
  text-shadow: 0.2rem 0.2rem 0 #fff;
}
</style>