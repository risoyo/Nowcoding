<template>
  <div class="home">
    <el-container>
      <el-header>Header</el-header>
      <el-main>
        <register-main></register-main>
      </el-main>
      <el-footer>Footer</el-footer>
    </el-container>
  </div>
</template>

<script>
// @ is an alias to /src
import RegisterMain from "@/components/Register/RegisterMain.vue";

export default {
  name: "register",
  components: {
    RegisterMain
  }
};
</script>
