
package com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for verifycashoutrequestResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="verifycashoutrequestResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0}verifycashoutresponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "verifycashoutrequestResponse", propOrder = {
    "_return"
})
public class VerifycashoutrequestResponse {

    @XmlElement(name = "return")
    protected Verifycashoutresponse _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link Verifycashoutresponse }
     *     
     */
    public Verifycashoutresponse getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link Verifycashoutresponse }
     *     
     */
    public void setReturn(Verifycashoutresponse value) {
        this._return = value;
    }

}
