package com.swifta.spfinancial.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Airtimesalesresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Balanceresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashinresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashoutresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.ParameterExtension;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Paybillsresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Transfertobankresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Verifycashoutresponse;

public class TransactionService {
	private TeasyMobileProcessor teasyMobileProcessor = new TeasyMobileProcessor();
	private PocketMoniProcessor pocketMoniProcessor = new PocketMoniProcessor();
	private FETsProcessor fetsProcessor = new FETsProcessor();
	private FortisProcessor fortisProcessor = new FortisProcessor();
	private ReadyCashProcessor readyCashProcessor = new ReadyCashProcessor();
	private PagaProcessor pagaProcessor = new PagaProcessor();

	public Cashinresponse performCashIn(String orginatingresourceid,
			String destinationresourceid, String amount,
			String sendingdescription, String receivingdescription,
			ParameterExtension extensionparameters) {
		Cashinresponse cashinresponse = new Cashinresponse();
		BigDecimal newAmount = parseInputToDecimal(amount);

		switch (extensionparameters.getMmoperator()) {
		case "teasymobile":
			cashinresponse = teasyMobileProcessor.cashinrequest(
					orginatingresourceid, destinationresourceid, newAmount,
					sendingdescription, receivingdescription,
					extensionparameters);
			break;
		case "pocketmoni":
			cashinresponse = pocketMoniProcessor.cashinrequest(
					orginatingresourceid, destinationresourceid, newAmount,
					sendingdescription, receivingdescription,
					extensionparameters);
			break;
		case "readycash":
			cashinresponse = readyCashProcessor.cashinrequest(
					orginatingresourceid, destinationresourceid, newAmount,
					sendingdescription, receivingdescription,
					extensionparameters);
			break;
		case "fets":
			cashinresponse = fetsProcessor.cashinrequest(orginatingresourceid,
					destinationresourceid, newAmount, sendingdescription,
					receivingdescription, extensionparameters);
			break;
		case "fortis":
			cashinresponse = fortisProcessor.cashinrequest(
					orginatingresourceid, destinationresourceid, newAmount,
					sendingdescription, receivingdescription,
					extensionparameters);
			break;
		case "paga":
			cashinresponse = pagaProcessor.cashinrequest(orginatingresourceid,
					destinationresourceid, newAmount, sendingdescription,
					receivingdescription, extensionparameters);
			break;
		default:
			break;
		}
		return cashinresponse;
	}

	public Cashoutresponse performCashOut(String orginatingresourceid,
			String destinationresourceid, String amount,
			String sendingdescription, String receivingdescription,
			ParameterExtension extensionparameters) throws Exception {
		Cashoutresponse cashoutresponse = new Cashoutresponse();
		BigDecimal newAmount = parseInputToDecimal(amount);

		switch (extensionparameters.getMmoperator()) {
		case "teasymobile":
			cashoutresponse = teasyMobileProcessor.cashoutrequest(
					orginatingresourceid, destinationresourceid, newAmount,
					sendingdescription, receivingdescription,
					extensionparameters);
			break;
		case "pocketmoni":
			cashoutresponse = pocketMoniProcessor.cashoutrequest(
					orginatingresourceid, destinationresourceid, newAmount,
					sendingdescription, receivingdescription,
					extensionparameters);
			break;
		case "readycash":
			cashoutresponse = readyCashProcessor.cashoutrequest(
					orginatingresourceid, destinationresourceid, newAmount,
					sendingdescription, receivingdescription,
					extensionparameters);
			break;
		case "fets":
			cashoutresponse = fetsProcessor.cashoutrequest(
					orginatingresourceid, destinationresourceid, newAmount,
					sendingdescription, receivingdescription,
					extensionparameters);
			break;
		case "fortis":
			cashoutresponse = fortisProcessor.cashoutrequest(
					orginatingresourceid, destinationresourceid, newAmount,
					sendingdescription, receivingdescription,
					extensionparameters);
			break;
		case "paga":
			cashoutresponse = pagaProcessor.cashoutrequest(
					orginatingresourceid, destinationresourceid, newAmount,
					sendingdescription, receivingdescription,
					extensionparameters);
			break;
		default:
			break;
		}
		return cashoutresponse;
	}

	public Cashoutresponse cashoutunregisteredrequest(
			java.lang.String orginatingresourceid,
			java.lang.String subscriberphonenumber,
			java.lang.String amount,
			java.lang.String referencenumber,
			java.lang.String referencecode,
			java.lang.String receivingdescription,
			com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.ParameterExtension extensionparameters)
			throws Exception {
		Cashoutresponse cashoutresponse = new Cashoutresponse();
		BigDecimal newAmount = parseInputToDecimal(amount);

		switch (extensionparameters.getMmoperator()) {
		case "teasymobile":
			cashoutresponse = teasyMobileProcessor.cashoutunregisteredrequest(
					orginatingresourceid, subscriberphonenumber, newAmount,
					referencenumber, referencecode, receivingdescription,
					extensionparameters);
			break;
		case "pocketmoni":
			cashoutresponse = pocketMoniProcessor.cashoutunregisteredrequest(
					orginatingresourceid, subscriberphonenumber, newAmount,
					referencenumber, referencecode, receivingdescription,
					extensionparameters);
			break;
		case "readycash":
			cashoutresponse = readyCashProcessor.cashoutunregisteredrequest(
					orginatingresourceid, subscriberphonenumber, newAmount,
					referencenumber, referencecode, receivingdescription,
					extensionparameters);
			break;
		case "fets":
			cashoutresponse = fetsProcessor.cashoutunregisteredrequest(
					orginatingresourceid, subscriberphonenumber, newAmount,
					referencenumber, referencecode, receivingdescription,
					extensionparameters);
			break;
		case "fortis":
			cashoutresponse = fortisProcessor.cashoutunregisteredrequest(
					orginatingresourceid, subscriberphonenumber, newAmount,
					referencenumber, referencecode, receivingdescription,
					extensionparameters);
			break;
		case "paga":
			cashoutresponse = pagaProcessor.cashoutunregisteredrequest(
					orginatingresourceid, subscriberphonenumber, newAmount,
					referencenumber, referencecode, receivingdescription,
					extensionparameters);
			break;
		default:
			break;
		}
		return cashoutresponse;
	}

	public Transfertobankresponse performTransferToBank(
			java.lang.String orginatingresourceid, java.lang.String amount,
			java.lang.String narration, ParameterExtension extensionparameters)
			throws Exception {
		Transfertobankresponse transfertobankresponse = new Transfertobankresponse();
		BigDecimal newAmount = parseInputToDecimal(amount);

		switch (extensionparameters.getMmoperator()) {
		case "teasymobile":
			transfertobankresponse = teasyMobileProcessor.transfertobank(
					orginatingresourceid, amount, narration,
					extensionparameters);
			break;
		case "pocketmoni":
			transfertobankresponse = pocketMoniProcessor.transfertobank(
					orginatingresourceid, amount, narration,
					extensionparameters);
			break;
		case "readycash":
			transfertobankresponse = readyCashProcessor.transfertobank(
					orginatingresourceid, amount, narration,
					extensionparameters);
			break;
		case "fets":
			transfertobankresponse = fetsProcessor.transfertobank(
					orginatingresourceid, amount, narration,
					extensionparameters);
			break;
		case "fortis":
			transfertobankresponse = fortisProcessor.transfertobank(
					orginatingresourceid, amount, narration,
					extensionparameters);
			break;
		case "paga":
			transfertobankresponse = pagaProcessor.transfertobank(
					orginatingresourceid, amount, narration,
					extensionparameters);
			break;
		default:
			break;
		}
		return transfertobankresponse;
	}

	public Verifycashoutresponse performVerifyCashout(
			java.lang.String orginatingresourceid,
			java.lang.String subscriberphonenumber, java.lang.String amount,
			java.lang.String referencenumber,
			ParameterExtension extensionparameters) throws Exception {
		Verifycashoutresponse verifycashoutresponse = new Verifycashoutresponse();
		BigDecimal newAmount = parseInputToDecimal(amount);

		switch (extensionparameters.getMmoperator()) {
		case "teasymobile":
			verifycashoutresponse = teasyMobileProcessor.verifycashoutrequest(
					orginatingresourceid, subscriberphonenumber, amount,
					referencenumber, extensionparameters);
			break;
		case "pocketmoni":
			verifycashoutresponse = pocketMoniProcessor.verifycashoutrequest(
					orginatingresourceid, subscriberphonenumber, amount,
					referencenumber, extensionparameters);
			break;
		case "readycash":
			verifycashoutresponse = readyCashProcessor.verifycashoutrequest(
					orginatingresourceid, subscriberphonenumber, amount,
					referencenumber, extensionparameters);
			break;
		case "fets":
			verifycashoutresponse = fetsProcessor.verifycashoutrequest(
					orginatingresourceid, subscriberphonenumber, amount,
					referencenumber, extensionparameters);
			break;
		case "fortis":
			verifycashoutresponse = fortisProcessor.verifycashoutrequest(
					orginatingresourceid, subscriberphonenumber, amount,
					referencenumber, extensionparameters);
			break;
		case "paga":
			verifycashoutresponse = pagaProcessor.verifycashoutrequest(
					orginatingresourceid, subscriberphonenumber, amount,
					referencenumber, extensionparameters);
			break;
		default:
			break;
		}
		return verifycashoutresponse;
	}

	public Airtimesalesresponse airtimesalesrequest(
			java.lang.String orginatingresourceid,
			java.lang.String beneficiarynumber,
			java.lang.String serviceprovider, java.lang.String amount,
			ParameterExtension extensionparameters) throws Exception {
		Airtimesalesresponse airtimesalesresponse = new Airtimesalesresponse();
		BigDecimal newAmount = parseInputToDecimal(amount);

		switch (extensionparameters.getMmoperator()) {
		case "teasymobile":
			airtimesalesresponse = teasyMobileProcessor.airtimesalesrequest(
					orginatingresourceid, beneficiarynumber, serviceprovider,
					amount, extensionparameters);
			break;
		case "pocketmoni":
			airtimesalesresponse = pocketMoniProcessor.airtimesalesrequest(
					orginatingresourceid, beneficiarynumber, serviceprovider,
					amount, extensionparameters);
			break;
		case "readycash":
			airtimesalesresponse = readyCashProcessor.airtimesalesrequest(
					orginatingresourceid, beneficiarynumber, serviceprovider,
					amount, extensionparameters);
			break;
		case "fets":
			airtimesalesresponse = fetsProcessor.airtimesalesrequest(
					orginatingresourceid, beneficiarynumber, serviceprovider,
					amount, extensionparameters);
			break;
		case "fortis":
			airtimesalesresponse = fortisProcessor.airtimesalesrequest(
					orginatingresourceid, beneficiarynumber, serviceprovider,
					amount, extensionparameters);
			break;
		case "paga":
			airtimesalesresponse = pagaProcessor.airtimesalesrequest(
					orginatingresourceid, beneficiarynumber, serviceprovider,
					amount, extensionparameters);
			break;
		default:
			break;
		}
		return airtimesalesresponse;
	}

	public Paybillsresponse paybillsrequest(
			java.lang.String orginatingresourceid,
			java.lang.String merchantcode, java.lang.String amount,
			java.lang.String sendingdescription,
			java.lang.String receivingdescription,
			ParameterExtension extensionparameters) throws Exception {
		Paybillsresponse paybillsresponse = new Paybillsresponse();
		BigDecimal newAmount = parseInputToDecimal(amount);

		switch (extensionparameters.getMmoperator()) {
		case "teasymobile":
			paybillsresponse = teasyMobileProcessor.paybillsrequest(
					orginatingresourceid, merchantcode, amount,
					sendingdescription, receivingdescription,
					extensionparameters);
			break;
		case "pocketmoni":
			paybillsresponse = pocketMoniProcessor.paybillsrequest(
					orginatingresourceid, merchantcode, amount,
					sendingdescription, receivingdescription,
					extensionparameters);
			break;
		case "readycash":
			paybillsresponse = readyCashProcessor.paybillsrequest(
					orginatingresourceid, merchantcode, amount,
					sendingdescription, receivingdescription,
					extensionparameters);
			break;
		case "fets":
			paybillsresponse = fetsProcessor.paybillsrequest(
					orginatingresourceid, merchantcode, amount,
					sendingdescription, receivingdescription,
					extensionparameters);
			break;
		case "fortis":
			paybillsresponse = fortisProcessor.paybillsrequest(
					orginatingresourceid, merchantcode, amount,
					sendingdescription, receivingdescription,
					extensionparameters);
			break;
		case "paga":
			paybillsresponse = pagaProcessor.paybillsrequest(
					orginatingresourceid, merchantcode, amount,
					sendingdescription, receivingdescription,
					extensionparameters);
			break;
		default:
			break;
		}
		return paybillsresponse;
	}

	public Balanceresponse balancerequest(String orginatingresourceid,
			ParameterExtension extensionparameters) throws Exception {
		Balanceresponse balanceresponse = new Balanceresponse();
		switch (extensionparameters.getMmoperator()) {
		case "teasymobile":
			balanceresponse = teasyMobileProcessor.balancerequest(
					orginatingresourceid, extensionparameters);
			break;
		case "pocketmoni":
			balanceresponse = pocketMoniProcessor.balancerequest(
					orginatingresourceid, extensionparameters);
			break;
		case "readycash":
			balanceresponse = readyCashProcessor.balancerequest(
					orginatingresourceid, extensionparameters);
			break;
		case "fets":
			balanceresponse = fetsProcessor.balancerequest(
					orginatingresourceid, extensionparameters);
			break;
		case "fortis":
			balanceresponse = fortisProcessor.balancerequest(
					orginatingresourceid, extensionparameters);
			break;
		case "paga":
			balanceresponse = pagaProcessor.balancerequest(
					orginatingresourceid, extensionparameters);
			break;
		default:
			break;
		}
		return balanceresponse;
	}

	/**
	 * Strips formatting from a currency string. All non-digit information is
	 * removed, but the sign is preserved.
	 * 
	 * @param s
	 *            String to be stripped
	 * @return Stripped string with all non-digits removed
	 */
	public static String stripCurrencyFormatting(String s) {
		if (s.length() == 0)
			return s;
		// remove all currency formatting and anything else which is not a
		// number
		String sign = s.trim().substring(0, 1);
		String stripped = s.trim().replaceAll("\\D*", "");
		if (sign.equals("+") || sign.equals("-")) {
			stripped = sign + stripped;
		}
		return stripped;
	}

	/**
	 * Parse an input string into a {@link BigDecimal} This method expects the
	 * amount including the decimal part
	 * 
	 * @param amountString
	 *            String with amount information
	 * @return BigDecimal with the amount parsed from <code>amountString</code>
	 */
	public static BigDecimal parseInputToDecimal(String amountString) {
		String clean = stripCurrencyFormatting(amountString);
		if (clean.length() == 0) // empty string
			return BigDecimal.ZERO;
		// all amounts are input to 2 decimal places, so after removing decimal
		// separator, divide by 100
		// TODO: Handle currencies with different kinds of decimal places
		BigDecimal amount = new BigDecimal(clean).setScale(2,
				RoundingMode.HALF_EVEN).divide(new BigDecimal(1), 2,
				RoundingMode.HALF_EVEN);
		return amount;
	}

}
