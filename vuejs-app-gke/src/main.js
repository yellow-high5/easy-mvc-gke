import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'

import UserForm from './components/UserForm.vue'
import UserList from './components/UserList.vue'

import vuetify from './plugins/vuetify'
import 'material-design-icons-iconfont/dist/material-design-icons.css'

Vue.config.productionTip = false
Vue.use(VueRouter)

const routes = [
  { path: '/', component: UserList },
  { path: '/new', component: UserForm }
]

const router = new VueRouter({
  mode: 'history',
  routes
})

new Vue({
  router,
  vuetify,
  render: (h) => h(App)
}).$mount('#app')
