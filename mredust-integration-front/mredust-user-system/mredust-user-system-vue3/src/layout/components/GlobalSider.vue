<template>
  <a-layout-sider
    breakpoint="lg"
    collapsed-width="0"
    @collapse="onCollapse"
    @breakpoint="onBreakpoint"
  >
    <div class="title">用户管理中心</div>
    <a-menu v-model:selectedKeys="selectedKeys" theme="dark" mode="inline">
      <a-menu-item key="home">
        <home-outlined />
        <span class="nav-text">首页</span>
      </a-menu-item>
      <a-menu-item key="user-manage">
        <user-outlined />
        <span class="nav-text">用户管理</span>
      </a-menu-item>
    </a-menu>
  </a-layout-sider>
</template>

<script setup lang="ts">
import { HomeOutlined, UserOutlined } from "@ant-design/icons-vue";
import { ref } from "vue";

const onCollapse = (collapsed: boolean, type: string) => {
  console.log(collapsed, type);
};

const onBreakpoint = (broken: boolean) => {
  console.log(broken);
};

const selectedKeys = ref<string[]>(["home"]);
</script>
<style scoped>
.title {
  color: #fff;
  font-size: 20px;
  text-align: center;
  margin: 20px;
}
</style>
