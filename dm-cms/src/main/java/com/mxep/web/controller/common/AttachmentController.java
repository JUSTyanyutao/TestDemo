package com.mxep.web.controller.common;

import com.google.common.collect.Maps;
import com.mxep.core.mapper.JsonMapper;
import com.mxep.model.Constant;
import com.mxep.model.SysConstant;
import com.mxep.web.service.ImageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Controller － 附件
 *
 * @author xingkong1221
 * @date 2015-10-21
 */
@Controller
@RequestMapping("/attachment")
public class AttachmentController extends WebController {

    /** 上传图片 */
    @ResponseBody
    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public ModelMap updateImage(@RequestParam("image") MultipartFile image) {
        ModelMap ret = new ModelMap("success", false);
        String suffix = "jpg";
        ret.put("image_url", SysConstant.IMAGE_DOMAIN);
        if(!image.isEmpty()) {
            String fileName = image.getOriginalFilename();
            if (StringUtils.isNotBlank(fileName)){
                int index = fileName.lastIndexOf(".");
                if (index > -1){
                    suffix = fileName.substring(index + 1);
                }
            }
            String result = ImageService.postImage(image, "c_1", suffix);
            List list = JsonMapper.nonEmptyMapper().fromJson(result, List.class);
            String url = ((Map)(list.get(0))).get("url").toString();
            int index = url.indexOf("/img");
            ret.addAttribute("success", true);
            ret.addAttribute("file_path", url.substring(index));
        } else {
        	ret.addAttribute("success", false);
            ret.addAttribute("msg", "空图片");
        }
        return ret;
    }

    /** 编辑器上传图片
     * {
     "state": "SUCCESS",
     "original": "80px - \u526f\u672c (2).jpg",
     "size": "13252",
     "title": "1465731377326075274.jpg",
     "type": ".jpg",
     "url": "/ueditor/jsp/upload/image/20160612/1465731377326075274.jpg"
     }
     * */
    @ResponseBody
    @RequestMapping(value = "/editorImage", method = {RequestMethod.POST,RequestMethod.GET})
    public Object editorUploadImage(@RequestParam("image") MultipartFile image) {
        Map<String,String> map = Maps.newHashMap();
        //ModelMap ret = new ModelMap("success", false);
        String suffix = "jpg";
        if(!image.isEmpty()) {
            String fileName = image.getOriginalFilename();
            if (StringUtils.isNotBlank(fileName)){
                int index = fileName.lastIndexOf(".");
                if (index > -1){
                    suffix = fileName.substring(index + 1);
                }
            }
            String result = ImageService.postImage(image, "c_1", suffix);
            List list = JsonMapper.nonEmptyMapper().fromJson(result, List.class);
            String url = ((Map)(list.get(0))).get("url").toString();
            int index = url.indexOf("/img");
            //ret.addAttribute("success", true);
            map.put("state","SUCCESS");
            map.put("original",image.getOriginalFilename());
            map.put("size",image.getSize()+"");
            map.put("title",url.substring(index));
            map.put("type",suffix);
            map.put("url",SysConstant.IMAGE_DOMAIN + url.substring(index));
//            ret.addAttribute("file_path",  SysConstant.IMAGE_DOMAIN + url.substring(index));
        } else {
            map.put("state","FAIL");
            map.put("original",image.getOriginalFilename());
            map.put("size",image.getSize()+"");
            map.put("title","空图片");
            map.put("type","");
            map.put("url","");
        }
        return map;
    }
    
//    /** 编辑器上传图片 */
//    @ResponseBody
//    @RequestMapping(value = "/editorImage", method = RequestMethod.POST)
//    public ModelMap editorUploadImage(@RequestParam("image") MultipartFile image) {
//        ModelMap ret = new ModelMap("success", false);
//        String suffix = "jpg";
//        if(!image.isEmpty()) {
//            String fileName = image.getOriginalFilename();
//            if (StringUtils.isNotBlank(fileName)){
//                int index = fileName.lastIndexOf(".");
//                if (index > -1){
//                    suffix = fileName.substring(index + 1);
//                }
//            }
//            String result = ImageService.postImage(image, "c_1", suffix);
//            List list = JsonMapper.nonEmptyMapper().fromJson(result, List.class);
//            String url = ((Map)(list.get(0))).get("url").toString();
//            int index = url.indexOf("/img");
//            ret.addAttribute("success", true);
//            ret.addAttribute("file_path",  SysConstant.IMAGE_DOMAIN + url.substring(index));
//        } else {
//        	ret.addAttribute("success", false);
//            ret.addAttribute("msg", "空图片");
//        }
//        return ret;
//    }
    
    /**
     * 上传文件(多张图片)
     * @param fileData
     * @return
     */
	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> uploadFile(@RequestParam MultipartFile[] fileData) {
		Map<String, Object> result = Maps.newHashMap();
		result.put("status", "ok");
		result.put("image_url", SysConstant.IMAGE_DOMAIN);
		try {
			if (fileData != null && fileData.length > 0) {
				// 循环获取file数组中得文件
				for (int i = 0; i < fileData.length; i++) {
					MultipartFile file = fileData[i];
					String fileName = file.getOriginalFilename();
					String suffix = "jpg";
					if (StringUtils.isNotBlank(fileName)){
						int index = fileName.lastIndexOf(".");
						if (index > -1){
							suffix = fileName.substring(index + 1);
						}
					}
					String ret = ImageService.postImage(file,"c_1",suffix);
					List list = JsonMapper.nonEmptyMapper().fromJson(ret, List.class);
					String url = ((Map)(list.get(0))).get("url").toString();
					int index = url.indexOf("/img");
					String picUrl = url.substring(index);
					result.put("fileName", file.getOriginalFilename());
					result.put("url", picUrl);
				}
			}
		} catch (Exception e) {
			result.put("status", "error");
		} 
		return result;
	}
}
