package com.swifta.spfinancial.utils;

import java.math.BigDecimal;

import com.mobilemoney.services.mwallet.MPWalletServiceStub.MTransferResponseType;
import com.mobilemoney.services.mwallet.TeasyMobileClient;
import com.ng.mats.psa.mt.teasymobile.model.MoneyTransfer;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashinresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashoutresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.ParameterExtension;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.StatusCode;

public class TeasyMobileProcessor extends MMOProcessor {

	@Override
	public Cashoutresponse cashoutrequest(String orginatingresourceid,
			String destinationresourceid, BigDecimal amount,
			String sendingdescription, String receivingdescription,
			ParameterExtension extensionparameters) {
		Cashoutresponse cashoutresponse = new Cashoutresponse();

		MoneyTransfer teasymoneyTransfer = new MoneyTransfer(
				destinationresourceid, amount, receivingdescription, "");
		TeasyMobileClient teasyMobileClient;
		MTransferResponseType response = null;
		try {
			teasyMobileClient = new TeasyMobileClient();
			System.out.println("posting to teasymobile");
			response = teasyMobileClient.doCashout(teasymoneyTransfer);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (response.getStatus() == 0)
			response.setResponseMessage("Transaction Successful");
		cashoutresponse.setDestinationpartnerbalanceafter(null);
		cashoutresponse.setOrginatingpartnerbalanceafter(null);
		cashoutresponse.setOrginatingpartnerfee(null);
		cashoutresponse.setStatuscode(StatusCode.COMPLETED);
		cashoutresponse.setResponseMessage(response.getResponseMessage());
		ParameterExtension parameterExtension = new ParameterExtension();
		parameterExtension.setSpTransactionid(response.getTransactionId());
		parameterExtension.getExtensionparam().add(response.getTransactionId());
		parameterExtension.getExtensionparam().add(
				response.getResponseMessage());
		cashoutresponse.setExtensionparameters(parameterExtension);
		return cashoutresponse;

	}

	@Override
	public Cashinresponse cashinrequest(String orginatingresourceid,
			String destinationresourceid, BigDecimal amount,
			String sendingdescription, String receivingdescription,
			ParameterExtension extensionparameters) {
		Cashinresponse cashinresponse = new Cashinresponse();
		MoneyTransfer teasymoneyTransfer = new MoneyTransfer(
				destinationresourceid, amount, receivingdescription, "");
		TeasyMobileClient teasyMobileClient;
		MTransferResponseType response = null;
		try {
			teasyMobileClient = new TeasyMobileClient();
			System.out.println("posting to teasymobile");
			response = teasyMobileClient.doCashIn(teasymoneyTransfer);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (response.getStatus() == 0)
			response.setResponseMessage("Transaction Successful");
		cashinresponse.setDestinationpartnerbalanceafter(null);
		cashinresponse.setFinancialtransactionid(response.getTransactionId());
		cashinresponse.setOrginatingpartnerbalanceafter(null);
		cashinresponse.setStatuscode(StatusCode.COMPLETED);
		cashinresponse.setFee(null);
		cashinresponse.setResponseMessage(response.getResponseMessage());
		ParameterExtension parameterExtension = new ParameterExtension();
		parameterExtension.setSpTransactionid(response.getTransactionId());
		parameterExtension.getExtensionparam().add(response.getTransactionId());
		parameterExtension.getExtensionparam().add(
				response.getResponseMessage());
		cashinresponse.setExtensionparameters(parameterExtension);
		return cashinresponse;
	}

}
