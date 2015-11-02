package com.swifta.spfinancial.utils;

import java.math.BigDecimal;
import java.util.logging.Logger;

import com.mobilemoney.services.mwallet.MPWalletServiceStub.MBalanceResponse;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.MTransferResponseType;
import com.mobilemoney.services.mwallet.TeasyMobileClient;
import com.mobilemoney.services.mwallet.TeasyMobilePropertyValues;
import com.ng.mats.psa.mt.teasymobile.model.MoneyTransfer;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Airtimesalesresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Balanceresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashinresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashoutresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.ParameterExtension;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Paybillsresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.StatusCode;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Transfertobankresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Verifycashoutresponse;

public class TeasyMobileProcessor extends MMOProcessor {
	private static final Logger logger = Logger
			.getLogger(TeasyMobileProcessor.class.getName());

	@Override
	public Cashoutresponse cashoutrequest(String orginatingresourceid,
			String destinationresourceid, BigDecimal amount,
			String sendingdescription, String receivingdescription,
			ParameterExtension extensionparameters) {
		Cashoutresponse cashoutresponse = new Cashoutresponse();
		MoneyTransfer teasymoneyTransfer = new TeasyMobilePropertyValues()
				.getPropertyValues();
		teasymoneyTransfer.setReceiver(orginatingresourceid);
		teasymoneyTransfer.setAmount(amount);
		teasymoneyTransfer.setReference(receivingdescription);
		teasymoneyTransfer.setTeasypin(extensionparameters.getExtensionparam()
				.get(0));
		TeasyMobileClient teasyMobileClient;
		MTransferResponseType response = null;
		try {
			teasyMobileClient = new TeasyMobileClient(teasymoneyTransfer);
			logger.info("---------------------posting to teasymobile and agent is =="
					+ teasymoneyTransfer.getSender());
			response = teasyMobileClient.doCashout(teasymoneyTransfer);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (response != null) {
			logger.info("---------------------response message gotten from Teasy is =="
					+ response.getResponseMessage());
			logger.info("---------------------response status gotten from Teasy is =="
					+ response.getStatus());
			logger.info("---------------------response transaction id gotten from Teasy is =="
					+ response.getTransactionId());
			if (response.getStatus() == 0) {
				ParameterExtension parameterExtension = new ParameterExtension();
				// uncomment this pls
				parameterExtension.setSpTransactionid(response
						.getTransactionId());
				parameterExtension.getExtensionparam().add(
						String.valueOf(response.getStatus()));
				parameterExtension.getExtensionparam().add(
						response.getResponseMessage());
				cashoutresponse.setExtensionparameters(parameterExtension);
				// if (cashoutresponse.getStatuscode().toString()
				// .equals("COMPLETED"))
				// cashoutresponse.getStatuscode().
				// if (cashoutresponse.getStatuscode().equals("COMPLETED")) {
				// parameterExtension.getExtensionparam().add("true");
				// } else {
				// parameterExtension.getExtensionparam().add("false");
				// }
				response.setResponseMessage("Transaction Successful");
				logger.info("---------------------response returned as "
						+ StatusCode.COMPLETED);
				cashoutresponse.setStatuscode(StatusCode.COMPLETED);

			} else {
				logger.info("---------------------response returned as "
						+ StatusCode.REJECTED);
				cashoutresponse.setStatuscode(StatusCode.REJECTED);
			}
			cashoutresponse.setDestinationpartnerbalanceafter("0");
			cashoutresponse.setOrginatingpartnerbalanceafter("0");
			cashoutresponse.setOrginatingpartnerfee("0");

			cashoutresponse.setResponseMessage(response.getResponseMessage());

			logger.info("-----------------------STATUS CODE IS :::::::"
					+ cashoutresponse.getStatuscode().toString());

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
		// MoneyTransfer teasymoneyTransfer = new MoneyTransfer(
		// orginatingresourceid, amount, receivingdescription, "");
		MoneyTransfer teasymoneyTransfer = new TeasyMobilePropertyValues()
				.getPropertyValues();
		teasymoneyTransfer.setReceiver(orginatingresourceid);
		teasymoneyTransfer.setAmount(amount);
		// teasymoneyTransfer.setTeasypin(extensionparameters.getExtensionparam()
		// .get(0));
		TeasyMobileClient teasyMobileClient;
		MTransferResponseType response = null;
		try {
			teasyMobileClient = new TeasyMobileClient(teasymoneyTransfer);
			System.out.println("posting to teasymobile >>>"
					+ teasymoneyTransfer.toString());
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
				cashinresponse.setDestinationpartnerbalanceafter(null);
				cashinresponse.setFinancialtransactionid(response
						.getTransactionId());
				cashinresponse.setOrginatingpartnerbalanceafter(null);
				cashinresponse.setFee(null);
				cashinresponse
						.setResponseMessage(response.getResponseMessage());
				ParameterExtension parameterExtension = new ParameterExtension();
				parameterExtension.setSpTransactionid(response
						.getTransactionId());
				parameterExtension.getExtensionparam().add(
						String.valueOf(response.getStatus()));
				parameterExtension.getExtensionparam().add(
						response.getResponseMessage());
				cashinresponse.setExtensionparameters(parameterExtension);
			} else {
				logger.info("---------------------response returned as "
						+ StatusCode.REJECTED);
				cashinresponse.setStatuscode(StatusCode.REJECTED);
			}

		} else {
			cashinresponse.setStatuscode(StatusCode.FAILED);
			logger.info("---------------------response returned as "
					+ StatusCode.FAILED);
		}
		return cashinresponse;
	}

	@Override
	public Verifycashoutresponse verifycashoutrequest(
			String orginatingresourceid, String subscriberphonenumber,
			String amount, String referencenumber,
			ParameterExtension extensionparameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transfertobankresponse transfertobank(String orginatingresourceid,
			String amount, String narration,
			ParameterExtension extensionparameters) {
		// TODO Auto-generated method stub
		return null;
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
		Balanceresponse balanceresponse = new Balanceresponse();
		MoneyTransfer teasymoneyTransfer = new TeasyMobilePropertyValues()
				.getPropertyValues();
		teasymoneyTransfer.setReceiver(orginatingresourceid);
		teasymoneyTransfer.setTeasypin(extensionparameters.getExtensionparam()
				.get(0));
		TeasyMobileClient teasyMobileClient;
		MBalanceResponse response = null;
		try {
			teasyMobileClient = new TeasyMobileClient(teasymoneyTransfer);
			logger.info("---------------------posting to teasymobile and agent is =="
					+ teasymoneyTransfer.getSender());
			response = teasyMobileClient.getBalance(teasymoneyTransfer);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (response != null) {
			logger.info("---------------------response message gotten from Teasy is =="
					+ response.getResponseMessage());
			logger.info("---------------------response status gotten from Teasy is =="
					+ response.getStatus());
			if (response.getStatus() == 0) {
				response.setResponseMessage("Transaction Successful");
				logger.info("---------------------response returned as "
						+ StatusCode.COMPLETED);
				balanceresponse.setStatuscode(StatusCode.COMPLETED);
			} else {
				logger.info("---------------------response returned as "
						+ StatusCode.REJECTED);
				balanceresponse.setStatuscode(StatusCode.REJECTED);
			}
			balanceresponse
					.setBalance(String.valueOf(response.getBalance() / 100));

			balanceresponse.setResponseMessage(response.getResponseMessage());
			ParameterExtension parameterExtension = new ParameterExtension();
			// uncomment this pls
			// parameterExtension.setSpTransactionid(response.getTransactionId());
			parameterExtension.getExtensionparam().add(
					String.valueOf(response.getStatus()));
			parameterExtension.getExtensionparam().add(
					response.getResponseMessage());
			logger.info("-----------------------STATUS CODE IS :::::::"
					+ balanceresponse.getStatuscode().toString());
			if (balanceresponse.getStatuscode().toString()
					.equalsIgnoreCase("COMPLETED")) {
				parameterExtension.getExtensionparam().add("true");
			} else {
				parameterExtension.getExtensionparam().add("false");
			}
			balanceresponse.setExtensionparameters(parameterExtension);
		} else {
			logger.info("---------------------response returned as "
					+ StatusCode.FAILED);
			balanceresponse.setStatuscode(StatusCode.FAILED);
		}
		return balanceresponse;

	}

	@Override
	public Cashoutresponse cashoutunregisteredrequest(
			String orginatingresourceid, String subscriberphonenumber,
			BigDecimal amount, String referencenumber, String referencecode,
			String receivingdescription, ParameterExtension extensionparameters) {
		// TODO Auto-generated method stub
		return null;
	}

}
