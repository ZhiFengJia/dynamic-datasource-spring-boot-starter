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
package com.baomidou.dynamic.datasource.provider;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author: miaozf
 * @Date: 2022-05-27
 * @Description:
 */
@Order(200)
@Component
@ConditionalOnMissingBean(value = DefaultServletProvider.class)
@ConditionalOnClass({HttpServletRequest.class})
@RequiredArgsConstructor
public class JakartaServletProvider implements ServletProvider {

    private final HttpServletRequest request;

    private final HttpServletResponse response;

    @Override
    public String getParameter(String name) {
        return request.getParameter(name);
    }

    @Override
    public String getParameter(String name, String defaultValue) {
        return toStr(request.getParameter(name), defaultValue);
    }

    @Override
    public String getAttribute(String name) {
        return toStr(request.getAttribute(name));
    }

    @Override
    public String getAttribute(String name, String defaultValue) {
        return toStr(request.getAttribute(name), defaultValue);
    }

    @Override
    public String getHeader(String name) {
        return toStr(request.getHeader(name));
    }

    @Override
    public String getHeader(String name, String defaultValue) {
        return toStr(request.getHeader(name) , defaultValue);
    }

    @Override
    public String getSessionAttribute(String name) {
        return toStr(request.getSession().getAttribute(name));
    }

    @Override
    public String getSessionAttribute(String name, String defaultValue) {
        return toStr(request.getSession().getAttribute(name) , defaultValue);
    }

    public static String toStr(Object value)
    {
        return toStr(value, null);
    }

    public static String toStr(Object value, String defaultValue)
    {
        if (null == value)
        {
            return defaultValue;
        }
        if (value instanceof String)
        {
            return (String) value;
        }
        return value.toString();
    }

}
