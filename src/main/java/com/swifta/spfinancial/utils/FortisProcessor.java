package com.swifta.spfinancial.utils;

import java.math.BigDecimal;
import java.util.logging.Logger;

import com.ng.mats.psa.mt.fortis.util.Constants;
import com.ng.mats.psa.mt.fortis.util.FortisClient;
import com.ng.mats.psa.mt.fortis.util.MoneyTransfer;
import com.ng.mats.psa.mt.fortis.xmlprocessor.Message;
import com.ng.mats.psa.mt.fortis.xmlprocessor.RefID;
import com.ng.mats.psa.mt.fortis.xmlprocessor.Response;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashinresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashoutresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.ParameterExtension;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.StatusCode;

public class FortisProcessor extends MMOProcessor {
	private static final Logger logger = Logger.getLogger(FortisProcessor.class
			.getName());

	@Override
	public Cashoutresponse cashoutrequest(String orginatingresourceid,
			String destinationresourceid, BigDecimal amount,
			String sendingdescription, String receivingdescription,
			ParameterExtension extensionparameters) {
		// TODO Auto-generated method stub
		FortisClient fortisClient = new FortisClient(Constants.account,
				Constants.TXNLOGIN);

		MoneyTransfer moneyTransfer = fortisClient.getMoneyTransfer();
		// moneyTransfer.setSourcePocketCode(Constants.SOURCEPOCKETCODEWALLET);
		// if (destinationresourceid.isEmpty())
		// moneyTransfer.setDestMdn(Constants.customerNumber);
		// else
		moneyTransfer.setDestMdn(orginatingresourceid);
		// moneyTransfer.setConfirmed("true");
		// moneyTransfer.setAgentCode(Constants.agentCode);
		// moneyTransfer.setDestPocketCode(Constants.DESTINATIONPOCKETCODEWALLET);
		moneyTransfer.setAmount(String.valueOf(amount));
		Response response = fortisClient
				.performCashoutUnregistered(moneyTransfer);
		logger.info("-----------------------After initiating login"
				+ response.toString());
		Cashoutresponse cashoutResponse = new Cashoutresponse();

		if (response != null) {
			logger.info("--------------------------------response is not null");
			Message message = response.getMessage();
			if (message != null) {
				logger.info("--------------------------------message is not null");
				boolean success = false;
				if (message.getCode().equalsIgnoreCase("298")) {
					success = true;
				}

				if (success) {
					logger.info("--------------------------------response is a success");
					cashoutResponse.setStatuscode(StatusCode.COMPLETED);
				} else {
					logger.info("--------------------------------response is not a success");
					cashoutResponse.setStatuscode(StatusCode.FAILED);
				}

				ParameterExtension parameterExtension = new ParameterExtension();
				parameterExtension.setMmoperator(extensionparameters
						.getMmoperator());
				RefID refId = response.getRefID();
				if (refId != null)
					parameterExtension.setSpTransactionid(refId.getValue());
				parameterExtension.getExtensionparam().add(
						String.valueOf(message.getCode()));
				parameterExtension.getExtensionparam().add(message.getValue());
				if (cashoutResponse.getStatuscode().toString()
						.equalsIgnoreCase("COMPLETED")) {
					parameterExtension.getExtensionparam().add("true");
				} else {
					parameterExtension.getExtensionparam().add("false");
				}

				cashoutResponse.setDestinationpartnerbalanceafter("0");
				cashoutResponse.setExtensionparameters(extensionparameters);
				cashoutResponse.setFinancialtransactionid("0");
				cashoutResponse.setOrginatingpartnerbalanceafter("0");
				cashoutResponse.setOrginatingpartnerfee("0");
				cashoutResponse.setResponseMessage(message.getValue());
			} else {
				logger.info("--------------------------------message is null");
			}
		} else {
			logger.info("--------------------------------response is null");
		}

		return cashoutResponse;
	}

	@Override
	public Cashinresponse cashinrequest(String orginatingresourceid,
			String destinationresourceid, BigDecimal amount,
			String sendingdescription, String receivingdescription,
			ParameterExtension extensionparameters) {
		FortisClient fortisClient = new FortisClient(Constants.account,
				Constants.TXNLOGIN);

		MoneyTransfer moneyTransfer = fortisClient.getMoneyTransfer();
		// //
		// moneyTransfer.setSourcePocketCode(Constants.SOURCEPOCKETCODEWALLET);
		// if (orginatingresourceid.isEmpty())
		// moneyTransfer.setDestMdn(Constants.customerNumber);
		// else
		moneyTransfer.setDestMdn(orginatingresourceid);
		// moneyTransfer.setConfirmed("true");
		// moneyTransfer.setAgentCode(Constants.agentCode);
		// moneyTransfer.setDestPocketCode(Constants.DESTINATIONPOCKETCODEWALLET);
		moneyTransfer.setAmount(String.valueOf(amount));
		Response response = fortisClient.performCashin(moneyTransfer);
		logger.info("-----------------------After initiating login"
				+ response.toString());
		Cashinresponse cashinResponse = new Cashinresponse();

		if (response != null) {
			logger.info("--------------------------------response is not null");
			cashinResponse.setDestinationpartnerbalanceafter("0");
			cashinResponse.setExtensionparameters(extensionparameters);
			cashinResponse.setFinancialtransactionid("0");
			cashinResponse.setOrginatingpartnerbalanceafter("0");
			cashinResponse.setFee("0");

			Message message = response.getMessage();
			if (message != null) {

				cashinResponse.setResponseMessage(message.getValue());
				boolean success = false;
				if (message.getCode().equalsIgnoreCase("298")) {
					success = true;
				}

				if (success) {
					logger.info("--------------------------------response is a success");
					cashinResponse.setStatuscode(StatusCode.COMPLETED);
				} else {
					logger.info("--------------------------------response is not a success");
					cashinResponse.setStatuscode(StatusCode.FAILED);
				}
			} else {
				logger.info("--------------------------------message is null");
			}
		} else {
			logger.info("--------------------------------response is null");
		}
		return cashinResponse;

	}

	@Override
	public Double balanceRequest(String orginatingresourceid,
			ParameterExtension extensionparameters) {
		// TODO Auto-generated method stub
		return null;
	}

}
