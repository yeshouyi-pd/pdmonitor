package com.netsdk.lib.structure;


import com.netsdk.lib.NetSDKLib;

/** 
* @author 291189
* @description  环境光线配置信息 
* @origin autoTool
* @date 2023/11/30 16:17:33
*/
public class CFG_VIDEOIN_BACKLIGHT_BASE extends NetSDKLib.SdkStructure {
/** 
背光模式 {@link com.netsdk.lib.enumeration.EM_VIDEOIN_BACKLIGHT_MODE}
*/
public			int					emMode;
/** 
背光补偿模式，当emMode == EM_BACKLIGHT_BACKLIGHT时有效 {@link com.netsdk.lib.enumeration.EM_BACKLIGHT_MODE}
*/
public			int					emBacklightMode;
/** 
背光补偿区域，当emBacklightMode == EM_BACKLIGHT_REGION时有效
*/
public NetSDKLib.CFG_RECT stuBacklightRegion=new NetSDKLib.CFG_RECT();
/** 
宽动态值，emMode == EM_BACKLIGHT_WIDEDYNAMIC时有效
*/
public			int					nWideDynamicRange;
/** 
强光抑制，emMode == EM_BACKLIGHT_GLAREINHIBITION时有效
*/
public			int					nGlareInhibition;
/** 
SSA对比度调节模式，emMode==EM_BACKLIGHT_SSA时有效 {@link com.netsdk.lib.enumeration.EM_INTENSITY_MODE}
*/
public			int					emInitensityMode;
/** 
SSA手动调整强度值，emInitensityMode==EM_INTENSITY_MANUAL时有效
*/
public			int					nIntensity;

public			CFG_VIDEOIN_BACKLIGHT_BASE(){
}

    @Override
    public String toString() {
        return "CFG_VIDEOIN_BACKLIGHT_BASE{" +
                "emMode=" + emMode +
                ", emBacklightMode=" + emBacklightMode +
                ", stuBacklightRegion=" + stuBacklightRegion +
                ", nWideDynamicRange=" + nWideDynamicRange +
                ", nGlareInhibition=" + nGlareInhibition +
                ", emInitensityMode=" + emInitensityMode +
                ", nIntensity=" + nIntensity +
                '}';
    }
}