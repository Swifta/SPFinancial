
package com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for transfertobankrequestResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="transfertobankrequestResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0}transfertobankresponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transfertobankrequestResponse", propOrder = {
    "_return"
})
public class TransfertobankrequestResponse {

    @XmlElement(name = "return")
    protected Transfertobankresponse _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link Transfertobankresponse }
     *     
     */
    public Transfertobankresponse getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link Transfertobankresponse }
     *     
     */
    public void setReturn(Transfertobankresponse value) {
        this._return = value;
    }

}
