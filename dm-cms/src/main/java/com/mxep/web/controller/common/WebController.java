package com.mxep.web.controller.common;

import com.mxep.core.utils.EncodeUtils;
import com.mxep.model.sys.User;
import com.mxep.web.common.exception.WebRequestException;
import com.mxep.web.service.AccountService;
import com.mxep.web.service.JxlsExcelView;
import com.mxep.web.shiro.ShiroDbRealm;
import com.mxep.web.web.DateEditor;
import com.mxep.web.web.JsonMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 所有控制器的基类
 *
 * @author ranfi
 */
@ControllerAdvice
public abstract class WebController {

    @Resource
    private AccountService accountService;

    /**
     * log4j 记录器
     */
    protected static final Logger log = LoggerFactory.getLogger(WebController.class);

    protected JxlsExcelView jxlsExcelView;

    @Resource
    public void setJxlsExcelView(JxlsExcelView jxlsExcelView) {
        this.jxlsExcelView = jxlsExcelView;
    }


    /**
     * 获取导出Excel视图
     *
     * @param request
     * @return
     * @throws Exception
     */
    protected ModelAndView getExcelView(HttpServletRequest request) throws Exception {

        ModelAndView mav = new ModelAndView();
        String fileName = request.getParameter("fileName");
        String location = "static/models/" + fileName;
        String exportPageNum = request.getParameter("exportPageNum");
        if (null == exportPageNum || "".equals(exportPageNum)) {
            jxlsExcelView.setFileName(fileName + ".xlsx");
        } else {
            jxlsExcelView.setFileName(fileName + "_" + exportPageNum + ".xlsx");
        }
        jxlsExcelView.setLocation(location);
        mav.setView(jxlsExcelView);
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        DecimalFormat df = new DecimalFormat("##0.00");
        mav.addObject("decimalFormat", df);
        mav.addObject("timeFormat", timeFormat);
        mav.addObject("dateFormat", dateFormat);

        return mav;
    }


    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        //对于需要转换为Date类型的属性，使用DateEditor进行处理
        binder.registerCustomEditor(Date.class, new DateEditor());
    }

    /**
     * 获取登陆用户
     *
     * @return 登陆用户
     */
    public User getLoginUser() {
        ShiroDbRealm.ShiroUser user = (ShiroDbRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
        if (user != null) {
            return accountService.findUserByAccount(user.getAccount());
        }
        return null;
    }

    /**
     * 解析绑定结果
     *
     * @param result 绑定结果
     * @param msg    消息
     * @return Json返回对象
     */
    public JsonMap parseErrorResult(BindingResult result, String msg) {
        JsonMap ret = new JsonMap(-10, msg);
        Map<String, String> errors = new HashMap<String, String>();
        List<FieldError> fieldErrors = result.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ret.addAttribute("errors", errors);
        return ret;
    }

    @ExceptionHandler({WebRequestException.class})
    @ResponseBody
    public void webDataException(WebRequestException ex, HttpServletRequest request, HttpServletResponse response) {
        response.addHeader("Cache-Control", "no-cache");
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Expires", "-1");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type", "text/plain;charset=utf-8");
        response.setStatus(300);
        String exceptionName = ex.getClass().getName();
        if (exceptionName.lastIndexOf("WebRequestException") != -1) {
            response.setHeader("Error-Json",
                    EncodeUtils.urlEncode("{\"code\":\"WebRequestException\",\"msg\":\"" + ex.getMessage() + "\"}"));
        } else if (exceptionName.lastIndexOf("CannotCreateTransactionException") >= 0) {
            response.setHeader("Error-Json",
                    EncodeUtils.urlEncode("{\"code\":\"Exception\",\"msg\":\"数据库连接错误，请确认数据库已经启动\"}"));
        } else if (exceptionName.lastIndexOf("Exception") >= 0 && exceptionName.indexOf("DataAccess") >= 0) {
            response.setHeader("Error-Json",
                    EncodeUtils.urlEncode("{\"code\":\"Exception\",\"msg\":\"数据库操作异常，可能数据表之间存在关联,请与管理员联系\"}"));
        } else {
            response.setHeader("Error-Json", EncodeUtils.urlEncode("{\"code\":\"Exception\",\"msg\":\"系统内部错误\"}"));
        }

    }

    @ExceptionHandler({RuntimeException.class})
    public ModelAndView webDataException(RuntimeException ex, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("error/500");
        model.addObject("errMsg", "系统不给力,请稍后再试!");
        model.addObject("ctx", request.getContextPath());
        return model;
    }


    /**
     * @ClassName: WebController.java
     * @Description: 判断是否是ajax请求
     * @param: WebController
     * @date: Aug 13, 2013
     * @return: boolean
     */
    public boolean isAjax(HttpServletRequest request) {
        String accept = request.getHeader("accept");
        String header = request.getHeader("X-Requested-With");
        if (header != null && "XMLHttpRequest".equals(header)) {
            return true;
        } else if (!"".equals(accept) && null != accept) {
            return accept.indexOf("application/json") >= 0;
        } else {
            return false;
        }
    }

    @Deprecated
    public String getErrorStack(Exception e, int length) {
        String error = null;
        if (e != null) {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PrintStream ps = new PrintStream(baos);
                e.printStackTrace(ps);
                error = baos.toString();
                if (length > 0) {
                    if (length > error.length()) {
                        length = error.length();
                    }
                    error = error.substring(0, length);
                }
                baos.close();
                ps.close();
            } catch (Exception e1) {
                error = e.toString();
            }
        }
        return error;
    }


    /**
     * 将String的搜索条件转为Map
     *
     * @param searchParams
     * @param prefix
     * @return
     */
    public Map<String, Object> getSearchParameters(String searchParams, String prefix) {
        Map<String, Object> params = new TreeMap<String, Object>();
        if (StringUtils.isBlank(searchParams)) {
            return null;
        }
        String[] paramArray = searchParams.split("&");
        if (paramArray.length == 0) {
            return null;
        }
        for (String tempStr : paramArray) {
            if (StringUtils.isNoneBlank(tempStr) && tempStr.startsWith(prefix)) {
                String[] keyAndVal = tempStr.split("=");
                if (keyAndVal.length > 1)
                    params.put(keyAndVal[0].substring(prefix.length()), keyAndVal[1]);
                else
                    continue;
            }
        }
        return params;
    }
}
