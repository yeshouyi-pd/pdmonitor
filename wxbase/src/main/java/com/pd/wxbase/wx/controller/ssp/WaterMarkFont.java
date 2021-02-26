package com.pd.wxbase.wx.controller.ssp;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class WaterMarkFont {
    /**
     * @param args
     */
    public static void main(String[] args) {

        File srcImgFile = new File("D:\\file\\pic\\202012\\Cqe3ifVO.jpg");
        String logoText = "2020-12-17 09:27";

        File outputRotateImageFile = new File("D:\\file\\pic\\202012\\Cqe3ifVO-2.jpg");


        createWaterMarkByText(srcImgFile, logoText, outputRotateImageFile, 45);
    }


    public static void createWaterMarkByText(File srcImgFile, String logoText,
                                             File outputImageFile) {
        createWaterMarkByText(srcImgFile, logoText, outputImageFile, 0);
    }

    public static void createWaterMarkByText(File srcImgFile, String logoText,
                                             File outputImageFile, double degree) {
        OutputStream os = null;
        try {
            Image srcImg = ImageIO.read(srcImgFile);

            // 定义图像的宽高和图像类型
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);

            // 创建一个 Graphics2D，可以将它绘制到此 BufferedImage 中
            // Graphics2D，它是用于在 Java(tm) 平台上呈现二维形状、文本和图像的基础类。
            Graphics2D g = buffImg.createGraphics();

            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            g.drawImage(
                    srcImg.getScaledInstance(srcImg.getWidth(null),
                            srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0,
                    null);

            if (degree>0) {
                // 旋转
                // 将用角度表示的角转换为近似相等的用弧度表示的角
                g.rotate(Math.toRadians(degree),
                        (double) buffImg.getWidth() / 2,
                        (double) buffImg.getHeight() / 2);
            }

            g.setColor(Color.RED);

            g.setFont(new Font("宋体", Font.BOLD, 36));

            float alpha = 0.8f;
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                    alpha));

            // 烙上水印
            g.drawString(logoText, buffImg.getWidth()/3, buffImg.getHeight()/2);

            // 释放此图形的上下文以及它使用的所有系统资源
            g.dispose();

            os = new FileOutputStream(outputImageFile);

            // 生成图片
            ImageIO.write(buffImg, "JPG", os);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != os)
                    os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}