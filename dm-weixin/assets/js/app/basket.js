define(['zepto', 'app/utils','underscore'], function($, utils, _) {

    /**
     * type: 1表示商品  2表示大橱
     *
     */
	return {
  		// 加
  		increase: function(itemId, quantity, type, callback, startTime) {
              $.ajax({
                  url: window.ctx + "shoppingCart/increase",
                  data: {
                      itemId: itemId,
                      quantity: quantity,
                      type: type,
                      startTime: startTime
                  },
                  loadMask: false,
                  type: "POST",
                  dataType: "json",
                  async: false,
                  success: function(data) {
                      typeof callback == "function" && callback(data);
                  }
              });
  		},

  		/**
       * 购物车减商品
       * @param  Integer   itemId    
       * @param  Integer   quantity  商品数量
       * @param  Integer   type      类型
       * @param  Function  callback  回调方法
       * @return void
       */
  		decrease: function(itemId, quantity, type, callback) {
  			$.ajax({
            url: window.ctx + "shoppingCart/decrease",
            data: {
                itemId: itemId,
                quantity: quantity,
                type: type
            },
            loadMask:false,
            async: false,
            type: "POST",
            dataType: "json",
            success: function(data) {
                typeof callback == "function" && callback(data);
            }
        });
  		},

      // 删除
      remove: function(id, callback) {
          $.ajax({
              url: window.ctx + "shoppingCart/delete",
              data: {
                  id: id
              },
              loadMask:false,
              async: false,
              type: "POST",
              dataType: "json",
              success: function(data) {
                  typeof callback == "function" && callback(data);
              }
          });
      },

      addCart : function(data, callback){
          var jsonData = utils.storage.get("shoppingCart") || "[]";
          var result = JSON.parse(jsonData);
          var isExist = false;
          _.each(result, function(item){
              if(item.id == data.id){
                  item.quantity = parseInt(item.quantity) + 1;
                  isExist = true;
                  return;
              }
          });
          if(!isExist) {
              result.push(data);
          }
          var count  = this.getCartGoodsNum(result);
          utils.storage.set("shoppingCartNum", count);
          utils.storage.set("shoppingCart",JSON.stringify(result));
          if(callback){
              callback(count);
          } else {
              this.showCartPoint();
          }
      },

      removeCart : function(id, callback){
          var jsonData = utils.storage.get("shoppingCart") || "[]";
          var data = JSON.parse(jsonData);
          var result = [];
          result = _.filter(data, function(item){
              if(parseInt(item.id) == parseInt(id)){
                  item.quantity =  item.quantity >= 1 ? item.quantity - 1 : 0;
                  return item.quantity > 0;
              } else {
                  return true;
              }
          });

          var count = this.getCartGoodsNum(result);
          utils.storage.set("shoppingCartNum", count);
          utils.storage.set("shoppingCart", JSON.stringify(result));
          if(callback){
              callback(count);
          } else {
              this.showCartPoint();
          }
      },

      clearCart : function(){
          utils.storage.set("shoppingCartNum", 0);
          utils.storage.set("shoppingCart", "[]");
      },

      getCarts : function(){
          var json = utils.storage.get("shoppingCart");
          return JSON.parse(json);
      },

      getCartGoodsNum : function(result){
          var count = 0;
          _.each(result, function(item){
              count += item.quantity;
          });
          return count;
      },

      showCartPoint : function(){
          var count = utils.storage.get("shoppingCartNum");
          if(count && count > 0){
              $(".he-footer .point").show().text(count);
          } else {
              $(".he-footer .point").hide();
          }
      }
	};
});
