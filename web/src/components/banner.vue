<template class="a">

    <div class="container" >
        <h1>하아아이!!</h1>
        <div style="width:300px; height:500px;">
        <transition-group
            name="fade"
            v-on:after-enter="fadeNext"
            tag="div"
            class="text-center">
            <img v-show="fade[0]" key="0" class="im" src="../../public/food.jpg">
                <img v-show="fade[1]" key="1" class="im" src="../../public/food2.jpg">
                    <img v-show="fade[2]" key="2" class="im" src="../../public/food3.jpg">
                        <img v-show="fade[3]" key="3" class="im" src="../../public/food4.jpg"></transition-group>

                    </div>
                   

    </div>
                </template>
                <script>
                    let fadeIdx = 0
                    export default {
                        name: 'App',

                        data: () => { // #1
                            return {
                                loading: false,
                                is_show: false,
                                recommend: [],
                                fade: [false, false, false, false]
                            }
                        },
                        methods: {
                            fadeNext: function () {
                                this
                                    .fade
                                    .splice(fadeIdx++, 1, true)
                            }
                        },
                        mounted() {
                            setTimeout(this.fadeNext, 0.1);
                        }
                    }
                </script>
                <style>
                    /* 배경화면 조정하는 css #bg랑 body, html */

                    .im {
                        position: fixed;
                        top: 0;
                        left: 0;
                        opacity: 1;
                        /* Preserve aspet ratio */
                        /* min-width: 100%;
                        min-height: 100%; */
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
                
                    .fade-enter {
                        opacity: 0;
                    }

                    .fade-enter-active {
                        transition: opacity 1.0s;
                    }
                </style>