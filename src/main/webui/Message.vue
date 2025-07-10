<template>
    <div :class='["message", { dark }]'>
        {{ text }}
    </div>
</template>

<script>
    export default {
        name: 'Message',
        props: [
            'text', // Content of the message
            'dark' // Background variant of the box
        ]
    }
</script>

<style scoped>
    .message {
        background: #d3d3d3;
        border-radius: 10px;
        padding: 1rem;
        width: fit-content;
    }

    .message.dark {
        background: #e9eaf6;
    }

    h5 {
        margin: 0 0 .5rem 0;
    }
</style>
