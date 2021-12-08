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
    consoleWrite () {
      console.log(this.data)
    },
    async update() {
      await axios.post("http://152.70.211.106:8080/api/incomes", 
        { data: { value: this.value, incomeType: this.incomeType, date: this.date }}
      )
    },
    getDate() {
      console.log(this.date)
    }  
  }
}
</script>

<style scoped>
.container {
  z-index: -1;
}
.container__header {
  padding: 1rem;
}
.container__title {
  width: 15rem;
  color: #1e5d84;
  background-image: url('../assets/images/blue-underline.svg');
  background-repeat: no-repeat;
  background-position: left bottom;
  background-size: 80% 0.3rem;
  padding-bottom: 0.2rem;
}
.container__body--secondary {
  background-color: #fbf8e9;
}
.container__footer {
  padding-bottom: 5rem;
  background-color: #f0f0f0;
}
.container__footer--right {
  padding: 1rem;
  text-align: right;
}
.container__wrapper {
  padding: 1rem;
  text-align: left;
}
.container__wrapper--date {
  text-align: center;
  background-color: #fbf8e9;
}
.form {
  padding: 1rem;
}
.form__label {
  font-family: cooper-hewitt-medium;
  font-size: .75rem;
  color: #1e5d84;
  display: block;
}
.form__input {
  box-sizing: border-box;
  width: 100%;
  font-size: 1rem;
  padding: .5rem .5rem .5rem .5rem;
  background-color: #fff;
  color: #1e5d84;
  border: 0.175rem solid #94b8d1;
  border-radius: 0.2rem;
  transition: border-color .25s ease, background-color .25s ease;
}
.form__input::placeholder {
  color: #94b8d1;
}
.form--value {
  padding: 1rem;
}
.form__input::placeholder {
  color: #94b8d1;
}
.form--value:before {
  font-family: cooper-hewitt-medium;
  content: 'R$';
  color: #E66C55;
  font-size: 30px;
  position: absolute;
  top: 108px;
  left: 25px;
  z-index: 1;
}
.form__input--value {
  box-sizing: border-box;
  width: 100%;
  font-size: 1.5rem;
  padding: .5rem .5rem .5rem 3rem;
  background-color: #fff;
  color: #1e5d84;
  border: 0.175rem solid #94b8d1;
  border-radius: 0.2rem;
  transition: border-color .25s ease, background-color .25s ease;
}
.form__input--value::placeholder {
  color: #94b8d1;
}
.date__wrapper {
  padding: 0 0 1rem 0;
  text-align: center;
}
.button {
  padding: .5rem 1rem .5rem 1rem;
  font-family: gt-pressura-mono-regular;
  text-transform: uppercase;
  letter-spacing: .10rem;
  border: 0.1rem solid transparent;
  transition: background-color .2s ease-in-out;
  line-height: 1.5;
  border-radius: 0.2rem;
  box-shadow: 0 .1rem .25rem 0 rgb(0 0 0 / 30%);
}
.button--red {
  background-color: #e66c55;
  color: #fff;
}
.background-color--gray {
  background-color: #f0f0f0;
}
</style>