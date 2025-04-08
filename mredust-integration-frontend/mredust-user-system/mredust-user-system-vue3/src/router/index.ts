import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    redirect: "/home",
    component: () => import("@/layout/BasicLayout.vue"),
    children: [
      {
        path: "/home",
        name: "首页",
        component: () => import("@/views/HomeView.vue"),
      },
      {
        path: "/admin/user-manage",
        name: "UserManage",
        component: () => import("@/views/admin/UserManage.vue"),
      },
    ],
  },
  // {
  //   path: "/admin",
  //   name: "管理员",
  //   component: () => import("@/layout/BasicLayout.vue"),
  //   children: [
  //     {
  //       path: "/admin/user-manage",
  //       name: "UserManage",
  //       component: () => import("@/views/admin/UserManage.vue"),
  //     },
  //   ],
  // },
  {
    path: "/user",
    name: "用户",
    children: [
      {
        path: "/user/login",
        name: "UserLogin",
        component: () => import("@/views/user/UserLogin.vue"),
      },
      {
        path: "/user/register",
        name: "UserRegister",
        component: () => import("@/views/user/UserRegister.vue"),
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
