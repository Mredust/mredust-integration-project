<template>
  <div id="app">
    <template v-if="route.path.includes('/user')">
      <router-view />
    </template>
    <template v-else>
      <router-view />
    </template>
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  height: 100%;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
<script setup lang="ts">
import { useRoute } from "vue-router";
import BasicLayout from "@/layout/BasicLayout.vue";

const route = useRoute();
</script>
