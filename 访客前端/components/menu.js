var menu_template = `
<div>
    <div> 
        <br>
        <div class="container">   
            <div class="row">
                <div class="col-md-1">
                    <img :src="url+blogger.photo" class="rounded-circle" style="width:75px;height:75px">
                </div>&nbsp&nbsp
                <div>
                    <br>
                    <h1 class="text-warning">{{blogger.nickname}}的博客</h1>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-md-9">
                    &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                    <h7 class="text-light">{{blogger.autograph}}</h7>
                </div>
                <div  class="col-md-1">
                </div>
                <div class="col-md-1">
                    <h6 class="text-white"><span class="float-right ">{{user.nickname}}</span></h6>
                </div>
                <div class="dropdown">
                        <img :src="url+user.photo" alt="John Doe" class="rounded-circle dropdown-toggle" data-toggle="dropdown" style="width:30px;">
                        
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="#modify">修改信息</a>
                            <a class="dropdown-item" href="#modifypwd">修改密码</a>
                          
                            <div class="dropdown-divider"></div>
                            <button v-on:click="logout" class="btn btn-link dropdown-item">退出</button>
                        </div>
                </div>
            </div>
        </div>
    </div>
    <br>
    <div class="container">
        <div class="row container">
            <div class="col-md-3" >
                <h5 class="bg-secondary text-center" id="r1"><b>公告栏</b></h5>
                <div class="text-center" id="t1">
                    <ul class="list-group">
                        <li v-on:click="selectnotice(item.id)" class="list-group-item" v-for="(item,index) in notices">{{item.title}}</li>
                    </ul>
                </div>
                <br>

                <h5 class="bg-secondary text-center" id="r1"><b>热门文章</b></h5>
                <div class="text-center" id="t1">
                    <ul class="list-group">
                        <li v-on:click="selectitem(item.id)" class="list-group-item" v-for="(item,index) in top">{{item.title}}</li>
                    </ul>
                </div>
                <br>

                <h5 class="bg-secondary text-center" id="r1"><b>关于作者</b></h5>
                <p style="text-indent:2em" id="t1">
                    {{blogger.introduce}}
                </p>
                <h5 class="bg-secondary text-center" id="r1"><b>作者博客</b></h5>
                <a href="https://blog.csdn.net/kongsanjin" target="_blank"><img src="./img/KX博客.jpg" class="col-md-12" id="t1"></a>
                &nbsp
                <h5 class="bg-secondary text-center" id="r1"><b>推荐博客</b></h5>
                <a href="https://blog.csdn.net/wan_ide" target="_blank"><img src="./img/wanide博客.jpg" href="" class="col-md-12" id="t1"></a>
                &nbsp
                <br>
            </div>
            <div class="col-md-9">
                <div class="container">
                    <ul class="list-group">
                        <li v-for="item in articles" v-on:click="selectitem(item.id)" class="list-group-item">
                            <h5><b>{{item.title}}</b></h5>
                            <h6>{{item.created}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;阅读量：{{item.hits}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;赞：{{item.zan}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;评论量：{{item.comments}}</h6>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <br>
    <br>
    <foot1></foot1>
</div>
  `
const menu = {
  data() {
    return {
        articles: '',
        top: '',
        blogger: '',
        user: '',
        notices: '',
        url: store.state.url
    }
  },
  methods: {
    selectitem(id) {
      router.push({
        path: 'detail',
        query: { id: id }
      })
    },
    selectnotice(id) {
      router.push({
        path: 'noticeDetail',
        query: { id: id }
      })
    },
    logout() {
            axios.post(this.url + 'logout')
              .then(response => {
                var temps = response.data;
                if (temps.status == 'success') {
                  //this.returnvalue = '退出成功';
                  alert("退出成功");
                  router.push({
                        path: '/'
                        })
                } else {
                  alert("退出失败");
                  
                }
              })
              .catch(error => console.log(error));// 请求失败处理
          },
    },
  mounted() {
    //发送get请求
    axios.get(store.state.url+'articles')
      .then(response => this.articles = response.data)
      .catch(error => console.log(error));// 请求失败处理
    //发送get请求
    axios.get(store.state.url+'getBlogger')
      .then(response => this.blogger = response.data)
      .catch(error => console.log(error));// 请求失败处理
    //发送get请求
    axios.get(store.state.url+'top')
    .then(response => this.top = response.data)
    .catch(error => console.log(error));// 请求失败处理
    axios.get(store.state.url+'getuser')
    .then(response => this.user = response.data)
    .catch(error => console.log(error));// 请求失败处理
    //发送get请求
    axios.get(store.state.url+'notices')
      .then(response => this.notices = response.data)
      .catch(error => console.log(error));// 请求失败处理
  },
  template: menu_template
}
