define(['zepto', 'underscore', 'backbone',
        'app/utils',
        'text!templates/favorites.html'
    ],
    function($, _, Backbone, utils, favorites) {
        var feedbackView = Backbone.View.extend({
            el: $('#favorites-page'),
            render: function() {
                var that = this;
                loadPage(that);
            },
          	events: {
              "tap .he-collection-list li" : "redirectToMerchant"
            },

            redirectToMerchant : function(e){
                var $this = $(e.currentTarget);
        				window.location.hash = 'merchant/' + $this.data('id');
            }
        });

        var loadPage = function(that) {
            utils.showPage(that.$el,function(){
              getFavList(function(data){
                  var template = _.template(favorites);
                  that.$el.empty().append(template(data));
              });
            });
        }

        var getFavList = function(callback) {
            $.ajax({
                url: window.ctx + "favorite/getList",
                type: "GET",
                dataType: 'json',
                success: function(data) {
                    callback(data.result);
                }
            });
        }
        return feedbackView;
    });
