import request from "@/config/request";

export async function userLoginAPI(body: { [key: string]: string }) {
  return request("/user/login", {
    method: "POST",
    data: body,
  });
}

export async function userLogoutAPI(
  params?: { [key: string]: any },
  options?: { [key: string]: any }
) {
  return request("/user/logout", {
    method: "POST",
    params: { ...params },
    ...(options || {}),
  });
}
