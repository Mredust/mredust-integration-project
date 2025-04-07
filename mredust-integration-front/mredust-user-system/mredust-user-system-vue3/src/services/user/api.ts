import request from "@/config/request";

export async function userLoginAPI(body: { [key: string]: string }) {
  return request("/user/login", {
    method: "POST",
    data: body,
  });
}
