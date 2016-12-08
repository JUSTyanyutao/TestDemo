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
                    url: ctx + "/comment/delete/{id}"
                },
                deleteBtn: {
                    url: ctx + "/comment/delete",
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
                    Dialog.warning("请先选择您要处理的商品评论");
                    return;
                }
                $("#ids").val(ids);
                $("#result").val('');
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
                            url: window.ctx+"/comment/approve",
                            type: "POST",
                            data: {
                                id: Pagination.getCheckedIds(),
                                result:$("#result").val()
                            },
                            dataType: "JSON",
                            success: function(data) {
                            	
                                if (data.code == 0) {
                                    Dialog.success(data.msg);
                                    Pagination.reload();
                                    $submitBtn.button("reset");
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
        
        
    };

    list.init();
});

function showPics(pics) {
	var _html = '<div style="text-align:center">';
	var strs= new Array(); //定义一数组
	strs = pics.split(","); //字符分割
	for (var i = 0;i < strs.length ;i++ ){
		var pic = strs[i];
		_html += '<img  height="200" width= "200"  style="margin:0 auto" src='  + pic + '>';
	}
	_html += '</div>';
    $("#showPicsContainer").html(_html);
    $("#showPics").modal({show: true});
}