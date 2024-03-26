package com.pd.monitor.netsdk.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    /**
     * 信息
     */
    String message;
    /**
     * Code
     */
    int code;
    /**
     * 数据
     */
    T data;
}
