         // 1. 定义路由
        // 每个路由应该映射一个组件。 其中"component" 可以是
        const routes = [
            { path: '/add', component: add },
            { path: '/detail', component: detail },
            { path: '/menu', component: menu },
            { path: '/admin', component: admin },
            { path: '/edit', component: edit },
            { path: '/login', component: login },
            { path: '/modify', component: modify },
            { path: '/modifypwd', component: modifypwd },
            { path: '/notices', component: notices },
            { path: '/noticeDetail', component: noticeDetail },
            { path: '/noticeAdd', component: noticeAdd },
            { path: '/noticeEdit', component: noticeEdit },
            { path: '/comments', component: comments },
            { path: '/', component: login }
        ]

        // 2. 创建 router 实例，然后传 `routes` 配置
        // 你还可以传别的配置参数, 不过先这么简单着吧。
        const router = new VueRouter({
            routes // （缩写）相当于 routes: routes
        })
