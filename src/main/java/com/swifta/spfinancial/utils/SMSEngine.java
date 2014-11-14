package com.swifta.spfinancial.utils;

import java.rmi.RemoteException;

import com.smslive247.bulksms.service.SMSSiteAdminProxyStub;
import com.smslive247.bulksms.service.SMSSiteAdminProxyStub.ArrayOfString;
import com.smslive247.bulksms.service.SMSSiteAdminProxyStub.Login;
import com.smslive247.bulksms.service.SMSSiteAdminProxyStub.LoginResponse;
import com.smslive247.bulksms.service.SMSSiteAdminProxyStub.MessageInfo;
import com.smslive247.bulksms.service.SMSSiteAdminProxyStub.ResponseInfo;
import com.smslive247.bulksms.service.SMSSiteAdminProxyStub.SMSTypeEnum;
import com.smslive247.bulksms.service.SMSSiteAdminProxyStub.SendSMS;
import com.smslive247.bulksms.service.SMSSiteAdminProxyStub.SendSMSResponse;

public class SMSEngine {
	private SMSSiteAdminProxyStub smsStub = null;

	public boolean sendSMS(SMSParameters smsParameters) throws RemoteException {
		boolean status = false;
		smsStub = new SMSSiteAdminProxyStub();
		SendSMS sendSMS = new SendSMS();
		String email = "oonwuzu@swifta.com";
		ArrayOfString arrayOfDeliveryNumbers = new ArrayOfString();
		arrayOfDeliveryNumbers.addString(smsParameters.getDestinationNumber());
		MessageInfo messageInfo = new MessageInfo();
		messageInfo.setCallBack(smsParameters.getCallBack());
		messageInfo.setDeliveryEmail(smsParameters.getDeliveryEmail());
		messageInfo.setDestination(arrayOfDeliveryNumbers);
		messageInfo.setMessage(smsParameters.getMessage());

		// MessageFolder. messageFolder = new MessageFolder(email, false);
		// messageInfo.setMessageFolder(messageFolder);
		messageInfo.setMessageID(smsParameters.getMessageId());
		messageInfo.setMessageType(SMSTypeEnum.TEXT);
		sendSMS.setNewSMS(messageInfo);
		sendSMS.setSite_token(login(smsParameters.getSiteId(),
				smsParameters.getPassword()));
		SendSMSResponse sendSmsResponse = smsStub.sendSMS(sendSMS);
		if (sendSmsResponse != null) {
			ResponseInfo response = sendSmsResponse.getSendSMSResult();
			if (response != null) {

				if (response.getErrorCode() == 1) {
					status = true;
				}
			}
		}
		return status;

	}

	public String login(String siteId, String password) throws RemoteException {
		smsStub = new SMSSiteAdminProxyStub();
		String siteToken = "";
		Login login = new Login();
		login.setSite_id(siteId);
		login.setPassword(password);
		LoginResponse loginResponse = smsStub.login(login);
		ResponseInfo responseInfo = loginResponse.getLoginResult();
		return siteToken;
	}
}
