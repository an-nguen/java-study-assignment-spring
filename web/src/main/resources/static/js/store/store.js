import Vue from 'vue'
import Vuex from 'vuex'
import messageApi from "api/messages.js"

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        messages: frontendData.messages,
        profile: frontendData.profile
    },
    getters: {
        sortedMessages: state => state.messages.sort((a, b) => -(a.id - b.id))
    },
    mutations: {
        addMsgMutation(state, message) {
            state.messages.push(message)
        },
        updMsgMutation(state, message) {
            const updateIndex = state.messages.findIndex(item => item.id === message.id);

            Vue.set(state.messages, updateIndex, message)
        },
        remMsgMutation(state, message) {
            const removeIndex = state.messages.findIndex(item => item.id === message.id);

            if (removeIndex > -1) {
                state.messages.splice(removeIndex, 1)
            }
        },
    },
    actions: {
        async addMsgAction({commit, state}, message) {
            const result = await messageApi.add(message);
            const data = await result.json();
            const index = state.messages.findIndex(item => item.id === data.id);

            if(index > -1) {
                commit('updMsgMutation', data)
            } else {
                commit('addMsgMutation', data)
            }
        },
        async updMsgAction({commit}, message) {
            const result = await messageApi.update(message);
            const data = await result.json();

            commit('updMsgMutation', data);
        },
        async remMsgAction({commit}, message) {
            const result = await messageApi.remove(message.id);

            if (result.ok) {
                commit('remMsgMutation', message)
            }
        }
    }
});