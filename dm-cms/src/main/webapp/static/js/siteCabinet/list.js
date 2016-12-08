$(function() {
	
	
	

    var list = {

        /** 初始化 */
        init: function() {
            this.initPagination();
            this.bindDisableAction();
            this.bindEnableAction();
            this.loadSiteStation();
        },

        /** 初始化分页 */
        initPagination: function() {
            Pagination.init({
                pageSize: 12,   // 每页显示12条记录
                editBtn: {
                    url: ctx + "/siteCabinet/editSiteCabinet/{id}"
                },
                deleteBtn: {
                    url: ctx + "/siteCabinet/deleteSiteCabinet",
                    callback: function(data) {
                        // 回调函数

                    }
                }
            });
        },
        
        loadSiteStation:function(){
        	$.ajax({
      		  url: window.ctx + "/siteStation/getEnableStations",
      		  dataType:'json',
      		  success:function(data){
      			  
      			  $("#siteStations").select2({
      				  placeholder: {
      					  id: "-1",
      					  text: "请选择站点",
      					},
      			      data: data,
      			      matcher: function (params, data) {
      			    	 if ($.trim(params.term) === '') {
     					    return data;
     					  }
     					  if (data.text.indexOf(params.term) > -1 || data.py.indexOf((params.term).toUpperCase()) > -1) {
     						  return $.extend({}, data, true);
     					  }
      					  return null;
      					},
      			      allowClear: false
      			  });
      			  
      			  
      			  if($("#siteStations").attr("data-select")){
      				  $("#siteStations").val($("#siteStations").attr("data-select")).trigger("change");
      			  }
	      		  
      		  }
      	  })
        	
        },
        
        /** 绑定禁用操作 */
        bindDisableAction: function() {
            $(".btn-disable").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要禁用的柜子");
                    return;
                }

                Dialog.confirm("禁用柜子", "确认要禁用这些柜子？",  function(result) {
                    if (result == true) {
                        $.ajax({
                            url: ctx + "/siteCabinet/disable",
                            type: "POST",
                            data: {
                                id: ids
                            },
                            dataType:"json",
                            success: function(data) {
                            	
                                if (data.code == 0) {
                                    Dialog.success(data.msg);
                                    Pagination.reload();
                                    //exports.reload();
                                }else{
                                	Dialog.danger(data.msg);
                                }
                            }
                        })
                    }
                });
            });
        },

        /** 绑定解禁操作 */
        bindEnableAction: function() {
            $(".btn-enable").on("click", function() {
                var ids = Pagination.getCheckedIds();

                if (ids.length <= 0) {
                    Dialog.warning("请先选择您要解禁的柜子");
                    return;
                }

                Dialog.confirm("解禁柜子", "确认要解禁这些柜子？",  function(result) {
                    if (result == true) {
                        $.ajax({
                            url: ctx + "/siteCabinet/enable",
                            type: "POST",
                            data: {
                                id: ids
                            },
                            dataType:"json",
                            success: function(data) {
                                if (data.code == 0) {
                                    Dialog.success(data.msg);
                                    Pagination.reload();
                                }else{
                                	Dialog.danger(data.msg);
                                }
                            }
                        })
                    }
                });
            });
        },
       

    };

    list.init();
    
    
});
