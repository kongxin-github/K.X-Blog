var admin_template =`
    <div  id="t2" class="container">
        <div class="panel panel-primary">
            <br>
            <h3 class="text-center">博客管理</h3>
            <br>
            <table class="table table-bordered table-striped text-center">
                <thead class="w-100">
                    <tr class="row mx-0">
                        <th class="col-4">标题</th>
                        <th class="col-1">阅读量</th>
                        <th class="col-1">评论量</th>
                        <th class="col-1">赞</th>
                        <th class="col-3">创建时间</th>
                        <th class="col-2">操作</th>
                    </tr>
                </thead>
                <tbody class="w-100">
                    <template v-for="(row,index) in articles ">
                        <tr class="row mx-0">
                            <td v-on:click="selectitem(row.id)" class="col-4">{{row.title}}</td>
                            <td v-on:click="selectitem(row.id)" class="col-1">{{row.hits}}</td>
                            <td v-on:click="selectitem(row.id)" class="col-1">{{row.comments}}</td>
                            <td v-on:click="selectitem(row.id)" class="col-1">{{row.zan}}</td>
                            <td v-on:click="selectitem(row.id)" class="col-3">{{row.created}}</td>
                            <td class="col-2"><button @click="Edit(row.id)" class="btn btn-warning">编辑</button>
                                <button @click="Delete(index)" class="btn btn-danger">删除</button>
                            </td>
                        </tr>
                    </template>
                </tbody>
            </table>
        </div>
        <foot1><foot1/>
    </div>
`

const admin = {
    data() {
      return {
          articles:'',
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
        //删除
        Delete: function (index) {
            //数据库操作
            axios.delete(store.state.url + 'article/' + this.articles[index].id)
            .then(response => {
                console.log(response.data);
                this.articles.splice(index, 1);//从下标i开始删除1个元素：删除下标为i的元素
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
        axios.get(store.state.url+'articles')
          .then(response => this.articles = response.data)
          .catch(error => console.log(error));// 请求失败处理
      },
    template: admin_template
}