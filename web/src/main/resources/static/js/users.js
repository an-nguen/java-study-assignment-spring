
var userApi = Vue.resource('/users{/id}');

Vue.component('user-register-form', {
    props: ['users'],
    data: function () {
        return {
            username: '',
            password: '',
            email: ''
        }
    },
    template: '<div>' +
                '<label for="username">Username</label>' +
                '<input type="text" name="username" id="username" v-model="username"/>' +
               '<label for="password">Password</label>' +
               '<input type="password" name="password" id="password" v-model="password"/>' +
               '<label for="email">Email</label>' +
               '<input type="email" name="email" id="email" v-model="email"/>' +
                '<input type="button" value="Register" v-on:click="save" >' +
             '</div>',
    methods: {
        save: function() {
            var user = {
                username : this.username,
                password : this.password,
                email: this.email
            };

            userApi.save({}, user).then(result => result.json().then(
                data => {
                    this.users.push(data);
                    this.username = '';
                    this.password = '';
                    this.email = '';
                }
            ));
        }
    }
});

Vue.component('user-row', {
    props: ['user'],
    template: '<div><i>{{ user.id }}</i> {{ user.username }}</div>'
});

Vue.component('users-list', {
    props: ['users'],
    template: '<div>' +
        '<user-register-form :users="users" />' +
        '<user-row v-for="user in users" :key="user.id" :user="user" />' +
        '</div>',
    created: function () {
        userApi.get().then(result =>
            result.json().then(data =>
                data.forEach(user => this.users.push(user))
            )
        )
    }
});

var app = new Vue({
    el: '#app-users',
    template: '<users-list :users="users" />',
    data: {
        users: []
    }
});