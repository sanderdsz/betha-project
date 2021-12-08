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
            R$ {{ incomesSum }}
          </div>
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
      loading: false
    }
  },
  methods: {
    async getIncomes () {
      this.loading = true
      const request = await axios.get("http://152.70.211.106:8080/api/incomes")
      .then(response => this.incomesResponse = response.data.data)
      this.incomesRefactor = this.incomesResponse.map(
        function(item) {
          return item.attributes.value
        }
      )
      for (let i = 0; i < this.incomesRefactor.length; i++) {
        this.incomesSum += this.incomesRefactor[i]
      }
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
    this.getIncomes()
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
  margin: 2rem 0 0 0;
  text-align: center;
}
</style>