package com.swifta.spfinancial.utils;

import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashinresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashoutresponse;

public abstract class MMOProcessor {
	public abstract Cashoutresponse cashoutrequest(java.lang.String orginatingresourceid,java.lang.String destinationresourceid,java.math.BigDecimal amount,java.lang.String sendingdescription,java.lang.String receivingdescription,com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.ParameterExtension extensionparameters); 
	public abstract Cashinresponse cashinrequest(java.lang.String orginatingresourceid,java.lang.String destinationresourceid,java.math.BigDecimal amount,java.lang.String sendingdescription,java.lang.String receivingdescription,com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.ParameterExtension extensionparameters) ;
}
