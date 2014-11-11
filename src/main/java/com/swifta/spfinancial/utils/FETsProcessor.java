package com.swifta.spfinancial.utils;

import java.math.BigDecimal;

import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashinresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashoutresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.ParameterExtension;

public class FETsProcessor extends MMOProcessor{

	@Override
	public Cashoutresponse cashoutrequest(String orginatingresourceid,
			String destinationresourceid, BigDecimal amount,
			String sendingdescription, String receivingdescription,
			ParameterExtension extensionparameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cashinresponse cashinrequest(String orginatingresourceid,
			String destinationresourceid, BigDecimal amount,
			String sendingdescription, String receivingdescription,
			ParameterExtension extensionparameters) {
		// TODO Auto-generated method stub
		return null;
	}



}
