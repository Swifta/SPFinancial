
package com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for parameterExtension complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="parameterExtension">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mmoperator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="spTransactionid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="extensionparam" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parameterExtension", propOrder = {
    "mmoperator",
    "spTransactionid",
    "extensionparam"
})
public class ParameterExtension {

    protected String mmoperator;
    protected String spTransactionid;
    @XmlElement(nillable = true)
    protected List<String> extensionparam;

    /**
     * Gets the value of the mmoperator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMmoperator() {
        return mmoperator;
    }

    /**
     * Sets the value of the mmoperator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMmoperator(String value) {
        this.mmoperator = value;
    }

    /**
     * Gets the value of the spTransactionid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpTransactionid() {
        return spTransactionid;
    }

    /**
     * Sets the value of the spTransactionid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpTransactionid(String value) {
        this.spTransactionid = value;
    }

    /**
     * Gets the value of the extensionparam property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extensionparam property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtensionparam().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getExtensionparam() {
        if (extensionparam == null) {
            extensionparam = new ArrayList<String>();
        }
        return this.extensionparam;
    }

}
