<?php
class Avatar extends Displayer{

	private $paths;

	public function __construct($path)
	{
		parent::__construct();
		$this->paths = $this->path_as_array($path);
		$this->display();
	}

	public function display()
	{
		$picinfo = array_pop($this->paths);
		//直接输出缓存图片
		$this->cache_show(WANBAN_FOLDER, $picinfo);

		$picinfo_array = explode('_',$picinfo);
		$key = $picinfo_array[0];
		if ( stristr($key, '.') )
		{
			$keyArr = explode('.', $key);
			$key = $keyArr[0];
		}

		//判断原图是否存在
		$path = $this->getFilePath(WANBAN_FOLDER, $key);

		if ( $path )
		{
			if ( !empty($picinfo_array[1]) && isset($picinfo_array[1]) )
			{
				$sizeArr = explode('.', $picinfo_array[1]);
				$sizes = $sizeArr[0];

				switch ( $sizes )
				{
					case '60x60':
						$this->adjust_img($path.$key.'.jpg', $sizes, $path.$picinfo);
						break;

					case '30x30':
						$this->adjust_img($path.$key.'.jpg', $sizes, $path.$picinfo);
						break;

					default:
						$this->cache_send($path.$key.'.jpg');
				}
			}
			else
			{
				$this->cache_send($path.$key.'.jpg');
			}
		}
		else
		{
			die('图片不存在');
		}

	}



}
