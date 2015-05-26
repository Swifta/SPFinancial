package com.swifta.spfinancial.utils;

import java.math.BigDecimal;
import java.util.logging.Logger;

import com.ng.mats.psa.mt.paga.data.MoneyTransfer;
import com.ng.mats.psa.mt.paga.data.PagaPropertyValues;
import com.ng.mats.psa.mt.paga.data.PagaResponse;
import com.ng.mats.psa.mt.paga.util.PagaClient;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Airtimesalesresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Balanceresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashinresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashoutresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.ParameterExtension;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Paybillsresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.StatusCode;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Transfertobankresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Verifycashoutresponse;

public class PagaProcessor extends MMOProcessor {
	private static final Logger logger = Logger.getLogger(PagaProcessor.class
			.getName());
	private PagaClient pagaClient;

	@Override
	public Cashoutresponse cashoutrequest(String orginatingresourceid,
			String destinationresourceid, BigDecimal amount,
			String sendingdescription, String receivingdescription,
			ParameterExtension extensionparameters) {
		MoneyTransfer moneyTransfer = new PagaPropertyValues()
				.getPropertyValues();
		moneyTransfer.setSenderPhone(orginatingresourceid);
		moneyTransfer.setAmount(amount.toString());
		moneyTransfer.setWithdrawalCode(destinationresourceid);
		moneyTransfer.setMessage(sendingdescription);
		moneyTransfer
				.setTransactionId(extensionparameters.getSpTransactionid());
		/*
		 * List<String> extensionParam =
		 * extensionparameters.getExtensionparam(); if (extensionParam != null)
		 * { logger.info(
		 * "--------------------------------extension parameter is not null so pin is set"
		 * ); moneyTransfer.setTransactionPin(extensionParam.get(0)); }
		 */
		pagaClient = new PagaClient();
		PagaResponse pagaResponse = pagaClient.performCashOut(moneyTransfer);
		Cashoutresponse cashoutResponse = new Cashoutresponse();

		if (pagaResponse != null) {
			if (pagaResponse.getResponseCode().equalsIgnoreCase("0")) {
				logger.info("--------------------------------serviceResponse is a success");
				cashoutResponse.setStatuscode(StatusCode.COMPLETED);
			} else {
				logger.info("--------------------------------serviceResponse is not a success");
				cashoutResponse.setStatuscode(StatusCode.FAILED);
			}

			ParameterExtension parameterExtension = new ParameterExtension();
			parameterExtension.setMmoperator(extensionparameters
					.getMmoperator());
			parameterExtension.setSpTransactionid(pagaResponse
					.getFinancialtransactionid());
			parameterExtension.getExtensionparam().add(
					String.valueOf(pagaResponse.getResponseCode()));
			parameterExtension.getExtensionparam().add(
					pagaResponse.getResponseDescription());
			if (cashoutResponse.getStatuscode().toString()
					.equalsIgnoreCase("COMPLETED")) {
				parameterExtension.getExtensionparam().add("true");
			} else {
				parameterExtension.getExtensionparam().add("false");
			}
			logger.info("--------------------------------serviceResponse is not null");
			cashoutResponse.setDestinationpartnerbalanceafter("0");
			cashoutResponse.setExtensionparameters(parameterExtension);
			cashoutResponse.setFinancialtransactionid("0");
			cashoutResponse.setOrginatingpartnerbalanceafter("0");
			cashoutResponse.setOrginatingpartnerfee("0");
			cashoutResponse.setResponseMessage(pagaResponse
					.getResponseDescription());

		} else {
			logger.info("--------------------------------serviceResponse is null");
		}

		return cashoutResponse;
	}

	@Override
	public Cashoutresponse cashoutunregisteredrequest(
			String orginatingresourceid, String subscriberphonenumber,
			BigDecimal amount, String referencenumber, String referencecode,
			String receivingdescription, ParameterExtension extensionparameters) {
		MoneyTransfer moneyTransfer = new PagaPropertyValues()
				.getPropertyValues();
		moneyTransfer.setSenderPhone(subscriberphonenumber);
		moneyTransfer.setAmount(amount.toString());
		moneyTransfer.setWithdrawalCode(referencenumber);
		moneyTransfer.setMessage(receivingdescription);
		moneyTransfer
				.setTransactionId(extensionparameters.getSpTransactionid());
		/*
		 * List<String> extensionParam =
		 * extensionparameters.getExtensionparam(); if (extensionParam != null)
		 * { logger.info(
		 * "--------------------------------extension parameter is not null so pin is set"
		 * ); moneyTransfer.setTransactionPin(extensionParam.get(0)); }
		 */
		pagaClient = new PagaClient();
		PagaResponse pagaResponse = pagaClient.performCashOut(moneyTransfer);
		Cashoutresponse cashoutResponse = new Cashoutresponse();

		if (pagaResponse != null) {
			if (pagaResponse.getResponseCode().equalsIgnoreCase("0")) {
				logger.info("--------------------------------serviceResponse is a success");
				cashoutResponse.setStatuscode(StatusCode.COMPLETED);
			} else {
				logger.info("--------------------------------serviceResponse is not a success");
				cashoutResponse.setStatuscode(StatusCode.FAILED);
			}

			ParameterExtension parameterExtension = new ParameterExtension();
			parameterExtension.setMmoperator(extensionparameters
					.getMmoperator());
			parameterExtension.setSpTransactionid(pagaResponse
					.getFinancialtransactionid());
			parameterExtension.getExtensionparam().add(
					String.valueOf(pagaResponse.getResponseCode()));
			parameterExtension.getExtensionparam().add(
					pagaResponse.getResponseDescription());
			if (cashoutResponse.getStatuscode().toString()
					.equalsIgnoreCase("COMPLETED")) {
				parameterExtension.getExtensionparam().add("true");
			} else {
				parameterExtension.getExtensionparam().add("false");
			}
			logger.info("--------------------------------serviceResponse is not null");
			cashoutResponse.setDestinationpartnerbalanceafter("0");
			cashoutResponse.setExtensionparameters(parameterExtension);
			cashoutResponse.setFinancialtransactionid("0");
			cashoutResponse.setOrginatingpartnerbalanceafter("0");
			cashoutResponse.setOrginatingpartnerfee("0");
			cashoutResponse.setResponseMessage(pagaResponse
					.getResponseDescription());

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
		MoneyTransfer moneyTransfer = new PagaPropertyValues()
				.getPropertyValues();
		moneyTransfer.setSenderPhone(orginatingresourceid);
		moneyTransfer.setAmount(amount.toString());
		moneyTransfer.setWithdrawalCode(destinationresourceid);
		moneyTransfer.setMessage(sendingdescription);
		moneyTransfer
				.setTransactionId(extensionparameters.getSpTransactionid());
		/*
		 * List<String> extensionParam =
		 * extensionparameters.getExtensionparam(); if (extensionParam != null)
		 * { logger.info(
		 * "--------------------------------extension parameter is not null so pin is set"
		 * ); moneyTransfer.setTransactionPin(extensionParam.get(0)); }
		 */
		pagaClient = new PagaClient();
		PagaResponse pagaResponse = pagaClient.performCashOut(moneyTransfer);
		Cashinresponse cashinresponse = new Cashinresponse();

		if (pagaResponse != null) {
			if (pagaResponse.getResponseCode().equalsIgnoreCase("0")) {
				logger.info("--------------------------------serviceResponse is a success");
				cashinresponse.setStatuscode(StatusCode.COMPLETED);
			} else {
				logger.info("--------------------------------serviceResponse is not a success");
				cashinresponse.setStatuscode(StatusCode.FAILED);
			}

			ParameterExtension parameterExtension = new ParameterExtension();
			parameterExtension.setMmoperator(extensionparameters
					.getMmoperator());
			parameterExtension.setSpTransactionid(pagaResponse
					.getFinancialtransactionid());
			parameterExtension.getExtensionparam().add(
					String.valueOf(pagaResponse.getResponseCode()));
			parameterExtension.getExtensionparam().add(
					pagaResponse.getResponseDescription());
			if (cashinresponse.getStatuscode().toString()
					.equalsIgnoreCase("COMPLETED")) {
				parameterExtension.getExtensionparam().add("true");
			} else {
				parameterExtension.getExtensionparam().add("false");
			}
			logger.info("--------------------------------serviceResponse is not null");
			cashinresponse.setDestinationpartnerbalanceafter("0");
			cashinresponse.setExtensionparameters(parameterExtension);
			cashinresponse.setFinancialtransactionid("0");
			cashinresponse.setOrginatingpartnerbalanceafter("0");
			cashinresponse.setResponseMessage(pagaResponse
					.getResponseDescription());

		} else {
			logger.info("--------------------------------serviceResponse is null");
		}

		return cashinresponse;
	}

	@Override
	public Verifycashoutresponse verifycashoutrequest(
			String orginatingresourceid, String subscriberphonenumber,
			String amount, String referencenumber,
			ParameterExtension extensionparameters) {
		MoneyTransfer moneyTransfer = new PagaPropertyValues()
				.getPropertyValues();
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transfertobankresponse transfertobank(String orginatingresourceid,
			String amount, String narration,
			ParameterExtension extensionparameters) {
		MoneyTransfer moneyTransfer = new PagaPropertyValues()
				.getPropertyValues();
		moneyTransfer.setSenderPhone(orginatingresourceid);
		moneyTransfer.setAmount(amount.toString());
		moneyTransfer.setMessage(narration);
		moneyTransfer
				.setTransactionId(extensionparameters.getSpTransactionid());
		/*
		 * List<String> extensionParam =
		 * extensionparameters.getExtensionparam(); if (extensionParam != null)
		 * { logger.info(
		 * "--------------------------------extension parameter is not null so pin is set"
		 * ); moneyTransfer.setTransactionPin(extensionParam.get(0)); }
		 */
		pagaClient = new PagaClient();
		PagaResponse pagaResponse = pagaClient.performCashOut(moneyTransfer);
		Transfertobankresponse transfertobankresponse = new Transfertobankresponse();

		if (pagaResponse != null) {
			if (pagaResponse.getResponseCode().equalsIgnoreCase("0")) {
				logger.info("--------------------------------serviceResponse is a success");
				transfertobankresponse.setStatuscode(StatusCode.COMPLETED);
			} else {
				logger.info("--------------------------------serviceResponse is not a success");
				transfertobankresponse.setStatuscode(StatusCode.FAILED);
			}

			ParameterExtension parameterExtension = new ParameterExtension();
			parameterExtension.setMmoperator(extensionparameters
					.getMmoperator());
			parameterExtension.setSpTransactionid(pagaResponse
					.getFinancialtransactionid());
			parameterExtension.getExtensionparam().add(
					String.valueOf(pagaResponse.getResponseCode()));
			parameterExtension.getExtensionparam().add(
					pagaResponse.getResponseDescription());
			if (transfertobankresponse.getStatuscode().toString()
					.equalsIgnoreCase("COMPLETED")) {
				parameterExtension.getExtensionparam().add("true");
			} else {
				parameterExtension.getExtensionparam().add("false");
			}
			logger.info("--------------------------------serviceResponse is not null");
			transfertobankresponse.setExtensionparameters(parameterExtension);
			transfertobankresponse.setResponseMessage(pagaResponse
					.getResponseDescription());

		} else {
			logger.info("--------------------------------serviceResponse is null");
		}

		return transfertobankresponse;
	}

	@Override
	public Paybillsresponse paybillsrequest(String orginatingresourceid,
			String merchantcode, String amount, String sendingdescription,
			String receivingdescription, ParameterExtension extensionparameters) {
		MoneyTransfer moneyTransfer = new PagaPropertyValues()
				.getPropertyValues();
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Airtimesalesresponse airtimesalesrequest(
			String orginatingresourceid, String beneficiarynumber,
			String serviceprovider, String amount,
			ParameterExtension extensionparameters) {
		MoneyTransfer moneyTransfer = new PagaPropertyValues()
				.getPropertyValues();
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Balanceresponse balancerequest(String orginatingresourceid,
			ParameterExtension extensionparameters) {
		MoneyTransfer moneyTransfer = new PagaPropertyValues()
				.getPropertyValues();
		// TODO Auto-generated method stub
		return null;
	}

}
