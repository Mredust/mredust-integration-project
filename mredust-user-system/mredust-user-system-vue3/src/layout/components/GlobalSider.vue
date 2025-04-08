<template>
  <a-layout-sider breakpoint="lg" collapsed-width="0">
    <div class="title">{{ Settings.title }}</div>
    <a-menu :selectedKeys="selectedKeys" theme="dark" mode="inline">
      <a-menu-item
        :key="item.key"
        v-for="item in menuItems"
        @Click="toView(item)"
      >
        <component :is="item.icon" />
        <span class="nav-text">{{ item.label }}</span>
      </a-menu-item>
    </a-menu>
  </a-layout-sider>
</template>

<script setup lang="ts">
import { HomeOutlined, UserOutlined } from "@ant-design/icons-vue";
import { ref } from "vue";
import Settings from "../../config/defaultSettings";
import { useRouter } from "vue-router";

const menuItems = [
  {
    key: "home",
    icon: HomeOutlined,
    label: "首页",
    path: "/home",
  },
  {
    key: "user-manage",
    icon: UserOutlined,
    label: "用户管理",
    path: "/admin/user-manage",
  },
];
const selectedKeys = ref<string[]>([menuItems[0].key]);
const router = useRouter();
const toView = (item: any) => {
  selectedKeys.value = [item.key];
  router.push(item.path);
};
</script>
<style scoped>
.title {
  color: #fff;
  font-size: 20px;
  text-align: center;
  margin: 20px;
}
</style>
