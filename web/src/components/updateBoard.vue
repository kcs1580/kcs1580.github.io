
<template>
	<div class="container">
	
    <h1 style="text-align: center; font-size: 3em; margin-bottom:"> 질문 게시판 </h1>
		<hr style="margin-bottom: 40px;">
		<div class="panel panel-default">
			<div class="panel-body">
				<form action="" method="post" id="_brdFrom" name = "brdForm" @submit.prevent="updateBoard">
					<input type="hidden" name="user_id" value="orora@com" />

					<div class="form-group">
						<label for="title">제목</label>
						<input data-msg="제목" type = "text" name="btitle" id="btitle" v-model="ctitle" class="form-control" placeholder="제목을 입력해주세요." style="width:100%" >
					</div>
					<div class="form-group">
						<label for="bcontent">내용</label>
						<input data-msg="내용"  type="text" name="bcontent" id="bcontent" v-model="ccontent"  class="form-control"  style="height:500px; width:100%" placeholder="내용을 입력해주세요." >
					</div>
					
					<div class="row">
						<div class="col-sm-12 text-right">
								<button type="submit" class="btn btn-info btn-flat">수정</button>
								
								<!-- <a href="" @click="showinsert()" class="btn btn-info btn-flat">글쓰기</a> -->
						</div>
					</div>
				</form>
			</div>
		</div>
</div>
</template>


<script>
import http from "../http-common";
import App from "../App.vue";
export default {
    name : "updateBoard",
   
     data() {
    return {
			ctitle:'',
			ccontent:'',
			ccount:0,
			cid:'',
			cuser_id: localStorage.getItem("id"),
			submitted: false,

      upHere: false,
      board: [],
      loading: true,
      errored: false
    };
  },
  methods: {
	showlist() {      
			this.$router.push("/listBoard");
    },
    viewBoards() {
      http
        .get("http://localhost:8090/api/infoboard/"+App.bid)
        .then(response => {
			this.board = response.data.resvalue;
			this.cid = this.board.bid;
			this.ctitle = this.board.btitle;

			this.ccontent = this.board.bcontent;
			})
        .catch(() => {
			//alert("fail");
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    } , 	
  
  updateBoard(){
	
	http.put("/updateboard",{
			bid: this.cid,
			btitle: this.ctitle,
			bcontent: this.ccontent,
			bcount: this.bcount,
			user_id: this.cuser_id,
		}

		).then(response => {						
				if (response.data.state==0) {
					alert("질문수정 완료.");
						this.showlist();
				}else{
					alert("질문수정 완료.");
						this.showlist();
				}
		});
		this.submitted = true;
        // .then(response => {
		// 	this.board = response.data.resvalue;
		// 	this.cid = this.board.bid;
		// 	this.ctitle = this.board.btitle;
		// 	this.ccontent = this.board.bcontent;
		// 	})
        // .catch(() => {
		// 	alert("fail");
        //   this.errored = true;
        // })
        // .finally(() => (this.loading = false));

  }
  },


 mounted() {
    this.viewBoards();
  }
};
</script>

  
}
