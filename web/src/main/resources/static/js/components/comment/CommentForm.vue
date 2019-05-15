<template>
    <v-layout align-start justify-start row class="px-3">
        <v-text-field v-model="text"
                      placeholder="Your comment"
                      @keyup.enter="save"
        ></v-text-field>
        <v-btn color="blue" @click="save">Comment</v-btn>
    </v-layout>
</template>

<script>
    import { mapActions } from 'vuex'

    export default {
        name: "CommentForm",
        props: ['messageId'],
        data() {
            return {
                text: ''
            }
        },
        methods: {
            ...mapActions(['addCommentAction']),
            async save() {
                await this.addCommentAction({
                   text: this.text,
                    message: {
                       id: this.messageId
                    }
                });
                this.text = '';
            }
        }
    }
</script>

<style scoped>

</style>