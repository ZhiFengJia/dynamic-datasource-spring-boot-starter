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

/**
 * @author: miaozf
 * @Date: 2022-05-27
 * @Description: javax.servlet 和 jakarta.servlet 兼容
 */

public interface ServletProvider {

    /**
     * 获取参数值
     * @param name
     * @return
     */
    String getParameter(String name);

    /**
     * 获取参数值
     * @param name
     * @param defaultValue 默认值
     * @return
     */
    String getParameter(String name, String defaultValue);

    /**
     * 获取参数值
     * @param name
     * @return
     */
    String getAttribute(String name);

    /**
     * 获取参数值
     * @param name
     * @param defaultValue 默认值
     * @return
     */
    String getAttribute(String name, String defaultValue);

    /**
     * header获取参数值
     * @param name
     * @return
     */
    String getHeader(String name);

    /**
     * header获取参数值
     * @param name
     * @param defaultValue
     * @return
     */
    String getHeader(String name, String defaultValue);

    /**
     * session获取参数值
     * @param name
     * @return
     */
    String getSessionAttribute(String name);

    /**
     * session获取参数值
     * @param name
     * @param defaultValue
     * @return
     */
    String getSessionAttribute(String name, String defaultValue);
}
