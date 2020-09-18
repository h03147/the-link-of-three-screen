<template>
  <div class="hello">
    <Index1></Index1>
  </div>
</template>

<script>
  import Index1 from '@/views/homepage/index1.vue'
export default {
  name: 'HelloWorld',
  components: {
    Index1
  },
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .hello {
    height: 48.80vw;
    width: 100vw;
  }

</style>
