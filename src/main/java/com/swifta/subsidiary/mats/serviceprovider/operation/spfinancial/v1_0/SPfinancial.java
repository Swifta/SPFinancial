package com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1_0;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.0.2
 * 2015-04-28T10:58:20.617+01:00
 * Generated source version: 3.0.2
 * 
 */
@WebService(targetNamespace = "http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0", name = "SPfinancial")
@XmlSeeAlso({com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.ObjectFactory.class})
public interface SPfinancial {

    @WebMethod(action = "verifycashoutrequest")
    @RequestWrapper(localName = "verifycashoutrequest", targetNamespace = "http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0", className = "com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Verifycashoutrequest")
    @ResponseWrapper(localName = "verifycashoutrequestResponse", targetNamespace = "http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0", className = "com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.VerifycashoutrequestResponse")
    @WebResult(name = "return", targetNamespace = "")
    public com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Verifycashoutresponse verifycashoutrequest(
        @WebParam(name = "orginatingresourceid", targetNamespace = "")
        java.lang.String orginatingresourceid,
        @WebParam(name = "subscriberphonenumber", targetNamespace = "")
        java.lang.String subscriberphonenumber,
        @WebParam(name = "amount", targetNamespace = "")
        java.lang.String amount,
        @WebParam(name = "referencenumber", targetNamespace = "")
        java.lang.String referencenumber,
        @WebParam(name = "extensionparameters", targetNamespace = "")
        com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.ParameterExtension extensionparameters
    );

    @WebMethod(action = "cashoutrequest")
    @RequestWrapper(localName = "cashoutrequest", targetNamespace = "http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0", className = "com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashoutrequest")
    @ResponseWrapper(localName = "cashoutrequestResponse", targetNamespace = "http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0", className = "com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.CashoutrequestResponse")
    @WebResult(name = "return", targetNamespace = "")
    public com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashoutresponse cashoutrequest(
        @WebParam(name = "orginatingresourceid", targetNamespace = "")
        java.lang.String orginatingresourceid,
        @WebParam(name = "destinationresourceid", targetNamespace = "")
        java.lang.String destinationresourceid,
        @WebParam(name = "amount", targetNamespace = "")
        java.lang.String amount,
        @WebParam(name = "sendingdescription", targetNamespace = "")
        java.lang.String sendingdescription,
        @WebParam(name = "receivingdescription", targetNamespace = "")
        java.lang.String receivingdescription,
        @WebParam(name = "extensionparameters", targetNamespace = "")
        com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.ParameterExtension extensionparameters
    );

    @WebMethod(action = "balancerequest")
    @RequestWrapper(localName = "balancerequest", targetNamespace = "http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0", className = "com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Balancerequest")
    @ResponseWrapper(localName = "balancerequestResponse", targetNamespace = "http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0", className = "com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.BalancerequestResponse")
    @WebResult(name = "return", targetNamespace = "")
    public com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Balanceresponse balancerequest(
        @WebParam(name = "orginatingresourceid", targetNamespace = "")
        java.lang.String orginatingresourceid,
        @WebParam(name = "extensionparameters", targetNamespace = "")
        com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.ParameterExtension extensionparameters
    );

    @WebMethod(action = "transfertobankrequest")
    @RequestWrapper(localName = "transfertobankrequest", targetNamespace = "http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0", className = "com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Transfertobankrequest")
    @ResponseWrapper(localName = "transfertobankrequestResponse", targetNamespace = "http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0", className = "com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.TransfertobankrequestResponse")
    @WebResult(name = "return", targetNamespace = "")
    public com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Transfertobankresponse transfertobankrequest(
        @WebParam(name = "orginatingresourceid", targetNamespace = "")
        java.lang.String orginatingresourceid,
        @WebParam(name = "amount", targetNamespace = "")
        java.lang.String amount,
        @WebParam(name = "bankcode", targetNamespace = "")
        java.lang.String bankcode,
        @WebParam(name = "extensionparameters", targetNamespace = "")
        com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.ParameterExtension extensionparameters
    );

    @WebMethod(action = "cashinrequest")
    @RequestWrapper(localName = "cashinrequest", targetNamespace = "http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0", className = "com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashinrequest")
    @ResponseWrapper(localName = "cashinrequestResponse", targetNamespace = "http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0", className = "com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.CashinrequestResponse")
    @WebResult(name = "return", targetNamespace = "")
    public com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashinresponse cashinrequest(
        @WebParam(name = "orginatingresourceid", targetNamespace = "")
        java.lang.String orginatingresourceid,
        @WebParam(name = "destinationresourceid", targetNamespace = "")
        java.lang.String destinationresourceid,
        @WebParam(name = "amount", targetNamespace = "")
        java.lang.String amount,
        @WebParam(name = "sendingdescription", targetNamespace = "")
        java.lang.String sendingdescription,
        @WebParam(name = "receivingdescription", targetNamespace = "")
        java.lang.String receivingdescription,
        @WebParam(name = "extensionparameters", targetNamespace = "")
        com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.ParameterExtension extensionparameters
    );

    @WebMethod(action = "airtimesalesrequest")
    @RequestWrapper(localName = "airtimesalesrequest", targetNamespace = "http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0", className = "com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Airtimesalesrequest")
    @ResponseWrapper(localName = "airtimesalesrequestResponse", targetNamespace = "http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0", className = "com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.AirtimesalesrequestResponse")
    @WebResult(name = "return", targetNamespace = "")
    public com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Airtimesalesresponse airtimesalesrequest(
        @WebParam(name = "orginatingresourceid", targetNamespace = "")
        java.lang.String orginatingresourceid,
        @WebParam(name = "beneficiarynumber", targetNamespace = "")
        java.lang.String beneficiarynumber,
        @WebParam(name = "serviceprovider", targetNamespace = "")
        java.lang.String serviceprovider,
        @WebParam(name = "amount", targetNamespace = "")
        java.lang.String amount,
        @WebParam(name = "extensionparameters", targetNamespace = "")
        com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.ParameterExtension extensionparameters
    );

    @WebMethod(action = "paybillsrequest")
    @RequestWrapper(localName = "paybillsrequest", targetNamespace = "http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0", className = "com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Paybillsrequest")
    @ResponseWrapper(localName = "paybillsrequestResponse", targetNamespace = "http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0", className = "com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.PaybillsrequestResponse")
    @WebResult(name = "return", targetNamespace = "")
    public com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Paybillsresponse paybillsrequest(
        @WebParam(name = "orginatingresourceid", targetNamespace = "")
        java.lang.String orginatingresourceid,
        @WebParam(name = "merchantcode", targetNamespace = "")
        java.lang.String merchantcode,
        @WebParam(name = "amount", targetNamespace = "")
        java.lang.String amount,
        @WebParam(name = "sendingdescription", targetNamespace = "")
        java.lang.String sendingdescription,
        @WebParam(name = "receivingdescription", targetNamespace = "")
        java.lang.String receivingdescription,
        @WebParam(name = "extensionparameters", targetNamespace = "")
        com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.ParameterExtension extensionparameters
    );
}
