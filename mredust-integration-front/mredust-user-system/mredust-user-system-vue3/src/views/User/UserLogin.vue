<template>
  <div id="user-login">
    <div class="title">{{ Settings.title }}</div>
    <a-form
      :model="loginForm"
      name="normal_login"
      class="login-form"
      @finish="onFinish"
      :label-col="{ span: 6 }"
      :wrapper-col="{ span: 14 }"
      autocomplete="off"
    >
      <a-form-item
        label="账号"
        name="account"
        :rules="[{ required: true, message: '请输入账号' }]"
      >
        <a-input v-model:value="loginForm.account">
          <template #prefix>
            <UserOutlined class="site-form-item-icon" />
          </template>
        </a-input>
      </a-form-item>

      <a-form-item
        label="密码"
        name="password"
        :rules="[{ required: true, message: '请输入密码' }]"
      >
        <a-input-password v-model:value="loginForm.password">
          <template #prefix>
            <LockOutlined class="site-form-item-icon" />
          </template>
        </a-input-password>
      </a-form-item>
      <a-form-item name="register" :wrapper-col="{ offset: 10, span: 16 }">
        <a @click="toRegister">没有账号? 前往注册</a>
      </a-form-item>
      <a-form-item
        style="display: flex; justify-content: center"
        :wrapper-col="{ span: 16 }"
      >
        <a-button type="primary" html-type="submit" class="login-form-button">
          登录
        </a-button>
      </a-form-item>
    </a-form>
    <a-space direction="horizontal" class="footer">
      <a :href="Settings.GithubAddr" target="_blank" style="color: black">
        <GithubOutlined />
        {{ Settings.Author }}
      </a>
      {{ Settings.Copyright }}
    </a-space>
  </div>
</template>

<script setup lang="ts">
import {
  GithubOutlined,
  LockOutlined,
  UserOutlined,
} from "@ant-design/icons-vue";
import Settings from "@/config/defaultSettings";
import { reactive } from "vue";
import router from "@/router";

interface FormState {
  account: string;
  password: string;
}

const loginForm = reactive<FormState>({
  account: "",
  password: "",
});
const onFinish = (values: any) => {
  console.log("Success:", values);
};
const toRegister = () => {
  router.push({ path: "/user/register" });
};
</script>
<style scoped>
#user-login {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: linear-gradient(135deg, #1fa2ff, #12d8fa, #a6ffcb);
}

.title {
  height: 10%;
  font-size: 30px;
  font-weight: bold;
  margin-bottom: 20px;
}

.login-form {
  width: 25%;
  height: 35%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  background-color: white;
  border-radius: 10px;
  padding-top: 2%;
}

.footer {
  height: 10%;
}

#user-login .login-form-button {
  width: 100%;
}
</style>
