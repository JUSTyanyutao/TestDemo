$(function() {

    var $form = $("#solveForm");
    var list = {

        /** 初始化 */
        init: function() {
            this.initPagination();
            this.checkAction();
            this.confirmAction();
            this.bindSortSlider();
            //this.redirectAddPage();
            //this.loadFoodMarket();
        },

        /** 初始化分页 */
        initPagination: function() {
            Pagination.init({
                pageSize: 12,   // 每页显示12条记录
                //editBtn: {
                //    url: ctx + "/carousel/{id}"
                //},
                deleteBtn: {
                    url: ctx + "/share/delete",
                    method: "POST",
                    callback: function(data) {
                        // 回调函数

                    }
                }
            });
        },

        /** 绑定排序值滑动效果 */
        bindSortSlider: function() {
            $("[data-ui-slider]").slider();
        },

        checkAction:function(){
            $(".btn-checked-action").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要处理的晒单");
                    return;
                }

                if (ids.length > 1) {
                    Dialog.warning("警告！一次只能处理一个晒单");
                    return;
                }
                $("#id").val(ids[0]);
                $("#comment").text('');
                $('#solveContent').modal('show');
            });
        },
        /** 确认处理操作 */
        confirmAction: function() {
            $(".btn-submit").on("click",function(){
                $form.validate({
                    ignore: "",
                    rules: {
                        comment:{
                            required:true,
                            maxlength: 255
                        }
                    },
                    messages: {
                        comment:{
                            required:"请输入审核意见",
                            maxlength: "处理结果的长度不能超过{0}个字符"
                        }
                    },
                    submitHandler: function() {
                        var buttonObj = this.submitButton;
                        $(buttonObj).button("loading");
                        var status = 1;
                        if($(buttonObj).text() == '驳回中...')
                        {
                            status = -1;
                        }
                        $.ajax({
                            url: window.ctx+"/share/check?status="+status,
                            type: "POST",
                            data: $form.serialize(),
                            dataType: "JSON",
                            success: function(data) {
                                if (data.code == 0) {
                                    Dialog.success(data.msg);
                                    Pagination.reload();
                                    $(".btn-submit").button("reset");
                                    $("#comment").val('');
                                    $("#cancleSolve").click();
                                    //exports.reload();
                                }else{

                                    Dialog.danger(data.msg);
                                    $submitBtn.button("reset");
                                }
                            }
                        });
                    }
                });
            });
        },



        loadFoodMarket:function(){
        	$.ajax({
        		  url: window.ctx + "/select2/getMarketList",
        		  type:"get",
        		  dataType:'json',
        		  success:function(data){
        				$("#foodMarketSelect").select2({
          				  placeholder: {
          					  id: "-1",
          					  text: "请选择菜场"
          					},
          			      data: data,
          			      matcher: function (params, data) {
          			    	if ($.trim(params.term) === '') {
         					    return data;
         					  }
         					  if (data.text.indexOf(params.term) > -1) {
         						  return $.extend({}, data, true);
         					  }
          					  return null;
          					},
          			      allowClear: true
          			  });

        		  }
        	  })
        },
        
        /** 跳转到添加菜单页面 */
        redirectAddPage: function() {
            $(".btn-add").on("click", function() {
                location.href = ctx + "/share";
            });
        }
    };
    list.init();
    $(document).on("click",".messageContent",function(){
        $("#myModal .modal-body").html($(this).html());
    });

});
