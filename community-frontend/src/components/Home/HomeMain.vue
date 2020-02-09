<template>
  <div class="hello">
      <el-tabs v-model="activeName" @tab-click="handleClick">
    <el-tab-pane label="最新" name="first">
          <h3>当前页码 is {{totalPageNumber}}</h3>

    <!-- 使用v-for读取infos中的数据 -->

    <div v-for="(info, i) in infos" :key="i">
      <div v-if="info.totalPageNumber"></div>
      <div v-else>
        <div>
          <p>{{ info.content }}</p>
          <p>
            <span>{{ info.user_name }}</span>
            <el-divider direction="vertical"></el-divider>
            <span style="padding-left:250px">发布于：{{info.create_time}}</span>
            <el-divider direction="vertical"></el-divider>
            <span>回帖：{{info.comment_count}}</span>
          </p>
        </div>
        <el-divider></el-divider>
      </div>
    </div>

    <div style="text-align: center;">
      <el-pagination
        @current-change="handleCurrentChange"
        layout="prev, pager, next"
        :size="maxRowsPerPage"
        :total="totalPageNumber"
        :current-page="currentPageNumber"
      ></el-pagination>
    </div>
    </el-tab-pane>

    
    <el-tab-pane label="最热" name="second">最热</el-tab-pane>
  </el-tabs>



  </div>
</template>

<script>
export default {
  data() {
    return {
      // 声明使用的数组
      currentPageNumber: 1,
      totalPageNumber: 0,
      maxRowsPerPage: 10,
      activeName: 'first',
      infos: []
    };
  },
  computed: {
    // 页面总数的计算属性，获取info中第一个节点的totalPageNumber数据
    getPageNumber() {
      var pageNumber = this.infos[0].totalPageNumber * 10;
      return pageNumber
    }

  },
  methods: {
    handleUrlChange(PageNumber) {
      let url = '/community/getIndexPost?currentPageNumber=' + PageNumber + '&maxRowsPerPage' + this.maxRowsPerPage;
      console.log(`url to: ${url}`);
      this.$axios
        // 不添加http等前缀，前缀由vue.config.js来补充，实现请求转发
        .get(url)
        .then(resp => {
          if (resp) {
            // 将resp中的data数据存入infos
            this.infos = resp.data;
            // totalPageNumber数据由后台传过来，页面上直接获取的话会报错，故使用计算属性来完成
            this.totalPageNumber = this.getPageNumber;

          }
        });
    },
    handleClick(tab, event) {
        console.log(tab, event);
      },
    handleCurrentChange(val) {
      this.currentPageNumber = val;
      console.log(`当前页: ${val}`);
      this.handleUrlChange(val);
    }
  },
  mounted() {
    this.handleUrlChange(1);
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
