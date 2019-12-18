import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'

import UserForm from './components/UserForm.vue'
import UserList from './components/UserList.vue'

Vue.config.productionTip = false
Vue.use(VueRouter)

const routes = [
  { path: '/list', component: UserList },
  { path: '/new', component: UserForm }
]

const router = new VueRouter({
  routes // `routes: routes` の短縮表記
})

new Vue({
  router,
  render: (h) => h(App)
}).$mount('#app')
