({
    appDir: '../',
    baseUrl: './',
    dir: './dist',
    modules: [
       {name: 'main'}
    ],
    // name: 'app',
    // out : "../build.min.js",
    fileExclusionRegExp: /^(r|build)\.js$/,
    optimizeCss: 'standard',
    removeCombined: true,
    paths: {
        zepto : 'libs/zepto.min',
        jquery : 'libs/jquery-1.11.3.min',
        backbone : 'libs/backbone.min',
        underscore : 'libs/underscore.min',
        text : 'libs/text',
        swiper : 'libs/swiper',
        echo : 'libs/echo.min',
        countdown: 'libs/countdown',
        jweixin : 'http://res.wx.qq.com/open/js/jweixin-1.1.0'
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
        }
    }
})
