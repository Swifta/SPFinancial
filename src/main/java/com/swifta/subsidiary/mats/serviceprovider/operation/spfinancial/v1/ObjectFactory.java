
package com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CashinrequestResponse_QNAME = new QName("http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0", "cashinrequestResponse");
    private final static QName _Cashoutrequest_QNAME = new QName("http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0", "cashoutrequest");
    private final static QName _Cashinrequest_QNAME = new QName("http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0", "cashinrequest");
    private final static QName _CashoutrequestResponse_QNAME = new QName("http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0", "cashoutrequestResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CashinrequestResponse }
     * 
     */
    public CashinrequestResponse createCashinrequestResponse() {
        return new CashinrequestResponse();
    }

    /**
     * Create an instance of {@link Cashoutrequest }
     * 
     */
    public Cashoutrequest createCashoutrequest() {
        return new Cashoutrequest();
    }

    /**
     * Create an instance of {@link Cashinrequest }
     * 
     */
    public Cashinrequest createCashinrequest() {
        return new Cashinrequest();
    }

    /**
     * Create an instance of {@link CashoutrequestResponse }
     * 
     */
    public CashoutrequestResponse createCashoutrequestResponse() {
        return new CashoutrequestResponse();
    }

    /**
     * Create an instance of {@link Cashoutresponse }
     * 
     */
    public Cashoutresponse createCashoutresponse() {
        return new Cashoutresponse();
    }

    /**
     * Create an instance of {@link ParameterExtension }
     * 
     */
    public ParameterExtension createParameterExtension() {
        return new ParameterExtension();
    }

    /**
     * Create an instance of {@link Cashinresponse }
     * 
     */
    public Cashinresponse createCashinresponse() {
        return new Cashinresponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CashinrequestResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0", name = "cashinrequestResponse")
    public JAXBElement<CashinrequestResponse> createCashinrequestResponse(CashinrequestResponse value) {
        return new JAXBElement<CashinrequestResponse>(_CashinrequestResponse_QNAME, CashinrequestResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Cashoutrequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0", name = "cashoutrequest")
    public JAXBElement<Cashoutrequest> createCashoutrequest(Cashoutrequest value) {
        return new JAXBElement<Cashoutrequest>(_Cashoutrequest_QNAME, Cashoutrequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Cashinrequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0", name = "cashinrequest")
    public JAXBElement<Cashinrequest> createCashinrequest(Cashinrequest value) {
        return new JAXBElement<Cashinrequest>(_Cashinrequest_QNAME, Cashinrequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CashoutrequestResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0", name = "cashoutrequestResponse")
    public JAXBElement<CashoutrequestResponse> createCashoutrequestResponse(CashoutrequestResponse value) {
        return new JAXBElement<CashoutrequestResponse>(_CashoutrequestResponse_QNAME, CashoutrequestResponse.class, null, value);
    }

}
