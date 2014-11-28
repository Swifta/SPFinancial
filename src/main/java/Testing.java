import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang.RandomStringUtils;

import com.swifta.spfinancial.utils.SMSEngine;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashinresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashoutresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.ParameterExtension;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.StatusCode;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1_0.SPfinancialPortImpl;

public class Testing {
	private static final Logger logger = Logger.getLogger(Testing.class
			.getName());

	public static void main(String args[]) throws Exception {

		// performCashinTeasy();
		// performCashoutTeasy();
		// performCashOut();
		performCashIn();
	}

	public static String generateReferencenNumber(int length) {
		// random PIN number
		String transactionId = "";
		transactionId = RandomStringUtils.randomNumeric(length);
		return transactionId;
	}

	public static void performCashOut() throws Exception {

		SPfinancialPortImpl impl = new SPfinancialPortImpl();
		// pocketmoni
		// String userPin = "7005";
		// String orginatingresourceid = "08063305711";
		// String destinationresourceid = "2348076763191";

		// teasymobile
		String userPin = "1234";
		String orginatingresourceid = "08063305711";
		String destinationresourceid = "2348104001339";

		// readycash
		// String userPin = "0000000000000000";
		// String destinationresourceid = "08034083054";
		// String orginatingresourceid = "08063305711";

		String amount = "40";
		String sendingdescription = "Payments";
		String receivingdescription = "details";
		ParameterExtension extensionparameters = new ParameterExtension();
		System.out
				.println("--------------------------------After instantiating extension parameters");
		extensionparameters.setMmoperator("teasymobile");
		extensionparameters.setSpTransactionid(generateReferencenNumber(12));
		extensionparameters.getExtensionparam().add(userPin);
		System.out
				.println("--------------------------------After setting extension parameters");
		Cashoutresponse cashoutResponse = impl.cashoutrequest(
				orginatingresourceid, destinationresourceid, amount,
				sendingdescription, receivingdescription, extensionparameters);
		System.out
				.println("--------------------------------After sending transaction to backend");
		String feedBack = "", subBalance = "", agentBalance = "", fee = "", responseMessage = "", mmOperator = "", transactionId = "";
		if (cashoutResponse != null) {
			System.out
					.println("--------------------------------Cashout response is not null");
			subBalance = cashoutResponse.getDestinationpartnerbalanceafter();
			ParameterExtension extensionParameters = cashoutResponse
					.getExtensionparameters();
			mmOperator = extensionParameters.getMmoperator();
			System.out.println("--------------------------------MM Operator"
					+ mmOperator);
			List<String> extensionParameterList = extensionParameters
					.getExtensionparam();
			Iterator<String> paramsIterator = extensionParameterList.iterator();
			while (paramsIterator.hasNext()) {
				System.out
						.println("--------------------------------Iterating...."
								+ paramsIterator.next());
			}
			extensionParameters.getSpTransactionid();
			fee = cashoutResponse.getOrginatingpartnerfee();
			transactionId = cashoutResponse.getFinancialtransactionid();
			agentBalance = cashoutResponse.getOrginatingpartnerbalanceafter();
			responseMessage = cashoutResponse.getResponseMessage();

			StatusCode statusCode = cashoutResponse.getStatuscode();
			if (statusCode != null) {
				System.out
						.println("--------------------------------Status code not null");
				feedBack = statusCode.toString();
			} else {
				System.out
						.println("--------------------------------Status code is null");
			}
		} else {
			System.out
					.println("--------------------------------cashoutResponse is null");
		}
		if (feedBack.equalsIgnoreCase("COMPLETED")) {
			System.out.println("--------------------------------"
					+ extensionparameters.getMmoperator().toUpperCase()
					+ " WAS SUCCESSFUL!!!! AZONTO!!!");
		} else {
			System.out.println("--------------------------------"
					+ extensionparameters.getMmoperator().toUpperCase()
					+ " has faults!!!!!!!!!!!!" + feedBack);
		}
		System.out.println("--------------------------------"
				+ extensionparameters.getMmoperator().toUpperCase()
				+ " Response message>>>>" + responseMessage + " ========"
				+ extensionparameters.getMmoperator().toUpperCase()
				+ " feedback>>>>>>>>" + feedBack);
		SMSEngine smsEngine = new SMSEngine();
		transactionId = transactionId == null ? "N/A" : transactionId;
		subBalance = subBalance == null ? "N/A" : subBalance;
		agentBalance = agentBalance == null ? "N/A" : agentBalance;
		String message = "Cashout>> " + transactionId + " was successful. N"
				+ subBalance + " was sent and your Balance is now :N"
				+ agentBalance;
		System.out.println("--------------------------------SMS message::::::"
				+ message);

		// smsEngine.sendSMS(smsParameters);
	}

	public static void performCashIn() throws Exception {
		// String userPin = "7005";
		SPfinancialPortImpl impl = new SPfinancialPortImpl();
		// pocketmoni
		String userPin = "7005";
		String orginatingresourceid = "08063305711";
		String destinationresourceid = "2348076763191";

		// teasymobile
		// String userPin = "1234";
		// String orginatingresourceid = "08063305711";
		// String destinationresourceid = "2348104001339";

		// readycash
		// String userPin = "0000000000000000";
		// String destinationresourceid = "08034083054";
		// String orginatingresourceid = "08063305711";

		String amount = "10";
		String sendingdescription = "Payments";
		String receivingdescription = "details";
		ParameterExtension extensionparameters = new ParameterExtension();
		System.out
				.println("--------------------------------After instantiating extension parameters");
		extensionparameters.setMmoperator("pocketmoni");
		extensionparameters.setSpTransactionid(generateReferencenNumber(12));
		extensionparameters.getExtensionparam().add(userPin);
		System.out
				.println("--------------------------------After setting extension parameters");
		Cashinresponse cashinResponse = impl.cashinrequest(
				orginatingresourceid, destinationresourceid, amount,
				sendingdescription, receivingdescription, extensionparameters);
		System.out
				.println("--------------------------------After sending transaction to backend");
		String feedBack = "", subBalance = "", agentBalance = "", fee = "", responseMessage = "", mmOperator = "", transactionId = "";
		if (cashinResponse != null) {
			System.out
					.println("--------------------------------Cashin response is not null");
			ParameterExtension extensionParameters = cashinResponse
					.getExtensionparameters();
			mmOperator = extensionParameters.getMmoperator();
			System.out.println("--------------------------------MM Operator"
					+ mmOperator);
			List<String> extensionParameterList = extensionParameters
					.getExtensionparam();
			Iterator<String> paramsIterator = extensionParameterList.iterator();
			while (paramsIterator.hasNext()) {
				System.out
						.println("--------------------------------Iterating...."
								+ paramsIterator.next());
			}
			extensionParameters.getSpTransactionid();
			subBalance = cashinResponse.getDestinationpartnerbalanceafter();
			cashinResponse.getExtensionparameters();
			fee = cashinResponse.getFee();
			transactionId = cashinResponse.getFinancialtransactionid();
			agentBalance = cashinResponse.getOrginatingpartnerbalanceafter();
			responseMessage = cashinResponse.getResponseMessage();

			StatusCode statusCode = cashinResponse.getStatuscode();
			if (statusCode != null) {
				System.out
						.println("--------------------------------Status code not null");
				feedBack = statusCode.toString();
			} else {
				System.out
						.println("--------------------------------Status code is null");
			}
		} else {
			System.out
					.println("--------------------------------Cashin Response is null");
		}
		if (feedBack.equalsIgnoreCase("COMPLETED")) {
			System.out.println("--------------------------------"
					+ extensionparameters.getMmoperator().toUpperCase()
					+ " WAS SUCCESSFUL!!!! AZONTO!!!");
		} else {
			System.out.println("--------------------------------"
					+ extensionparameters.getMmoperator().toUpperCase()
					+ " has faults!!!!!!!!!!!!" + feedBack);
		}
		System.out.println("--------------------------------"
				+ extensionparameters.getMmoperator().toUpperCase()
				+ " Response message>>>>" + responseMessage + " ========"
				+ extensionparameters.getMmoperator().toUpperCase()
				+ " feedback>>>>>>>>" + feedBack);
		SMSEngine smsEngine = new SMSEngine();
		transactionId = transactionId == null ? "N/A" : transactionId;
		subBalance = subBalance == null ? "N/A" : subBalance;
		agentBalance = agentBalance == null ? "N/A" : agentBalance;
		String message = "Cashin " + transactionId + " was successful. N"
				+ subBalance + " was sent and your Balance is now :N"
				+ agentBalance;
		System.out.println("--------------------------------SMS message::::::"
				+ message);

		// smsEngine.sendSMS(smsParameters);
	}

}
