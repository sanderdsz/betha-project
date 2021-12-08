import { createApp } from 'vue'
import App from './App.vue'
import { router } from './router'
import { library } from '@fortawesome/fontawesome-svg-core'
import { 
  faWallet, 
  faHandHoldingUsd, 
  faHome, 
  faMoneyBillWave, 
  faHouseUser ,
  faLaughBeam,
  faUtensils,
  faCar,
  faBook,
  faHeartbeat,
  faUserLock,
  faShoppingCart
} from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { SetupCalendar, Calendar, DatePicker } from 'v-calendar';
import './reset.css'
import './assets/styles.css'

library.add(
  faHome, 
  faWallet, 
  faHandHoldingUsd, 
  faMoneyBillWave,
  faHouseUser,
  faLaughBeam,
  faUtensils,
  faCar,
  faBook,
  faHeartbeat,
  faUserLock,
  faShoppingCart
);

createApp(App)
  .use(router)
  .use(SetupCalendar)
  .component("font-awesome-icon", FontAwesomeIcon)
  .component('Calendar', Calendar)
  .component('DatePicker', DatePicker)
  .mount('#app')
