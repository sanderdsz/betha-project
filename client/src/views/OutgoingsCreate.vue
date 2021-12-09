<template>
  <div class='container'>
    <form v-on:submit.prevent>
      <div class='container__header'>
        <h2 class='container__title'>
          Register Income
        </h2>
      </div>
      <div class='container__body'>
        <div class='form--value'>
          <label class='form__label' >Income amount</label>
          <input class='form__input--value' placeholder="Value" type="number" v-model='value'>
        </div>
        <div class='container__body--secondary'>
          <div class='form'>
            <label class='form__label'>Transaction date</label>
          </div>
          <div class='date__wrapper'>
            <DatePicker mode="date" v-model="date" @input='getDate'/>
          </div>
        </div>
        <div class='background-color--gray'>
          <div class='form'>
            <label class='form__label'>Transaction type</label>
            <select class='form__input' v-model='incomeType'>
              <option disabled value="">Choose a type</option>
              <option value="salary">Salary</option>
              <option value="transaction">Transaction</option>
            </select>
          </div>
        </div>
      </div>
      <div class='container__footer'>
        <div class='container__footer--right'>
          <button class='button button--red' v-on:click='update'>Submit</button>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
// import { DatePicker } from 'v-calendar'
import axios from 'axios' 

export default {
  name: 'IncomeCreate',
  // components: { DatePicker },
  data() {
    return {
      value: '',
      incomeType: '',
      date: new Date()
    }
  },
  methods: {
    async update() {
      await axios.post("http://152.70.211.106:8080/api/outgoings", 
        { data: { value: this.value, incomeType: this.incomeType, date: this.date }}
      )
    }
  }
}
</script>

<style scoped>

</style>