import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang.RandomStringUtils;

import com.swifta.spfinancial.utils.SMSEngine;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Airtimesalesresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashinresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashoutresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.ParameterExtension;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Paybillsresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.StatusCode;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Verifycashoutresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1_0.SPfinancialPortImpl;

public class Testing {
	private static final Logger logger = Logger.getLogger(Testing.class
			.getName());

	public static void main(String args[]) throws Exception {

		// performCashinTeasy();
		// performCashoutTeasy();
		purchaseAirtime();
		// paybills();
		// performCashIn();
		// performCashOut();
		// performVerifyCashOut();

		// doCashOutUnregistered();
		/*
		 * Error Code: -1 Message: Transaction timed out Other reference:
		 * 201502121817495 Reference: 09FG201502121817353512123 Amount: 0.0
		 * Date: null
		 */
	}

	private static void paybills() {
		SPfinancialPortImpl impl = new SPfinancialPortImpl();

		// paga
		// String userPin = "0000000000000000";
		String orginatingresourceid = "2348051739048";
		String billerPubicId = "A3878DC1-F07D-48E7-AA59-8276C3C26647";
		String billServicename = "ACCESS";
		String accountNumber = "4115702261";
		String FirstName = "Modupe";
		String LastName = "Ladejebi";

		// fortis
		// String orginatingresourceid = "2349082068605";
		// String billerPubicId = "20702";
		// String accountNumber = "12345678";
		// String billServicename = "ACCESS";

		// String companyid = "01";
		String amount = "1500";
		// String sendingdescription = "Payments";
		// String receivingdescription = "details";
		ParameterExtension extensionparameters = new ParameterExtension();
		System.out
				.println("--------------------------------After instantiating extension parameters");
		extensionparameters.setMmoperator("pagatech");
		extensionparameters.setSpTransactionid(generateReferencenNumber(12));
		extensionparameters.getExtensionparam().add(FirstName);
		extensionparameters.getExtensionparam().add(LastName);
		System.out
				.println("--------------------------------After setting extension parameters");

		Paybillsresponse paybillsresponse = impl.paybillsrequest(
				orginatingresourceid, billerPubicId, amount, billServicename,
				accountNumber, extensionparameters);
		System.out
				.println("--------------------------------After sending transaction to backend");
		String feedBack = "", subBalance = "", agentBalance = "", fee = "", responseMessage = "", mmOperator = "", transactionId = "";
		if (paybillsresponse != null) {
			System.out
					.println("--------------------------------Cashin response is not null");
			ParameterExtension extensionParameters = paybillsresponse
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
			// extensionParameters.getSpTransactionid();
			// subBalance =
			// airtiemResoponse.getDestinationpartnerbalanceafter();
			// airtiemResoponse.getExtensionparameters();
			// fee = airtiemResoponse.getFee();
			// transactionId = airtiemResoponse.getFinancialtransactionid();
			// agentBalance =
			// airtiemResoponse.getOrginatingpartnerbalanceafter();
			responseMessage = paybillsresponse.getResponseMessage();

			StatusCode statusCode = paybillsresponse.getStatuscode();
			if (statusCode != null) {
				System.out
						.println("--------------------------------Status code not null");
				feedBack = statusCode.toString();
				System.out.println(feedBack = statusCode.toString());
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
					+ " WAS SUCCESSFUL!!!! AZONTO!!!"
					+ extensionparameters.getSpTransactionid());
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

	}

	private static void purchaseAirtime() {
		SPfinancialPortImpl impl = new SPfinancialPortImpl();

		// paga
		// String userPin = "0000000000000000";
		String orginatingresourceid = "08030000000";
		// String destinationresourceid = "";
		// String subscriberphonenumber = "";
		// String transactionreference = "323507V";
		// String redeemcode = "38436881";

		// fortis
		// String orginatingresourceid = "2348032249455";
		String companyid = "01";
		String amount = "100";
		// String sendingdescription = "Payments";
		// String receivingdescription = "details";
		ParameterExtension extensionparameters = new ParameterExtension();
		System.out
				.println("--------------------------------After instantiating extension parameters");
		extensionparameters.setMmoperator("pagatech");
		extensionparameters.setSpTransactionid(generateReferencenNumber(12));
		// extensionparameters.getExtensionparam().add(userPin);
		System.out
				.println("--------------------------------After setting extension parameters");
		Airtimesalesresponse airtiemResoponse = impl.airtimesalesrequest(
				orginatingresourceid, "", companyid, amount,
				extensionparameters);
		System.out
				.println("--------------------------------After sending transaction to backend");
		String feedBack = "", subBalance = "", agentBalance = "", fee = "", responseMessage = "", mmOperator = "", transactionId = "";
		if (airtiemResoponse != null) {
			System.out
					.println("--------------------------------Cashin response is not null");
			ParameterExtension extensionParameters = airtiemResoponse
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
			// extensionParameters.getSpTransactionid();
			// subBalance =
			// airtiemResoponse.getDestinationpartnerbalanceafter();
			// airtiemResoponse.getExtensionparameters();
			// fee = airtiemResoponse.getFee();
			// transactionId = airtiemResoponse.getFinancialtransactionid();
			// agentBalance =
			// airtiemResoponse.getOrginatingpartnerbalanceafter();
			responseMessage = airtiemResoponse.getResponseMessage();

			StatusCode statusCode = airtiemResoponse.getStatuscode();
			if (statusCode != null) {
				System.out
						.println("--------------------------------Status code not null");
				feedBack = statusCode.toString();
				System.out.println(feedBack = statusCode.toString());
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
					+ " WAS SUCCESSFUL!!!! AZONTO!!!"
					+ extensionparameters.getSpTransactionid());
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

	}

	private static void doCashOutUnregistered() {

		SPfinancialPortImpl impl = new SPfinancialPortImpl();

		// fets
		// String userPin = "0000000000000000";
		// String orginatingresourceid = "08032249455";
		// String destinationresourceid = "";
		// String subscriberphonenumber = "";
		// String transactionreference = "323507V";
		// String redeemcode = "38436881";

		// fortis
		String orginatingresourceid = "2348032249455";
		String destinationresourceid = "2348032249455";
		String transactionreference = "652279";
		String redeemcode = "384134085";
		String subscriberphonenumber = "";

		String amount = "100";
		String sendingdescription = "Payments";
		String receivingdescription = "details";
		ParameterExtension extensionparameters = new ParameterExtension();
		System.out
				.println("--------------------------------After instantiating extension parameters");
		extensionparameters.setMmoperator("fortis");
		extensionparameters.setSpTransactionid(generateReferencenNumber(12));
		// extensionparameters.getExtensionparam().add(userPin);
		System.out
				.println("--------------------------------After setting extension parameters");
		Cashoutresponse cashoutresponse = impl.cashoutunregisteredrequest(
				orginatingresourceid, subscriberphonenumber, amount,
				transactionreference, redeemcode, receivingdescription,
				extensionparameters);
		System.out
				.println("--------------------------------After sending transaction to backend");
		String feedBack = "", subBalance = "", agentBalance = "", fee = "", responseMessage = "", mmOperator = "", transactionId = "";
		if (cashoutresponse != null) {
			System.out
					.println("--------------------------------Cashin response is not null");
			ParameterExtension extensionParameters = cashoutresponse
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
			// subBalance = cashinResponse.getDestinationpartnerbalanceafter();
			cashoutresponse.getExtensionparameters();
			// fee = cashinResponse.getFee();
			transactionId = cashoutresponse.getFinancialtransactionid();
			agentBalance = cashoutresponse.getOrginatingpartnerbalanceafter();
			responseMessage = cashoutresponse.getResponseMessage();

			StatusCode statusCode = cashoutresponse.getStatuscode();
			if (statusCode != null) {
				System.out
						.println("--------------------------------Status code not null");
				feedBack = statusCode.toString();
				System.out.println(feedBack = statusCode.toString());
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
					+ " WAS SUCCESSFUL!!!! AZONTO!!!"
					+ extensionparameters.getSpTransactionid());
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

	}

	private static void performVerifyCashOut() {

		SPfinancialPortImpl impl = new SPfinancialPortImpl();

		// fets
		// String userPin = "5678";
		// // customer is the reciever
		// // agent is the payer number
		// String orginatingresourceid = "";
		// String destinationresourceid = "";
		// String referencenumber = "323476V";
		// String subscriberphonenumber = "";

		// fortis
		String orginatingresourceid = "";
		String destinationresourceid = "";
		String referencenumber = "";
		String subscriberphonenumber = "";

		// TODO Auto-generated method stub
		String amount = "200";
		String sendingdescription = "Payments";
		String receivingdescription = "details";
		ParameterExtension extensionparameters = new ParameterExtension();
		System.out
				.println("--------------------------------After instantiating extension parameters");
		extensionparameters.setMmoperator("fortis");
		extensionparameters.setSpTransactionid(generateReferencenNumber(12));
		// extensionparameters.getExtensionparam().add(userPin);
		// extensionparameters.getExtensionparam().add(transferId);
		// extensionparameters.getExtensionparam().add(secretCode);
		// extensionparameters.getExtensionparam().add(vouchercode);
		// extensionparameters.getExtensionparam().add(withdrawalcode);
		System.out
				.println("--------------------------------After setting extension parameters");
		Verifycashoutresponse cashoutResponse = impl.verifycashoutrequest(
				orginatingresourceid, subscriberphonenumber, amount,
				referencenumber, extensionparameters);
		System.out
				.println("--------------------------------After sending transaction to backend");
		String feedBack = "", subBalance = "", agentBalance = "", fee = "", responseMessage = "", mmOperator = "", transactionId = "";
		if (cashoutResponse != null) {
			System.out
					.println("--------------------------------Cashout response is not null");
			// subBalance = cashoutResponse.getDestinationpartnerbalanceafter();
			ParameterExtension extensionParameters = cashoutResponse
					.getExtensionparameters();

			mmOperator = extensionparameters.getMmoperator();
			System.out.println("--------------------------------MM Operator"
					+ mmOperator);
			List<String> extensionParameterList = extensionparameters
					.getExtensionparam();
			Iterator<String> paramsIterator = extensionParameterList.iterator();
			while (paramsIterator.hasNext()) {
				System.out
						.println("--------------------------------Iterating...."
								+ paramsIterator.next());
			}
			extensionparameters.getSpTransactionid();
			// fee = cashoutResponse.getOrginatingpartnerfee();
			transactionId = cashoutResponse.getFinancialtransactionid();
			// agentBalance =
			// cashoutResponse.getOrginatingpartnerbalanceafter();
			responseMessage = cashoutResponse.getResponseMessage();

			StatusCode statusCode = cashoutResponse.getStatuscode();
			if (statusCode != null) {
				System.out
						.println("--------------------------------Status code not null");
				feedBack = statusCode.toString();
				System.out.println(statusCode.toString());
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
		// String orginatingresourceid = "2348076763191";
		// String destinationresourceid = "";

		// paga
		String withdrawalcode = "75715";
		// String orginatingresourceid = "08177777722";
		String destinationresourceid = "14104";

		// teasymobile
		// customer number is in reciever 2348104001339
		// agent number is in sender 2348170730938
		// the originating resource id is hardcoded for this user. and pin too
		// String orginatingresourceid = "2348104001339";
		// String userPin = "1234";
		// // test data
		// String destinationresourceid = "2348171000157";
		// production data

		// String destinationresourceid = "";

		// readycash
		// String userPin = "015567519128";
		// reciever is the agent
		// sender is the customer reference
		// String vouchercode = "001550039288";
		// String orginatingresourceid = "08034083054";
		// String destinationresourceid = "";

		// fets
		// String userPin = "500";
		// // customer is the reciever
		// // agent is the payer number
		String orginatingresourceid = "09082068605";
		// String destinationresourceid = "";
		// String reference_No

		String amount = "200";
		String sendingdescription = "Payments";
		String receivingdescription = "details";
		ParameterExtension extensionparameters = new ParameterExtension();
		System.out
				.println("--------------------------------After instantiating extension parameters");
		extensionparameters.setMmoperator("fets");
		extensionparameters.setSpTransactionid(generateReferencenNumber(12));
		// extensionparameters.getExtensionparam().add(userPin);
		// extensionparameters.getExtensionparam().add(transferId);
		// extensionparameters.getExtensionparam().add(secretCode);
		// extensionparameters.getExtensionparam().add(vouchercode);
		extensionparameters.getExtensionparam().add(withdrawalcode);
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

			mmOperator = extensionparameters.getMmoperator();
			System.out.println("--------------------------------MM Operator"
					+ mmOperator);
			List<String> extensionParameterList = extensionparameters
					.getExtensionparam();
			Iterator<String> paramsIterator = extensionParameterList.iterator();
			while (paramsIterator.hasNext()) {
				System.out
						.println("--------------------------------Iterating...."
								+ paramsIterator.next());
			}
			extensionparameters.getSpTransactionid();
			fee = cashoutResponse.getOrginatingpartnerfee();
			transactionId = cashoutResponse.getFinancialtransactionid();
			agentBalance = cashoutResponse.getOrginatingpartnerbalanceafter();
			responseMessage = cashoutResponse.getResponseMessage();

			StatusCode statusCode = cashoutResponse.getStatuscode();
			if (statusCode != null) {
				System.out
						.println("--------------------------------Status code not null");
				feedBack = statusCode.toString();
				System.out.println(statusCode.toString());
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
		// String userPin = "7005";
		// String orginatingresourceid = "08063305711";
		// String destinationresourceid = "2348076763191";

		// teasymobile
		// String userPin = "1234";
		// String orginatingresourceid = "08063305711";
		// String destinationresourceid = "2348104001339";

		// readycash
		// String userPin = "0000000000000000";
		// String destinationresourceid = "08034083054";
		// String orginatingresourceid = "08063305711";

		// fortis
		// String userPin = "0000000000000000";
		// String orginatingresourceid = "09082068605";
		// String destinationresourceid = "09082068605";

		// paga
		// String userPin = "0000000000000000";
		String orginatingresourceid = "08177777722";
		String destinationresourceid = "09082068605";

		// fets
		// String userPin = "0000000000000000";
		// String orginatingresourceid = "09082068605";
		// String destinationresourceid = "09082068605";

		String amount = "100";
		String sendingdescription = "Payments";
		String receivingdescription = "details";
		ParameterExtension extensionparameters = new ParameterExtension();
		System.out
				.println("--------------------------------After instantiating extension parameters");
		extensionparameters.setMmoperator("pagatech");
		extensionparameters.setSpTransactionid(generateReferencenNumber(12));
		// extensionparameters.getExtensionparam().add(userPin);
		System.out
				.println("--------------------------------After setting extension parameters");
		Cashinresponse cashinResponse = impl.cashinrequest(
				orginatingresourceid, destinationresourceid, amount,
				sendingdescription, receivingdescription, extensionparameters);
		System.out
				.println("--------------------------------After sending transaction to backend");
		System.out.println("its meeeee>>>>>"
				+ cashinResponse.getResponseMessage());
		String feedBack = "", subBalance = "", agentBalance = "", fee = "", responseMessage = "", mmOperator = "", transactionId = "";
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
			System.out.println("--------------------------------Iterating...."
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
			System.out.println(feedBack = statusCode.toString());
		} else {
			System.out
					.println("--------------------------------Status code is null");
		}
		System.out.println("its mee againnnn >>>>>>>>>> "
				+ cashinResponse.getResponseMessage());
		if (feedBack.equalsIgnoreCase("COMPLETED")) {
			System.out.println("--------------------------------"
					+ extensionparameters.getMmoperator().toUpperCase()
					+ " WAS SUCCESSFUL!!!! AZONTO!!!"
					+ extensionparameters.getSpTransactionid());
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
