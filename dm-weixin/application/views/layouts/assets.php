<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="format-detection" content="telephone=no" />
<meta name="viewport" content="user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1, width=device-width" />
<meta name="description" content="怎么吃,怎么吃生鲜,生鲜">
<meta name="keywords" content="怎么吃,怎么吃生鲜,生鲜">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!-- <link rel="stylesheet" href="<?php echo site_url("assets/css/??normalize.min.css,swiper.min.css,global.min.css,index.min.css") ?>"> -->
<link rel="stylesheet" href="<?php echo site_url("assets/css/normalize.css") ?>?<?= ASSETS_VERSION ?>">
<link rel="stylesheet" href="<?php echo site_url("assets/css/weui.min.css") ?>?<?= ASSETS_VERSION ?>">
<link rel="stylesheet" href="<?php echo site_url("assets/css/swiper.min.css") ?>?<?= ASSETS_VERSION ?>">
<link rel="stylesheet" href="<?php echo site_url("assets/css/bootstrap.min.css") ?>?<?= ASSETS_VERSION ?>">
<link rel="stylesheet" href="<?php echo site_url("assets/css/public.css") ?>?<?= ASSETS_VERSION ?>">
<link rel="stylesheet" href="<?php echo site_url("assets/css/button.css") ?>?<?= ASSETS_VERSION ?>">
<link rel="stylesheet" href="<?php echo site_url("assets/css/frozen.css") ?>?<?= ASSETS_VERSION ?>">
<link rel="stylesheet" href="<?php echo site_url("assets/css/dropload.css") ?>?<?= ASSETS_VERSION ?>">
<link rel="stylesheet" href="<?php echo site_url("assets/css/mobiscroll.css") ?>?<?= ASSETS_VERSION ?>">
<link rel="stylesheet" href="<?php echo site_url("assets/css/mobiscroll_002.css") ?>?<?= ASSETS_VERSION ?>">
<link rel="stylesheet" href="<?php echo site_url("assets/css/mobiscroll_003.css") ?>?<?= ASSETS_VERSION ?>">
<link rel="stylesheet" href="<?php echo site_url("assets/css/index.css") ?>?<?= ASSETS_VERSION ?>">


<script>
    window.ctx = "<?= site_url() ?>";
    window.serverApiUrl = "<?= BASE_API_URL ?>";
    window.imageDomain = "<?= IMAGE_DOMAIN ?>";
    window.assetVersion = "<?= ASSETS_VERSION ?>";
    window.serverDomain = "<?= WX_MOBILE_SITE ?>";

    var signPackage = {
        "appId":     "<?= isset($signPackage['appId']) ? $signPackage['appId'] : '' ?>",
        "timestamp": "<?= isset($signPackage['timestamp']) ?  $signPackage['timestamp'] : '' ?>",
        "nonceStr":  "<?= isset($signPackage['nonceStr']) ? $signPackage['nonceStr'] : '' ?>",
        "signature": "<?= isset($signPackage['signature']) ? $signPackage['signature'] : ''?>" //微信签名方式:
    }

    function isWeiXin() {
        var ua = window.navigator.userAgent.toLowerCase();
        if (ua.match(/MicroMessenger/i) == 'micromessenger') {
            return true;
        } else {
            return false;
        }
    }

    var stopMain = false;
</script>
<script src="<?php echo site_url("assets/js/require.min.js") ?>" data-main="<?php echo site_url("assets/js/main.js") ?>"></script>
