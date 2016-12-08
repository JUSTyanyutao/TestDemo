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
    <p class="he-header-left"></p>
    <p class="he-header-center">注册</p>
    <p id="btn-login" class="he-header-right">登陆</p>
</div>

<ul class="he-lareg1-list">
    <input type="hidden" name="token" id="token" value="<?php echo $token;?>">
    <li>
        输入密码 <input type="password" id="password" name="password" placeholder="请输入您的密码"/>
    </li>
    <li>
        再次输入 <input type="password" id="repeat" name="repeat" placeholder="请再次输入密码"/>
    </li>
</ul>
<div class="he-lareg1-btn">
    <button id="btn-register" class="btn btn-green">确 定</button>
</div>

<script type="text/javascript">
    requirejs(['app/register/step2']);
</script>
</body>
</html>
