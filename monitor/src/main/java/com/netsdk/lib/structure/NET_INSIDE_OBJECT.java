package com.netsdk.lib.structure;

import com.netsdk.lib.NetSDKLib;


/**
 * 物品类型
 *
 * @author ： 260611
 * @since ： Created in 2021/10/19 09:19
 */
public class NET_INSIDE_OBJECT extends NetSDKLib.SdkStructure {

    /**
     *  物品危险等级 {@link NetSDKLib.EM_DANGER_GRADE_TYPE }
     */
    public int					emDangerGrade;

    /**
     *  物品类型  {@link com.netsdk.lib.enumeration.EM_INSIDE_OBJECT_TYPE}
     */
    public int					emObjType;

    /**
     *  相似度
     */
    public int					nSimilarity;

    /**
     *  包围盒
     */
    public NetSDKLib.NET_RECT stuBoundingBox = new NetSDKLib.NET_RECT();

    /**
     * 自定义物品类型，emObjType为 EM_INSIDE_OBJECT_UNKNOWN 时使用
     */
    public byte[]     szObjectType=new byte[32];

    /**
     *  保留字节,留待扩展
     */
    public byte					byReserved[] = new byte[76];



    @Override
    public String toString() {
        return "NET_INSIDE_OBJECT{" +
                "emDangerGrade=" + emDangerGrade +
                ", emObjType=" + emObjType +
                ", nSimilarity=" + nSimilarity +
                ", stuBoundingBox=" + stuBoundingBox.toString() +
                ", szObjectType=" + new String(szObjectType)+
                '}';
    }
}