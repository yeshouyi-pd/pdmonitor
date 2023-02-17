
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
 *         &lt;element name="UpFileDataResult" type="{http://tempuri.org/}retClass" minOccurs="0"/>
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
    "upFileDataResult"
})
@XmlRootElement(name = "UpFileDataResponse")
public class UpFileDataResponse {

    @XmlElement(name = "UpFileDataResult")
    protected RetClass upFileDataResult;

    /**
     * ��ȡupFileDataResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link RetClass }
     *     
     */
    public RetClass getUpFileDataResult() {
        return upFileDataResult;
    }

    /**
     * ����upFileDataResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link RetClass }
     *     
     */
    public void setUpFileDataResult(RetClass value) {
        this.upFileDataResult = value;
    }

}
