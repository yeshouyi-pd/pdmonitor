package com.pd.monitor.netsdk.callback;

import com.pd.monitor.netsdk.lib.NetSDKLib;
import com.pd.monitor.netsdk.lib.common.SavePath;
import com.sun.jna.Pointer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

/**
 * @author xutongxue
 * @date 2023/8/28 14:23
 */
public class FSnapReceiveCallback implements NetSDKLib.fSnapRev{
    BufferedImage bufferedImage = null;
    public void invoke(NetSDKLib.LLong lLoginID, Pointer pBuf, int RevLen, int EncodeType, int CmdSerial, Pointer dwUser) {
        if(pBuf != null && RevLen > 0) {
            String strFileName = SavePath.getSavePath().getSaveCapturePath();

            System.out.println("strFileName = " + strFileName);

            byte[] buf = pBuf.getByteArray(0, RevLen);
            ByteArrayInputStream byteArrInput = new ByteArrayInputStream(buf);
            try {
                bufferedImage = ImageIO.read(byteArrInput);
                if(bufferedImage == null) {
                    return;
                }
                ImageIO.write(bufferedImage, "jpg", new File(strFileName));
            } catch (IOException e) {
                e.printStackTrace();
            }

            // todo 照片回显

        }
    }
}
