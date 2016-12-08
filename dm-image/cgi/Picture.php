<?php

class Picture extends Displayer{
	
	private $paths;
	private $query;
	private $allowed_image_sizes;
	
	public function __construct($path, $query)
	{
		parent::__construct();
		$this->paths = $this->path_as_array($path);
		$this->query = $query;
		$this->allowed_image_sizes = $this->get_allowed_image_sizes();
		$this->display();
	}

	private function get_allowed_image_sizes(){
		return array("720x266","224x185","155x148","180x135","170x150","215x200","317x180","720x275","60x60","360x310","356x180","356x130");
	}
	
	public function display()
	{
		$picinfo = array_pop($this->paths);
		$picinfo_array = explode('_',$picinfo);

		$sizes = "";
		$key = "";
		$prefix_name  = "jpg";
		if(count($picinfo_array) == 3){
			$key = $picinfo_array[0];
			$sizes = $picinfo_array[1];
			$prefix_name = $picinfo_array[2];
		} else {
			$key = $picinfo_array[0];
			$arr = explode('.', $picinfo_array[1]);
			$sizes = $arr[0];
			$prefix_name = $arr[1];
		}
		

		//判断原图是否存在
		$path = $this->getFilePath(IMAGE_FOLDER."img/", $key, FALSE, ".".$prefix_name);
		if (empty($path))
		{
			die('原图不存在');
		}
		if ( !empty($sizes))
		{
			//判断裁剪尺寸是否合法
			if(in_array($sizes,$this->allowed_image_sizes)){
				$this->adjust_img($path.$key.'.'.$prefix_name, $sizes, $path.$key."_".$sizes.'.'.$prefix_name);
			} else {
				die('图片的尺寸不合法');
			}
		}
		else
		{
			$this->cache_send($path.$key.'.'.$prefix_name);
		}		
	}
	
	
	
}
