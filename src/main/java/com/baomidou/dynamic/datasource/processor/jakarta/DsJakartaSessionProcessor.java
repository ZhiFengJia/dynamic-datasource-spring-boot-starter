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
package com.baomidou.dynamic.datasource.processor.jakarta;

import com.baomidou.dynamic.datasource.processor.DsProcessor;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;


/**
 * @author TaoYu
 * @since 3.6.0
 */
public class DsJakartaSessionProcessor extends DsProcessor {

    /**
     * session开头
     */
    private static final String SESSION_PREFIX = "#session";

    private static final Method GET_REQUEST_METHOD;

    static {
        try {
            GET_REQUEST_METHOD=ServletRequestAttributes.class.getDeclaredMethod("getRequest");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean matches(String key) {
        return key.startsWith(SESSION_PREFIX);
    }

    @SneakyThrows
    @Override
    public String doDetermineDatasource(MethodInvocation invocation, String key) {
            Object invoke = GET_REQUEST_METHOD.invoke(RequestContextHolder.getRequestAttributes());
            HttpServletRequest request = (HttpServletRequest) invoke;
            return request.getSession().getAttribute(key.substring(9)).toString();
    }
}