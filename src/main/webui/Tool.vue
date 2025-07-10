<template>
    <div class="wrapper">
        <input class="radio" id="one" name="group" type="radio" checked/>
        <input class="radio" id="two" name="group" type="radio"/>
        <input class="radio" id="three" name="group" type="radio"/>
        <input class="radio" id="four" name="group" type="radio"/>
        <input class="radio" id="five" name="group" type="radio"/>
        <input class="radio" id="six" name="group" type="radio"/>
        <input class="radio" id="seven" name="group" type="radio"/>
        <input class="radio" id="eight" name="group" type="radio"/>
        <div class="tabs">
            <label class="tab" id="one-tab" for="one">AI</label>
        </div>
        <div class="panels">
            <div class="panel" id="one-panel">
                <AI/>
            </div>
        </div>
    </div>
</template>

<script>
    import AI from '/AI.vue';
    import WS from '/ws';
    export default {
        name: 'Tool',
        components: {
            AI
        }
    }
</script>
<style>
    #tool {
        font-family: Avenir, Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
    }
    button {
        background-color:#ffffff;
        color: black;
        border: 0.5px solid white;
        text-align: left;
        border-radius: 3px;
        font-size: smaller;
    }
    button:hover:enabled {
        border: 0.5px solid black;
    }
    .button-disabled {
        opacity: 0.5;
    }
    .above {
        background-color: #fff;
        display:flex;
        justify-content: center;
        margin-bottom: 20px;
    }
    .above button {
        margin-left: 20px;
        margin-right: 20px;
    }
</style>
<style scoped>
    #tool .wrapper {
        display:flex;
        flex-direction: column;
        align-items: center;
    }
    #tool .tab {
        cursor: pointer;
        padding:10px 20px;
        margin:0px 2px;
        background:darkgray;
        display:inline-block;
        color: black;
        border-radius:3px 3px 0px 0px;
        box-shadow: 0 0.5rem 0.8rem #00000080;
    }
    #tool .panels {
        height: 100%;
        width: 100%;
        background:#fff;
        border-radius:3px;
        overflow:hidden;
        padding:20px;
    }
    #tool .panel {
        height: 100%;
        display:none;
        animation: fadein .8s;
    }
    @keyframes fadein {
        from {
            opacity:0;
        }
        to {
            opacity:1;
        }
    }
    #tool .panel-title {
        font-size:1.5em;
        font-weight:bold;
        margin-bottom:20px;
    }
    #tool .radio {
        display:none;
    }
    #one:checked ~ .panels #one-panel,
    #two:checked ~ .panels #two-panel {
        text-align: center;
        display:block
    }
    #one:checked ~ .tabs #one-tab,
    #two:checked ~ .tabs #two-tab {
        background:#ffffffff;
        color:#000;
        border-top: 3px solid darkgray;
    }
</style>
