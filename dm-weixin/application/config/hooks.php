<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/*
| -------------------------------------------------------------------------
| Hooks
| -------------------------------------------------------------------------
| This file lets you define "hooks" to extend CI without hacking the core
| files.  Please see the user guide for info:
|
|	http://codeigniter.com/user_guide/general/hooks.html
|
*/

// 微信过滤器
// $hook['post_controller_constructor'][] = array(
// 	'class' => 'Filter',
//     'function' => 'weixin',
//     'filename' => 'Filter.php',
//     'filepath' => 'hooks',
//     'params'=> array()
// 	);

// 全局过滤器
$hook['post_controller_constructor'][] = array(
    'class' => 'Filter',
    'function' => 'filter',
    'filename' => 'Filter.php',
    'filepath' => 'hooks',
    'params'=> array()
);
