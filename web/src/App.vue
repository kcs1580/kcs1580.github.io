<template>
    <div id="app">
        <header>
            <nav
                class="navbar navbar-inverse navbar-fixed-top justify-content-end"
                style="height:100px;">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a href="/">
                            <img
                                src="../public/ssafy_logo.png"
                                alt="logo"
                                style="width:80px;height:60px; ;"></a>
                            <router-link class="nav_btn" to="/">홈으로</router-link>
                            |
                            <router-link class="nav_btn" to="/listNotice">공지사항</router-link>
                            |
                            <router-link class="nav_btn" to="/productlist">상품정보</router-link>
                            |

                            <template v-if="!sessionCheck()">
                                <span class="dropdown" v-bind:class="loginPopup" style=" width:100px; ">
                                    <div
                                        class="btn dropdown-toggle"
                                        href="#"
                                        @click="loginPopupfunc()"
                                        data-toggle="dropdown">
                                        <span style="color:red;">
                                            <i class="fas fa-user fa-2x" style="color:black; "></i>
                                        </span>
                                    </div>
                                    <div class="dropdown-menu" style="padding: 17px; float:right;">
                                        <form @submit.prevent="loginfunc()">
                                            <div class="form-group">
                                                <label for="id">아이디</label>
                                                <input
                                                    type="text"
                                                    v-model="id"
                                                    class="form-control"
                                                    id="id"
                                                    name="id"
                                                    placeholder="ID"
                                                    autocomplete="off"></div>
                                                <div class="form-group">
                                                    <label for="pw">패스워드</label>
                                                    <input
                                                        type="password"
                                                        v-model="pw"
                                                        class="form-control"
                                                        id="pw"
                                                        name="pw"
                                                        placeholder="Password"></div>
                                                    <div class="form-group">
                                                        <button type="submit" class="btn btn-block btn-primary">로그인</button>
                                                        <template v-if="!sessionCheck()">
                                                            <router-link class="btn btn-block btn-success" to="/signuppage">회원가입</router-link>
                                                        </template>
                                                    </div>
                                                </form>

                                            </div>
                                        </span>
                                    </template>
                                    <template v-if="sessionCheck()">
                                        <router-link class="nav_btn" to="/listBoard">질문게시판</router-link>
                                        |
                                        <router-link class="nav_btn" to="/mytakeinfo">섭취정보</router-link>

                                        <div style="float: right; margin-left:700px; ">
                                            <template v-if="sessionCheck()">
                                                <p
                                                    class="glyphicon glyphicon-user"
                                                    id="loginUser"
                                                    style="color:black; font-size:15px; ">
                                                    <i class="fas fa-user-plus"></i>
                                                    ID:
                                                    {{userInfo}}</p>
                                                <button @click="logoutfunc">
                                                    <a class="btn btn-sm btn-dark">
                                                        <span >&nbsp;Logout</span>
                                                    </a>
                                                </button>
                                                <button @click="memupdatefunc">
                                                    <a class="btn btn-sm btn-dark">
                                                        <span >&nbsp;회원정보 수정</span>
                                                    </a>
                                                </button>
                                            </template>

                                        </div>

                                    </template>

                                </div>
                            </div>
                        </nav>
                    </header>
                    <!-- <div style="height:100px;"></div> -->
                    <span>
                        <div class="row" style="margin-top:100px;">

                            <router-view/>
                            <template v-if="pageCheck()">
                                <span v-if="sessionCheck()" style="margin-right:100px;">
                                    <div
                                        style="width:500px; height:550px;margin-top:50px; background:rgba(255,255,255,0.7);">
                                        <div
                                            class="panel-heading"
                                            style="text-align:center; font-weight: bold; font-size:20px; padding: 5px;background:#b6b9bb ">실시간 채팅</div>
                                        <br>
                                            <div id="send">
                                                <span class="form-group">
                                                    <span style="float: left; width:70px; "></span>
                                                    <span style="float: left;">
                                                        <input
                                                            data-msg="내용"
                                                            type="text"
                                                            v-model="iccontent"
                                                            class="form-control"
                                                            style=" width:250px; margin-left:5px;"
                                                            placeholder=" 내용을 입력해주세요."></span>

                                                    </span>
                                                    <span class="row">
                                                        <div class="col-sm-12 text-right">
                                                            <div style="float:left;">
                                                                <div class="btn btn-primary" style="width:70px;" @click="insertchat()">보내기</div>
                                                            </div>

                                                        </div>
                                                    </span>

                                                </div>
                                                <hr>
                                                    <div
                                                        v-for="com in list"
                                                        class="row productRow"
                                                        :key="com.cnum"
                                                        style="display:block;  margin: 10px;">

                                                        <div class="container" style="display:block;">
                                                            <div class="row">
                                                                <div class="content" style="border:none; float:left; font-size:20px; ">

                                                                    {{com.user_name}}
                                                                    :
                                                                    {{com.ccontent }}

                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>

                                            </span>
                                        </template>
                                    </div>

                                </span>
                                <footer style="background: #ececec; padding: 50px;">
                                    <h1>Find Us</h1>
                                    <hr>
                                        <p>
                                            <i class="glyphicon glyphicon-envelope"></i>
                                            (SSAFY) 서울시 강남구 테헤란로 멀티스퀘어
                                        </p>
                                        <p>
                                            <i class="glyphicon glyphicon-envelope"></i>
                                            1544-9001
                                        </p>
                                        <p>
                                            <i class="glyphicon glyphicon-envelope"></i>
                                            admin@ssafy.com
                                        </p>
                                    </footer>
                                </div>
                            </template>

                            <script>
                                import http from "./http-common";
                                import {mapActions} from "vuex"
                                import Store from "./store"
                                global.jQuery = require('jquery')
                                export default {
                                    name: "app",
                                    data() {
                                        // active: 'home';
                                        return {
                                            id: null,
                                            pw: null,
                                            loginPopup: false,
                                            upHere: false,
                                            board: [],
                                            loading: true,
                                            errored: false,
                                            list: [],
                                            timer: ''
                                        }
                                    },
                                    computed: {
                                        // ...mapState(['isLogin', 'isLoginError', 'userInfo']),
                                        isLogin() {
                                            return Store.state.isLogin;
                                        },
                                        isLoginError() {
                                            return Store.state.isLoginError;
                                        },
                                        userInfo() {
                                            return localStorage.getItem("id");
                                        }

                                    },
                                    methods: {
                                        makeActive: function (item) {
                                            this.active = item;
                                        },
                                        ...mapActions(['login']),
                                        loginfunc() {
                                            if (this.id == '') {
                                                alert('아이디를 입력하세요.');
                                                return;
                                            }
                                            if (this.pw == '') {
                                                alert('비밀번호를 입력하세요.');
                                                return;
                                            }

                                            window
                                                .console
                                                .log({id: this.id, pw: this.pw})
                                            //this.login({id: this.id, pw: this.pw})
                                            Store.dispatch('login', {
                                                id: this.id,
                                                pw: this.pw
                                            })
                                            this.id = ''
                                            this.pw = null
                                            this.loginPopupfunc()
                                            this.isLogin = true;
                                            this.userInfo = this.id;

                                        },
                                        pageCheck() {

                                            if (window.location.pathname != '/' && window.location.pathname != '/updatemem' && window.location.pathname != '/signuppage') 
                                                return true;
                                            else 
                                                return false;

                                            }
                                        ,
                                        sessionCheck() {
                                            if (localStorage.getItem("id") != null) 
                                                return true;
                                            
else 
                                                return false;
                                            }
                                        ,
                                        memupdatefunc() {
                                            this
                                                .$router
                                                .push('/updatemem');
                                        },
                                        logoutfunc() {
                                            Store.dispatch('logout');
                                            this.id = ''
                                            this.pw = null
                                            this.isLogin = false;
                                        },
                                        loginPopupfunc() {
                                            this.loginPopup = (
                                                this.loginPopup
                                                    ? false
                                                    : true
                                            )
                                            window
                                                .console
                                                .log(this.loginPopup)
                                        },
                                        autoreload: function () {

                                            this.getchat()

                                        },
                                        insertchat() {
                                            if (this.iccontent == '') {
                                                alert('내용을 입력하세요.');
                                                return;
                                            }

                                            http
                                                .post('/registerchat', {
                                                    cnum: 0,
                                                    ccontent: this.iccontent,
                                                    user_id: localStorage.getItem("id"),
                                                    user_name: ''
                                                })
                                                .then(response => {
                                                    if (response.data.resCode == 'succ') {

                                                        this.iccontent = '';
                                                        this.getchat();
                                                    } else {
                                                        alert("댓글등록 실패");
                                                    }
                                                });
                                            this.submitted = true;
                                        },
                                        getchat() {
                                            http
                                                .get("/listchat")
                                                .then(response => {
                                                    this.list = response.data.resvalue;
                                                })
                                                .catch(() => {
                                                   
                                                    this.errored = true;
                                                })
                                                . finally(() => (this.loading = false));

                                        }
                                    },
                                    filters: {},
                                    created() {
                                        // http     .post('/registerchat', {         cnum: 0,         ccontent:
                                        // localStorage.getItem("id") + '님이 입장하셨습니다.',         user_id:
                                        // localStorage.getItem("id"),         user_name: ''     })     .then(response
                                        // => {         if (response.data.resCode == 'succ') {
                                        // this.iccontent = '';             this.getchat();         } else {
                                        // alert("댓글등록 실패");         }     }); this.submitted = true;
                                        this.autoreload();
                                        this.timer = setInterval(this.autoreload, 300);

                                    },

                                    mounted() {
                                        this.isLogin = false;
                                    }
                                }
                            </script>
                            <style>
                                #app {
                                    background-image: url("../public/bg.jpg");
                                }
                                @font-face {
                                    font-family: 'BBTreeGL';
                                    src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_nine_@1.1/BBTreeGL.woff") format('woff');
                                    font-weight: normal;
                                    font-style: normal;
                                }
                                html * {
                                    font-family: 'BBTreeGL';
                                }

                                .nav_btn {
                                    color: black;
                                    font-size: 20px;
                                }
                                #loginUser {
                                    color: white;
                                    padding: 15px;
                                    margin: 0;
                                }

                                .material {
                                    overflow: hidden;
                                    text-overflow: ellipsis;
                                    height: 120px;
                                }

                                .jumbotron {
                                    background-color: #eef8ff;
                                    text-align: center;
                                    padding: 20px;
                                    box-shadow: 0 10px 6px -6px #999;
                                }

                                div.ex {
                                    background-color: #ccc;
                                    width: 600px;
                                    padding: 50px;
                                    border-style: outset;
                                    margin: 70px auto;
                                }

                                .form-group .control-label:after {
                                    content: "*";
                                    color: red;
                                }

                                td,
                                th {
                                    vertical-align: middle !important;
                                }

                                th {
                                    white-space: nowrap;
                                    text-align: center;
                                }

                                input[type=password] {
                                    font-family: auto;
                                }

                                #loginUser {
                                    color: white;
                                    padding: 15px;
                                    margin: 0;
                                }

                                .dropdown-menu {
                                    width: 350px;
                                    color: black;
                                }

                                body,
                                html {
                                    height: 100%;
                                }

                                .container {
                                    min-height: 70%;
                                }

                                * {
                                    margin: 0;
                                    padding: 0;
                                }

                                a,
                                a:visited {
                                    outline: none;
                                    color: rgba(255, 255, 255,0.8);
                                }

                                a:hover {
                                    text-decoration: none;
                                }

                                aside,
                                footer,
                                header,
                                nav,
                                section {
                                    display: block;
                                }

                                nav {
                                    display: inline-block;
                                    margin: 120px auto 100px;
                                    background-color: rgba(0, 0, 0,0.4);
                                    box-shadow: 0 1px 1px #ccc;
                                    border-radius: 2px;
                                    margin: 0 auto;
                                }

                                nav a {
                                    display: inline-block;
                                    padding: 18px 20px;
                                    color: #fff !important;
                                    font-weight: bold;
                                    font-size: 10px;
                                    text-decoration: none !important;
                                    line-height: 1;
                                    text-transform: uppercase;
                                    background-color: transparent;
                                    text-align: center;
                                    margin: 0 auto;
                                    -webkit-transition: background-color 0.25s;
                                    -moz-transition: background-color 0.25s;
                                    transition: background-color 0.25s;
                                }

                                nav a:first-child {
                                    border-radius: 2px 0 0 2px;
                                }

                                nav a:last-child {
                                    border-radius: 0 2px 2px 0;
                                }

                                p {
                                    font-size: 18px;
                                    font-weight: bold;
                                    color: #7d9098;
                                }

                                p b {
                                    color: #ffffff;
                                    display: inline-block;
                                    padding: 5px 10px;
                                    background-color: #c4d7e0;
                                    border-radius: 2px;
                                    text-transform: uppercase;
                                    font-size: 15px;
                                }
                                .resource {
                                    margin: 20px 0;
                                }
                            </style>