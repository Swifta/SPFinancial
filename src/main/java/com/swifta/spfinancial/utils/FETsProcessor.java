package com.swifta.spfinancial.utils;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import com.fets.mm.soap.services.FetsServicesServiceStub.ServiceResponse;
import com.ng.mats.psa.mt.fets.utils.FetsClient;
import com.ng.mats.psa.mt.fets.utils.MoneyTransfer;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashinresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashoutresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.ParameterExtension;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.StatusCode;

public class FETsProcessor extends MMOProcessor {
	private FetsClient fetsClient = new FetsClient();
	private static final Logger logger = Logger.getLogger(FETsProcessor.class
			.getName());
	private long channelId = 10;
	private long charge = 50;

	@Override
	public Cashoutresponse cashoutrequest(String orginatingresourceid,
			String destinationresourceid, BigDecimal amount,
			String sendingdescription, String receivingdescription,
			ParameterExtension extensionparameters) {
		// TODO Auto-generated method stub
		MoneyTransfer moneyTransfer = new MoneyTransfer();
		moneyTransfer.setPayerNumber(orginatingresourceid);
		moneyTransfer.setAmount(amount.doubleValue());
		// moneyTransfer.setBillerMerchantId(billerMerchantId);
		moneyTransfer.setBillerTransactionRef(extensionparameters
				.getSpTransactionid());
		moneyTransfer.setChannelId(channelId);
		moneyTransfer.setCharge(charge);
		// moneyTransfer.setPayerWalletId(payerWalletId);
		moneyTransfer.setRecieverNumber(destinationresourceid);
		moneyTransfer.setRemarks(sendingdescription);
		moneyTransfer
				.setTransactionId(extensionparameters.getSpTransactionid());
		List<String> extensionParam = extensionparameters.getExtensionparam();
		if (extensionParam != null) {
			logger.info("--------------------------------extension parameter is not null so pin is set");
			moneyTransfer.setTransactionPin(extensionParam.get(0));
		}
		ServiceResponse serviceResponse = fetsClient.doCashOut(moneyTransfer);
		Cashoutresponse cashoutResponse = new Cashoutresponse();
		if (serviceResponse != null) {
			logger.info("--------------------------------serviceResponse is not null");
			cashoutResponse.setDestinationpartnerbalanceafter("0");
			cashoutResponse.setExtensionparameters(extensionparameters);
			cashoutResponse.setFinancialtransactionid("0");
			cashoutResponse.setOrginatingpartnerbalanceafter("0");
			cashoutResponse.setOrginatingpartnerfee("0");
			cashoutResponse.setResponseMessage(serviceResponse.getMessage());
			if (serviceResponse.getSuccess()) {
				logger.info("--------------------------------serviceResponse is a success");
				cashoutResponse.setStatuscode(StatusCode.COMPLETED);
			} else {
				logger.info("--------------------------------serviceResponse is not a success");
				cashoutResponse.setStatuscode(StatusCode.FAILED);
			}
		} else {
			logger.info("--------------------------------serviceResponse is null");
		}

		return cashoutResponse;
	}

	@Override
	public Cashinresponse cashinrequest(String orginatingresourceid,
			String destinationresourceid, BigDecimal amount,
			String sendingdescription, String receivingdescription,
			ParameterExtension extensionparameters) {
		// TODO Auto-generated method stub
		MoneyTransfer moneyTransfer = new MoneyTransfer();
		moneyTransfer.setPayerNumber(orginatingresourceid);
		moneyTransfer.setAmount(amount.doubleValue());
		// moneyTransfer.setBillerMerchantId(billerMerchantId);
		moneyTransfer.setBillerTransactionRef(extensionparameters
				.getSpTransactionid());
		moneyTransfer.setChannelId(channelId);
		moneyTransfer.setCharge(charge);
		// moneyTransfer.setPayerWalletId(payerWalletId);
		moneyTransfer.setRecieverNumber(destinationresourceid);
		moneyTransfer.setRemarks(sendingdescription);
		moneyTransfer
				.setTransactionId(extensionparameters.getSpTransactionid());
		List<String> extensionParam = extensionparameters.getExtensionparam();
		if (extensionParam != null) {
			logger.info("--------------------------------extension parameter is not null so pin is set");
			moneyTransfer.setTransactionPin(extensionParam.get(0));
		}
		ServiceResponse serviceResponse = fetsClient.doCashIn(moneyTransfer);
		Cashinresponse cashinResponse = new Cashinresponse();
		if (serviceResponse != null) {
			logger.info("--------------------------------serviceResponse is not null");
			cashinResponse.setDestinationpartnerbalanceafter("0");
			cashinResponse.setExtensionparameters(extensionparameters);
			cashinResponse.setFinancialtransactionid("0");
			cashinResponse.setOrginatingpartnerbalanceafter("0");
			cashinResponse.setFee("0");
			cashinResponse.setResponseMessage(serviceResponse.getMessage());
			if (serviceResponse.getSuccess()) {
				logger.info("--------------------------------serviceResponse is a success");
				cashinResponse.setStatuscode(StatusCode.COMPLETED);
			} else {
				logger.info("--------------------------------serviceResponse is not a success");
				cashinResponse.setStatuscode(StatusCode.FAILED);
			}
		} else {
			logger.info("--------------------------------serviceResponse is null");
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
