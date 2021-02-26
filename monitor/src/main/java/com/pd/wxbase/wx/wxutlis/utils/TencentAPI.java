package com.pd.wxbase.wx.wxutlis.utils;

/**
 *@Title TencentAPI.java
 *@description TODO
 *@time 2018-5-12 上午10:19:06
 *@author ldq
 *@version 1.0
 */
public class TencentAPI {
	//自己的APPID
    public static final String APP_ID_AI = "1106826297";

    //自己的APPKEY
    public static final String APP_KEY_AI = "96UJulPOwrbSy7G0";

	/*public static final String APP_ID_AI = "2159980541";
	public static final String APP_KEY_AI = "2RcYrJiPAfYMqxXu";*/
    
    public static final String PERSON_ID = "https://api.ai.qq.com/fcgi-bin/ocr/ocr_idcardocr";  //身份证识别
    public static final String PHOTO_SPEAK = "https://api.ai.qq.com/fcgi-bin/vision/vision_imgtotext"; //看图说话
    public static final String SCENE_RECOGNITION  = "https://api.ai.qq.com/fcgi-bin/vision/vision_scener"; //场景识别：对图行进行场景识别，快速找出图片中包含的场景信息
    public static final String OBJECT_RECOGNITION = "https://api.ai.qq.com/fcgi-bin/vision/vision_objectr"; //物体识别:对图行进行物体识别，快速找出图片中包含的物体信息
    public static final String IMAGE_LABEL = "https://api.ai.qq.com/fcgi-bin/image/image_tag"; //图像标签识别:识别一个图像的标签信息,对图像分类。
    public static final String FACE_MERGE="https://api.ai.qq.com/fcgi-bin/ptu/ptu_facemerge";  //人脸融合


	//腾讯云识别
	public static final  String SFZ_ID = "https://ocr.tencentcloudapi.com";

	/**
	 * 人脸检测接口地址
	 */
	public static final String FACE_DETECT = "https://api.ai.qq.com/fcgi-bin/face/face_detectface";
	/**
	 * 人脸美妆接口地址
	 */
	public static final String FACE_COSMETIC = "https://api.ai.qq.com/fcgi-bin/face/face_detectface";
	/**
	 * 人脸变装
	 */
	public static final String FACE_DECORATION = "https://api.ai.qq.com/fcgi-bin/ptu/ptu_facedecoration";
	public static final String FACE_FACECOMPARE = "https://api.ai.qq.com/fcgi-bin/face/face_facecompare";
	//身份证识别接口地址
	public static final String OCR_IDCARD = "https://api.ai.qq.com/fcgi-bin/ocr/ocr_idcardocr";
	public static final String OCR_GENERAL = "https://api.ai.qq.com/fcgi-bin/ocr/ocr_generalocr";
	//基本文本分析-分词接口地址
	public static final String NLP_WORDSEG = "https://api.ai.qq.com/fcgi-bin/nlp/nlp_wordseg";
	//情感分析识别 	
	public static final String NLP_TEXTPOLAR = "https://api.ai.qq.com/fcgi-bin/nlp/nlp_textpolar";
	//语音合成-AI LAB
	public static final String AAI_TTS = "https://api.ai.qq.com/fcgi-bin/aai/aai_tts";
	//语音合成-优图
	public static final String AAI_TTA = "https://api.ai.qq.com/fcgi-bin/aai/aai_tta";
	//语音识别-echo版
	public static final String AAI_ASR = "https://api.ai.qq.com/fcgi-bin/aai/aai_asr";
	//长语音识别	
	public static final String AAI_WXASRLONG = "https://api.ai.qq.com/fcgi-bin/aai/aai_wxasrlong";
	//语音识别-流式版(WeChat AI) 	对音频进行流式识别，轻松实现边录音边识别 	
	public static final String AAI_WXASRS = "https://api.ai.qq.com/fcgi-bin/aai/aai_wxasrs";
	//银行卡识别
	public static final String OCR_BANK = "https://api.ai.qq.com/fcgi-bin/ocr/ocr_creditcardocr";
	//行驶证驾驶证OCR识别
	public static final String OCR_DRIVERLICENSEOCR="https://api.ai.qq.com/fcgi-bin/ocr/ocr_driverlicenseocr";
	//智能鉴黄  识别一个图像是否为色情图像
	public static final String VISION_PORN = "https://api.ai.qq.com/fcgi-bin/vision/vision_porn";
}
