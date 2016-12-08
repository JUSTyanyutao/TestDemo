package com.mxep.web.utils;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.Map;

/**
 * Created by ranfi on 11/9/15.
 */
public class freemarkerUtils {

  //  private final static String TEMPLATE_LOADER_PATH = "/opt/iznet/www/ftl";
    
    public static final String TEMPLATE_LOADER_PATH ="D:/WNPHP/nginx/html/data/templet";
    public static final String TEMPLATE_DOMAIN = "http://127.0.0.1/data/templet";

    /**
     * 创建静态文件
     *
     * @param templateFile   模板文件
     * @param data           加载数据
     * @param targetHtmlPath 存储页面路径
     * @return
     */
    public static boolean createHtml(String templateFile, Map<String, Object> data, String targetHtmlPath) {

        TemplateLoader templateLoader = null;
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        try {
            File rootFile = new File(TEMPLATE_LOADER_PATH);
            templateLoader = new FileTemplateLoader(rootFile);
            cfg.setTemplateLoader(templateLoader);
            Template template = cfg.getTemplate(templateFile, "UTF-8");
            template.setOutputEncoding("UTF-8");

            //静态页面路径
            File htmlFile = new File(targetHtmlPath);
            String parentFolder = htmlFile.getParent();
            if (!new File(parentFolder).exists()) {
                new File(parentFolder).mkdirs();
            }
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));
            //处理模版
            template.process(data, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
