<template>
    <div class='app'>
        <div class='chat-box'>
            <input v-model='input' placeholder='Enter text to chat with the AI' type='text' @keyup='handleEnterKey'/>
            <button type="button" @click="send" :disabled="nothing_to_send" :class="{ 'button-disabled': nothing_to_send }">
                Send
            </button>
        </div>
        <div ref='messages' class='messages'>
            <Message v-for='message in messages'
                     :class='["message", { right: message.mine }]'
                     :dark='message.mine'
                     :text='message.response'/>
        </div>
    </div>
</template>
<script>
    import WS from '/ws';
    import Message from './Message.vue';
    export default {
        components: {
            Message
        },
        data() {
            return {
                input: '',
                messages: []
            };
        },
        mounted() {
            const self = this;
            WS.addEventListener('message', function (event) {
                const msg = JSON.parse(event.data);
                if ('ai_chat' === msg.type) {
                    self.messages = self.messages.concat(msg.ai_chat_output);
                }
            });
        },
        methods: {
            send() {
                let message = {
                    "response": this.input,
                    "mine": true
                };
                this.messages.push(message);
                WS.send(JSON.stringify({"type": "ai_chat", "input": this.input}));
                this.input = '';
            },
            handleEnterKey(event) {
                if (event.keyCode === 13) {
                    this.send();
                }
            }
        },
        computed: {
            nothing_to_send() {
                return (!this.input || this.input.length === 0);
            }
        }
    }
</script>
<style scoped>
    .main {
        background-color: #fff;
    }
    .app {
        height: 100vh;
        display: flex;
        flex-direction: column;
    }
    .chat-box {
        width: 100%;
        display: flex;
        background: #e3e3e3;
    }
    input {
        width: min(100%, 20rem);
        flex-grow: 1;
    }
    .messages {
        flex-grow: 1;
        overflow: auto;
        padding: 1rem;
    }
    .message + .message {
        margin-top: 1rem;
    }
    .message.right {
        margin-left: auto;
    }
</style>