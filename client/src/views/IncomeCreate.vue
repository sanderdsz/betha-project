<template>
  <div class='container'>
    <DatePicker v-model="date" @input='getDate'/>
    <input>
  </div>
</template>

<script>
import { DatePicker } from 'v-calendar'

export default {
  name: 'IncomeCreate',
  components: { DatePicker },
  data() {
    return {
      date: new Date()
    }
  },
  methods: {
    getDate() {
      console.log(this.date)
    }  
  }
}
</script>

<style scoped>
.container {
  display: flex;
  padding: 1rem;
}
</style>