$(function() {

    var list = {

        /** 初始化 */
        init: function() {
            this.initPagination();
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
                    url: ctx + "/signIn/delete",
                    method: "POST",
                    callback: function(data) {
                        // 回调函数

                    }
                }
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
          					  text: "请选择菜场",
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
});
