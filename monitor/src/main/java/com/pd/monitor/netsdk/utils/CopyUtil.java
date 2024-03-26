package com.pd.monitor.netsdk.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class CopyUtil {
    /**
     * bean转为另一个bean
     *
     * @param source
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> T transfer(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        try {
            T t = targetClass.newInstance();
            BeanUtils.copyProperties(source, t);
            return t;
        } catch (InstantiationException e) {
            throw new RuntimeException("拷贝bean有误：" + e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("拷贝bean有误：" + e.getMessage(), e);
        }
    }


    /**
     * 泛型为一种bean的list转为另一种泛型bean的list
     *
     * @param sourceList
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> List<T> transfer(List<?> sourceList, Class<T> targetClass) {
        if (sourceList == null) {
            return null;
        }
        return sourceList.stream().map((source) -> transfer(source, targetClass)).collect(Collectors.toList());
    }

}
