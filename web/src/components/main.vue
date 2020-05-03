<template class="a">
    <div class="container">
        <div class="im">
            <transition-group
                name="fade"
                v-on:after-enter="fadeNext"
                tag="div"
                class="text-center">
                <img v-show="fade[0]" key="0" class="im" src="../../public/food.jpg">
                    <img v-show="fade[1]" key="1" class="im" src="../../public/food2.jpg">
                        <img v-show="fade[2]" key="2" class="im" src="../../public/food3.jpg">
                            <img v-show="fade[3]" key="3" class="im" src="../../public/food4.jpg">
                                <img v-show="fade[4]" key="4" class="im" src="../../public/food.jpg"></transition-group>
                            </div>
                            <!-- <img src="../../public/food.jpg" id="bg" alt=""> -->
                            <nav class="navbar">

                                <template v-if="!sessionCheck()">
                                    <span class="dropdown" v-bind:class="loginPopup" style=" width:100px; ">
                                        <div
                                            class="btn dropdown-toggle"
                                            href="#"
                                            @click="loginPopupfunc()"
                                            data-toggle="dropdown">
                                            <span > <i class="fas fa-user fa-2x" style="color:white; float:left;"></i> 
                                            <p style="color:white;"> 로그인</p>
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
                                            <div style="float: right;">
                                                <p
                                                    class="glyphicon glyphicon-user"
                                                    id="loginUser"
                                                    style="color:white; font-size:15px; ">
                                                    <i class="fas fa-user-plus fa-2x"></i>
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
                                            </div>
                                        </template>

                                        <button
                                            @click="modal_recommend "
                                            type="button"
                                            class="btn btn-link"
                                            style="color:white; font-size:25px;">
                                            <i class="fas fa-utensils"></i>
                                            오늘 뭐먹징?</button>

                                    </nav>

                                    <div
                                        v-show="is_show"
                                        style="background:rgba( 255, 255, 255,0.6 ); opacity:0.9; height:auto; width:auto; float:right">
                                        <img :src="require('../' + recommend.img)" width="200">
                                            <table class="table table-hover">
                                                <tr>
                                                    <th>제품명</th>
                                                    <td id="name" v-html="recommend.name"></td>
                                                </tr>
                                                <tr>
                                                    <th>제조사</th>
                                                    <td id="maker" v-html="recommend.maker"></td>
                                                </tr>

                                            </table>
                                            <div>
                                                <button @click="modalbtn" class="btn btn-danger" style="float:right;">닫기</button>
                                            </div>

                                        </div>
                                        <router-link class="nav_btn" to="/banner">공지사항</router-link>
                                        |

                                        <vue-3d-menu
                                            style="min-width:500px; font-size:500px; "
                                            :title="'MENU'"
                                            :items="items"></vue-3d-menu>

                                        <router-view/>

                                    </div>

                                </template>
                                <script>
                                    import Vue from 'vue';
                                    import Vue3dMenu from 'vue-3d-menu';
                                    import axios from 'axios'
                                    import Store from "../store"
                                    import {mapActions} from "vuex"
                                    import http from "../http-common";

                                    Vue.use(Vue3dMenu);
                                    let fadeIdx = 0
                                    export default {
                                        name: 'App',

                                        data() { // #1
                                            return {
                                                id: null,
                                                pw: null,
                                                loginPopup: false,
                                                loading: false,
                                                is_show: false,
                                                recommend: [],
                                                fade: [
                                                    false, false, false, false, false
                                                ],
                                                items: [
                                                    {
                                                        title: 'WELCOME!',
                                                        click: () => alert("환영합니다:) ")
                                                    }, {
                                                        title: '공지사항',
                                                        click: () => this.go("공지사항")
                                                    }, {
                                                        title: '상품정보',
                                                        click: () => this.go("상품정보")
                                                    },
                                                    // {
                                                    //     title: '섭취정보',
                                                    //     click: () => this.go("섭취정보")
                                                    // },
                                                     {
                                                        title: '질문게시판',
                                                        click: () => this.go("질문게시판")
                                                    }, {
                                                        title: '회원가입',
                                                        click: () => this.go("회원가입")
                                                    }
                                                ]
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
                                            fadeNext: function () {
                                                this
                                                    .fade
                                                    .splice(fadeIdx++, 1, true)
                                            },
                                            go(page) {
                                                if (page == "공지사항") {
                                                    this
                                                        .$router
                                                        .push("/listNotice")
                                                } else if (page == "상품정보") {
                                                    this
                                                        .$router
                                                        .push("/productlist")
                                                } else if (page == "섭취정보") {
                                                    this
                                                        .$router
                                                        .push("/mytakeinfo")
                                                } else if (page == "질문게시판") {
                                                    this
                                                        .$router
                                                        .push("/listBoard")
                                                } else if (page == "회원가입") {
                                                    this
                                                        .$router
                                                        .push("/signuppage")
                                                }
                                            },

                                            modalbtn: function () {
                                                this.is_show = !this.is_show; // #2, #3

                                            },
                                            modal_recommend: function () {
                                                axios
                                                    .get("http://localhost:8090/api/recommendFood")
                                                    .then(response => {
                                                        this.recommend = response.data.recommend;
                                                        //alert(this.recommend.img);
                                                        this.is_show = true;
                                                    })
                                                    .catch(() => {
                                                        this.errored = true
                                                    })
                                                    . finally(() => this.loading = false)
                                            },
                                            init: function () {
                                                axios
                                                    .get("http://localhost:8090/api/recommendFood")
                                                    .then(response => {
                                                        this.recommend = response.data.recommend;
                                                        this.is_show = false;
                                                    })
                                                    .catch(() => {
                                                        this.errored = true
                                                    })
                                                    . finally(() => this.loading = false)
                                            },
                                            show_banner: function () {
                                                this
                                                    .$router
                                                    .push("/banner");
                                            },
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
                                                this
                                                    .$router
                                                    .push('/');
                                            },
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
                                            }
                                        },
                                        mounted() {
                                            this.isLogin = false;
                                            http.get('/countfood')
                                            .catch(() => {
                                                        this.errored = true
                                                    })
                                                    . finally(() => this.loading = false)

                                            setTimeout(this.fadeNext, 0.5);
                                            this.init();
                                        }
                                    }
                                </script>
                                <style>
                                    /* 배경화면 조정하는 css #bg랑 body, html */
                                    #bg {
                                        position: fixed;
                                        top: 0;
                                        left: 0;
                                        opacity: 1;
                                        /* Preserve aspet ratio */
                                        min-width: 100%;
                                        min-height: 100%;
                                    }
                                    .im {
                                        position: fixed;
                                        top: 0;
                                        left: 20;
                                        min-width: 100%;
                                        min-height: 100%;
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

                                    /* body,
                html {
                    margin: 0;
                    padding: 0;
                    height: 80%;
                } */

                                    .nav_btn1 {
                                        color: white;
                                        font-size: 30px;
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
                                    .modal-mask {
                                        position: fixed;
                                        z-index: 9998;
                                        top: 0;
                                        left: 300;
                                        width: 100%;
                                        height: 100%;
                                        background-color: rgba(0, 0, 0, .5);
                                        display: table;
                                        transition: opacity 0.3s ease;
                                    }

                                    .modal-wrapper {
                                        display: table-cell;
                                        vertical-align: middle;
                                    }

                                    .modal-container {
                                        width: 300px;
                                        margin: 0 auto;
                                        padding: 20px 30px;
                                        background-color: #fff;
                                        border-radius: 2px;
                                        box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
                                        transition: all 0.3s ease;
                                        font-family: Helvetica, Arial, sans-serif;
                                    }

                                    #appp {
                                        font-family: "Avenir", Helvetica, Arial, sans-serif;
                                        -webkit-font-smoothing: antialiased;
                                        -moz-osx-font-smoothing: grayscale;
                                        text-align: center;
                                        color: #2c3e50;
                                        margin: 0;
                                        width: 100px;
                                        height: 100px;
                                        box-sizing: border-box;
                                        background-color: rgba(0, 0, 0, 0.3);
                                        position: absolute;
                                        top: 200px;
                                        left: 500px;
                                    }

                                    .fade-enter {
                                        opacity: 0;
                                    }

                                    .fade-enter-active {
                                        transition: opacity 3.0s;
                                    }
                                </style>