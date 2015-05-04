
package com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for balancerequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="balancerequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orginatingresourceid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="extensionparameters" type="{http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0}parameterExtension" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "balancerequest", propOrder = {
    "orginatingresourceid",
    "extensionparameters"
})
public class Balancerequest {

    protected String orginatingresourceid;
    protected ParameterExtension extensionparameters;

    /**
     * Gets the value of the orginatingresourceid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrginatingresourceid() {
        return orginatingresourceid;
    }

    /**
     * Sets the value of the orginatingresourceid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrginatingresourceid(String value) {
        this.orginatingresourceid = value;
    }

    /**
     * Gets the value of the extensionparameters property.
     * 
     * @return
     *     possible object is
     *     {@link ParameterExtension }
     *     
     */
    public ParameterExtension getExtensionparameters() {
        return extensionparameters;
    }

    /**
     * Sets the value of the extensionparameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParameterExtension }
     *     
     */
    public void setExtensionparameters(ParameterExtension value) {
        this.extensionparameters = value;
    }

}
