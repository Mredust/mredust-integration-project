export default [
    {
        path: '/user',
        layout: false,
        routes: [
            {name: '登录', path: '/user/login', component: './User/Login'},
            {
                name: '注册',
                path: '/user/register',
                component: './User/Register'
            }]
    },
    {path: '/', redirect: '/welcome'},
    {path: '/welcome', name: '欢迎', icon: 'smile', component: './Welcome'},
    {
        path: '/admin',
        name: '管理页',
        icon: 'crown',
        access: 'canAdmin',
        routes: [
            {path: '/admin', redirect: '/admin/sub-page'},
            {path: '/admin/sub-page', name: '二级管理页', component: './Admin'},
        ],
    },
    {name: '用户管理', icon: 'user', path: '/admin/user-manage', component: './Admin/UserManage', access: "canAdmin"},
    {path: '*', layout: false, component: './404'},
];
