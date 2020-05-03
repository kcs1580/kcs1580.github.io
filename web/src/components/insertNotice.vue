
<template>
	<div class="container">
		<h1> 공지사항 글쓰기</h1>
			<div class="panel-body">
				<form action="" method="post" id="_brdFrom" name = "brdForm" @submit.prevent="insertNotice">
					<!-- <input type="hidden" name="user_id" value="orora@com" /> Notice는 필요없다. -->

					<div class="form-group">
						<label for="title">제목</label>
						<input data-msg="제목" type = "text" name="ntitle" id="ntitle" v-model="ctitle" class="form-control" placeholder="제목을 입력해주세요." style="width:100%" >
					</div>
					<div class="form-group">
						<label for="bcontent">내용</label>
						<input data-msg="내용"  type="text" name="ncontent" id="ncontent" v-model="ccontent" class="form-control"  style="height:500px; width:100%" placeholder="내용을 입력해주세요." >
					</div>
					
					<div class="row">
						<div class="col-sm-12 text-right">
							<div style="float:left;">
							<button type="reset" class="btn btn-danger" style="width:550px;">취소 </button>
							</div>
							<div  style="float:left;">
							<button type="submit" class="btn btn-primary" style="width:550px;">글쓰기</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>



</template>

<script>
import http from "../http-common";

export default {
	name: "insertNotice",
	data() {
		return {
			loading: true,
			errored: false,			
			ctitle:'',
			ccontent:'',
			ccount:0,
			cid:'',			
			submitted: false
		};
	},
		mounted () {

	},

	methods: {
		showlist() {      
			this.$router.push("/listNotice");
    },
	insertNotice() {
		if(this.ctitle==''){ alert('제목을 입력하세요.'); return ;}
		if(this.ccontent==''){ alert('내용을 입력하세요.'); return ;}
		
		
		http.post('/registernotice', {
			nid: this.cid,
			ntitle: this.ctitle,
			ncontent: this.ccontent,
			ncount: this.ccount

		} 
		).then(response => {
				if (response.data.state==0) {
					alert("공지사항 등록 완료.");

					this.showlist();
				}else{
					alert("공지사항 등록 성공.");
					this.showlist();
				}
		});
		this.submitted = true;
	},

		
  }
}
</script>

<style>
.submitform {
  max-width: 300px;
  margin: auto;
}
</style>