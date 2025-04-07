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
              <a-menu-item>
                <a href="javascript:;">个人信息</a>
              </a-menu-item>
              <a-menu-item>
                <a href="javascript:;">退出登录</a>
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
