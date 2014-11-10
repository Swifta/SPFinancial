package com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1_0;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.0.1 2014-10-25T17:40:09.272+03:00
 * Generated source version: 3.0.1
 * 
 */
@WebService(targetNamespace = "http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0", name = "SPfinancial")
@XmlSeeAlso({ com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.ObjectFactory.class })
public interface SPfinancial {

	@WebResult(targetNamespace = "http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0")
	@RequestWrapper(localName = "cashoutrequest", targetNamespace = "http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0", className = "com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashoutrequest")
	@WebMethod(operationName = "spcashout", action = "urn:spcashout")
	@ResponseWrapper(localName = "cashoutrequestResponse", targetNamespace = "http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0", className = "com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.CashoutrequestResponse")
	public com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashoutresponse cashoutrequest(
			@WebParam(name = "orginatingresourceid", targetNamespace = "") java.lang.String orginatingresourceid,
			@WebParam(name = "destinationresourceid", targetNamespace = "") java.lang.String destinationresourceid,
			@WebParam(name = "amount", targetNamespace = "") java.lang.String amount,
			@WebParam(name = "sendingdescription", targetNamespace = "") java.lang.String sendingdescription,
			@WebParam(name = "receivingdescription", targetNamespace = "") java.lang.String receivingdescription,
			@WebParam(name = "extensionparameters", targetNamespace = "") com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.ParameterExtension extensionparameters);

	@WebResult(name = "return", targetNamespace = "")
	@RequestWrapper(localName = "cashinrequest", targetNamespace = "http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0", className = "com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashinrequest")
	@WebMethod(operationName = "spcashin", action = "urn:spcashin")
	@ResponseWrapper(localName = "cashinrequestResponse", targetNamespace = "http://swifta.com/subsidiary/mats/serviceprovider/operation/spfinancial/v1.0", className = "com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.CashinrequestResponse")
	public com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashinresponse cashinrequest(
			@WebParam(name = "orginatingresourceid", targetNamespace = "") java.lang.String orginatingresourceid,
			@WebParam(name = "destinationresourceid", targetNamespace = "") java.lang.String destinationresourceid,
			@WebParam(name = "amount", targetNamespace = "") java.lang.String amount,
			@WebParam(name = "sendingdescription", targetNamespace = "") java.lang.String sendingdescription,
			@WebParam(name = "receivingdescription", targetNamespace = "") java.lang.String receivingdescription,
			@WebParam(name = "extensionparameters", targetNamespace = "") com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.ParameterExtension extensionparameters);
}
