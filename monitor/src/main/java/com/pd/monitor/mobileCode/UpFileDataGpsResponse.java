
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
 *         &lt;element name="UpFileDataGpsResult" type="{http://tempuri.org/}retClass" minOccurs="0"/>
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
    "upFileDataGpsResult"
})
@XmlRootElement(name = "UpFileDataGpsResponse")
public class UpFileDataGpsResponse {

    @XmlElement(name = "UpFileDataGpsResult")
    protected RetClass upFileDataGpsResult;

    /**
     * ��ȡupFileDataGpsResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link RetClass }
     *     
     */
    public RetClass getUpFileDataGpsResult() {
        return upFileDataGpsResult;
    }

    /**
     * ����upFileDataGpsResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link RetClass }
     *     
     */
    public void setUpFileDataGpsResult(RetClass value) {
        this.upFileDataGpsResult = value;
    }

}
