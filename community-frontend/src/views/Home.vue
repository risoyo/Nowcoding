<template>
  <div class="home">
    <el-container>
      <el-header>Header</el-header>
      <el-main>
        <home-main></home-main>
      </el-main>
      <el-footer>Footer</el-footer>
    </el-container>
  </div>
</template>

<script>
// @ is an alias to /src
import HomeMain from "@/components/Home/HomeMain.vue";

export default {
  name: "home",
  components: {
    HomeMain
  }
};
</script>
