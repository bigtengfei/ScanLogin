<template>
  <div class="login-wrap">
    <div class="login-dialog">
      <p class="title">扫码登录</p>
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="扫码登录" name="first">
          <el-image
            style="width: 200px; height: 200px;border-radius: 10px;"
            :src="imgUrl1"
            :fit="fit"
          ></el-image>
        </el-tab-pane>
        <el-tab-pane label="扫码确认登录" name="second">
          <el-image style="width: 200px; height: 200px;" :src="imgUrl2" :fit="fit"></el-image>
          <div id="confirm_shade"></div>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
export default {
  name: "login",
  data() {
    return {
      activeName: "first",
      imgUrl1: require("@/assets/qr_code_1.png"),
      imgUrl2: require("@/assets/qr_code_2.png")
    };
  }
};
</script>

<style scoped>
.login-wrap {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  background-image: url("../../assets/login-bg.png");
  background-size: cover;
}
.login-dialog {
  width: 380px;
  height: 380px;
  padding: 0 20px;
  position: absolute;
  left: 50%;
  top: 35%;
  text-align: center;
  transform: translate(-50%, -50%);
  background-color: rgba(255, 255, 255, 0.9);
  border-radius: 5px;
}
p.title {
  font-size: 20px;
  color: #676767;
  font-weight: 700;
}

::v-deep .el-tabs__nav-scroll {
  width: 50%;
  margin: 0 auto;
}

#confirm_shade {
  position: relative;
  display: inline-block;
  overflow: hidden;
  width: 200px;
  height: 200px;
  border-radius: 10px;
  background: #fff;
}
</style>