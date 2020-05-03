import Vue from "vue"
import Vuex from "vuex"
//  import router from "./router"
import http from "./http-common";

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        userInfo: {
            id: '',
            name: '',
            addr: '',
            tel: '',
            allergy: ''
        },
        isLogin: false,
        isLoginError: false
    },
    mutations: {
        //로그인 성공
        loginSuccess(state, loginInfo) {
            state.isLogin = true
            state.isLoginError = false
            state.userInfo.id = loginInfo.id
            state.userInfo.name = loginInfo.mname
            state.userInfo.addr = loginInfo.addr
            state.userInfo.tel = loginInfo.tel
            state.userInfo.allergy = loginInfo
                .allergy
                localStorage
                .setItem("id", loginInfo.id)
            localStorage.setItem("name", loginInfo.mname)
            window
                .console
                .log(state.userInfo)
        },
        //로그인 실패
        loginError(state) {
            state.isLogin = false
            state.isLoginError = true
        },
        logout(state) {
            state.isLogin = false
            state.isLoginError = false
            state.userInfo = {
                id: '',
                name: '',
                addr: '',
                tel: '',
                allergy: ''
            }

            window
                .console
                .log("로그아웃")
        }
    },
    actions: {
        //로그인 시도
        login({
            commit
        }, loginObj) {
            http
                .get("/memlist/" + loginObj.id)
                .then(response => {
                    if (response.data.resvalue.password == loginObj.pw) {
                        commit("loginSuccess", response.data.resvalue)
                        // router.push("/").catch(err => {err})
                        alert(response.data.resvalue.mname + " 님이 로그인 하셨습니다.")
                        location.reload();
                    } else {
                        commit("loginError")
                        alert("아이디 또는 비밀번호를 확인해주세요.")
                    }
                })
                .catch(() => {
                    commit("loginError")
                    alert("아이디 또는 비밀번호를 확인해주세요.")
                })
            },
        logout({commit}) {
            //this.$session.destroy()
            alert("로그아웃 되었습니다.")
            localStorage.removeItem("id")
            localStorage.removeItem("name")
            commit("logout")
            location.reload();
            // router.push("/").catch(err => {err})
        }
    }
})