<template>
    <div class="container">

        <h1 style="text-align: center; font-size: 3em; margin-top:40px;">Q&amp;A</h1>
        <hr style="margin-bottom: 40px;">
            <div
                class="panel panel-info"
                style="background-color: rgba( 255, 255, 255,0.8 ); height: 100%;">
                <div
                    class="panel-heading"
                    style="text-align:center; font-weight: bold;  padding: 20px;background:#b6b9bb ">
                      <h3><i class="fab fa-quora"></i>
                    [질문 no.
                    {{board.bid}}]
                    {{board.btitle }}
                    </h3></div>
                <br>
                    <div style="float:right;">
                        작성자:
                        {{board.user_name}}
                        <span style="margin: 5px;"></span>
                        조회수:
                        {{board.bcount}}
                        <span style="margin: 5px;"></span>

                    </div>
                    <br>
                        <hr>
                            <div class="panel-body" style="height:400px; font-size:20px; padding:40px; ">
                                {{board.bcontent }}
                            </div>
                            <hr>

                                <div
                                    v-for="com in list"
                                    class="row productRow"
                                    :key="com.cnum"
                                    style="display:block;  margin: 50px;">
                                    <hr color="lightgrey" width="50%" align="left">
                                    <div class="container" style="display:block;">
                                        <div
                                            class="container"
                                            style="border:none; font-size:20px; font-weight:bold;">
                                            <i class="fab fa-replyd fa-2x"></i>
                                            [{{com.user_name}}]
                                            :
                                            {{com.ccontent }}
                                    <template v-if="cusercheck(com.user_id)">
                                        <button class="btn btn-outline-danger " @click="removecomment(com.cnum)" >삭제</button>
                                    </template>
                                        </div>
                                    </div>
                                   
                                </div>
                                <br>
                                    <br>
                                        <br>
                                            <hr>
                                                <div>

                                                    <span class="form-group">
                                                        <span style="float: left; width:70px;"></span>
                                                        <span style="float: left;">
                                                            <input
                                                                data-msg="내용"
                                                                type="text"
                                                                v-model="iccontent"
                                                                class="form-control"
                                                                style="height:50px; width:800px; margin-left:20px;"
                                                                placeholder=" 내용을 입력해주세요."></span>

                                                        </span>
                                                        <div class="row">
                                                            <div class="col-sm-12 text-right">

                                                                <div style="float:left;">
                                                                    <div class="btn btn-primary" style="width:60px;" @click="insertcomment()">등록</div>
                                                                </div>

                                                            </div>
                                                        </div>

                                                    </div>
                                                    <br>
                                                        <div class="panel-footer text-right">
                                                            <template v-if="usercheck()">
                                                                <button class="btn btn-success" @click="edit()" style="width:200px;">수정</button>
                                                                <button class="btn btn-danger" @click="remove()" style="width:200px;">삭제</button>
                                                            </template>
                                                            <button class="btn btn-dark" @click="backtolist()" style="width:200px;">목록</button>
                                                        </div>
                                                    </div>

                                                </div>
                                            </template>
                                            <script>
                                                import http from "../http-common";

                                                export default {
                                                    props: ['bid'],
                                                    name: "viewBoard",
                                                    data() {
                                                        return {upHere: false, board: [], loading: true, errored: false, list: []};
                                                    },
                                                    methods: {
                                                        getname(id) {
                                                            http
                                                                .get('/memlist/' + id)
                                                                .then(response => {
                                                                    this.name = response.data.mem.mname;
                                                                });
                                                            alert(this.name);

                                                        },
                                                        insertcomment() {
                                                            if (this.iccontent == '') {
                                                                alert('내용을 입력하세요.');
                                                                return;
                                                            }

                                                            http
                                                                .post('/registercomment', {
                                                                    cnum: 0,
                                                                    bid: this.bid,
                                                                    ccontent: this.iccontent,
                                                                    user_id: localStorage.getItem("id"),
                                                                    user_name: ''
                                                                })
                                                                .then(response => {
                                                                    if (response.data.resCode == 'succ') {
                                                                        alert("댓글등록 완료.");
                                                                        this.iccontent = '';
                                                                        this.getcomment();
                                                                    } else {
                                                                        alert("댓글등록 실패");

                                                                        this.getcomment();
                                                                    }
                                                                });
                                                            this.submitted = true;
                                                        },
                                                        getcomment() {
                                                            http
                                                                .get("/listcomment/" + this.bid)
                                                                .then(response => {
                                                                    this.list = response.data.resvalue;
                                                                })
                                                                .catch(() => {
                                                                    alert("fail");
                                                                    this.errored = true;
                                                                })
                                                                . finally(() => (this.loading = false));

                                                        },
                                                        removecomment(cnum) {

                                                            http
                                                                .delete("/deletecomment/" + cnum)
                                                                .then(response => {
                                                                    if (response.data.state == "succ") {
                                                                        alert("댓글 삭제 완료.");
                                                                        // this.retrieveCustomers();
                                                                    } else {
                                                                        alert("댓글 삭제 완료.");

                                                                    }
                                                                })
                                                                .catch(() => {
                                                                    this.errored = true;
                                                                })
                                                                . finally(() => {
                                                                    this.loading = false;

                                                                    this.getcomment();
                                                                });

                                                        },

                                                        usercheck() {
                                                            if (localStorage.getItem("id") == this.board.user_id) 
                                                                return true;
                                                            else 
                                                                return false;
                                                            }
                                                        ,
                                                        cusercheck(cid) {
                                                            if (localStorage.getItem("id") == cid) 
                                                                return true;
                                                            else 
                                                                return false;
                                                            }
                                                        ,
                                                        showlist() {
                                                            this
                                                                .$router
                                                                .push("/listBoard");
                                                        },
                                                        viewBoards() {
                                                            http
                                                                .get("http://localhost:8090/api/infoboard/" + this.bid)
                                                                .then(response => {
                                                                    this.board = response.data.resvalue;
                                                                })
                                                                .catch(() => {
                                                                    alert("fail");
                                                                    this.errored = true;
                                                                })
                                                                . finally(() => (this.loading = false));
                                                            this.getcomment();
                                                        },
                                                        edit() {
                                                            this
                                                                .$router
                                                                .push("/UpdateBoard");
                                                        },
                                                        remove() {
                                                            alert(this.bid + "번 게시물 삭제합니다.");
                                                            http
                                                                .delete("/deleteboard/" + this.bid + "/" + localStorage.getItem("id"))
                                                                .then(response => {
                                                                    if (response.data.state == "succ") {
                                                                        alert("게시글 삭제를 하였습니다.");
                                                                        this
                                                                            .$router
                                                                            .push("/listBoard");
                                                                        // this.retrieveCustomers();
                                                                    } else {
                                                                        alert("게시글 삭제를 하였습니다.");
                                                                        this
                                                                            .$router
                                                                            .push("/listBoard");
                                                                    }
                                                                })
                                                                .catch(() => {
                                                                    this.errored = true;
                                                                })
                                                                . finally(() => (this.loading = false));

                                                        },
                                                        backtolist() {
                                                            this.showlist();
                                                        }
                                                    },
                                                    filters: {},
                                                    mounted() {
                                                        this.viewBoards();
                                                    }
                                                };
                                            </script>
                                            <style></style>