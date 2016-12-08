requirejs.config({
    //By default load any module IDs from js/lib
    // baseUrl: 'assets/js/lib',
    //except, if the module ID starts with "app",
    //load it from the js/app directory. paths
    //config is relative to the baseUrl, and
    //never includes a ".js" extension since
    //the paths config could be for a directory.
    paths: {
        zepto : 'libs/zepto.min',
        jquery : 'libs/jquery-1.11.3.min',
        backbone : 'libs/backbone.min',
        underscore : 'libs/underscore.min',
        text : 'libs/text',
        swiper : 'libs/swiper',
        echo : 'libs/echo.min',
        countdown: 'libs/countdown',
        frozen: 'libs/frozen',
        jweixin : 'http://res.wx.qq.com/open/js/jweixin-1.1.0',
        dropload : 'libs/dropload',
        mobiscroll: 'libs/mobiscroll',
        fly: 'libs/fly'
    },
    shim: {
        'underscore': {
            exports: '_'
        },
        'jquery': {
            exports: '$'
        },
        'zepto': {
            exports: '$'
        },
        'backbone': {
            deps: ['underscore', 'zepto'],
            exports: 'Backbone'
        },
        'swiper' : {
            deps: ['zepto'],
            exports: 'Swiper'
        },
        'jweixin' : {
            deps:[],
            exports : 'jweixin'
        },
        'frozen': {
            deps: ['jquery'],
            exports: 'Frozen'
        },
        'mobiscroll': {
            deps: ['jquery'],
            exports: 'Mobiscroll'
        }
    },
    urlArgs: "v=" + window.assetVersion
});

// Start the router
requirejs(['router'],function(Router){
      Router.initialize();
});

if(isWeiXin()){
    requirejs(['app/weixin'], function(weixin){
        weixin.initConfig(signPackage);
    });
}
