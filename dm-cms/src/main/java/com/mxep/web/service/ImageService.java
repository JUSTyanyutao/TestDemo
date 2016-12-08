package com.mxep.web.service;

import com.mxep.core.utils.http.HttpUtils;
import com.mxep.model.SysConstant;
import org.javasimon.aop.Monitored;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by dk on 14-11-13.
 */
@Component
@Transactional
@Monitored
public class ImageService {

	public static String postImage(MultipartFile file,String fileName,String fix){
        String ret = "";
        try {  
            String url = SysConstant.IMAGE_DOMAIN + "/upload?ftype=image";
        	ret = HttpUtils.uploadMultipart(url,file.getInputStream(),fileName,fileName+"."+fix);
        	System.err.println(ret);
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return ret;
	}

}
