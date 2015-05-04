package com.swifta.spfinancial.utils;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import org.apache.axis2.AxisFault;

import com.ng.mats.psa.mt.readycash.model.MoneyTransfer;
import com.ng.mats.psa.mt.readycash.util.ReadyCashClient;
import com.ng.mats.psa.mt.readycash.util.ReadyCashPropertyValues;
import com.readycashng.www.ws.api._1_0.test.AgentServiceServiceStub.ServiceResponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Airtimesalesresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Balanceresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashinresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashoutresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.ParameterExtension;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Paybillsresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.StatusCode;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Transfertobankresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Verifycashoutresponse;

public class ReadyCashProcessor extends MMOProcessor {
	private static final Logger logger = Logger
			.getLogger(ReadyCashProcessor.class.getName());
	private ReadyCashClient readyCashClient;

	@Override
	public Cashoutresponse cashoutrequest(String orginatingresourceid,
			String destinationresourceid, BigDecimal amount,
			String sendingdescription, String receivingdescription,
			ParameterExtension extensionparameters) {

		// TODO Auto-generated method stub
		logger.info("----------------------------------Hello World");
		MoneyTransfer moneyTransfer = new ReadyCashPropertyValues()
				.getPropertyValues();
		// String key = "ABCDEDF00000FFFF";
		String password = "password";

		moneyTransfer.setAmount(amount);
		moneyTransfer.setMmo(extensionparameters.getMmoperator());
		// moneyTransfer.setAgentUsername("mats@mats.com");
		// moneyTransfer.setReadyCashPin(password);
		// moneyTransfer.setReceiver(destinationresourceid);
		// moneyTransfer.setReceiver(Constants.READYCASH_AGENT_MSISDN);
		moneyTransfer.setReceiver(orginatingresourceid);
		moneyTransfer.setSender(destinationresourceid);
		List<String> extensionParam = extensionparameters.getExtensionparam();
		moneyTransfer.setAgentPin(Constants.READYCASH_AGENT_PIN);

		if (extensionParam != null) {
			logger.info("--------------------------------extension parameter is not null so pin is set");
			moneyTransfer.setSender(extensionParam.get(0));
		} else {
			logger.info("--------------------------------extension parameter is null so THERE IS NO TOKEN TO BE PASSED");
		}

		// check that dev branch is working
		logger.info("--------------------------------contents being sent"
				+ moneyTransfer.toString());
		try {
			logger.info("--------------------------------inside the try catch");
			readyCashClient = new ReadyCashClient(moneyTransfer);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			logger.info("--------------------------------AxisFault Exception thrown"
					+ e.getMessage());
			e.printStackTrace();
		}
		ServiceResponse serviceResponse = readyCashClient
				.performCashout(moneyTransfer);
		Cashoutresponse cashoutresponse = new Cashoutresponse();
		if (serviceResponse != null) {
			if (serviceResponse.getCode().equals("0000"))
				cashoutresponse.setStatuscode(StatusCode.COMPLETED);
			else if (serviceResponse.getCode().equals("0051")
					|| serviceResponse.getCode().equals("0091")
					|| serviceResponse.getCode().equals("0096")
					|| serviceResponse.getCode().equals("9999"))
				cashoutresponse.setStatuscode(StatusCode.FAILED);
			else
				cashoutresponse.setStatuscode(StatusCode.PENDING);

			ParameterExtension newParam = new ParameterExtension();
			newParam.setMmoperator(extensionparameters.getMmoperator());
			newParam.setSpTransactionid(serviceResponse.getStan());
			newParam.getExtensionparam().add(serviceResponse.getCode());
			newParam.getExtensionparam().add(serviceResponse.getDesc());
			if (cashoutresponse.getStatuscode().toString()
					.equalsIgnoreCase("COMPLETED")) {
				newParam.getExtensionparam().add("true");
			} else {
				newParam.getExtensionparam().add("false");
			}
			logger.info("--------------------------------serviceResponse is not null");
			cashoutresponse.setDestinationpartnerbalanceafter(serviceResponse
					.getAvailableBalance());
			cashoutresponse.setExtensionparameters(newParam);
			cashoutresponse
					.setFinancialtransactionid(serviceResponse.getStan());
			cashoutresponse.setOrginatingpartnerbalanceafter(serviceResponse
					.getLedgerBalance());
			// cashoutresponse.setOrginatingpartnerfee(partnerFee);
			cashoutresponse.setResponseMessage(serviceResponse.getDesc());

		} else {
			logger.info("--------------------------------serviceResponse is null");
		}
		// balanceEnquiry(moneyTransfer);
		return cashoutresponse;
	}

	@Override
	public Cashinresponse cashinrequest(String orginatingresourceid,
			String destinationresourceid, BigDecimal amount,
			String sendingdescription, String receivingdescription,
			ParameterExtension extensionparameters) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		logger.info("----------------------------------Hello World");
		MoneyTransfer moneyTransfer = new ReadyCashPropertyValues()
				.getPropertyValues();
		String key = "ABCDEDF00000FFFF";
		String password = "password";

		moneyTransfer.setAmount(amount);
		moneyTransfer.setMmo(extensionparameters.getMmoperator());
		moneyTransfer.setAgentUsername("mats@mats.com");
		moneyTransfer.setReadyCashPin(password);
		moneyTransfer.setReceiver(destinationresourceid);
		List<String> extensionParam = extensionparameters.getExtensionparam();
		if (extensionParam != null) {
			logger.info("--------------------------------extension parameter is not null so pin is set");
			moneyTransfer.setAgentPin(extensionParam.get(0));
		} else {
			logger.info("--------------------------------extension parameter is null so default pin is set");
			moneyTransfer.setAgentPin("0000000000000000");
		}
		// check that dev branch is working
		logger.info("--------------------------------contents being sent"
				+ moneyTransfer.toString());
		try {
			logger.info("--------------------------------inside the try catch");
			readyCashClient = new ReadyCashClient(moneyTransfer);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			logger.info("--------------------------------AxisFault Exception thrown"
					+ e.getMessage());
			e.printStackTrace();
		}
		ServiceResponse serviceResponse = readyCashClient
				.performCashIn(moneyTransfer);
		Cashinresponse cashinresponse = new Cashinresponse();
		cashinresponse.setDestinationpartnerbalanceafter(serviceResponse
				.getAvailableBalance());
		cashinresponse.setExtensionparameters(extensionparameters);
		cashinresponse.setFinancialtransactionid(serviceResponse.getStan());
		cashinresponse.setOrginatingpartnerbalanceafter(serviceResponse
				.getLedgerBalance());
		// cashoutresponse.setOrginatingpartnerfee(partnerFee);
		cashinresponse.setResponseMessage(serviceResponse.getDesc());
		if (serviceResponse.getCode().equals("0000"))
			cashinresponse.setStatuscode(StatusCode.COMPLETED);
		else if (serviceResponse.getCode().equals("0051")
				|| serviceResponse.getCode().equals("0091")
				|| serviceResponse.getCode().equals("0096")
				|| serviceResponse.getCode().equals("9999"))
			cashinresponse.setStatuscode(StatusCode.FAILED);
		else
			cashinresponse.setStatuscode(StatusCode.PENDING);
		// balanceEnquiry(moneyTransfer);
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
		// TODO Auto-generated method stub
		return null;
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
