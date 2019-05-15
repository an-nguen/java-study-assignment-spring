<template>
    <v-app>
        <v-toolbar app>
            <v-toolbar-title>
                Learning: Spring Boot REST
            </v-toolbar-title>

            <v-btn @click="showMessages"
                   flat
                   v-if="profile"
                   :disabled="$route.path === '/'">
                Messages
            </v-btn>

            <v-spacer></v-spacer>

            <v-toolbar-items v-if="profile" class="hidden-sm-and-down">
                <v-btn
                       flat
                       @click="showProfile"
                       :disabled="$route.path === '/user'">
                    {{profile.name}}
                </v-btn>

                <v-btn
                       icon
                       href="/logout">
                    <v-icon>exit_to_app</v-icon>
                </v-btn>
            </v-toolbar-items>

            <v-menu v-if="profile" class="hidden-md-and-up">
                <v-toolbar-side-icon slot="activator"></v-toolbar-side-icon>
                <v-list>
                    <v-btn
                            flat
                            @click="showProfile"
                            :disabled="$route.path === '/profile'">
                        {{profile.name}}
                    </v-btn>

                    <v-btn
                            icon
                            href="/logout">
                        <v-icon>exit_to_app</v-icon>
                    </v-btn>
                </v-list>
            </v-menu>
        </v-toolbar>
        <v-content>
            <router-view></router-view>
        </v-content>
    </v-app>
</template>

<script>
    import { mapState, mapMutations } from 'vuex'
    import { addHandler } from "util/ws";

    export default {
        computed: mapState(['profile', 'messages']),
        methods : {
            ...mapMutations([
                'addMsgMutation',
                'updMsgMutation',
                'remMsgMutation',
                'addCommentMutation'
            ]),
            showMessages() {
                this.$router.push('/')
            },
            showProfile() {
                this.$router.push('/user')
            }
        },
        created() {
            addHandler(data => {
                if (data.objectType === 'MESSAGE') {
                    switch (data.eventType) {
                        case 'CREATE':
                            this.addMsgMutation(data.body);
                            break;
                        case 'UPDATE':
                            this.updMsgMutation(data.body);
                            break;
                        case 'REMOVE':
                            this.remMsgMutation(data.body);
                            break;
                        default:
                            console.error(`Event type is unknown "${data.eventType}"`)
                    }
                } else if (data.objectType === 'COMMENT') {
                    switch (data.eventType) {
                        case 'CREATE':
                            this.addCommentMutation(data.body);
                            break;
                        default:
                            console.error(`Event type is unknown "${data.eventType}"`)
                    }
                } else {
                    console.error(`Object type is unknown "${data.objectType}"`)
                }
            })
        },
        beforeMount() {
            if (!this.profile) {
                this.$router.replace('/auth')
            }
        }
    }
</script>

<style>
    .main-app {

    }
</style>