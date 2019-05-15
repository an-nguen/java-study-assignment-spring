import Vue from 'vue'
import Vuex from 'vuex'
import messageApi from "api/messages.js"
import commentApi from "api/comment.js"

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        messages,
        profile,
        ...frontendData
    },
    getters: {
        sortedMessages: state => (state.messages || []).sort((a, b) => -(a.id - b.id))
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
        addCommentMutation(state, comment) {
            const updateIndex = state.messages.findIndex(item => item.id === comment.message.id);
            const message = state.messages[updateIndex];

            if (!message.comments.find(it => it.id === comment.id) || (message.comments !== null)) {
                Vue.set(state.messages, updateIndex, {
                    ...message,
                    comments: [
                        ...message.comments,
                        comment
                    ]
                })
            }
        },
        addMsgPageMutation(state, messages) {
            const targetMessages = state.messages
                .concat(messages)
                .reduce((res, val) => {
                    res[val.id] = val;
                    return res
                }, {});

            state.messages = Object.values(targetMessages)
        },
        updTotalPagesMutation(state, totalPages) {
            state.totalPages = totalPages;
        },
        updCurrentPageMutation(state, currentPage) {
            state.currentPage = currentPage;
        }
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
        },
        async addCommentAction({commit, state}, comment) {
            const response = await commentApi.add(comment);
            const data = await response.json();
            commit('addCommentMutation', data);
        },
        async loadPageAction({commit, state}) {
            const response = await messageApi.page(state.currentPage + 1);
            const data = await response.json();
            commit('addMsgPageMutation', data.messages);
            commit('updTotalPagesMutation', data.totalPages);
            commit('updCurrentPageMutation', Math.min(data.currentPage, data.totalPages - 1))
        },
    }
});