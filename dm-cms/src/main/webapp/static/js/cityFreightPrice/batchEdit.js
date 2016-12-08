$(function () {

  var $form = $("#form-edit");
  var $submitBtn = $(".btn-submit");
  var edit = {

    /** 初始化函数 */
    init: function () {
      this.validateForm();
    },


    /** 验证表单字段 */
    validateForm: function () {
      var validator = $form.validate({
        ignore: "",
        submitHandler: function () {
          $.ajax({
            url: $form.attr("action"),
            type: "POST",
            data: $form.serialize(),
            dataType: "JSON",
            success: function (data) {
              if (data.code == 0) {
                Dialog.success(data.msg, function () {
                  location.href = window.ctx +"/city/freight/price/list";
                }, 1500);
              } else {
                Dialog.danger(data.msg);
              }
            }
          });

        }
      })
    }
  };

  edit.init();

  $(".btn-submit").click(function(){
      if($("#inputfile").val()== null  || $("#inputfile").val()== ""){
        Dialog.danger("请先导入数据");
        return;
      }
  });




  $("#inputfile").change(function(){
    var options = {
      //target: '#output',          //把服务器返回的内容放入id为output的元素中
      //beforeSubmit: showRequest,  //提交前的回调函数
      //提交后的回调函数
//			link: ctx + "/coupon/export",                 //默认是form的action， 如果申明，则会覆盖
      type: "post",               //默认是form的method（get or post），如果申明，则会覆盖
      data:$('#ajaxform').serialize(),
      dataType: "text",           //html(默认), xml, script, json...接受服务端返回的类型
      //clearForm: true,          //成功提交后，清除所有表单元素的值
      //resetForm: true,          //成功提交后，重置所有表单元素的值
      timeout: 3000,		   //限制请求的时间，当请求大于3秒后，跳出请求
      success: function(responseText, statusText){
        alert(responseText+":"+statusText);
      }
    };

    /*$("#ajaxform").ajaxForm({
     link: "/coupon/export",
     type:"post",
     enctype:'multipart/form-data',
     data:$('#ajaxform').serialize(),
     success:function(data, statusText) {
     alert(data);
     },error:function(data) {
     alert("出错！");
     }
     });*/
    $('#ajaxform').submit();
  });


});

var provinces = [];
var cities = [];
var prices = [];

function uploadOnload() {
  var body = $(window.frames['frameFile'].document.body);   // iframe中的body
  var data = body.context.textContent;
  var dataJson;
  var j = 0 ;
  if(data.length!=0){

    dataJson = JSON.parse(data);
    for(var i=1; i<dataJson.length; i++)
    {
      if(dataJson[i][1].length>0){
        j++;
        provinces.push(dataJson[i][0]);
        cities.push(dataJson[i][1]);
        prices.push(dataJson[i][2]);
        //
      }
    }
  }
  if(j!=0){
    var num = j-1;
    $("#form-edit").append('<input type="hidden"  name="provinces" value="'+provinces+'">');
    $("#form-edit").append('<input type="hidden"  name="cities" value="'+cities+'">');
    $("#form-edit").append('<input type="hidden"  name="prices" value="'+prices+'">');
    $("#divInfo").html("你已导入 "+num+" 条数据");
  }

}