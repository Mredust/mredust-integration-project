import {Footer} from '@/layout';
import {LockOutlined, UserOutlined,} from '@ant-design/icons';
import {LoginForm, ProFormCheckbox, ProFormText,} from '@ant-design/pro-components';
import {Helmet, history, useModel} from '@umijs/max';
import {message} from 'antd';
import React from 'react';
import {flushSync} from 'react-dom';
import Settings from '../../../../config/defaultSettings';
import {userLoginAPI} from "@/services/user/api";


const Login: React.FC = () => {
    const {initialState, setInitialState} = useModel('@@initialState');
    const fetchUserInfo = async () => {
        const userInfo = await initialState?.fetchUserInfo?.();
        if (userInfo) {
            flushSync(() => {
                setInitialState((s) => ({
                    ...s,
                    currentUser: userInfo,
                }));
            });
        }
    };
    const handleSubmit = async (values: UserAPI.UserLoginRequest) => {
        try {
            // 登录
            const res = await userLoginAPI({...values});
            if (res.code === 200) {
                message.success("登录成功");
                localStorage.setItem("userInfo", JSON.stringify(res.data));
                await fetchUserInfo();
                const urlParams = new URL(window.location.href).searchParams;
                history.push(urlParams.get('redirect') || '/');
                return;
            }
            console.log(res.msg);
        } catch (error) {
            const defaultLoginFailureMessage = '登录失败，请重试！';
            console.log(error);
            message.error(defaultLoginFailureMessage);
        }
    };
    return (
        <div
            style={{
                display: 'flex',
                flexDirection: 'column',
                height: '100vh',
                overflow: 'auto',
                backgroundSize: '100% 100%',
                backgroundImage: "url('https://mdn.alipayobjects.com/yuyan_qk0oxh/afts/img/V-_oS6r-i7wAAAAAAAAAAAAAFl94AQBr')",
            }}
        >
            <Helmet>
                <title>
                    {'登录'}- {Settings.title}
                </title>
            </Helmet>
            <div
                style={{
                    flex: '1',
                    padding: '32px 0',
                    marginTop: 150
                }}
            >
                <LoginForm
                    contentStyle={{
                        minWidth: 280,
                        maxWidth: '75vw',
                        marginTop: 100
                    }}
                    title="用户管理中心"
                    initialValues={{
                        autoLogin: true,
                    }}
                    onFinish={async (values) => {
                        await handleSubmit(values as UserAPI.UserLoginRequest);
                    }}
                >
                    <div>
                        <ProFormText
                            name="account"
                            fieldProps={{
                                size: 'large',
                                prefix: <UserOutlined/>,
                            }}
                            placeholder={'请输入账号'}
                            rules={[
                                {
                                    required: true,
                                    message: '账号是必填项！',
                                },
                            ]}
                        />
                        <ProFormText.Password
                            name="password"
                            fieldProps={{
                                size: 'large',
                                prefix: <LockOutlined/>,
                            }}
                            placeholder={'请输入密码'}
                            rules={[
                                {
                                    required: true,
                                    message: '密码是必填项！',
                                },
                            ]}
                        />
                    </div>

                    <div
                        style={{
                            marginBottom: 24,
                        }}
                    >
                        <ProFormCheckbox noStyle name="autoLogin">
                            自动登录
                        </ProFormCheckbox>
                        <a
                            style={{
                                float: 'right',
                            }}
                            onClick={() => {
                                history.push('/user/register');
                            }}
                        >
                            账号注册
                        </a>
                    </div>
                </LoginForm>
            </div>
            <Footer/>
        </div>
    );
};
export default Login;
