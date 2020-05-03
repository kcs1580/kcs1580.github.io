<template>
    <div class="container">
        <h1 style="text-align: center; font-size: 3em; margin-bottom:">제품 상세 정보</h1>
        <hr style="margin-bottom: 40px;">

            <div class="row productRow"  style=" background-color: rgba( 255, 255, 255,0.3 );">
                <div class="col-xs-12 col-sm-5 col-md-4 col-lg-3">
                    <img
                        class="media-object"
                        :src="require('../' + food.img)"
                        alt="food.name"
                        width="250"
                        style="margin: 0 auto;">
                                <h3 style="text-align:center; font-weight:bold"><i class="fas fa-search-plus"></i> : {{food.searchCount}} </h3>
                        </div>

                    <div class="col-xs-12 col-sm-7 col-md-8 col-lg-9">
                        <table class="table table-hover">
                            <tr>
                                <th>제품명</th>
                                <td id="product_name">{{food.name }}</td>
                            </tr>
                            <tr>
                                <th>제조사</th>
                                <td id="product_maker">{{food.maker }}</td>
                            </tr>
                            <tr>
                                <th>원재료</th>
                                <td>
                                    <p id="product_material">{{food.material }}</p>
                                </td>
                            </tr>
                        </table>

                        <div class="input-group" >
                    
                            
                                <br>
                                    <div class="input-group-btn" style="">
                                        <div
                                            id="insertintake"
                                            class="btn btn-flat btn-warning"
                                            role="button"
                                            @click="insertintake()">
                                             <span><i class="fas fa-shopping-cart fa-2x"></i></span>  담기
                                        </div>
                                       
                                    </div>
                                </div>
                                <div class="btn-group" role="group"></div>
                            </div>
                        </div>
                        <br>
                        <h1 style="text-align: center; font-size: 3em;">영양 정보</h1>

                        <hr style="margin-bottom: 40px;" >
                            <div class="row" >
                                <div class="col-sm-8"  style=" background-color: rgba( 255, 255, 255,0.3 );">
                                    <h1>
                                        영양정보 차트
                                    </h1>
                                    <canvas id="myChart2"></canvas>
                                </div>

                                <div class="col-sm-4"  style=" background-color: rgba( 255, 255, 255,0.3 );">
                                    <table class="table">
                                        <tr>
                                            <td>일일 제공량</td>
                                            <td id="SERVING_WT">{{food.supportpereat }}</td>
                                        </tr>

                                        <tr>
                                            <td>칼로리</td>
                                            <td id="NUTR_CONT1">{{food.calory }}</td>
                                        </tr>

                                        <tr>
                                            <td>탄수화물</td>
                                            <td id="NUTR_CONT2">{{food.carbo }}</td>
                                        </tr>

                                        <tr>
                                            <td>단백질</td>
                                            <td id="NUTR_CONT3">{{food.protein }}</td>
                                        </tr>

                                        <tr>
                                            <td>지방</td>
                                            <td id="NUTR_CONT4">{{food.fat }}</td>
                                        </tr>

                                        <tr>
                                            <td>당류</td>
                                            <td id="NUTR_CONT5">{{food.sugar }}</td>
                                        </tr>

                                        <tr>
                                            <td>나트륨</td>
                                            <td id="NUTR_CONT6">{{food.natrium }}</td>
                                        </tr>

                                        <tr>
                                            <td>콜레스테롤</td>
                                            <td id="NUTR_CONT7">{{food.chole }}</td>
                                        </tr>

                                        <tr>
                                            <td>포화 지방산</td>
                                            <td id="NUTR_CONT8">{{food.fattyacid }}</td>
                                        </tr>

                                        <tr>
                                            <td>트렌스지방</td>
                                            <td id="NUTR_CONT9">{{food.transfat }}</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </template>

                    <script>
                        import http from "../http-common";
                        // import App from "../App.vue";
                        import Chart from 'chart.js';
                        export default {
                            props: ['code'],
                            name: "productdetail",
                            data() {
                                return {
                                    upHere: false,
                                    food: [],
                                    list: [],
                                    sum: [],
                                    loading: true,
                                    errored: false,
                                    id: 'orora@com',
                                    icount: 0,
                                    ReceivedData: 'false'

                                };
                            },
                            computed: {
                                checkDataVar: function () {
                                    return this.ReceivedData;
                                }
                            },
                            methods: {
                            
                                dynamicColors() {
                                    var r = Math.floor(Math.random() * 255);
                                    var g = Math.floor(Math.random() * 255);
                                    var b = Math.floor(Math.random() * 255);
                                    return "rgba(" + r + "," + g + "," + b + ",0.8)";
                                },

                                infofood() {
                                    http
                                        .get("/infofood/" + this.code)
                                        .then(response => {
                                            this.food = response.data.food;
                                            this.ReceivedData = true;
                                            
                                        })
                                        .catch(() => {
                                            alert("fail" + this.code);
                                            this.errored = true;
                                        })
                                        . finally(() => (this.loading = false));
                                },
                                drawchart() {                                   
                                            const myChart2 = new Chart("myChart2", {
                                                type: 'doughnut',
                                                data: {
                                                    labels: [
                                                        "칼로리",
                                                        "탄수화물",
                                                        "단백질",
                                                        "지방",
                                                        "당류",
                                                        "나트륨",
                                                        "콜레스테롤",
                                                        "포화 지방산",
                                                        "트랜스지방"
                                                    ],
                                                    datasets: [
                                                        {
                                                            label: '# of Votes',
                                                            data: [
                                                                this.food.calory,
                                                                this.food.carbo,
                                                                this.food.protein,
                                                                this.food.fat,
                                                                this.food.sugar,
                                                                this.food.natrium,
                                                                this.food.chole,
                                                                this.food.fattyacid,
                                                                this.food.transfat
                                                            ],
                                                            backgroundColor: [
                                                                this.dynamicColors(),
                                                                this.dynamicColors(),
                                                                this.dynamicColors(),
                                                                this.dynamicColors(),
                                                                this.dynamicColors(),
                                                                this.dynamicColors(),
                                                                this.dynamicColors(),
                                                                this.dynamicColors(),
                                                                this.dynamicColors()
                                                            ]
                                                        }
                                                    ]
                                                },
                                                options: {
                                                    maintainAspectRatio: true,
                                                    scales: {
                                                        yAxes: [
                                                            {
                                                                ticks: {
                                                                    beginAtZero: true
                                                                }
                                                            }
                                                        ]
                                                    }
                                                }
                                            });
                                            myChart2;

                                       
                                },
                                insertintake() {
                                    this.user_id = localStorage.getItem("id");
                                    http
                                        .get("/insertintake/" + this.user_id + "/" + this.food.code)
                                        .then(response => {
                                            this.res = response.data.resvalue;
                                            alert(this.food.name + " 섭취목록에 넣었습니다.");

                                        })
                                        .catch(() => {
                                            alert("fail" + this.user_id + " " + this.food.code)
                                            this.errored = true;
                                        })
                                        . finally(() => (this.loading = false));

                                }
                            },

                            mounted() {
                                this.infofood();
                                setTimeout(() => {
                                    this.drawchart();
                                }, 500)
                                this.ReceivedData = false
                            }
                        };
                    </script>
                    <style>
                    th{
                        font-size: 30px;
                    }

                    td{
                        font-size: 25px;
                    }
                    </style>