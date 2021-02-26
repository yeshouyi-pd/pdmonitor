<template>
    <div>
      <div style="text-align: center;margin-top: 20%" >
          <van-image
              round
              width="10rem"
              height="10rem"
              src="/static/wx/txz/success.png"
          />

      </div>
      <div style="text-align: center;padding-top: 20px">
        <font style="font-size: 1.2em"> 操作成功</font>
      </div>
      <div class="van-address-list__bottom">
        <van-button  round block type="info"
                     color="linear-gradient(to right, #1E90FF,#7FFFAA)"
                     to="/index">
          返回首页
        </van-button>
        <div style="margin-top: 8px"></div>
      </div>

    </div>
</template>

<script>

    export default {
        name:'txzssuccess',
        data:function(){
            return{

            }
        },
        mounted:function(){//mounted初始化方法
            let _this = this;




        },
        methods:{


        },


    }
</script>

<style scoped>


</style>
