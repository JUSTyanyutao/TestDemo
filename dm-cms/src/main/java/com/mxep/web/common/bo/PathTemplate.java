package com.mxep.web.common.bo;

/**
 * Created by ranfi on 10/19/15.
 */
public class PathTemplate {

	
	/**
     * 景区图片保存路径
     * {0} 景区ID, {1} 图片UUID, {2} 图片后缀名
     */
    public static String SCENIC_PIC_PATH = "/scenic/{0}/{1}{2}";

    /**
     * 景区地图路径
     * {0} 景区ID, {1} 地图UUID, {2} 图片后缀名
     */
    public static String SCENIC_MAP_PATH = "/scenic/{0}/map/{1}{2}";

    /**
     * 景区地图瓦片分级路径
     * {0} 景区ID，{1}地图层级,{2} 瓦片的x坐标，{3} 瓦片的y坐标 {4} 图片后缀名
     */

    public static String SCENIC_MAP_TILE_PATH = "/scenic/{0}/map/{1}/{2}_{3}{4}";

    /**
     * {0} 景区ID，{1}景点ID,{2} 图片的UUID,{3} 图片或者音频的后缀名
     */
    public static String SCENIC_SPOTS_PIC_OR_AUDIO_PATH = "/scenic/{0}/spot/{1}/{2}{3}";


    /**
     * {0} 景区ID，{1} 兴趣点的UUID， {2} 图片的后缀
     */
    public static String SCENIC_POI_PIC_PATH = "/scenic/{0}/poi/{1}{2}";


}
