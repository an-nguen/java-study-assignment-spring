
import Vue from 'vue'
import Vuetify from 'vuetify'
import '@babel/polyfill'
import 'api/resource'
import router from 'router/router'
import 'vuetify/dist/vuetify.min.css'
import App from 'pages/App.vue'
import store from 'store/store.js'
import { connect } from "./util/ws";

Vue.use(Vuetify);

if (frontendData.profile) {
    connect();
}

new Vue({
    el: '#app',
    store,
    router,
    render: a => a(App)
});
