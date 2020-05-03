<template>
<div class="outexx" >
    <div class="exx">
<div class="accordion" id="accordionExample" style="width:1000px;">
  <div class="card" style="border-radius: 30px;">
    <div class="card-header" id="headingOne" >
      <h2 class="mb-0">
        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
        <h3>오지윤</h3> <img src="../../public/me.png"/> 
        </button>
      </h2>
    </div>

    <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample">
      <div class="card-body">
              <img src='https://res.cloudinary.com/landry-bete/image/upload/v1488769144/dessert14_trnhnj.jpg' class="circle vignette ">      </div>

    </div>
  </div>
  <div class="card" style="border-radius: 30px;">
    <div class="card-header" id="headingTwo">
      <h2 class="mb-0">
        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
<h3>김창수</h3> <img src="../../public/cc.png"/> 
        </button>
      </h2>
    </div>
    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
      <div class="card-body">
        <img src='https://res.cloudinary.com/landry-bete/image/upload/v1488769144/dessert14_trnhnj.jpg' class="circle vignette ">      </div>
      </div>
    </div>
  
  <div class="card" style="border-radius: 30px;">
    <div class="card-header" id="headingThree">
      <h2 class="mb-0">
        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
<h3>방준영</h3> <img src="../../public/bb.png"/> 
        </button>
      </h2>
    </div>
    <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
      <div class="card-body">
        <img src='https://res.cloudinary.com/landry-bete/image/upload/v1488769144/dessert14_trnhnj.jpg' class="circle vignette ">      </div>
    </div>
  </div>
</div>
</div>
</div>
        
    </template>
    <script>
            import * as $ from 'jquery';
        
        export default {
 
            mounted() {
                $('.collapse').collapse();
                 $('.collapsible').collapsible();

            },
            data() {
                return {                cards : [
                    {
                        title: 'Macaron',
                        src: 'https://res.cloudinary.com/landry-bete/image/upload/v1488769144/dessert14_trnh' +
                                'nj.jpg',
                        description: 'Is this thing French ?'
                    }, {
                        title: 'Tajine',
                        src: 'https://res.cloudinary.com/landry-bete/image/upload/v1525135352/tajine_mfnbd8.' +
                                'jpg',
                        description: 'Moroccan people seem to love that dish...'
                    }, {
                        title: 'Cake',
                        src: 'https://res.cloudinary.com/landry-bete/image/upload/v1525135530/cake_lnh2hn.jp' +
                                'g',
                        description: 'Eat healthy my boy!'
                    }
                ]
                }

            }

        }
    </script>
    <style >
    .outexx{
    width: 1000px;
    text-align: left;
    padding: 50px 650px;
    font-size: 20px;
    background: rgba(255,255,255,0);
     }
.exx{
 /* display: inline-block; */
 height:900px; 
 width:800px;
}
    .outside{
        height: 1000px;
    }
    .card-body{
        /* max-width: 100px;
        max-height: 100px; */
        width: 500px;
        height: 500px;
    }
        /* .vignette {
            max-width: 60px;
            max-height: 60px;
            margin-right: 4%;
        }

        #app {
            margin-top: 50px;
        } */
    </style>