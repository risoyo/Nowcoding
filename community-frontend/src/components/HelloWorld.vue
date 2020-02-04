<template>
  <div class="hello">
    <h3>my data is</h3>
    <div>
      <!-- 使用v-for读取infos中的数据 -->
      <p v-for="(info,i) in infos" :key="i">
        {{info.content}}
        {{info.user_name}}
      </p>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 声明使用的数组
      infos: [],
    };
  },
  mounted() {
    this.$axios
    // 不添加http等前缀，前缀由vue.config.js来补充，实现请求转发
      .get("/community/getIndexPost")
      .then(resp => {
                    if (resp) {
                      // 将resp中的data数据存入infos
                        this.infos = resp.data;
                    }
                });
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
