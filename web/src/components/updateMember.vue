<template>
<div id="outex">
	<div class="ex">
		<form class="form-horizontal" role="form" method="put"
			@submit.prevent="updatemem">
			<input type="hidden" name="action" value="signup" />
			<div class="form-group" id="divId">
				<label for="inputId" class="col-lg-2 control-label">아이디</label>
				<div class="col-lg-10">
					<input type="email" class="form-control onlyAlphabetAndNumber"
						id="id" name="id" data-rule-required="true" placeholder="" maxlength="30" v-model="cid" disabled>
				</div>
			</div>

			<div class="form-group" id="divPassword">
				<label for="inputPassword" class="col-lg-2 control-label">패스워드</label>
				<div class="col-lg-10">
					<input type="password" class="form-control" id="password"
						name="password" data-rule-required="true"
						placeholder="영문 숫자포함  6자리 이상" maxlength="30"  v-model="cpassword">
				</div>
			</div>
			<div class="form-group" id="divName">
				<label for="inputName" class="col-lg-2 control-label">이름</label>
				<div class="col-lg-10">
					<input type="text" class="form-control onlyHangul" id="mname" name="mname"
						data-rule-required="true" placeholder="이름" maxlength="15"  v-model="cmname">
				</div>
			</div>
			<div class="form-group" id="divAddress">
				<label for="inputAddress" class="col-lg-2 control-label">주소</label>
				<div class="col-lg-10">
					<input type="text" class="form-control" id="addr" name="addr"
						data-rule-required="true" placeholder="주소" maxlength="50"  v-model="caddr">
				</div>
			</div>
			<div class="form-group" id="divPhoneNumber">
				<label for="inputPhoneNumber" class="col-lg-2 control-label">전화번호</label>
				<div class="col-lg-10">
					<input type="tel" class="form-control onlyNumber" id="tel" name="tel"
						data-rule-required="true" placeholder="010-xxx-xxxx"
						maxlength="11"  v-model="ctel">
				</div>
			</div>
			<div class="form-group" id="divAllergy">
				<label for="inputAllergy" class="col-lg-2 control-label">알레르기</label>
				<div class="col-lg-10">
					<fieldset>
						
						<label class="checkbox-inline"><input type="checkbox" name="allergy"
							value="대두"  v-model="carr">대두</label> <label class="checkbox-inline"><input
							type="checkbox" name="allergy" value="땅콩" v-model="carr">땅콩</label> <label
							class="checkbox-inline"><input type="checkbox" name="allergy" value="우유"  v-model="carr">우유</label>
						<label class="checkbox-inline"><input type="checkbox" name="allergy"
							value="게"  v-model="carr">게</label> <br> <label class="checkbox-inline"><input
							type="checkbox" name="allergy" value="새우"  v-model="carr">새우</label> <label
							class="checkbox-inline"><input type="checkbox" name="allergy" value="참치"  v-model="carr">참치</label>
						<label class="checkbox-inline"><input type="checkbox" name="allergy"
							value="연어"  v-model="carr">연어</label> <label class="checkbox-inline"><input
							type="checkbox" name="allergy" value="쑥"  v-model="carr">쑥</label> <br> <label
							class="checkbox-inline"><input type="checkbox" name="allergy"
							value="소고기"  v-model="carr">소고기</label> <label class="checkbox-inline"><input
							type="checkbox" name="allergy" value="닭고기"  v-model="carr">닭고기</label> <label
							class="checkbox-inline"><input type="checkbox" name="allergy"
							value="돼지고기"  v-model="carr">돼지고기</label> <br> <label
							class="checkbox-inline"><input type="checkbox" name="allergy"
							value="복숭아"  v-model="carr">복숭아</label> <label class="checkbox-inline"><input
							type="checkbox" name="allergy" value="민들레"  v-model="carr">민들레</label> <label
							class="checkbox-inline"><input type="checkbox" name="allergy"
							value="계란흰자"  v-model="carr">계란흰자</label>

					</fieldset>

				</div>
			</div>
			<div class= "form-group" id="userCalorie">
				<label for="inputCalorie" class="col-lg-2">일일 목표 칼로리</label>
				<div class="col-lg-10">
					<input type="text" class="form-control" id="calorie" name="calorie"
						placeholder="목표 칼로리" maxlength="50"  v-model="ccal" style="width:200px;">
				</div>	
			</div>
			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<button type="submit" class="btn btn-primary">수정하기</button>
				</div>
			</div>
		</form>
	</div>
</div>
</template>
<script>
import http from "../http-common";

export default {
	name: "updatemem",
	data() {
		return {
			loading: true,
            errored: false,	
            cid:"",
            cpassword:"",
            cmname:"",
            caddr:"",
            ctel:"",
            callergy:"",
            cquestion:"",
            canswer:"",
			carr: [],
			ccal: "",
			member:[],
			submitted: false
		};
	},
		mounted () {
			this.infomem();
	},

	methods: {
		showlist() {      
			this.$router.push("/");
	},
	infomem(){
		http
		.get("http://localhost:8090/api/memlist/"+localStorage.getItem("id"))
        .then(response => {
			this.member = response.data.resvalue;
			this.cid = this.member.id;
            this.cpassword = this.member.password;
            this.cmname = this.member.mname;
            this.caddr = this.member.addr;
			this.ctel = this.member.tel;
			this.ccal = this.member.calorie_goal;
			this.carr = this.member.allergyArr;
			})
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
	},
	updatemem() {
		http.put('/memupdate', {
			id: this.cid,
            password: this.cpassword,
            mname: this.cmname,
            addr: this.caddr,
			tel: this.ctel,
			allergyArr: this.carr,
			calorie_goal: this.ccal
		} 
		).then(response => {
				if (response.data.state==0) {
					alert("회원정보 수정 완료.");
						this.showlist();
				}else{
					alert("회원정보 수정 성공.");
						this.showlist();
				}
		}).catch(() => {
			alert("fail");
          this.errored = true;
        });
		this.submitted = true;
	},

		
  }
}
</script>


<style scoped>
#outex{
    width: 500px;
    text-align: left;
    padding: 50px 650px;
    font-size: 20px;

}
.ex{
 display: inline-block;
 height:950px; 
 width:800px;
 background-color: white;
 opacity: 0.9;
}
</style>