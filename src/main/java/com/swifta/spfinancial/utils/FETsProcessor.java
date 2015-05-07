package com.swifta.spfinancial.utils;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import org.apache.axis2.AxisFault;

import com.fets.mm.soap.services.test.FetsServiceStub.ServiceResponse;
import com.ng.mats.psa.mt.fets.utils.FetsClient;
import com.ng.mats.psa.mt.fets.utils.FetsPropertyValues;
import com.ng.mats.psa.mt.fets.utils.MoneyTransfer;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Airtimesalesresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Balanceresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashinresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashoutresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.ParameterExtension;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Paybillsresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.StatusCode;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Transfertobankresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Verifycashoutresponse;

public class FETsProcessor extends MMOProcessor {
	private FetsClient fetsClient = null;
	private static final Logger logger = Logger.getLogger(FETsProcessor.class
			.getName());

	@Override
	public Cashoutresponse cashoutrequest(String orginatingresourceid,
			String destinationresourceid, BigDecimal amount,
			String sendingdescription, String receivingdescription,
			ParameterExtension extensionparameters) {
		// TODO Auto-generated method stub
		MoneyTransfer moneyTransfer = new FetsPropertyValues()
				.getPropertyValues();
		moneyTransfer.setPayerNumber(orginatingresourceid);
		moneyTransfer.setAmount(amount.doubleValue());
		moneyTransfer.setBillerTransactionRef(extensionparameters
				.getSpTransactionid());
		moneyTransfer.setRemarks(sendingdescription);
		moneyTransfer
				.setTransactionId(extensionparameters.getSpTransactionid());
		/*
		 * List<String> extensionParam =
		 * extensionparameters.getExtensionparam(); if (extensionParam != null)
		 * { logger.info(
		 * "--------------------------------extension parameter is not null so pin is set"
		 * ); moneyTransfer.setTransactionPin(extensionParam.get(0)); }
		 */
		try {
			fetsClient = new FetsClient(moneyTransfer);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ServiceResponse serviceResponse = fetsClient.doCashOut(moneyTransfer);
		Cashoutresponse cashoutResponse = new Cashoutresponse();

		if (serviceResponse != null) {
			if (serviceResponse.getSuccess()) {
				logger.info("--------------------------------serviceResponse is a success");
				cashoutResponse.setStatuscode(StatusCode.PENDING);
			} else {
				logger.info("--------------------------------serviceResponse is not a success");
				cashoutResponse.setStatuscode(StatusCode.FAILED);
			}

			ParameterExtension parameterExtension = new ParameterExtension();
			parameterExtension.setMmoperator(extensionparameters
					.getMmoperator());
			parameterExtension
					.setSpTransactionid(serviceResponse.getTnxRefNo());
			parameterExtension.getExtensionparam().add(
					String.valueOf(serviceResponse.getResponseCode()));
			parameterExtension.getExtensionparam().add(
					serviceResponse.getMessage());
			if (cashoutResponse.getStatuscode().toString()
					.equalsIgnoreCase("COMPLETED")) {
				parameterExtension.getExtensionparam().add("true");
			} else {
				parameterExtension.getExtensionparam().add("false");
			}
			logger.info("--------------------------------serviceResponse is not null");
			cashoutResponse.setDestinationpartnerbalanceafter("0");
			cashoutResponse.setExtensionparameters(extensionparameters);
			cashoutResponse.setFinancialtransactionid("0");
			cashoutResponse.setOrginatingpartnerbalanceafter("0");
			cashoutResponse.setOrginatingpartnerfee("0");
			cashoutResponse.setResponseMessage(serviceResponse.getMessage());

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
		MoneyTransfer moneyTransfer = new FetsPropertyValues()
				.getPropertyValues();
		moneyTransfer.setPayerNumber(orginatingresourceid);
		moneyTransfer.setAmount(amount.doubleValue());
		// moneyTransfer.setBillerMerchantId(billerMerchantId);
		moneyTransfer.setBillerTransactionRef(extensionparameters
				.getSpTransactionid());
		// moneyTransfer.setChannelId(channelId);
		// moneyTransfer.setCharge(charge);
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
		try {
			fetsClient = new FetsClient(moneyTransfer);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public Verifycashoutresponse verifycashoutrequest(
			String orginatingresourceid, String subscriberphonenumber,
			String amount, String referencenumber,
			ParameterExtension extensionparameters) {
		// TODO Auto-generated method stub
		MoneyTransfer moneyTransfer = new FetsPropertyValues()
				.getPropertyValues();
		moneyTransfer.setReference(referencenumber);

		try {
			fetsClient = new FetsClient(moneyTransfer);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ServiceResponse serviceResponse = fetsClient
				.verifyCashout(moneyTransfer);
		Verifycashoutresponse verifycashoutresponse = new Verifycashoutresponse();
		if (serviceResponse != null) {
			logger.info("--------------------------------serviceResponse is not null");
			verifycashoutresponse.setExtensionparameters(extensionparameters);
			verifycashoutresponse.setFinancialtransactionid(serviceResponse
					.getTnxRefNo());
			verifycashoutresponse.setResponseMessage(serviceResponse
					.getMessage());
			if (serviceResponse.getSuccess()) {
				logger.info("--------------------------------serviceResponse is a success");
				verifycashoutresponse.setStatuscode(StatusCode.COMPLETED);
			} else {
				logger.info("--------------------------------serviceResponse is not a success");
				verifycashoutresponse.setStatuscode(StatusCode.FAILED);
			}
		} else {
			logger.info("--------------------------------serviceResponse is null");
		}

		return verifycashoutresponse;
	}

	@Override
	public Transfertobankresponse transfertobank(String orginatingresourceid,
			String amount, String narration,
			ParameterExtension extensionparameters) {
		// TODO Auto-generated method stub
		MoneyTransfer moneyTransfer = new FetsPropertyValues()
				.getPropertyValues();
		moneyTransfer.setReference(narration);
		moneyTransfer.setAmount(Double.valueOf(amount));
		// moneyTransfer.setBillerMerchantId(billerMerchantId);
		try {
			fetsClient = new FetsClient(moneyTransfer);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ServiceResponse serviceResponse = fetsClient
				.walletToBank(moneyTransfer);
		Transfertobankresponse transfertobankresponse = new Transfertobankresponse();
		if (serviceResponse != null) {
			logger.info("--------------------------------serviceResponse is not null");
			transfertobankresponse.setExtensionparameters(extensionparameters);
			transfertobankresponse.setResponseMessage(serviceResponse
					.getMessage());
			if (serviceResponse.getSuccess()) {
				logger.info("--------------------------------serviceResponse is a success");
				transfertobankresponse.setStatuscode(StatusCode.COMPLETED);
			} else {
				logger.info("--------------------------------serviceResponse is not a success");
				transfertobankresponse.setStatuscode(StatusCode.FAILED);
			}
		} else {
			logger.info("--------------------------------serviceResponse is null");
		}

		return transfertobankresponse;
	}

	@Override
	public Paybillsresponse paybillsrequest(String orginatingresourceid,
			String merchantcode, String amount, String sendingdescription,
			String receivingdescription, ParameterExtension extensionparameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Airtimesalesresponse airtimesalesrequest(
			String orginatingresourceid, String beneficiarynumber,
			String serviceprovider, String amount,
			ParameterExtension extensionparameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Balanceresponse balancerequest(String orginatingresourceid,
			ParameterExtension extensionparameters) {
		MoneyTransfer moneyTransfer = new FetsPropertyValues()
				.getPropertyValues();
		List<String> extensionParam = extensionparameters.getExtensionparam();

		try {
			fetsClient = new FetsClient(moneyTransfer);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String balance = fetsClient.getBalance(moneyTransfer);
		Balanceresponse balanceresponse = new Balanceresponse();

		if (!balance.equals("")) {
			logger.info("--------------------------------serviceResponse is a success");
			balanceresponse.setStatuscode(StatusCode.COMPLETED);
		} else {
			logger.info("--------------------------------serviceResponse is not a success");
			balanceresponse.setStatuscode(StatusCode.FAILED);
		}

		ParameterExtension parameterExtension = new ParameterExtension();
		parameterExtension.setMmoperator(extensionparameters.getMmoperator());
		if (balanceresponse.getStatuscode().toString()
				.equalsIgnoreCase("COMPLETED")) {
			parameterExtension.getExtensionparam().add("true");
		} else {
			parameterExtension.getExtensionparam().add("false");
		}
		logger.info("--------------------------------serviceResponse is not null");
		balanceresponse.setBalance(balance);
		balanceresponse.setResponseMessage(balance);
		balanceresponse.setExtensionparameters(extensionparameters);

		return balanceresponse;
	}

	@Override
	public Cashoutresponse cashoutunregisteredrequest(
			String orginatingresourceid, String subscriberphonenumber,
			BigDecimal amount, String referencenumber, String referencecode,
			String receivingdescription, ParameterExtension extensionparameters) {

		// TODO Auto-generated method stub
		MoneyTransfer moneyTransfer = new FetsPropertyValues()
				.getPropertyValues();
		moneyTransfer.setRecipientMsisdn(subscriberphonenumber);
		moneyTransfer.setTxnRefNo(referencenumber);
		moneyTransfer.setRedeemCode(referencecode);
		moneyTransfer.setAmount(amount.doubleValue());

		moneyTransfer.setBillerTransactionRef(extensionparameters
				.getSpTransactionid());

		moneyTransfer.setRemarks(receivingdescription);
		moneyTransfer
				.setTransactionId(extensionparameters.getSpTransactionid());
		List<String> extensionParam = extensionparameters.getExtensionparam();

		try {
			fetsClient = new FetsClient(moneyTransfer);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ServiceResponse serviceResponse = fetsClient
				.doCashOutUnregistered(moneyTransfer);
		Cashoutresponse cashoutResponse = new Cashoutresponse();

		if (serviceResponse != null) {
			if (serviceResponse.getSuccess()) {
				logger.info("--------------------------------serviceResponse is a success");
				cashoutResponse.setStatuscode(StatusCode.COMPLETED);
			} else {
				logger.info("--------------------------------serviceResponse is not a success");
				cashoutResponse.setStatuscode(StatusCode.FAILED);
			}

			ParameterExtension parameterExtension = new ParameterExtension();
			parameterExtension.setMmoperator(extensionparameters
					.getMmoperator());
			parameterExtension
					.setSpTransactionid(serviceResponse.getTnxRefNo());
			parameterExtension.getExtensionparam().add(
					String.valueOf(serviceResponse.getResponseCode()));
			parameterExtension.getExtensionparam().add(
					serviceResponse.getMessage());
			if (cashoutResponse.getStatuscode().toString()
					.equalsIgnoreCase("COMPLETED")) {
				parameterExtension.getExtensionparam().add("true");
			} else {
				parameterExtension.getExtensionparam().add("false");
			}
			logger.info("--------------------------------serviceResponse is not null");
			cashoutResponse.setDestinationpartnerbalanceafter("0");
			cashoutResponse.setExtensionparameters(extensionparameters);
			cashoutResponse.setFinancialtransactionid("0");
			cashoutResponse.setOrginatingpartnerbalanceafter("0");
			cashoutResponse.setOrginatingpartnerfee("0");
			cashoutResponse.setResponseMessage(serviceResponse.getMessage());

		} else {
			logger.info("--------------------------------serviceResponse is null");
		}

		return cashoutResponse;

	}

}
