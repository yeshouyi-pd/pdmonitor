package com.pd.monitor.netsdk.controller;

import com.pd.monitor.netsdk.module.PtzControlModule;
import com.pd.monitor.netsdk.utils.Result;
import com.pd.monitor.netsdk.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 云台控制接口实现
 * 主要有 ：八个方向控制、变倍、变焦、光圈功能
 */
@Slf4j
@RequestMapping("/ptzController")
@RestController
public class PtzController {


    @GetMapping("/ptzControlStart/{type}/{nChannelID}/{lParam1}/{lParam2}")
    public Result ptzControlStart(@PathVariable String type, @PathVariable int nChannelID, @PathVariable int lParam1, @PathVariable int lParam2){
        if("Up".equals(type)){
            /**
             * 向上
             */
            if(PtzControlModule.ptzControlUpStart(nChannelID,lParam1,lParam2)){
                return ResultUtils.getSuccessResult("操作成功");
            }
        }else if("Down".equals(type)){
            /**
             * 向下
             */
            if(PtzControlModule.ptzControlDownStart(nChannelID,lParam1,lParam2)){
                return ResultUtils.getSuccessResult("操作成功");
            }
        }else if("Left".equals(type)){
            /**
             * 向左
             */
            if(PtzControlModule.ptzControlLeftStart(nChannelID,lParam1,lParam2)){
                return ResultUtils.getSuccessResult("操作成功");
            }
        }else if("Right".equals(type)){
            /**
             * 向右
             */
            if(PtzControlModule.ptzControlRightStart(nChannelID,lParam1,lParam2)){
                return ResultUtils.getSuccessResult("操作成功");
            }
        }else if("LeftUp".equals(type)){
            /**
             * 向左上
             */
            if(PtzControlModule.ptzControlLeftUpStart(nChannelID,lParam2,lParam2)){
                return ResultUtils.getSuccessResult("操作成功");
            }
        }else if("RightUp".equals(type)){
            /**
             * 向右上
             */
            if(PtzControlModule.ptzControlRightUpStart(nChannelID,lParam2,lParam2)){
                return ResultUtils.getSuccessResult("操作成功");
            }
        }else if("LeftDown".equals(type)){
            /**
             * 向左下
             */
            if(PtzControlModule.ptzControlLeftDownStart(nChannelID,lParam2,lParam2)){
                return ResultUtils.getSuccessResult("操作成功");
            }
        }else if("RightDown".equals(type)){
            /**
             * 向右下
             */
            if(PtzControlModule.ptzControlRightDownStart(nChannelID,lParam2,lParam2)){
                return ResultUtils.getSuccessResult("操作成功");
            }
        }else if("ZoomTele".equals(type)){
            /**
             * 变倍+
             */
            if(PtzControlModule.ptzControlZoomAddStart(nChannelID,lParam2)){
                return ResultUtils.getSuccessResult("操作成功");
            }
        }else if("ZoomWide".equals(type)){
            /**
             * 变倍-
             */
            if(PtzControlModule.ptzControlZoomDecStart(nChannelID,lParam2)){
                return ResultUtils.getSuccessResult("操作成功");
            }
        }else if("FocusNear".equals(type)){
            /**
             * 变焦+
             */
            if(PtzControlModule.ptzControlFocusAddStart(nChannelID,lParam2)){
                return ResultUtils.getSuccessResult("操作成功");
            }
        }else if("FocusFar".equals(type)){
            /**
             * 变焦-
             */
            if(PtzControlModule.ptzControlFocusDecStart(nChannelID,lParam2)){
                return ResultUtils.getSuccessResult("操作成功");
            }
        }else if("IrisLarge".equals(type)){
            /**
             * 光圈+
             */
            if(PtzControlModule.ptzControlIrisAddStart(nChannelID,lParam2)){
                return ResultUtils.getSuccessResult("操作成功");
            }
        }else if("IrisSmall".equals(type)){
            /**
             * 光圈-
             */
            if(PtzControlModule.ptzControlIrisDecStart(nChannelID,lParam2)){
                return ResultUtils.getSuccessResult("操作成功");
            }
        }else if("GotoPreset".equals(type)){
            /**
             * 预置点
             */
            if(PtzControlModule.ptzControlPointMoveStart(nChannelID,lParam2)){
                return ResultUtils.getSuccessResult("操作成功");
            }
        }
        return ResultUtils.getFailResult("操作失败");
    }

    @GetMapping("/ptzControlEnd/{type}/{nChannelID}")
    public Result ptzControlEnd(@PathVariable String type, @PathVariable int nChannelID){
        if("Up".equals(type)){
            /**
             * 向上
             */
            if(PtzControlModule.ptzControlUpEnd(nChannelID)){
                return ResultUtils.getSuccessResult("操作成功");
            }
        }else if("Down".equals(type)){
            /**
             * 向下
             */
            if(PtzControlModule.ptzControlDownEnd(nChannelID)){
                return ResultUtils.getSuccessResult("操作成功");
            }
        }else if("Left".equals(type)){
            /**
             * 向左
             */
            if(PtzControlModule.ptzControlLeftEnd(nChannelID)){
                return ResultUtils.getSuccessResult("操作成功");
            }
        }else if("Right".equals(type)){
            /**
             * 向右
             */
            if(PtzControlModule.ptzControlRightEnd(nChannelID)){
                return ResultUtils.getSuccessResult("操作成功");
            }
        }else if("LeftUp".equals(type)){
            /**
             * 向左上
             */
            if(PtzControlModule.ptzControlLeftUpEnd(nChannelID)){
                return ResultUtils.getSuccessResult("操作成功");
            }
        }else if("RightUp".equals(type)){
            /**
             * 向右上
             */
            if(PtzControlModule.ptzControlRightUpEnd(nChannelID)){
                return ResultUtils.getSuccessResult("操作成功");
            }
        }else if("LeftDown".equals(type)){
            /**
             * 向左下
             */
            if(PtzControlModule.ptzControlLeftDownEnd(nChannelID)){
                return ResultUtils.getSuccessResult("操作成功");
            }
        }else if("RightDown".equals(type)){
            /**
             * 向右下
             */
            if(PtzControlModule.ptzControlRightDownEnd(nChannelID)){
                return ResultUtils.getSuccessResult("操作成功");
            }
        }else if("ZoomTele".equals(type)){
            /**
             * 变倍+
             */
            if(PtzControlModule.ptzControlZoomAddEnd(nChannelID)){
                return ResultUtils.getSuccessResult("操作成功");
            }
        }else if("ZoomWide".equals(type)){
            /**
             * 变倍-
             */
            if(PtzControlModule.ptzControlZoomDecEnd(nChannelID)){
                return ResultUtils.getSuccessResult("操作成功");
            }
        }else if("FocusNear".equals(type)){
            /**
             * 变焦+
             */
            if(PtzControlModule.ptzControlFocusAddEnd(nChannelID)){
                return ResultUtils.getSuccessResult("操作成功");
            }
        }else if("FocusFar".equals(type)){
            /**
             * 变焦-
             */
            if(PtzControlModule.ptzControlFocusDecEnd(nChannelID)){
                return ResultUtils.getSuccessResult("操作成功");
            }
        }else if("IrisLarge".equals(type)){
            /**
             * 光圈+
             */
            if(PtzControlModule.ptzControlIrisAddEnd(nChannelID)){
                return ResultUtils.getSuccessResult("操作成功");
            }
        }else if("IrisSmall".equals(type)){
            /**
             * 光圈-
             */
            if(PtzControlModule.ptzControlIrisDecEnd(nChannelID)){
                return ResultUtils.getSuccessResult("操作成功");
            }
        }else if("GotoPreset".equals(type)){
            /**
             * 预置点
             */
            if(PtzControlModule.ptzControlPointMoveEnd(nChannelID)){
                return ResultUtils.getSuccessResult("操作成功");
            }
        }
        return ResultUtils.getFailResult("操作失败");
    }

}
