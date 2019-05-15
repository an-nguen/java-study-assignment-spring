<template>
    <v-card class="my-2">
        <v-card-title><i>Message #{{ message.id }}</i></v-card-title>
        <v-card-text >
            <user-link :user="message.author"
                        size="48"></user-link>
            <div class="pt-3">
                {{ message.text }}
            </div>
        </v-card-text>
        <media v-if="message.link" :message="message"></media>
        <v-card-actions>
            <v-btn small flat icon @click="edit">
                <v-icon>edit</v-icon>
            </v-btn>
            <v-btn small flat icon @click="del">
                <v-icon>delete</v-icon>
            </v-btn>
        </v-card-actions>
        <comment-list
                :comments="message.comments"
                :message-id="message.id"
        >
        </comment-list>
    </v-card>
</template>

<script>
    import { mapActions } from 'vuex'
    import Media from 'components/media/Media.vue'
    import CommentList from "components/comment/CommentList.vue";
    import UserLink from "components/UserLink.vue";

    export default {
        name: "MessageRow",
        props: ['message', 'editMethod'],

        components: {
            UserLink,
            Media,
            CommentList
        },
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