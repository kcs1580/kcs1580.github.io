<template>
 <div class="container">
<h1 style="text-align: center; font-size: 3em; margin-top:40px;">공지 사항 </h1>
		<hr style="margin-bottom: 40px;">
		
		<div class="panel panel-info" style="background-color: rgba( 255, 255, 255,0.8 ); height: 800px; ">
			<div class="panel-heading" style="height:70px; padding: 20px;  font-weight: bold;background:#b6b9bb ">
				<h3> <i class="far fa-bell"></i>
         [공지 no. {{notice.nid}}] {{notice.ntitle }}</h3>
			</div>
      <br>
      <div style="float:right;">
        작성자: 관리자 
                                <span style="margin: 5px;"></span>
  조회수: {{notice.ncount}}
                          <span style="margin: 5px;"></span>

      </div>
      <br>
      <hr>
			<div class="panel-body" style="height:650px; font-size:20px; padding:40px;">
				{{notice.ncontent }}
			</div>
			<div class="panel-footer text-right">
				<button class="btn btn-success" @click="edit()" style="width:200px;">수정</button>
        <button class="btn btn-danger" @click="remove()" style="width:200px;">삭제</button>
        <button class="btn btn-dark" @click="backtolist()" style="width:200px;">목록</button>
			</div>
		</div>
		
		
  </div>
</template>
<script>
import http from "../http-common";
import App from "../App.vue";
export default {
  name: "viewNotice",
  data() {
    return {
      upHere: false,
      notice: [],
      loading: true,
      errored: false
    };
  },
  methods: {
	showlist() {      
			this.$router.push("/listNotice");
    },
    viewNotices() {
      http
        .get("http://localhost:8090/api/infonotice/"+App.bid)
        .then(response => {
            this.notice = response.data.resvalue;

			})
        .catch(() => {
			alert("fail");
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    },
    edit(){
		this.$router.push("/updateNotice");

    },
    remove(){    
      alert(App.bid + "번 공지사항을 삭제합니다.");
      http
        .delete("/deletenotice/" + App.bid)
        .then(response => {
          if (response.data.state == "succ") {
			alert("공지사항 삭제를 하였습니다.");
			this.$router.push("/listNotice");
            // this.retrieveCustomers();
          } else {
			alert("공지사항 삭제를 하였습니다.");
			this.$router.push("/listNotice");
          }
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    
      },
    backtolist(){
		this.showlist();
    }
  },
  filters: {
  },
  mounted() {
    this.viewNotices();
  }
};
</script>
<style>
</style>