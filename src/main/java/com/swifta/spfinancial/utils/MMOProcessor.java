package com.swifta.spfinancial.utils;

import java.math.BigDecimal;

import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Airtimesalesresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Balanceresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashinresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Cashoutresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.ParameterExtension;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Paybillsresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Transfertobankresponse;
import com.swifta.subsidiary.mats.serviceprovider.operation.spfinancial.v1.Verifycashoutresponse;

public abstract class MMOProcessor {
	public abstract Cashoutresponse cashoutrequest(
			java.lang.String orginatingresourceid,
			java.lang.String destinationresourceid, BigDecimal amount,
			java.lang.String sendingdescription,
			java.lang.String receivingdescription,
			ParameterExtension extensionparameters);

	public abstract Cashoutresponse cashoutunregisteredrequest(
			String orginatingresourceid, String subscriberphonenumber,
			BigDecimal amount, String referencenumber, String referencecode,
			String receivingdescription, ParameterExtension extensionparameters);

	public abstract Cashinresponse cashinrequest(
			java.lang.String orginatingresourceid,
			java.lang.String destinationresourceid,
			java.math.BigDecimal amount, java.lang.String sendingdescription,
			java.lang.String receivingdescription,
			ParameterExtension extensionparameters);

	public abstract Verifycashoutresponse verifycashoutrequest(
			java.lang.String orginatingresourceid,
			java.lang.String subscriberphonenumber, java.lang.String amount,
			java.lang.String referencenumber,
			ParameterExtension extensionparameters);

	public abstract Transfertobankresponse transfertobank(
			java.lang.String orginatingresourceid, java.lang.String amount,
			java.lang.String narration, ParameterExtension extensionparameters);

	public abstract Paybillsresponse paybillsrequest(
			java.lang.String orginatingresourceid,
			java.lang.String merchantcode, java.lang.String amount,
			java.lang.String sendingdescription,
			java.lang.String receivingdescription,
			ParameterExtension extensionparameters);

	public abstract Airtimesalesresponse airtimesalesrequest(
			java.lang.String orginatingresourceid,
			java.lang.String beneficiarynumber,
			java.lang.String serviceprovider, java.lang.String amount,
			ParameterExtension extensionparameters);

	public abstract Balanceresponse balancerequest(
			java.lang.String orginatingresourceid,
			ParameterExtension extensionparameters);

	/*
	 * public abstract Airtimeresponse airtimeSale( java.lang.String
	 * orginatingresourceid, java.lang.String destinationresourceid,
	 * java.math.BigDecimal amount, java.lang.String sendingdescription,
	 * com.swifta
	 * .subsidiary.mats.serviceprovider.operation.spfinancial.v1.ParameterExtension
	 * extensionparameters); public abstract PaymentsResponse makePayment(
	 * java.lang.String orginatingresourceid, java.lang.String
	 * destinationresourceid, java.math.BigDecimal amount, java.lang.String
	 * sendingdescription, java.lang.String receivingdescription,
	 * com.swifta.subsidiary
	 * .mats.serviceprovider.operation.spfinancial.v1.ParameterExtension
	 * extensionparameters);
	 */
}
