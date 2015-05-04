
package com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for verifycashoutrequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="verifycashoutrequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orginatingresourceid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subscriberphonenumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="referencenumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "verifycashoutrequest", propOrder = {
    "orginatingresourceid",
    "subscriberphonenumber",
    "amount",
    "referencenumber",
    "extensionparameters"
})
public class Verifycashoutrequest {

    protected String orginatingresourceid;
    protected String subscriberphonenumber;
    protected String amount;
    protected String referencenumber;
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
     * Gets the value of the subscriberphonenumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriberphonenumber() {
        return subscriberphonenumber;
    }

    /**
     * Sets the value of the subscriberphonenumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriberphonenumber(String value) {
        this.subscriberphonenumber = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmount(String value) {
        this.amount = value;
    }

    /**
     * Gets the value of the referencenumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferencenumber() {
        return referencenumber;
    }

    /**
     * Sets the value of the referencenumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferencenumber(String value) {
        this.referencenumber = value;
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
