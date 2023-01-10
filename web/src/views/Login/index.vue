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
          <div class="confirm_qr_code">
            <el-image class="qr_code_2" :src="imgUrl2" :fit="fit"></el-image>
            <div class="confirm_shade">
              <el-image class="imgYes" :src="imgYes" :fit="fit"></el-image>
              <p>扫码成功<br/>请在手机上确认</p>
            </div>
          </div>
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
      imgUrl2: require("@/assets/qr_code_2.png"),
      imgYes: require("@/assets/yes.png")
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

.confirm_qr_code {
  position: relative;
  width: 200px;
  height: 200px;
  display: inline-block;
}

.qr_code_2 {
  position: absolute;
  width: 100%;
  height: 100%;
  display: block;
  border-radius: 10px;
}

.confirm_shade {
  position: absolute;
  width: 100%;
  height: 100%;
  border-radius: 10px;
  background-color: rgba(255, 255, 255, 0.9);
  z-index: 1;
}
.confirm_shade p{
  font-size: 14px;
  margin-top: 45px;
  flood-color: gray;
}

.imgYes{
  width: 40px;
  height: 40px;
  background-color: #fff;
  padding: 5px;
  border-radius: 50%;
  top: 30px;
}
</style>