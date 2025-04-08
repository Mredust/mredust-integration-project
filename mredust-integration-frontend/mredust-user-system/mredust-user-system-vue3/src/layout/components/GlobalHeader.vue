<template>
  <a-layout-header :style="{ background: '#fff', padding: 0 }">
    <a-space class="user" direction="horizontal" :size="16">
      <a-space wrap :size="8">
        <a-avatar size="large">
          <template #icon>
            <UserOutlined />
          </template>
        </a-avatar>
      </a-space>
      <a-space wrap :size="8">
        <a-dropdown placement="bottomRight">
          <a class="ant-dropdown-link" @click.prevent style="color: black"
            >管理员</a
          >
          <template #overlay>
            <a-menu style="margin-top: 10px">
              <a-menu-item v-for="item in menuList" :key="item.key">
                <a @click="to(item)">{{ item.label }}</a>
              </a-menu-item>
            </a-menu>
          </template>
        </a-dropdown>
      </a-space>
    </a-space>
  </a-layout-header>
</template>

<script setup lang="ts">
import { UserOutlined } from "@ant-design/icons-vue";
import { message } from "ant-design-vue";
import { userLogoutAPI } from "@/services/user/api";
import { useRouter } from "vue-router";

const menuList = [
  {
    key: "info",
    label: "个人信息",
    path: "/user/info",
  },
  {
    key: "logout",
    label: "退出登录",
    path: "/user/login",
  },
];
const router = useRouter();
const to = async (item: any) => {
  if (item.key === "logout") {
    let userInfo = JSON.parse(localStorage.getItem("userInfo") || "{}");
    await userLogoutAPI({ userId: userInfo.userId });
    await router.push({
      path: "/user/login",
    });
  } else {
    message.warning("功能尚未开发");
  }
};
</script>
<style scoped>
.user {
  width: 100%;
  display: flex;
  justify-content: right;
  align-items: center;
  padding-right: 50px;
}
</style>
