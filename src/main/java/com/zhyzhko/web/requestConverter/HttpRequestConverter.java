package com.zhyzhko.web.requestConverter;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by user on 04.07.17.
 */
public interface HttpRequestConverter<T> {

    T fromRequest(HttpServletRequest req);
}
