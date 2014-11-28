package com.swifta.spfinancial.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashinresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashoutresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.ParameterExtension;

public class TransactionService {
	private TeasyMobileProcessor teasyMobileProcessor = new TeasyMobileProcessor();
	private PocketMoniProcessor pocketMoniProcessor = new PocketMoniProcessor();
	private FETsProcessor fetsProcessor = new FETsProcessor();
	private FortisProcessor fortisProcessor = new FortisProcessor();
	private ReadyCashProcessor readyCashProcessor = new ReadyCashProcessor();

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
		default:
			break;
		}
		return cashoutresponse;
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
