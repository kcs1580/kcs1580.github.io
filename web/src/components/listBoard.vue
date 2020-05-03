<template>
    <div class="container">
        <h1 style="text-align: center; font-size: 3em; margin-bottom;">Q&amp;A</h1>
        <hr style="margin-bottom: 40px;">

            <div
                id="my_form"
                class="form-inline justify-content-center search-form rounded"
                method="post"
                action="qnaSearch.do?page=1">
                <div class="form-group mb-2">
                    <select
                        v-model="selected"
                        class="form-control"
                        name='searchOpt'
                        id="search-option">
                        <option value="q_title" selected="selected">제목</option>
                        <option value="q_name">작성자</option>
                    </select>
                </div>

                <div class="form-group mx-sm-3 mb-2">
                    <input
                        type="text"
                        v-model="keyword"
                        class="form-control"
                        id="name"
                        name='keyword'
                        placeholder="키워드"
                        @keyup.enter="searchboard">
                        </div>

                    <div @click="searchboard()" class="btn btn-info mb-2" style=" float:left;">검색</div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-body" style="background-color: rgba( 255, 255, 255,0.5 );">
                        <table class="table text-center table-bordered table-hover">
                            <thead>
                                <tr style="background:#b6b9bb;">
                                    <th>번호</th>
                                    <th>제목</th>
                                    <th>작성자</th>
                                    <th>조회수</th>
                                </tr>
                            </thead>
                            <tr v-for="board in list" :key="board.bid">
                                <td v-html="board.bid"></td>
                                <td v-html="board.btitle" @click="show_detail(board.bid)"></td>
                                <td v-html="board.user_name"></td>
                                <td v-html="board.bcount"></td>

                            </tr>

                        </table>
                        <div class="row">
                            <div class="col-sm-12 text-right">
                                <a href="" @click="showinsert()" class="btn btn-info btn-flat">글쓰기</a>

                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </template>

        <script>
            import http from "../http-common";
            import App from "../App.vue";
            export default {
                name: "board-list",
                data() {
                    return {
                        upHere: false,
                        list: [],
                        loading: true,
                        errored: false,
                        keyword: '',
                        selected: 'q_title'
                    };
                },
                methods: {
                    searchboard() {
                        if (this.keyword == "") {
                            http
                            .get("/listboard")
                            .then(response => (this.list = response.data['resvalue']))
                            .catch(() => {
                                this.errored = true;
                            })
                            . finally(() => (this.loading = false));
                        } else {
                            http
                                .get("/searchBoardByKeyword/" + this.selected + "/" + this.keyword)
                                .then(response => (this.list = response.data['list']))
                                .catch(() => {
                                    this.errored = true;
                                })
                                . finally(() => (this.loading = false));
                        }
                    },
                    showinsert() {
                        this
                            .$router
                            .push("/insertboard");
                    },
                    retrieveBoards() {
                        http
                            .get("/listboard")
                            .then(response => (this.list = response.data['resvalue']))
                            .catch(() => {
                                this.errored = true;
                            })
                            . finally(() => (this.loading = false));
                    },
                    refreshList() {
                        this.retrieveBoards();
                    },
                    show_detail: function (bid) {
                        //alert(bid + " 클릭했음");
                        App.bid = bid;
                        this
                            .$router
                            .push({
                                name: 'viewBoard',
                                params: {
                                    bid: App.bid
                                }
                            });
                    }
                },
                filters: {},
                mounted() {
                    this.retrieveBoards();
                }
            };
        </script>

        <style></style>