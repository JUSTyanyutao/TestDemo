<!DOCTYPE html>
<?php $this->load->view('layouts/assets'); ?>
<script>
    requirejs(['app/weixin'],function(weixin){
        var param = {
                "appId": "<?= $appId ?>",
                "timestamp": "<?= $timeStamp ?>",
                "nonceStr": "<?= $nonceStr ?>",
                "package": "<?= $package ?>",
                "signType": "<?= $signType ?>", //微信签名方式:
                "paySign": "<?= $paySign ?>" //微信签名
            };

        var signPackage = {
                "appId": "<?= $signPackage['appId']?>",
                "timestamp": "<?= $signPackage['timestamp']?>",
                "nonceStr": "<?= $signPackage['nonceStr'] ?>",
                "signature": "<?= $signPackage['signature'] ?>" //微信签名方式:
            };
        var redirectHash = '<?= $redirectHash ?>' || '';
        redirectHash = (redirectHash && redirectHash != 'null' && redirectHash != '') ? redirectHash : "personalCenter";
        var success = function(res){
            location.href = window.ctx + "#" + redirectHash;
        }
        var error = function(){
            location.href = window.ctx + "#personalCenter";
        }
        var cancel = function(){
            location.href = window.ctx + "#" + redirectHash;
        }
        weixin.doPayV3(signPackage, param, success, error, cancel);
    });
</script>