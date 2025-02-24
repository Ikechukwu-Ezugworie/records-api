package com.mkobo.assessment.recordsapi.util;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.logging.Logger;

@Component
public class CustomKeyGenerator implements KeyGenerator {

    Logger logger = Logger.getLogger(CustomKeyGenerator.class.getName());

    private static final String SEPARATOR = "_";


    @Override
    public Object generate(Object target, Method method, Object... params) {
        return target.getClass().getSimpleName() + SEPARATOR
                + method.getName() + SEPARATOR
                + StringUtils.arrayToDelimitedString(params, SEPARATOR);
    }
}
