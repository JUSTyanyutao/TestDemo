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
<div class="he-header">
    <p class="he-header-cebter">注册</p>
    <p id="btn-login" class="he-header-right">登陆</p>
</div>
<ul class="he-lareg-list">
    <li class="top">
        +86 <input type="text" id="mobile" name="mobile" placeholder="请输入手机号"/>
    </li>
    <li class="bottom">
        <input type="text" id="captcha" name="captcha" placeholder="请输入验证码"/>
        <button id="btn-captcha">获取验证码</button>
    </li>
</ul>
<div class="he-lareg-bottom">
    <button id="btn-register" class="btn  btn-green">确 定</button>
    <p>
        点击 <span class="determine">[确定]</span> 即表示同意 <span class="agreement">软件许可及服务协议</span>
    </p>
</div>

<script type="text/javascript">
    requirejs(['app/register/step1']);
</script>
</body>
</html>
