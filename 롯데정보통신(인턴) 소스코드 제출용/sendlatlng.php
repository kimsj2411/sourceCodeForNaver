<?php 

require_once ($_SERVER['DOCUMENT_ROOT']."/preset.php");

session_start();
$data = json_decode(file_get_contents('php://input'),true);
$latlng_info = $data['latlng_info'];

echo "soojin";
?>