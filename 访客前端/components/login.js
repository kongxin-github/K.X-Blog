var login_template = `
<div class="container">
  <br>
  
      <h1 class="display-3 text-warning"><center>K.X的博客</center></h1>
    
  <br>
  <div class="row">
    <div class="col-md-3">
    </div>
    <div class="container col-md-6" id="t3">
      <br>
      <h4 class="text-center">访客登录</h4><br>

          <div class="form-group">
              <label for="username">用户名:</label>&nbsp;
              <input type="text" v-model="username" class="form-control" id="username">
          </div>
          <div class="form-group">
              <label for="pwd">密码:</label>&nbsp;
              <input type="password" v-model="pwd" class="form-control" id="pwd">
          </div>
          <div class="form-group">
              <button v-on:click="login_1" class="btn btn-primary btn-block">登录</button>
          </div>
          <div class="form-group">
            <button v-on:click="register_1" class="btn btn-primary btn-block">注册</button>
          </div>
    </div>
    <div class="col-md-3">
    </div>
  </div>
</div>
  `
const login = {
  data() {
    return {
      username: '',
      pwd: '',
      url: store.state.url,
    }
  },
  methods: {
    login_1() {
      axios.post(this.url + 'login?username=' + this.username + '&password=' + this.pwd)
        .then(response => {
          var temps = response.data;
          if (temps.status == 'success') {
            // //this.returnvalue = '登录成功';
            // this.login = false;
            alert("登录成功");
            router.push({
              path: 'menu'
            })
          } else {
            alert("身份错误！");
            return;
          }
        })
        .catch(error => console.log(error));// 请求失败处理
    },
    register_1(){
      router.push({
        path: 'register'
      })
    },
  },
  template: login_template
}
