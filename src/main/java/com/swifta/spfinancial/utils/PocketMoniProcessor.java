package com.swifta.spfinancial.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.UnknownHostException;

import com.etranzact.fundgate.ws.FundGateImplServiceStub.FundResponse;
import com.etranzact.fundgate.ws.PocketMoneyClient;
import com.ng.mats.psa.mt.pocketmoni.model.MoneyTransfer;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashinresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashoutresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.ParameterExtension;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.StatusCode;

public class PocketMoniProcessor extends MMOProcessor {

	@Override
	public Cashoutresponse cashoutrequest(String orginatingresourceid,
			String destinationresourceid, BigDecimal amount,
			String sendingdescription, String receivingdescription,
			ParameterExtension extensionparameters) {
		Cashoutresponse cashoutresponse = new Cashoutresponse();
		String userPin = "";
		if (extensionparameters != null) {
			userPin = extensionparameters.getExtensionparam().get(0);
		}
		MoneyTransfer moneyTransfer = new MoneyTransfer(orginatingresourceid,
				destinationresourceid, null, amount.longValue(),
				extensionparameters.getMmoperator(), sendingdescription,
				userPin);

		PocketMoneyClient pocketMoneyClient = null;
		try {
			pocketMoneyClient = new PocketMoneyClient();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			pocketMoneyClient.configureSecurity();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FundResponse response = null;
		try {
			response = pocketMoneyClient.doCashOut(moneyTransfer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (response != null) {
			cashoutresponse.setDestinationpartnerbalanceafter(String
					.valueOf(response.getClosingBalance()));// response.getOpeningBalance();

			cashoutresponse.setOrginatingpartnerfee(null);
			cashoutresponse.setFinancialtransactionid(response.getReference());// response.getOtherReference();
			cashoutresponse.setOrginatingpartnerbalanceafter(String
					.valueOf(response.getOpeningBalance()));
			cashoutresponse.setResponseMessage(response.getMessage());
			cashoutresponse.setStatuscode(StatusCode.COMPLETED);
			ParameterExtension parameterExtension = new ParameterExtension();
			parameterExtension.setSpTransactionid(response.getOtherReference());

			parameterExtension.getExtensionparam().add(
					String.valueOf(response.getError()));
			parameterExtension.getExtensionparam().add(response.getMessage());
			parameterExtension.getExtensionparam().add(
					String.valueOf(response.getTotalFailed()));
			cashoutresponse.setExtensionparameters(parameterExtension);
		} else {
			cashoutresponse.setStatuscode(StatusCode.FAILED);
			cashoutresponse
					.setResponseMessage("There was no response from PocketMoni");
		}
		return cashoutresponse;

	}

	@Override
	public Cashinresponse cashinrequest(String orginatingresourceid,
			String destinationresourceid, BigDecimal amount,
			String sendingdescription, String receivingdescription,
			ParameterExtension extensionparameters) {
		Cashinresponse cashinresponse = new Cashinresponse();
		String userPin = "";
		if (extensionparameters != null) {
			userPin = extensionparameters.getExtensionparam().get(0);
		}
		MoneyTransfer moneyTransfer = new MoneyTransfer(orginatingresourceid,
				destinationresourceid, null, amount.longValue(),
				extensionparameters.getMmoperator(), sendingdescription,
				userPin);

		PocketMoneyClient pocketMoneyClient = null;
		try {
			pocketMoneyClient = new PocketMoneyClient();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			pocketMoneyClient.configureSecurity();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FundResponse response = null;
		try {
			response = pocketMoneyClient.doCashIn(moneyTransfer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (response != null) {
			cashinresponse.setDestinationpartnerbalanceafter(String
					.valueOf(response.getClosingBalance()));// response.getOpeningBalance();

			cashinresponse.setFee(null);
			cashinresponse.setFinancialtransactionid(response.getReference());// response.getOtherReference();
			cashinresponse.setOrginatingpartnerbalanceafter(String
					.valueOf(response.getOpeningBalance()));
			cashinresponse.setResponseMessage(response.getMessage());
			cashinresponse.setStatuscode(StatusCode.COMPLETED);
			ParameterExtension parameterExtension = new ParameterExtension();
			parameterExtension.setSpTransactionid(response.getOtherReference());
			parameterExtension.getExtensionparam().add(
					String.valueOf(response.getError()));
			parameterExtension.getExtensionparam().add(response.getMessage());
			parameterExtension.getExtensionparam().add(
					String.valueOf(response.getTotalFailed()));
			parameterExtension.getExtensionparam().add(
					String.valueOf(response.getTotalSuccess()));
			cashinresponse.setExtensionparameters(parameterExtension);
		} else {
			cashinresponse.setStatuscode(StatusCode.FAILED);
			cashinresponse
					.setResponseMessage("There was no response from PocketMoni");
		}
		return cashinresponse;

	}

	@Override
	public Double balanceRequest(String orginatingresourceid,
			ParameterExtension extensionparameters) {
		// TODO Auto-generated method stub
		return null;
	}

}
