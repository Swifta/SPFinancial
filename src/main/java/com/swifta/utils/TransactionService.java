package com.swifta.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.mobilemoney.services.mwallet.TeasyMobileClient;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.MTransferResponseType;
import com.ng.mats.psa.mt.model.MoneyTransfer;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashoutresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.ParameterExtension;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.StatusCode;

public class TransactionService {
	public Cashoutresponse initiateCashin(String orginatingresourceid,
			String destinationresourceid,
			String amount,
			String sendingdescription,
			String receivingdescription,
			ParameterExtension extensionparameters) throws Exception{
		Cashoutresponse cashoutresponse = new Cashoutresponse();
		switch (extensionparameters.getMmoperator()) {
		case "teasymobile":
			MoneyTransfer teasymoneyTransfer = new MoneyTransfer(
					destinationresourceid, parseInputToDecimal(amount),
					receivingdescription, "");
			TeasyMobileClient teasyMobileClient = new TeasyMobileClient();
			System.out.println("posting to teasymobile");
			MTransferResponseType response = teasyMobileClient
					.doCashout(teasymoneyTransfer);
			if (response.getStatus() == 0)
				response.setResponseMessage("Transaction Successful");
			cashoutresponse.setDestinationpartnerbalanceafter(null);
			cashoutresponse.setOrginatingpartnerbalanceafter(null);
			cashoutresponse.setOrginatingpartnerfee(null);
			cashoutresponse.setStatuscode(StatusCode.COMPLETED);
			cashoutresponse.setResponseMessage(response
					.getResponseMessage());
			ParameterExtension parameterExtension = new ParameterExtension();
			parameterExtension.setSpTransactionid(response
					.getTransactionId());
			parameterExtension.getExtensionparam().add(
					response.getTransactionId());
			parameterExtension.getExtensionparam().add(
					response.getResponseMessage());
			cashoutresponse.setExtensionparameters(parameterExtension);
			return cashoutresponse;
			
		default:
			cashoutresponse
					.setDestinationpartnerbalanceafter("not supported");
			return cashoutresponse;
		}
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
				RoundingMode.HALF_EVEN).divide(new BigDecimal(100), 2,
				RoundingMode.HALF_EVEN);
		return amount;
	}

}
