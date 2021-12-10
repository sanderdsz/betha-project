<template>
  <div class='container'>
    <form v-on:submit.prevent>
      <div class='container__header'>
        <h2 class='container__title'>
          Register Expense
        </h2>
      </div>
      <div class='container__body'>
        <div class='form--value'>
          <label class='form__label' >Expense amount</label>
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
        <div class='background-color--brown'>
          <div class='form'>
            <label class='form__label'>Transaction type</label>
            <select class='form__input' v-model='transactionType'>
              <option disabled value="">Choose a type</option>
              <option value="feeding">Food</option>
              <option value="housing">Housing</option>
              <option value="leisure">Leisure</option>
              <option value="transportation">Transportation</option>
              <option value="health">Health</option>
              <option value="personal">Personal</option>
              <option value="shopping">Shopping</option>
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
  name: 'OutgoingsCreate',
  // components: { DatePicker },
  data() {
    return {
      value: '',
      transactionType: '',
      date: new Date()
    }
  },
  methods: {
    async update() {
      await axios.post("http://152.70.211.106:8080/api/outgoings", 
        { data: { value: this.value, transactionType: this.transactionType, date: this.date }}
      )
    }
  }
}
</script>

<style scoped>

</style>