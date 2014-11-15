package com.swifta.spfinancial.utils;

import java.math.BigDecimal;
import java.util.logging.Logger;

import com.mobilemoney.services.mwallet.MPWalletServiceStub.MTransferResponseType;
import com.mobilemoney.services.mwallet.TeasyMobileClient;
import com.ng.mats.psa.mt.teasymobile.model.MoneyTransfer;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashinresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashoutresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.ParameterExtension;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.StatusCode;

public class TeasyMobileProcessor extends MMOProcessor {
	private static final Logger logger = Logger
			.getLogger(TeasyMobileProcessor.class.getName());

	@Override
	public Cashoutresponse cashoutrequest(String orginatingresourceid,
			String destinationresourceid, BigDecimal amount,
			String sendingdescription, String receivingdescription,
			ParameterExtension extensionparameters) {
		Cashoutresponse cashoutresponse = new Cashoutresponse();

		MoneyTransfer teasymoneyTransfer = new MoneyTransfer(
				destinationresourceid, amount, receivingdescription,
				extensionparameters.getExtensionparam().get(0));
		TeasyMobileClient teasyMobileClient;
		MTransferResponseType response = null;
		try {
			teasyMobileClient = new TeasyMobileClient();
			logger.info("---------------------posting to teasymobile");
			response = teasyMobileClient.doCashout(teasymoneyTransfer);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (response != null) {
			if (response.getStatus() == 0) {
				response.setResponseMessage("Transaction Successful");
				logger.info("---------------------response returned as "
						+ StatusCode.COMPLETED);
				cashoutresponse.setStatuscode(StatusCode.COMPLETED);
			} else {
				logger.info("---------------------response returned as "
						+ StatusCode.REJECTED);
				cashoutresponse.setStatuscode(StatusCode.REJECTED);
			}
			cashoutresponse.setDestinationpartnerbalanceafter(null);
			cashoutresponse.setOrginatingpartnerbalanceafter(null);
			cashoutresponse.setOrginatingpartnerfee(null);

			cashoutresponse.setResponseMessage(response.getResponseMessage());
			ParameterExtension parameterExtension = new ParameterExtension();
			parameterExtension.setSpTransactionid(response.getTransactionId());
			parameterExtension.getExtensionparam().add(
					response.getTransactionId());
			parameterExtension.getExtensionparam().add(
					response.getResponseMessage());
			cashoutresponse.setExtensionparameters(parameterExtension);
		} else {
			logger.info("---------------------response returned as "
					+ StatusCode.FAILED);
			cashoutresponse.setStatuscode(StatusCode.FAILED);
		}
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
		if (response != null) {
			if (response.getStatus() == 0) {
				response.setResponseMessage("Transaction Successful");
				logger.info("---------------------response returned as "
						+ StatusCode.COMPLETED);
				cashinresponse.setStatuscode(StatusCode.COMPLETED);
			} else {
				logger.info("---------------------response returned as "
						+ StatusCode.REJECTED);
				cashinresponse.setStatuscode(StatusCode.REJECTED);
			}
			cashinresponse.setDestinationpartnerbalanceafter(null);
			cashinresponse.setFinancialtransactionid(response
					.getTransactionId());
			cashinresponse.setOrginatingpartnerbalanceafter(null);
			cashinresponse.setFee(null);
			cashinresponse.setResponseMessage(response.getResponseMessage());
			ParameterExtension parameterExtension = new ParameterExtension();
			parameterExtension.setSpTransactionid(response.getTransactionId());
			parameterExtension.getExtensionparam().add(
					response.getTransactionId());
			parameterExtension.getExtensionparam().add(
					response.getResponseMessage());
			cashinresponse.setExtensionparameters(parameterExtension);
		} else {
			cashinresponse.setStatuscode(StatusCode.FAILED);
			logger.info("---------------------response returned as "
					+ StatusCode.FAILED);
		}
		return cashinresponse;
	}

}
