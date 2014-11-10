
package com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for cashoutresponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cashoutresponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orginatingpartnerfee" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orginatingpartnerbalanceafter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="destinationpartnerbalanceafter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statuscode" type="{http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0}statusCode" minOccurs="0"/>
 *         &lt;element name="ResponseMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "cashoutresponse", propOrder = {
    "orginatingpartnerfee",
    "orginatingpartnerbalanceafter",
    "destinationpartnerbalanceafter",
    "statuscode",
    "responseMessage",
    "extensionparameters"
})
public class Cashoutresponse {

    protected String orginatingpartnerfee;
    protected String orginatingpartnerbalanceafter;
    protected String destinationpartnerbalanceafter;
    @XmlSchemaType(name = "string")
    protected StatusCode statuscode;
    @XmlElement(name = "ResponseMessage")
    protected String responseMessage;
    protected ParameterExtension extensionparameters;

    /**
     * Gets the value of the orginatingpartnerfee property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrginatingpartnerfee() {
        return orginatingpartnerfee;
    }

    /**
     * Sets the value of the orginatingpartnerfee property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrginatingpartnerfee(String value) {
        this.orginatingpartnerfee = value;
    }

    /**
     * Gets the value of the orginatingpartnerbalanceafter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrginatingpartnerbalanceafter() {
        return orginatingpartnerbalanceafter;
    }

    /**
     * Sets the value of the orginatingpartnerbalanceafter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrginatingpartnerbalanceafter(String value) {
        this.orginatingpartnerbalanceafter = value;
    }

    /**
     * Gets the value of the destinationpartnerbalanceafter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationpartnerbalanceafter() {
        return destinationpartnerbalanceafter;
    }

    /**
     * Sets the value of the destinationpartnerbalanceafter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationpartnerbalanceafter(String value) {
        this.destinationpartnerbalanceafter = value;
    }

    /**
     * Gets the value of the statuscode property.
     * 
     * @return
     *     possible object is
     *     {@link StatusCode }
     *     
     */
    public StatusCode getStatuscode() {
        return statuscode;
    }

    /**
     * Sets the value of the statuscode property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusCode }
     *     
     */
    public void setStatuscode(StatusCode value) {
        this.statuscode = value;
    }

    /**
     * Gets the value of the responseMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     * Sets the value of the responseMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseMessage(String value) {
        this.responseMessage = value;
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
