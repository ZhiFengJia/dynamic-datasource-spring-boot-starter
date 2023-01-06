/*
 * Copyright © 2018 organization baomidou
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.baomidou.dynamic.datasource.processor.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author: miaozf
 */
@Slf4j
@Order(100)
@Component
@ConditionalOnClass({HttpServletRequest.class})
public class JavaxServletProvider implements ServletProvider {

    public static String toStr(Object value) {
        return toStr(value, null);
    }

    public static String toStr(Object value, String defaultValue) {
        if (null == value) {
            return defaultValue;
        }
        if (value instanceof String) {
            return (String) value;
        }
        return value.toString();
    }

    @Override
    public String getParameter(String name) {
        return this.getRequest().getParameter(name);
    }

    @Override
    public String getParameter(String name, String defaultValue) {
        return toStr(this.getRequest().getParameter(name), defaultValue);
    }

    @Override
    public String getAttribute(String name) {
        return toStr(this.getRequest().getAttribute(name));
    }

    @Override
    public String getAttribute(String name, String defaultValue) {
        return toStr(this.getRequest().getAttribute(name), defaultValue);
    }

    @Override
    public String getHeader(String name) {
        return toStr(this.getRequest().getHeader(name));
    }

    @Override
    public String getHeader(String name, String defaultValue) {
        return toStr(this.getRequest().getHeader(name), defaultValue);
    }

    @Override
    public String getSessionAttribute(String name) {
        return toStr(this.getRequest().getSession().getAttribute(name));
    }

    @Override
    public String getSessionAttribute(String name, String defaultValue) {
        return toStr(this.getRequest().getSession().getAttribute(name), defaultValue);
    }

    /**
     * 获取request
     */
    private HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取response
     */
    private HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取session
     */
    private HttpSession getSession() {
        return getRequest().getSession();
    }

    private ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }
}