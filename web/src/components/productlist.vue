<template>
    <div class="container">
        <div
            class="col-xs-12"
            style=" top:20px; text-align: right; margin-bottom: 15px; display:block; float:right">
            <input type="hidden"/>
            <div class="form-group" style="width:120px; float:left;">
                <select v-model="selected" class="form-control" placeholder="선택">
                    <option value="name">상품명</option>
                    <option value="maker">제조사</option>
                    <option value="material">재료명</option>
                </select>
            </div>
            <div style="width:20px; float:left;"></div>
            <div class="form-group" style="width:900px;  float:left;">
                <input
                    type="text"
                    name="keyword"
                    v-model="keyword"
                    class="form-control"
                    placeholder="검색어를 입력하세요."
                    @keyup.enter="searchfood"></div>
                <div
                    @click="searchfood()"
                    class="btn btn-info btn-warning"
                    style=" float:left;">검색</div>

            </div>
            <div
                class="panel panel productPanel"
                style=" width:100%; height:2500px; background-color: rgba( 255, 255, 255,0.3 ); ">
                <span
                    v-for="item in list"
                    class="row productRow"
                    :key="item.code"
                    style="display:block;  margin: 50px;">
                    <div class="container" style="display:block;">
                        <div class="content" style="border:none; float:left;">
                            <a @click="fooddetail(item.code)">
                                <div class="content-overlay"></div>
                                <img :src="require('../' + item.img)" alt="item.name" style="width:300px;">
                                    <div class="content-details fadeIn-bottom">
                                        <h3 class="content-title">{{item.name}}
                                            <br>자세히보기</h3>
                                        </div>
                                        <a
                                            class="btn btn-lg btn-warning"
                                            @click="fooddetail(item.code)"
                                            style="text-align: center;color:white; font-weight:bold;  width:280px;">{{item.name}}
                                            &nbsp;
                                        </a>

                                    </a>
                                </div>
                            </div>
                        </span>
                    </div>

                </div>

            </template>

            <script>
                import http from "../http-common";
                import App from "../App.vue";
                export default {
                    name: "productlist",
                    data() {
                        return {
                            upHere: false,
                            list: [],
                            loading: true,
                            errored: false,
                            keyword: '',
                            selected: 'name'
                        };
                    },
                    methods: {
                        searchfood() {
                            if (this.keyword == "") {
                                http
                                    .get("/listfood")
                                    .then(response => (this.list = response.data['list']))
                                    .catch(() => {
                                        this.errored = true;
                                    })
                                    . finally(() => (this.loading = false));
                            } else {
                                http
                                    .get("/searchFoodByKeyword/" + this.selected + "/" + this.keyword)
                                    .then(response => (this.list = response.data['list']))
                                    .catch(() => {
                                        this.errored = true;
                                    })
                                    . finally(() => (this.loading = false));
                            }
                        },

                        fooddetail: function (ccode) {
                            App.code = ccode;
                            this
                                .$router
                                .push({
                                    name: 'productdetail',
                                    params: {
                                        code: ccode
                                    }
                                });
                        }
                    },
                    filters: {},
                    mounted() {

                        http
                            .get("/listfood")
                            .then(response => (this.list = response.data['list']))
                            .catch(() => {
                                this.errored = true;
                            })
                            . finally(() => (this.loading = false));
                    }
                };
            </script>

            <style>
                .content {
                    position: relative;
                    width: 100%;
                    max-width: 300px;
                    margin: auto;
                    overflow: hidden;
                }

                .content .content-overlay {
                    background: rgba(0,0,0,0.7);
                    position: absolute;
                    height: 99%;
                    width: 100%;
                    left: 0;
                    top: 0;
                    bottom: 0;
                    right: 0;
                    opacity: 0;
                    -webkit-transition: all 0.4s ease-in-out 0s;
                    -moz-transition: all 0.4s ease-in-out 0s;
                    transition: all 0.4s ease-in-out 0s;
                }

                .content:hover .content-overlay {
                    opacity: 1;
                }

                .content-image {
                    width: 50%;
                }

                .content-details {
                    position: absolute;
                    text-align: center;
                    padding-left: 1em;
                    padding-right: 1em;
                    width: 100%;
                    top: 50%;
                    left: 50%;
                    opacity: 0;
                    -webkit-transform: translate(-50%, -50%);
                    -moz-transform: translate(-50%, -50%);
                    transform: translate(-50%, -50%);
                    -webkit-transition: all 0.3s ease-in-out 0s;
                    -moz-transition: all 0.3s ease-in-out 0s;
                    transition: all 0.3s ease-in-out 0s;
                }

                .content:hover .content-details {
                    top: 50%;
                    left: 50%;
                    opacity: 1;
                }

                .content-details h3 {
                    color: #fff;
                    font-weight: 500;
                    letter-spacing: 0.15em;
                    margin-bottom: 0.5em;
                    text-transform: uppercase;
                }

                .content-details p {
                    color: #fff;
                    font-size: 0.8em;
                }

                .fadeIn-bottom {
                    top: 80%;
                }
            </style>