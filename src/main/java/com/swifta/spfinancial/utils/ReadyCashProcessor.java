package com.swifta.spfinancial.utils;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import com.ng.mats.psa.mt.readycash.model.MoneyTransfer;
import com.ng.mats.psa.mt.readycash.util.ReadyCashClient;
import com.readycashng.www.ws.api._1_0.AgentServiceServiceStub.ServiceResponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashinresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashoutresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.ParameterExtension;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.StatusCode;

public class ReadyCashProcessor extends MMOProcessor {
	private static final Logger logger = Logger
			.getLogger(ReadyCashProcessor.class.getName());
	private ReadyCashClient readyCashClient = new ReadyCashClient();

	@Override
	public Cashoutresponse cashoutrequest(String orginatingresourceid,
			String destinationresourceid, BigDecimal amount,
			String sendingdescription, String receivingdescription,
			ParameterExtension extensionparameters) {

		// TODO Auto-generated method stub
		logger.info("----------------------------------Hello World");
		MoneyTransfer moneyTransfer = new MoneyTransfer();
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
		ServiceResponse serviceResponse = readyCashClient
				.performCashout(moneyTransfer);
		Cashoutresponse cashoutresponse = new Cashoutresponse();
		if (serviceResponse != null) {
			ParameterExtension newParam = new ParameterExtension();
			newParam.setMmoperator(extensionparameters.getMmoperator());
			newParam.setSpTransactionid(serviceResponse.getStan());
			newParam.getExtensionparam().add(serviceResponse.getCode());
			newParam.getExtensionparam().add(serviceResponse.getDesc());
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
			if (serviceResponse.getCode().equals("0000"))
				cashoutresponse.setStatuscode(StatusCode.COMPLETED);
			else if (serviceResponse.getCode().equals("0051")
					|| serviceResponse.getCode().equals("0091")
					|| serviceResponse.getCode().equals("0096")
					|| serviceResponse.getCode().equals("9999"))
				cashoutresponse.setStatuscode(StatusCode.FAILED);
			else
				cashoutresponse.setStatuscode(StatusCode.PENDING);
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
		MoneyTransfer moneyTransfer = new MoneyTransfer();
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
	public Double balanceRequest(String orginatingresourceid,
			ParameterExtension extensionparameters) {
		// TODO Auto-generated method stub
		return null;
	}

}
