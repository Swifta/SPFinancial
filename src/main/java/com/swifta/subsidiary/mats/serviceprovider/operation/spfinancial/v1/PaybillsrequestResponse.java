
package com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for paybillsrequestResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="paybillsrequestResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0}paybillsresponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "paybillsrequestResponse", propOrder = {
    "_return"
})
public class PaybillsrequestResponse {

    @XmlElement(name = "return")
    protected Paybillsresponse _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link Paybillsresponse }
     *     
     */
    public Paybillsresponse getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link Paybillsresponse }
     *     
     */
    public void setReturn(Paybillsresponse value) {
        this._return = value;
    }

}