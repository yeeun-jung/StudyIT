<?php
	/**
	 * Created by PhpStorm.
	 * User: a201704031
	 * Date: 2017. 6. 21.
	 * Time: PM 6:26
	 */
	header("Content-Type: text/html; charset=UTF-8");

	$mode               = isset($_POST['mode'])     ? $_POST['mode']    : '';
	$param['key']       = isset($_POST['key'])      ? $_POST['key']     : '';
	$param['value']     = isset($_POST['value'])    ? $_POST['value']   : '';
	$param['expire']    = isset($_POST['expire'])   ? $_POST['expire']  : '';

	$redis      = new Redis();

	try {
		$redis->connect('127.0.0.1', 6379, 2.5);//2.5 sec timeout

		//Auth Password(redis.conf requirepass)
		$redis->auth('redis');
	} catch (Exception $e) {
		exit( "Cannot connect to redis server : ".$e->getMessage() );
	}

	if ( !$redis->select(0) ){
		exit( "NOT DB Select");
	}

	/**
	 * DB connect process 추가해야함
	 */

	if($mode == 'insert') {
		/**
		 * DB insert & select process 추가해야함
		 * select 해온 값을 $param['key'], $param['value'] 에 할당해서 redis에 set하면 실시간 반영 가능
		 */

		$flag = $redis->set($param['key'], $param['value'], (int)$param['expire']);
		echo '{"result": "ok", "flag": "'.$flag.'"}';
	}else if($mode =='search'){
		$redis_value = $redis->get($param['key']);
		if($redis_value){
			echo '{"result": "ok", "value":"'.$redis_value.'"}';
		}else{
			/**
			 * redis에서 값을 못찾을 경우 DB select 해서 가져오는 process 추가해야함
			 */
			$db_result = 'DB select result';
			$redis_value = $db_result;
			if($db_result){
				echo '{"result": "ok", "value":"'.$redis_value.'"}';
			}else {
				echo '{"result":"fail"}';
			}
		}
	}
?>