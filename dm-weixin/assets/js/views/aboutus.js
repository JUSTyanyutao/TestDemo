define(['zepto', 'underscore', 'backbone',
        'app/utils',
        'text!templates/aboutus.html'
    ],

    function($, _, Backbone, utils, aboutus) {

        var $type;
        var aboutusView = Backbone.View.extend({
            el: $('#aboutus-page'),
            render: function(type) {
                var that = this;
                $type = type;
                loadPage(that);
            }
        });

        var loadPage = function(that) {
            utils.showPage(that.$el,function(){
                var template = _.template(aboutus);
                that.$el.empty().append(template());
                if(null != $type && typeof($type) !== 'undefined'){
                    $(".he-header").hide();
                    $page.css("padding-top","0px");
                }
            });
        };
        return aboutusView;
    });
