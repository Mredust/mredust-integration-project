<template>
  <a-layout>
    <GlobalSider style="height: 100vh" />
    <a-layout>
      <GlobalHeader />
      <a-layout-content :style="{ margin: '24px 16px 0' }">
        <div
          :style="{
            padding: '24px',
            background: '#fff',
            minHeight: '360px',
            height: '100%',
          }"
        >
          <router-view />
        </div>
      </a-layout-content>
      <a-layout-footer style="text-align: center">
        <a :href="Settings.GithubAddr" target="_blank" style="color: black">
          <GithubOutlined />
          {{ Settings.Author }}
        </a>
        {{ Settings.Copyright }}
      </a-layout-footer>
    </a-layout>
  </a-layout>
</template>
<script lang="ts" setup>
import GlobalSider from "@/layout/components/GlobalSider.vue";
import GlobalHeader from "@/layout/components/GlobalHeader.vue";
import { GithubOutlined } from "@ant-design/icons-vue";
import Settings from "@/config/defaultSettings";
</script>
<style scoped>
#components-layout-demo-responsive .logo {
  height: 32px;
  background: rgba(255, 255, 255, 0.2);
  margin: 16px;
}

.site-layout-sub-header-background {
  background: #fff;
}

.site-layout-background {
  background: #fff;
}

[data-theme="dark"] .site-layout-sub-header-background {
  background: #141414;
}
</style>
