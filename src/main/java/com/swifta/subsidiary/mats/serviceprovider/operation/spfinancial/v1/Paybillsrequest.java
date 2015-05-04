
package com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for paybillsrequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="paybillsrequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orginatingresourceid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="merchantcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sendingdescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receivingdescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "paybillsrequest", propOrder = {
    "orginatingresourceid",
    "merchantcode",
    "amount",
    "sendingdescription",
    "receivingdescription",
    "extensionparameters"
})
public class Paybillsrequest {

    protected String orginatingresourceid;
    protected String merchantcode;
    protected String amount;
    protected String sendingdescription;
    protected String receivingdescription;
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
     * Gets the value of the merchantcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMerchantcode() {
        return merchantcode;
    }

    /**
     * Sets the value of the merchantcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMerchantcode(String value) {
        this.merchantcode = value;
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
     * Gets the value of the sendingdescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendingdescription() {
        return sendingdescription;
    }

    /**
     * Sets the value of the sendingdescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendingdescription(String value) {
        this.sendingdescription = value;
    }

    /**
     * Gets the value of the receivingdescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceivingdescription() {
        return receivingdescription;
    }

    /**
     * Sets the value of the receivingdescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceivingdescription(String value) {
        this.receivingdescription = value;
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
