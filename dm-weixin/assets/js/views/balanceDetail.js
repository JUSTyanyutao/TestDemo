define(['zepto', 'underscore', 'backbone', 'app/utils', 'app/basket.min', 'echo', 'text!templates/balanceDetail.html'],
	function($, _, Backbone, utils, basket, echo, page) {

		var $page = $("#balance-detail-page");
		var $template;
		var template;
		var carts;


		var PageView = Backbone.View.extend({
			el: $("#balance-detail-page"),

			render: function() {
				utils.showPage($page, function() {
	                if (utils.isEmpty($page)) {
	                	utils.showHeader({title : "商品清单"});
	                    $page.empty().append(page);

	                    $tplGoodsList   = $page.find("#tpl-goods-list");
	                    $goodsContainer = $page.find("#cart-list");
	                    $tplFavList     = $page.find("#tpl-fav-list");

	                    initShoppingCartData();

	                } else {
	                    initShoppingCartData();
	                }
	            });
			},

			events: {
				// 删除商品
				"tap .trash": "deleteGoods",
				// 商品数量加
				"tap .btn-plus": "plusGoodsQuantity",
				// 商品数量减
				"tap .btn-sub": "subGoodQuantity"
			},

			subGoodQuantity: function(e) {
				var $this    = $(e.currentTarget);
                var goodsId  = $this.data("id");                  // 商品编号
                var $numText = $this.siblings(".number-number");  // 个数显示
                var quantity = parseInt($numText.text());         // 数量

                if (--quantity <= 0) {
                    $.Dialog.confirm("提示", "确认要从购物车中移除此商品吗？", function() {
                        basket.remove(goodsId, function() {
                            initShoppingCartData();
                        });
                    });
                } else {
                    // 更新商品数量
                    basket.decrease(goodsId, 1, function() {
                        initShoppingCartData();
                    });
                }

				// var $this   = $(e.currentTarget);
				// var goodsId = $this.data("id");

				// var $number = $this.next();
				// var currentNumber = parseInt($number.text());

				// if (currentNumber == 1) {
				// 	$.Dialog.confirm("提示", "确认要从移除此商品吗？", function() {
				// 		$.each(carts.goods, function(i, obj){
				// 			if (obj.id == goodsId) {
				// 				console.info(i);
				// 				carts.total -= obj.quantity;
				// 				carts.sum -= obj.sumPrice;
				// 				carts.goods.splice(i, 1);

				// 				console.info(JSON.stringify(carts));

				// 				utils.storage.set("carts", JSON.stringify(carts));

				// 				$this.parent().parent().parent().parent().remove();
	   //                  		updateTotalPrice();
				// 			}
				// 		});
	   //              });
				// } else {
				// 	$.each(carts.goods, function(i, obj){
				// 		if (obj.id == goodsId) {
				// 			carts.total             -= 1;
				// 			carts.sum               -= obj.price;
				// 			carts.goods[i].sumPrice -= obj.price;
				// 			carts.goods[i].quantity -= 1;

				// 			console.info(JSON.stringify(carts));

				// 			utils.storage.set("carts", JSON.stringify(carts));
				// 			updateTotalPrice();

				// 			$number.text(currentNumber - 1);
				// 		}
				// 	});
				// }
			},

			// 商品数量加1
			plusGoodsQuantity: function(e) {
				var $this            = $(e.currentTarget);
                var $number          = $this.prev();
                
                var nature           = parseInt($number.data('nature'));
                var number           = parseInt($number.text());
                var originalPrice    = $number.data("original-price").toFixed(2);
                var originalUnit     = $number.data("original-unit");
                var originalUnitName = $number.data("original-unit-name");

                if (nature == 2 && number == 1) {
                    $.Dialog.confirm("提示", "亲，您购买的商品为活动商品，只可享受一件优惠，第二件恢复原价¥"+originalPrice+"/"+originalUnit+originalUnitName+"，是否继续？", function() {
                        basket.increase($this.data('id'), 1, function() {
                            initShoppingCartData();
                        });
                    });
                } else {
                    basket.increase($this.data('id'), 1, function() {
                        initShoppingCartData();
                    });
                }

				// var $this   = $(e.currentTarget);
				// var goodsId = $this.data("id");

				// var $number = $this.prev();
				// var number  = parseInt($number.text()) + 1;

				// $.each(carts.goods, function(i, obj){
				// 	if (obj.id == goodsId) {
				// 		carts.total             += 1;
				// 		carts.sum               += obj.price;
				// 		carts.goods[i].sumPrice += obj.price;
				// 		carts.goods[i].quantity += 1;

				// 		console.info(JSON.stringify(carts));

				// 		utils.storage.set("carts", JSON.stringify(carts));
				// 		updateTotalPrice();

				// 		$number.text(number);
				// 	}
				// });				
			},

			// 删除商品
			deleteGoods: function(e) {
				var $this = $(e.currentTarget);

                var goodsId = $this.data("id");
           
                $.Dialog.confirm("提示", "确认要从购物车中移除此商品吗？", function() {
                    basket.remove(goodsId, function() {
                        initShoppingCartData();
                    });
                });
				// var $this   = $(e.currentTarget);
				// var goodsId = $this.data("id");

				// $.Dialog.confirm("提示", "确认要从移除此商品吗？", function() {
				// 	$.each(carts.goods, function(i, obj){
				// 		if (obj.id == goodsId) {
				// 			console.info(i);
				// 			carts.total -= obj.quantity;
				// 			carts.sum -= obj.sumPrice;
				// 			carts.goods.splice(i, 1);

				// 			console.info(JSON.stringify(carts));

				// 			utils.storage.set("carts", JSON.stringify(carts));

				// 			$this.parent().parent().parent().remove();
    //                 		updateTotalPrice();
				// 		}
				// 	});

					
    //             });
			}
		});

		/**
         * 加载购物车数据
         */
        var initShoppingCartData = function(callback) {
            $.ajax({
                url: window.ctx + "shoppingCart",
                type: "GET",
                dataType: "json",
                success: function(data) {
                    var template  = _.template($tplGoodsList.html());
                    $goodsContainer.empty().append(template(data.result));
                    
                    totalPrice = data.result.data.total_price;

                    var goods    = []
	                var total    = 0;
	                var number   = 0;
	                var sumPrice = '';
	                $page.find(".number-number").each(function(i, obj) {
	                    // 每类商品的个数
	                    number = parseInt($(obj).text());
	                    sumPrice = $(obj).parent().siblings(".sum-price").data('sum-price');
	                    goods.push({
	                        id: $(obj).data('id'),
	                        quantity: number,
	                        pic: $(obj).data('pic'),
	                        currentPrice: $(obj).data('current-price'),
	                        name: $(obj).data('name'),
	                        sumPrice: sumPrice,
	                        price: $(obj).data('price')
	                    });
	                    // 总数量累加
	                    total += number;
	                });
	                var carts = {
	                    goods: goods,
	                    total: total,
	                    sum: totalPrice
	                };
	                utils.storage.set('carts', JSON.stringify(carts));

                    typeof callback == "function" && callback();

                    initEvent();
                }
            });
        };

		var updateTotalPrice = function() {
            //$page.find(".balance-price").text("¥" + carts.sum.toFixed(2));
            $page.find('#cart-list').empty().append(template(carts));
            initEvent();
        };

		var initEvent = function() {
            setTimeout(function() {
                echo.init({
                    throttle: 1000
                });    
            }, 300);
        };

		return PageView;
	});