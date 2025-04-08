import {AvatarDropdown, AvatarName, Footer, Question} from '@/layout';
import type {Settings as LayoutSettings} from '@ant-design/pro-components';
import type {RunTimeLayoutConfig} from '@umijs/max';
import {history} from '@umijs/max';
import defaultSettings from '../config/defaultSettings';
import {requestConfig} from "@/requestConfig";
import {getLoginUserAPI} from "@/services/user/api";

const loginPath = '/user/login';

/**
 * @see  https://umijs.org/zh-CN/plugins/plugin-initial-state
 * */
export async function getInitialState(): Promise<{
    settings?: Partial<LayoutSettings>;
    currentUser?: UserAPI.UserVO;
    loading?: boolean;
    fetchUserInfo?: () => Promise<UserAPI.UserVO | undefined>;
}> {
    const fetchUserInfo = async () => {
        let userInfo = JSON.parse(localStorage.getItem('userInfo'));
        try {
            const res = await getLoginUserAPI({
                userId: userInfo.userId
            }, {skipErrorHandler: true,});
            return res.data;
        } catch (error) {
            history.push(loginPath);
        }
        return undefined;
    };
    // 如果不是登录页面，执行
    const {location} = history;
    if (location.pathname !== loginPath) {
        const currentUser = await fetchUserInfo();
        return {
            fetchUserInfo,
            currentUser,
            settings: defaultSettings as Partial<LayoutSettings>,
        };
    }
    return {
        fetchUserInfo,
        settings: defaultSettings as Partial<LayoutSettings>,
    };
}

// ProLayout 支持的api https://procomponents.ant.design/components/layout
export const layout: RunTimeLayoutConfig = ({initialState, setInitialState}) => {
    return {
        actionsRender: () => [<Question key="doc"/>],
        avatarProps: {
            src: initialState?.currentUser?.avatarUrl,
            title: <AvatarName/>,
            render: (_, avatarChildren) => {
                return <AvatarDropdown menu={true}>{avatarChildren}</AvatarDropdown>;
            },
        },
        footerRender: () => <Footer/>,
        onPageChange: () => {
            const {location} = history;
            // 如果没有登录，重定向到 login
            if (!initialState?.currentUser && location.pathname !== loginPath) {
                history.push(loginPath);
            }
        },
        bgLayoutImgList: [
            {
                src: 'https://mdn.alipayobjects.com/yuyan_qk0oxh/afts/img/D2LWSqNny4sAAAAAAAAAAAAAFl94AQBr',
                left: 85,
                bottom: 100,
                height: '303px',
            },
            {
                src: 'https://mdn.alipayobjects.com/yuyan_qk0oxh/afts/img/C2TWRpJpiC0AAAAAAAAAAAAAFl94AQBr',
                bottom: -68,
                right: -45,
                height: '303px',
            },
            {
                src: 'https://mdn.alipayobjects.com/yuyan_qk0oxh/afts/img/F6vSTbj8KpYAAAAAAAAAAAAAFl94AQBr',
                bottom: 0,
                left: 0,
                width: '331px',
            },
        ],
        menuHeaderRender: undefined,
        // 自定义 403 页面
        // unAccessible: <div>unAccessible</div>,
        ...initialState?.settings,
    };
};


export const request = {
    ...requestConfig,
};
