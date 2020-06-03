var noticeDetail_template = `
<div class="container r" id="t2">
    <br>
    <h2 style="text-align: center;">{{notice.title}}</h2>
    <br>
    <br>
    <div v-html="notice.content"></div>
    <br><br>
        <div class="row float-right">
                <h6><b>时间：{{notice.created}}</b>
        </div>
</div>
        `
const noticeDetail = {
    data() {
        return {
          notice: '',
        }
    },
    mounted() {
        //发送get请求
        axios.get(store.state.url + 'notice/' + this.$route.query.id)
            .then(response => {
                //console.log(response);
                this.notice = response.data;
            })
            .catch(error => console.log(error));// 请求失败处理
    },
    template: noticeDetail_template
}
