$(function() {

    var list = {

        /** 初始化 */
        init: function() {
            this.initPagination();
            //this.loadChart();
        },

        /** 初始化分页 */
        initPagination: function() {
            var self = this;
            Pagination.init({
                pageSize: 10
            });
        },

        //loadChart: function () {
        //        $.ajax({
        //        url: window.ctx + "/order/chart",
        //        type: "POST",
        //        data: $("#pagination-form").serialize(),
        //        dataType: "JSON",
        //        success: function(data) {
        //            console.log(data);
        //            var data1 = new Array();
        //            var data2 = new Array();
        //            for(var i=0; i<data.content.length;i++)
        //            {
        //                data1[i] = data.content.content;
        //                data2[i] = data.data[i].countNum;
        //            }
        //            var randomScalingFactor = function(){ return Math.round(Math.random()*100)};
        //
        //
        //        }
        //    });
        //
        //},


    };

    list.init();

});
