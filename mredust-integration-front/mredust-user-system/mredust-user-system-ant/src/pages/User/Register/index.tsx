import {Footer} from '@/components';
import {LockOutlined, UserOutlined,} from '@ant-design/icons';
import {LoginForm, ProFormText,} from '@ant-design/pro-components';
import {Helmet, history} from '@umijs/max';
import {message} from 'antd';
import React from 'react';
import Settings from '../../../../config/defaultSettings';
import {userRegisterAPI} from "@/services/user/api";


const Register: React.FC = () => {
    const handleSubmit = async (values: UserAPI.UserRegisterRequest) => {
        try {
            // 登录
            const res = await userRegisterAPI({...values});
            if (res.code === 200) {
                message.success("注册成功");
                history.push('/user/login');
                return;
            } else {
                message.error(res.msg)
            }
        } catch (error) {
            const defaultLoginFailureMessage = '注册失败，请联系管理员！';
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
                    {'注册'}- {Settings.title}
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
                    submitter={{
                        searchConfig: {
                            submitText: '注册'
                        }
                    }}
                    onFinish={async (values) => {
                        await handleSubmit(values as UserAPI.UserRegisterRequest);
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
                        <ProFormText.Password
                            name="checkPassword"
                            fieldProps={{
                                size: 'large',
                                prefix: <LockOutlined/>,
                            }}
                            placeholder={'请再次输入密码'}
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
                        <a
                            style={{
                                float: 'right',
                                marginBottom: '24px',
                            }}
                            onClick={() => {
                                history.push('/user/login');
                            }}
                        >
                            返回登录
                        </a>
                    </div>
                </LoginForm>
            </div>
            <Footer/>
        </div>
    );
};
export default Register;
