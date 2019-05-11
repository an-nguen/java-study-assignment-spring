<template>
    <v-app>
        <v-toolbar app>
            <v-toolbar-title>
                Learning: Spring Boot REST
            </v-toolbar-title>

            <v-spacer></v-spacer>
            <v-item-group>
                <v-item>
                    <div class="authenticated" v-if="profile">
                        <span id="user">{{profile.name}}</span>
                        <v-btn icon href="/logout">
                            <v-icon>exit_to_app</v-icon>
                        </v-btn>
                    </div>
                </v-item>
            </v-item-group>
        </v-toolbar>
        <v-content>
            <v-container class="unauthenticated" v-if="!profile">
                Необходимо авторизоваться <a href="/login">Google</a>
            </v-container>
            <v-container v-else>
                <message-list />
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import { mapState, mapMutations } from 'vuex'
    import MessageList from 'components/MessageList.vue'
    import { addHandler } from "util/ws";

    export default {
        components: {
            MessageList
        },
        computed: mapState(['profile', 'messages']),
        mutations: mapMutations(['addMsgMutation', 'updMsgMutation', 'remMsgMutation']),
        created() {
            addHandler(data => {
                if (data.objectType === 'Message') {
                    let index = this.messages.findIndex(item => item.id === data.body.id);

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
                } else {
                    console.error(`Object type is unknown "${data.objectType}"`)
                }
            })
        }
    }
</script>

<style>
    .main-app {

    }
</style>