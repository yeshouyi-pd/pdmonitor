package com.pd.monitor.wx;

import com.pd.server.main.dto.ResponseDto;
import com.pd.server.exception.WxException;
import com.pd.server.exception.WxStrException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerWxExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerWxExceptionHandler.class);

    @ExceptionHandler(value = WxException.class)
    @ResponseBody
    public ResponseDto businessExceptionHandler(WxException e) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccess(false);
        LOG.error("业务异常：{}", e.getCode().getDesc());
        responseDto.setMessage(e.getCode().getDesc());
        return responseDto;
    }

    @ExceptionHandler(value = WxStrException.class)
    @ResponseBody
    public ResponseDto businessExceptionHandler(WxStrException e) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccess(false);
        LOG.error("业务异常：{}", e.getMsg()

        );
        responseDto.setMessage(e.getMsg());
        return responseDto;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseDto businessExceptionHandler(Exception e) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccess(false);
        LOG.error("业务异常：{}", e.getMessage()

        );
        responseDto.setMessage("系统异常,请稍后重试!");
        return responseDto;
    }
}
