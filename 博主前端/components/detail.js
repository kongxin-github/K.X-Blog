var detail_template = `
<div class="container" id="t2">
    <br>
    <div style="text-align: center;"><h4><b>{{article.title}}</b><h4></div>
      <br>
        <div >
            <h6><a herf="#" @click="Edit(article.id)"><span class="float-right text-primary">编辑</span></a></h6>
        </div>
        <br>
        <br>
      
        <div v-html="article.content">
        </div>
        <br><br>
        <div class="row float-right">
                <h6><b>{{article.created}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;阅读量：{{article.hits}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;赞：{{article.zan}}</h6>&nbsp&nbsp<img src="./img/赞.jpg" width="30px" height="30px" @click="addZan()"/></b>
        </div><br><br>
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
            url: store.state.url
        }
    },
    methods: {
        addZan() {
            //发送get请求
            axios.put(store.state.url + 'article/' + this.article.id)
            .then(response => {this.article = response.data;
                location.reload();
            })
            .catch(error => console.log(error));// 请求失败处理
            
        },
        //编辑
        Edit: function (id) {
            router.push({
                path: 'edit',
                query: { id: id }
              })
        }
    },
    mounted() {
        //发送get请求
        axios.get(store.state.url + 'article/' + this.$route.query.id)
            .then(response => this.article = response.data)
            .catch(error => console.log(error));// 请求失败处理
    },
    template: detail_template
}
