<template>
    <v-layout align-space-around justify-start column>

    <message-form :messageAttr="message" />
        <message-row v-for="message in sortedMessages"
                     :key="message.id"
                     :message="message"
                     :editMethod="editMethod" />
    </v-layout>
</template>

<script>
    import { mapGetters } from 'vuex';
    import MessageRow from 'components/MessageRow.vue'
    import MessageForm from 'components/MessageForm.vue'

    export default {
        components: {
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