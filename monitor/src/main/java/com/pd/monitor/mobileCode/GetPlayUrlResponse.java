
package com.pd.monitor.mobileCode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetPlayUrlResult" type="{http://tempuri.org/}retClass" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getPlayUrlResult"
})
@XmlRootElement(name = "GetPlayUrlResponse")
public class GetPlayUrlResponse {

    @XmlElement(name = "GetPlayUrlResult")
    protected RetClass getPlayUrlResult;

    /**
     * ��ȡgetPlayUrlResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link RetClass }
     *     
     */
    public RetClass getGetPlayUrlResult() {
        return getPlayUrlResult;
    }

    /**
     * ����getPlayUrlResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link RetClass }
     *     
     */
    public void setGetPlayUrlResult(RetClass value) {
        this.getPlayUrlResult = value;
    }

}
