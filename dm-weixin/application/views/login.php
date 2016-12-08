<!doctype html>
<html class="no-js">
<head>
    <title>怎么吃</title>
    <?php $this->load->view('layouts/assets'); ?>
    <script>
        var stopMain = true;        
    </script>
</head>
<body>

    <input type="hidden" id="redirect" value="<?=$redirect_url?>" />


    <div class="he-header">
        <p class="he-header-center">登陆</p>
        <p id="btn-register" class="he-header-right">注册</p>
    </div>
    <ul class="he-laind-list">
        <li>
            账号 <input type="text" id="mobile" placeholder="请输入您的手机号或者账号"/>
        </li>
        <li>
            密码 <input type="password" id="password" placeholder="请输入密码"/>
        </li>
    </ul>
    <a href="/#forgotPassword" class="forgot-password">忘记密码</a>
    <div class="he-laind-btn">
        <button id="btn-login" class="btn-login">登陆</button>
    </div>

    <script type="text/javascript">
        requirejs(['app/login']);
    </script>
</body>
</html>
