package com.mkobo.assessment.recordsapi.util;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.logging.Logger;

@Component
public class CustomKeyGenerator implements KeyGenerator {

    Logger        logger = Logger.getLogger(CustomKeyGenerator.class.getName());


    @Override
    public Object generate(Object target, Method method, Object... params) {
        return target.getClass().getSimpleName() + "_"
                + method.getName() + "_"
                + StringUtils.arrayToDelimitedString(params, "_");
    }
}
