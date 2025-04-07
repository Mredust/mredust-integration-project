import axios from "axios";

// default config
axios.defaults.headers["Content-Type"] = "application/json;charset=utf-8";

// create axios instance
const request = axios.create({
  baseURL: process.env.VUE_APP_URL,
  withCredentials: true,
  timeout: 10000,
});

// request interceptor
request.interceptors.request.use(
  (config) => {
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// response interceptor
request.interceptors.response.use(
  (res) => {
    return res.data;
  },
  (error) => {
    let { message } = error;
    if (message.includes("Network Error")) {
      message = "后端接口连接异常";
    } else if (message.includes("timeout")) {
      message = "系统接口请求超时";
    } else if (message.includes("Request failed with status code")) {
      message = "系统接口" + message.substr(message.length - 3) + "异常";
    }
    error.message = message;
    return Promise.reject(error);
  }
);
export default request;
