<template>
    <v-layout align-start justify-start row>
        <v-text-field v-model="text"
                      placeholder="Your message"
        ></v-text-field>
        <v-btn color="blue" @click="save">Send</v-btn>
    </v-layout>
</template>

<script>
    import { mapActions } from 'vuex'

    export default {
        name: "MessageForm",
        props: ['messageAttr'],
        data() {
            return {
                id: '',
                text: ''
            }
        },
        watch: {
            messageAttr: function (newVal) {
                this.text = newVal.text;
                this.id = newVal.id;
            }
        },
        methods: {
            ...mapActions(['addMsgAction', 'updMsgAction']),
            save() {
                const message = {
                    id: this.id,
                    text: this.text
                };

                if (this.id) {
                    this.updMsgAction(message);
                } else {
                    this.addMsgAction(message);
                }
                this.text = '';
                this.id = ''
            }
        }
    }
</script>

<style scoped>

</style>