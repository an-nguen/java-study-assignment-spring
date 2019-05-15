<template>
    <v-container>
        <v-layout align-space-around justify-start column>
            <message-form :messageAttr="message" />
            <message-row v-for="message in sortedMessages"
                         :key="message.id"
                         :message="message"
                         :editMethod="editMethod" />
            <lazy-loader></lazy-loader>
        </v-layout>
    </v-container>
</template>

<script>
    import { mapGetters } from 'vuex';
    import MessageRow from 'components/message/MessageRow.vue'
    import MessageForm from 'components/message/MessageForm.vue'
    import LazyLoader from "components/LazyLoader.vue";

    export default {
        components: {
            LazyLoader,
            MessageRow,
            MessageForm
        },
        name: "MessageList",
        data() {
            return {
                message: null
            }
        },
        computed: mapGetters(['sortedMessages']),
        methods: {
            editMethod(message) {
                this.message = message
            }
        }
    }
</script>

<style scoped>

</style>