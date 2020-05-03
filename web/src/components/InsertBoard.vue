
<style>
.submitform {
  max-width: 300px;
  margin: auto;
}


</style>


<template>
	<div class="container">
<div>
	<h1 style="text-align: center; font-size: 50px; margin-bottom:">Q&amp;A</h1>

		<hr style="margin-bottom: 40px;">
		<div class="panel panel-default">
			<div class="panel-body" >
				<form action="" method="post" id="_brdFrom" name = "brdForm" @submit.prevent="insertBoard">
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
		


 </div> 

</div>

</template>

<script>
import http from "../http-common";

export default {
	name: "InsertBoard",
	data() {
		return {
			loading: true,
			errored: false,			
			ctitle:'',
			ccontent:'',
			ccount:0,
			cid:'',
			user_name:'',
			submitted: false
		};
	},
		mounted () {

	},

	methods: {
		showlist() {      
			this.$router.push("/listBoard");
    },
	insertBoard() {
		if(this.ctitle==''){ alert('제목을 입력하세요.'); return ;}
		if(this.ccontent==''){ alert('내용을 입력하세요.'); return ;}
		
		
		http.post('/registerboard', {
			bid: this.cid,
			btitle: this.ctitle,
			bcontent: this.ccontent,
			bcount: this.bcount,
			user_name: localStorage.getItem("name"),
			user_id: localStorage.getItem("id")
		} 
		).then(response => {
				if (response.data.state==0) {
					alert("질문등록 완료.");
						this.showlist();
				}else{
					alert("질문등록 성공.");
						this.showlist();
				}
		});
		this.submitted = true;
	},

  }
}
</script>

