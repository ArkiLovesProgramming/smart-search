<template>
  <div class="common-layout index">
    <el-container>
      <el-header class="myheader">
        <el-affix :offset="0">
          <SHeader></SHeader>
        </el-affix>
      </el-header>
      <el-container class="container">
        <el-row class="" :gutter="10" style="width: inherit;">
          <el-col :xs="24" :sm="16">
            <div class="result_box">
              <router-view></router-view>
            </div>
          </el-col>
          <el-col :xs="0" :sm="8">
            <div class="result_box">
              <el-affix :offset="36">
                <SSideVue/>
              </el-affix>
            </div>
          </el-col>
        </el-row>
      </el-container>
      <el-footer>
        <SFooter></SFooter>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
import SHeader from './components/SHeader.vue';
import SBody from './components/SBody.vue';
import SFooter from './components/SFooter.vue';
import SSideVue from './components/SSide.vue';
  export default {
    name: 'App',
    components: {
      SHeader,
      SBody,
      SFooter,
      SSideVue
    },
    setup() {
      return {

      }
    }
  }
</script>

<style>
  .index {
  background-color: #f4f4f5;
  /* height: 100%; */
  width: 100%;
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
}
.el-container {
  width: 100%;
  min-height: 100%;
  display: flex;
  flex-direction: column;
  justify-content:space-between;
}
.el-main {
  height: 100%;
  overflow: hidden;
}
.el-footer {
  /* display: flex; */
  height: 4vh;
  width: 100%;
  align-items: center;
  text-align: center;
}
.result_box{
  padding: 10px 0px;
}
.myheader{
  padding: 0px!important;
}
</style>
