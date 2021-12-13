<template>
  <div class='container'>
   <div class='container__header'>
     <div class='container__title'>
       <h2>Welcome</h2>
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
        <div class='board'>
          <li class='board__body' v-for='item in sumArr' :key='item.value'>
            <div v-show='item.type === "housing"'>
              <font-awesome-icon :icon="['fas', 'house-user']" size="md" />
            </div>
            <div v-show='item.type === "leisure"'>
              <font-awesome-icon :icon="['fas', 'laugh-beam']" size="md" />
            </div>
            <div v-show='item.type === "feeding"'>
              <font-awesome-icon :icon="['fas', 'utensils']" size="md" />
            </div>
            <div v-show='item.type === "transportation"'>
              <font-awesome-icon :icon="['fas', 'car']" size="md" />
            </div>
            <div v-show='item.type === "health"'>
              <font-awesome-icon :icon="['fas', 'heartbeat']" size="md" />
            </div>
            <div v-show='item.type === "personal"'>
              <font-awesome-icon :icon="['fas', 'user-lock']" size="md" />
            </div>
            <div v-show='item.type === "shopping"'>
              <font-awesome-icon :icon="['fas', 'shopping-cart']" size="md" />
            </div>
            <div class='body__items'>
              <h3>{{ item.percentage }} %</h3>
              <span>R$ {{ item.value }}</span>
            </div>
          </li>
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
      expenses: {},
      personalExpenses: {},
      personalSum: 0,
      healthExpenses: {},
      healthSum: 0,
      feedingExpenses: {},
      feedingSum: 0,
      leisureExpenses: {},
      leisureSum: 0,
      housingExpenses: {},
      housingSum: 0,
      shoppingExpenses: {},
      shoppingSum: 0,
      transportationExpenses: {},
      transportationSum: 0,
      expensesResponse: {},
      expensesRefactor: {},
      expensesSum: 0,
      balance: 0,
      loading: false,
      month: '',
      sumArr: []
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
      this.balance = this.incomesSum - this.expensesSum 
      this.loading = false
      return request
    },
    async getExpensesAndFilter () {
      this.loading = true
      const today = new Date()
      const lastDayOfMonth = new Date(today.getFullYear(), today.getMonth() + 1, 0).toISOString().split('T')[0]
      const firstDayOfMonth = new Date(today.getFullYear(), today.getMonth(), 1).toISOString().split('T')[0]
      await axios.get(`http://152.70.211.106:8080/api/outgoings` + 
      `?filters[$and][0][date][$gte]=${firstDayOfMonth}` +
      `&filters[$and][1][date][$lte]=${lastDayOfMonth}`)
      .then(response => {
        this.expenses = response.data.data
        this.shoppingExpenses = this.expenses.filter(this.shoppingFilter)
        this.shoppingSum = this.calculate(this.shoppingExpenses)
        this.transportationExpenses = this.expenses.filter(this.transportationFilter)
        this.transportationSum = this.calculate(this.transportationExpenses)
        this.housingExpenses = this.expenses.filter(this.housingFilter)
        this.housingSum = this.calculate(this.housingExpenses)
        this.leisureExpenses = this.expenses.filter(this.leisureFilter)
        this.leisureSum = this.calculate(this.leisureExpenses)
        this.personalExpenses = this.expenses.filter(this.personalFilter)
        this.personalSum = this.calculate(this.personalExpenses)
        this.healthExpenses = this.expenses.filter(this.healthFilter)
        this.healthSum = this.calculate(this.healthExpenses)
        this.feedingExpenses = this.expenses.filter(this.feedingFilter)
        this.feedingSum = this.calculate(this.feedingExpenses)
      })
      this.loading = false
    },
    shoppingFilter (value) {
      return value.attributes.transactionType === 'shopping'
    },
    transportationFilter (value) {
      return value.attributes.transactionType === 'transportation'
    },
    housingFilter (value) {
      return value.attributes.transactionType === 'housing'
    },
    leisureFilter (value) {
      return value.attributes.transactionType === 'leisure'
    },
    personalFilter (value) {
      return value.attributes.transactionType === 'personal'
    },
    healthFilter (value) {
      return value.attributes.transactionType === 'health'
    },
    feedingFilter (value) {
      return value.attributes.transactionType === 'feeding'
    },
    calculate (obj) {
      if (obj[0].attributes.transactionType === 'shopping') {
        this.shoppingSum = this.expenseMapper(obj)
        this.sumArr.push({ 
          value: this.expenseMapper(obj),
          type: 'shopping',
          percentage: ((this.shoppingSum / this.expensesSum) * 100).toFixed(1)
        })
        //this.sumArr['shopping'] = this.expenseMapper(obj)
      }
      if (obj[0].attributes.transactionType === 'transportation') {
        this.transportationSum = this.expenseMapper(obj)
        this.sumArr.push({ 
          value: this.expenseMapper(obj),
          type: 'transportation',
          percentage: ((this.transportationSum / this.expensesSum) * 100).toFixed(1)
        })
        //this.sumArr['transportation'] = this.expenseMapper(obj)
      }
      if (obj[0].attributes.transactionType === 'housing') {
        this.housingSum = this.expenseMapper(obj)
        this.sumArr.push({ 
          value: this.expenseMapper(obj),
          type: 'housing',
          percentage: ((this.housingSum / this.expensesSum) * 100).toFixed(1)
        })
        //this.sumArr['housing'] = this.expenseMapper(obj)
      }
      if (obj[0].attributes.transactionType === 'leisure') {
        this.leisureSum = this.expenseMapper(obj)
        this.sumArr.push({ 
          value: this.expenseMapper(obj),
          type: 'leisure',
          percentage: ((this.leisureSum / this.expensesSum) * 100).toFixed(1)
        })
        //this.sumArr['leisure'] = this.expenseMapper(obj)
      }
      if (obj[0].attributes.transactionType === 'health') {
        this.healthSum = this.expenseMapper(obj)
        this.sumArr.push({ 
          value: this.expenseMapper(obj),
          type: 'health',
          percentage: ((this.healthSum / this.expensesSum) * 100).toFixed(1)
        })
        //this.sumArr['health'] = this.expenseMapper(obj)
      }
      if (obj[0].attributes.transactionType === 'feeding') {
        this.feedingSum = this.expenseMapper(obj)
        this.sumArr.push({ 
          value: this.expenseMapper(obj),
          type: 'feeding',
          percentage: ((this.feedingSum / this.expensesSum) * 100).toFixed(1)
        })
        //this.sumArr['feeding'] = this.expenseMapper(obj)
      }
      if (obj[0].attributes.transactionType === 'personal') {
        this.personalSum = this.expenseMapper(obj)
        //this.sumArr['personal'] = this.expenseMapper(obj)
        this.sumArr.push({ 
          value: this.expenseMapper(obj),
          type: 'personal',
          percentage: ((this.personalSum / this.expensesSum) * 100).toFixed(1)
        })
      }
      this.sumArr.sort(this.sortBy('value', true, parseInt))
      console.log(this.sumArr)
    },
    expenseMapper (obj) {
      let objDecompiler = obj.map(
        function (item) {
          return item.attributes.value
        }
      )
      var arraySumarizer = 0
      for (let i = 0; i < objDecompiler.length; i++) {
        arraySumarizer += objDecompiler[i]
      }
      return arraySumarizer
    },
    sortBy (field, reverse, primer) {
      const key = primer ?
      function(x) {
        return primer(x[field])
      } :
      function(x) {
        return x[field]
      }
      reverse = !reverse ? 1 : -1;
      return function(a, b) {
        return a = key(a), b = key(b), reverse * ((a > b) - (b > a))
      }
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
    await this.getExpensesAndFilter()
  },
  mounted () {
  }
}
</script>

<style scoped>
ul, li {
  list-style-type: none;
}
.container {
  background-color: #F7F5F3;
}
.container__header {
  background-color: #FCE180;;
}
.container__title {
  width: 7rem;
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
  display: flex;
  padding-top: 2rem;
  padding-bottom: 3.5rem;
  bottom: 0;
  width: 100%;
  margin: 4rem 0 0 0;
  height: 1rem;
  background: linear-gradient(-60deg, #f2c4c4 50%, #e66c55 50%);
}
.total--left {
  padding: 0 0 0 1rem;
  display: flex;
}
.total--right {
  padding: 0 0 0 7rem;
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
  font-size: 2rem;
  text-shadow: 0.2rem 0.2rem 0 #1e5d84;
}
.total__label {
  font-size: 1.75rem;
  color: #1e5d84;
  text-shadow: 0.2rem 0.2rem 0 #fff;
}
.board {
  display: flex;
  flex-wrap: wrap;
  margin-top: 1.5rem;
  margin-left: .5rem;
  margin-right: .5rem;
  justify-content: center;
}
.board__body {
  display: flex;
  font-family: cooper-hewitt-medium;
  color: #fff;
  margin: .25rem;
  padding: .5rem;
  background-color: #1e5d84;
  align-items: center;
}
.body__items {
  margin-left: .5rem;
  display: flex;
  flex-direction: column;
}
</style>