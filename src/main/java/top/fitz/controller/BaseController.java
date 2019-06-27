package top.fitz.controller;

import top.fitz.model.UserDomain;
import top.fitz.utils.MapCache;
import top.fitz.utils.TaleUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @title: BaseController
 * @package com.fitz.controller
 * @description: 基础控制器
 * @author: junYin
 * @date: 2019-06-09 8:34
 */
public abstract class BaseController {

    protected MapCache cache = MapCache.single();

    public BaseController title(HttpServletRequest request, String title) {
        request.setAttribute("title", title);
        return this;
    }

    /**
     *@title: user
     *@description: 获取请求绑定的登录对象
     *@author: junYin
     *@date: 2019/06/09 8:41
     *@param: * @param request 1
     *@return: UserDomain
     *@throws:
     */
    public UserDomain user(HttpServletRequest request) {
        return TaleUtils.getLoginUser(request);
    }

    /**
     *@title: getUid
     *@description: 获取请求绑定登录用户Uid
     *@author: junYin
     *@date: 2019/06/09 8:43
     *@param: * @param request 1
     *@return: java.lang.Integer
     *@throws:
     */
    public Integer getUid(HttpServletRequest request) {
        return this.user(request).getUid();
    }


    /**
     *@title: join
     *@description: 数组转字符串
     *@author: junYin
     *@date: 2019/06/09 8:43
     *@param: * @param arr 1
     *@return: java.lang.String
     *@throws:
     */
    public String join(String[] arr) {
        StringBuffer buffer = new StringBuffer();
        String[] temp = arr;
        int length = arr.length;

        for (int i = 0; i < length; i++) {
            String item = temp[i];
            buffer.append(",").append(item);
        }

        return buffer.length() > 0 ? buffer.substring(1) : buffer.toString();
    }


}
