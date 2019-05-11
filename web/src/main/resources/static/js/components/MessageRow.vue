<template>
    <v-card class="my-2">
        <v-card-text primary-title>
            <b>{{ message.id }}</b> {{ message.text }}
        </v-card-text>

        <v-card-actions>
            <v-btn small flat icon @click="edit">
                <v-icon>edit</v-icon>
            </v-btn>
            <v-btn small flat icon @click="del">
                <v-icon>delete</v-icon>
            </v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
    import { mapActions } from 'vuex'

    export default {
        name: "MessageRow",
        props: ['message', 'editMethod'],
        methods: {
            ...mapActions(['remMsgAction']),
            edit() {
                this.editMethod(this.message);
            },
            del() {
                this.remMsgAction(this.message);
            }
        }
    }
</script>

<style scoped>

</style>