package com.swifta.spfinancial.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.ng.mats.psa.mt.fortis.util.Constants;
import com.ng.mats.psa.mt.fortis.util.FortisClient;
import com.ng.mats.psa.mt.fortis.util.MoneyTransfer;
import com.ng.mats.psa.mt.fortis.xmlprocessor.Message;
import com.ng.mats.psa.mt.fortis.xmlprocessor.RefID;
import com.ng.mats.psa.mt.fortis.xmlprocessor.Response;
import com.ng.mats.psa.mt.fortis.xmlprocessor.SctlID;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Airtimesalesresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Balanceresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashinresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashoutresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.ParameterExtension;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Paybillsresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.StatusCode;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Transfertobankresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Verifycashoutresponse;

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

		logger.info(extensionparameters.getExtensionparam().get(0));

		MoneyTransfer moneyTransfer = fortisClient.getMoneyTransfer();
		// moneyTransfer.setSourcePocketCode(Constants.SOURCEPOCKETCODEWALLET);
		// if (destinationresourceid.isEmpty())
		// moneyTransfer.setDestMdn(Constants.customerNumber);
		// else
		moneyTransfer.setDestMdn(orginatingresourceid);
		// moneyTransfer.setConfirmed("true");
		// moneyTransfer.setAgentCode(Constants.agentCode);
		// moneyTransfer.setDestPocketCode(Constants.DESTINATIONPOCKETCODEWALLET);
		moneyTransfer.setTransferId(extensionparameters.getExtensionparam()
				.get(0));
		moneyTransfer.setSecreteCode(extensionparameters.getExtensionparam()
				.get(1));
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
				cashoutResponse.setResponseMessage(message.getValue());
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
				cashoutResponse.setExtensionparameters(parameterExtension);
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
			Message message = response.getMessage();
			SctlID sctlID = response.getSctlID();
			logger.info("-----------------------SCTID"
					+ response.getSctlID().getValue());
			if (message != null) {
				logger.info("--------------------------------message is not null");
				System.out.println(message.getCode().equalsIgnoreCase("296"));
				boolean success = false;
				if (message.getCode().equalsIgnoreCase("296")) {
					success = true;
				}

				ParameterExtension parameterExtension = new ParameterExtension();
				if (success) {
					logger.info("--------------------------------response is a success");
					cashinResponse.setStatuscode(StatusCode.COMPLETED);
					cashinResponse.setResponseMessage(response.getMessage()
							.getValue());
					parameterExtension.setMmoperator(extensionparameters
							.getMmoperator());
					parameterExtension.setSpTransactionid(sctlID.getValue());
					// RefID refId = response.getRefID();
					// parameterExtension.getExtensionparam().add(
					// String.valueOf(message.getCode()));
					// parameterExtension.getExtensionparam().add(
					// message.getValue());
					cashinResponse.setDestinationpartnerbalanceafter("0");
					cashinResponse.setExtensionparameters(parameterExtension);
					cashinResponse.setFinancialtransactionid("0");
					cashinResponse.setOrginatingpartnerbalanceafter("0");
					cashinResponse.setFee("0");
					cashinResponse.setResponseMessage(message.getValue());
					// if (refId != null)

				} else {
					logger.info("--------------------------------response is not a success");
					cashinResponse.setStatuscode(StatusCode.FAILED);
				}

			} else {
				logger.info("--------------------------------message is null");
				cashinResponse.setStatuscode(StatusCode.FAILED);
			}
		} else {
			logger.info("--------------------------------response is null");
			cashinResponse.setStatuscode(StatusCode.FAILED);
		}
		return cashinResponse;

	}

	@Override
	public Verifycashoutresponse verifycashoutrequest(
			String orginatingresourceid, String subscriberphonenumber,
			String amount, String referencenumber,
			ParameterExtension extensionparameters) {
		FortisClient fortisClient = new FortisClient(Constants.account,
				Constants.TXNLOGIN);
		MoneyTransfer moneyTransfer = fortisClient.getMoneyTransfer();
		String response = fortisClient.getHistory(moneyTransfer);

		Verifycashoutresponse verifycashoutresponse = new Verifycashoutresponse();

		String responsecode = "";

		Document document = null;

		ByteArrayOutputStream s = new ByteArrayOutputStream();
		;

		if (!response.isEmpty()) {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = null;
			try {
				db = dbf.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				// document = db.parse(response);
				document = db.parse(new InputSource(new ByteArrayInputStream(
						response.getBytes("utf-8"))));
			} catch (SAXException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			NodeList nodeList = document.getElementsByTagName("message");

			responsecode = nodeList.item(0).getAttributes()
					.getNamedItem("code").getNodeValue();
			// System.out.println(nodeList.item(0).getAttributes()
			// .getNamedItem("code").getNodeValue());
		}

		Transformer t;
		try {
			t = TransformerFactory.newInstance().newTransformer();
			// t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			// t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
			// "2");
			t.transform(new DOMSource(document), new StreamResult(s));
			System.out.println("i'm here");
			System.out.println(new String(s.toByteArray()));
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (responsecode.equals("39")) {
			verifycashoutresponse.setStatuscode(StatusCode.COMPLETED);
			verifycashoutresponse
					.setResponseMessage(new String(s.toByteArray()));
			verifycashoutresponse.setFinancialtransactionid("0");
		}

		return verifycashoutresponse;
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
			String billerPubicId, String amount, String billServicename,
			String accountNumber, ParameterExtension extensionparameters) {
		FortisClient fortisClient = new FortisClient(Constants.account,
				Constants.TXNLOGIN);

		MoneyTransfer moneyTransfer = fortisClient.getMoneyTransfer();
		// //
		// moneyTransfer.setSourcePocketCode(Constants.SOURCEPOCKETCODEWALLET);
		// if (orginatingresourceid.isEmpty())
		// moneyTransfer.setDestMdn(Constants.customerNumber);
		// else
		moneyTransfer.setDestMdn(orginatingresourceid);
		moneyTransfer.setBillerCode(billerPubicId);
		moneyTransfer.setBillNo(accountNumber);
		// moneyTransfer.setConfirmed("true");
		// moneyTransfer.setAgentCode(Constants.agentCode);
		// moneyTransfer.setDestPocketCode(Constants.DESTINATIONPOCKETCODEWALLET);
		moneyTransfer.setAmount(String.valueOf(amount));
		Response response = fortisClient.performBillPayment(moneyTransfer);
		logger.info("-----------------------After initiating login"
				+ response.toString());
		Paybillsresponse paybillsresponse = new Paybillsresponse();

		logger.info("--------------------------------response is not null");
		Message message = response.getMessage();
		SctlID sctlID = response.getSctlID();
		logger.info("-----------------------SCTID"
				+ response.getSctlID().getValue());
		if (message != null) {
			logger.info("--------------------------------message is not null");
			System.out.println(message.getCode().equalsIgnoreCase("715"));
			boolean success = false;
			if (message.getCode().equalsIgnoreCase("715")) {
				success = true;
			}

			ParameterExtension parameterExtension = new ParameterExtension();
			if (success) {
				logger.info("--------------------------------response is a success");
				paybillsresponse.setStatuscode(StatusCode.COMPLETED);
				paybillsresponse.setResponseMessage(response.getMessage()
						.getValue());
				parameterExtension.setMmoperator(extensionparameters
						.getMmoperator());
				parameterExtension.setSpTransactionid(sctlID.getValue());
				// RefID refId = response.getRefID();
				// parameterExtension.getExtensionparam().add(
				// String.valueOf(message.getCode()));
				// parameterExtension.getExtensionparam().add(
				// message.getValue());
				paybillsresponse.setExtensionparameters(parameterExtension);
				paybillsresponse.setResponseMessage(message.getValue());
				// if (refId != null)

			} else {
				logger.info("--------------------------------response is not a success");
				paybillsresponse.setStatuscode(StatusCode.FAILED);
			}

		} else {
			logger.info("--------------------------------message is null");
			paybillsresponse.setStatuscode(StatusCode.FAILED);
		}
		return paybillsresponse;

	}

	@Override
	public Airtimesalesresponse airtimesalesrequest(
			String orginatingresourceid, String beneficiarynumber,
			String serviceprovider, String amount,
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
		moneyTransfer.setCompanyId(serviceprovider);
		// moneyTransfer.setConfirmed("true");
		// moneyTransfer.setAgentCode(Constants.agentCode);
		// moneyTransfer.setDestPocketCode(Constants.DESTINATIONPOCKETCODEWALLET);
		moneyTransfer.setAmount(String.valueOf(amount));
		Response response = fortisClient.performAirtimeSales(moneyTransfer);
		logger.info("-----------------------After initiating login"
				+ response.toString());
		Airtimesalesresponse airtimesalesresponse = new Airtimesalesresponse();

		logger.info("--------------------------------response is not null");
		Message message = response.getMessage();
		SctlID sctlID = response.getSctlID();
		logger.info("-----------------------SCTID"
				+ response.getSctlID().getValue());
		if (message != null) {
			logger.info("--------------------------------message is not null");
			System.out.println(message.getCode().equalsIgnoreCase("715"));
			boolean success = false;
			if (message.getCode().equalsIgnoreCase("715")) {
				success = true;
			}

			ParameterExtension parameterExtension = new ParameterExtension();
			if (success) {
				logger.info("--------------------------------response is a success");
				airtimesalesresponse.setStatuscode(StatusCode.COMPLETED);
				airtimesalesresponse.setResponseMessage(response.getMessage()
						.getValue());
				parameterExtension.setMmoperator(extensionparameters
						.getMmoperator());
				parameterExtension.setSpTransactionid(sctlID.getValue());
				// RefID refId = response.getRefID();
				// parameterExtension.getExtensionparam().add(
				// String.valueOf(message.getCode()));
				// parameterExtension.getExtensionparam().add(
				// message.getValue());
				airtimesalesresponse.setExtensionparameters(parameterExtension);
				airtimesalesresponse.setResponseMessage(message.getValue());
				// if (refId != null)

			} else {
				logger.info("--------------------------------response is not a success");
				airtimesalesresponse.setStatuscode(StatusCode.FAILED);
			}

		} else {
			logger.info("--------------------------------message is null");
			airtimesalesresponse.setStatuscode(StatusCode.FAILED);
		}
		return airtimesalesresponse;

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

		FortisClient fortisClient = new FortisClient(Constants.account,
				Constants.TXNLOGIN);

		// logger.info(extensionparameters.getExtensionparam().get(0));

		MoneyTransfer moneyTransfer = fortisClient.getMoneyTransfer();
		// moneyTransfer.setSourcePocketCode(Constants.SOURCEPOCKETCODEWALLET);
		// if (destinationresourceid.isEmpty())
		// moneyTransfer.setDestMdn(Constants.customerNumber);
		// else
		moneyTransfer.setDestMdn(orginatingresourceid);
		// moneyTransfer.setConfirmed("true");
		// moneyTransfer.setAgentCode(Constants.agentCode);
		// moneyTransfer.setDestPocketCode(Constants.DESTINATIONPOCKETCODEWALLET);
		moneyTransfer.setTransferId(referencenumber);
		moneyTransfer.setSecreteCode(referencecode);
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
				if (message.getCode().equalsIgnoreCase("680")) {
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
				cashoutResponse.setExtensionparameters(parameterExtension);
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

}
