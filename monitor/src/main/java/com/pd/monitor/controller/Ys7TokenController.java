package com.pd.monitor.controller;


import com.pd.monitor.utils.Ys7Utils;
import com.pd.server.main.dto.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ys7Token")
public class Ys7TokenController {

    private static final Logger LOG = LoggerFactory.getLogger(Ys7TokenController.class);

    /**
     * 获取萤石云token
     * @return
     */
    @GetMapping("/getLatestDate")
    public ResponseDto getLatestDate() {
        ResponseDto responseDto = new ResponseDto();
        String token_ys = Ys7Utils.YS_TOKEN;
        responseDto.setContent(token_ys);
        return responseDto;
    }
}
