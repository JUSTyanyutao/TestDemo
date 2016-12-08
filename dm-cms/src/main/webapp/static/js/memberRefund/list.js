$(function() {

	var $form = $("#solveForm");
    var $submitBtn = $("#confirmsolve");
	
    var list = {

        /** 初始化 */
        init: function() {
            this.initPagination();
            this.solveAction();
            this.confirmAction();
        },

        /** 初始化分页 */
        initPagination: function() {
            Pagination.init({
            	pageSize: 12,   // 每页显示12条记录
                editBtn: {
                    url: ctx + "/memberRefund/delete/{id}"
                },
                deleteBtn: {
                    url: ctx + "/memberRefund/delete",
                    callback: function(data) {
                        // 回调函数

                    }
                }
            });
        },
        
        
        /** 绑定处理操作 */
        solveAction: function() {
            $(".btn-solve-action").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要处理的记录");
                    return;
                }
                $("#ids").val(ids);
                $("#remark").val('');
                $('#solveContent').modal('show');
               
            });
        },
        
        /** 确认处理操作 */
        confirmAction: function() {
            $("#confirmsolve").on("click",function(){
            	$form.validate({
            		ignore: "",
            		rules: {
                        result:{
                        	maxlength: 255
                        },
                    },
                    messages: {
                        result:{
                        	maxlength: "处理结果的长度不能超过{0}个字符"
                        },
                    },
                    submitHandler: function() {
                    	var buttonObj = this.submitButton;
                    	$(buttonObj).button("loading");
                        $.ajax({
                            url: window.ctx+"/memberRefund/approve",
                            type: "POST",
                            data: {
                                id: Pagination.getCheckedIds(),
                                remark:$("#remark").val()
                            },
                            dataType: "JSON",
                            success: function(data) {
                            	
                                if (data.code == 0) {
                                    Dialog.success(data.msg);
                                    Pagination.reload();
                                    $submitBtn.button("reset");
                                    $("#cancleSolve").click();
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
        
    };

    list.init();
});

