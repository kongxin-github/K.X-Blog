var detail_template = `
<div class="container" id="t2">
    <br>
    <div style="text-align: center;"><h4><b>{{article.title}}</b><h4></div>
      <br>
        
        <br>
        <br>
      
        <div v-html="article.content">
        </div>
        <br><br>
        <div class="row float-right">
                <h6><b>{{article.created}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;阅读量：{{article.hits}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;赞：{{article.zan}}</h6>&nbsp&nbsp<img src="./img/赞.jpg" width="30px" height="30px" @click="addZan()"/></b>
        </div><br><br>
        <h4>评论:</h4>
        <br>
        <div class="input-group mb-3 input-group-lg">
            <input type="text" class="form-control"  v-model="comment.content">
        </div>
        <button type="button" class="btn btn-primary btn-lg float-right" v-on:click="release()">发表评论</button>
        <br><br>
        <br>
        <div class="media border p-3" v-for="(item,index) in article.commentList">
            <img :src="url+item.user.photo" alt="John Doe" class="mr-3 mt-3 rounded-circle" style="width:40px;">
            <div class="media-body">
            <b>{{item.user.nickname}}</b>
            <p style="text-indent:1em">{{item.content}}</p>      
            </div>
            </div>
        <div>
        <foot1></foot1>
    </div>
</div>
        `
const detail = {
    data() {
        return {
            article: '',
            user: '',
            comment:  {articleid: '', userid: '', content: ''},
            url: store.state.url
        }
    },
    methods: {
        addZan() {
            //发送get请求
            axios.put(store.state.url + 'article/' + this.article.id)
            .then(response => {this.article = response.data;
                location.reload(); //刷新
            })
            .catch(error => console.log(error));// 请求失败处理
            
        },
        //编辑
        Edit: function (id) {
            router.push({
                path: 'edit',
                query: { id: id }
              })
        },
        release() {
            if (this.comment.content == '') {
                alert("内容不能为空!");
                return;
            }
            this.comment.articleid=this.article.id;
            this.comment.userid=this.user.id;
            axios.post(store.state.url + 'comment/', this.comment)
                .then(response => {console.log(response.data);
                    location.reload();  
                })
                .catch(error => console.log(error));// 请求失败处理
            
        }
    },
    mounted() {
        //发送get请求
        axios.get(store.state.url + 'article/' + this.$route.query.id)
            .then(response => this.article = response.data)
            .catch(error => console.log(error));// 请求失败处理
        axios.get(store.state.url+'getuser')
            .then(response => this.user = response.data)
            .catch(error => console.log(error));// 请求失败处理
    },
    template: detail_template
}
