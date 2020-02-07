<template>
  <div id="color" class="hello">
    <button v-if="status !== 0" v-on:click="getRed()">Get Red</button>
    <p v-if="status === -1">Click above button!</p>
    <p v-if="status === 0">Loading...</p>
    <p v-if="status === 1" class="red">Get Red Failed, server address is not correct!</p>
    <p v-if="status === 2" class="green">Get Red Success!!! This is a success message, it should be green ^^!</p>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'HelloWorld',
  data: function() {
    return {
      status: -1,
    }
  },
  methods: {
    getRed: function() {
      let serverAddress = "34.87.11.99"
      let serverPort = "8080"
      let apiPath = "checkRed"

      this.status = 0;

      axios({
        url: `http://${serverAddress}:${serverPort}/${apiPath}`,
        method: 'get',
        timeout: 5000
      })
        .then(() => {
          this.status = 2
        })
        .catch(() => {
          this.status = 1
        })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.red {
  color: red
}
.green {
  color: green
}
</style>
