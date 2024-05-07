package com.bsn.ex_no_10;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class Receiver extends BroadcastReceiver {

    public static final String SMS_BUNDLE = "pdus";
    @Override
    public void onReceive(Context context, Intent intent) {
        String no = null, mgs = null;

        Bundle b = intent.getExtras();
        System.out.println("Bundle for Message " + b.toString());
        if(b != null) {
            Object[] sms = (Object[]) b.get(SMS_BUNDLE);
            for(int i = 0 ; i < sms.length; i++) {
                SmsMessage sm = SmsMessage.createFromPdu((byte[]) sms[i]);
                no = sm.getOriginatingAddress();
                mgs = sm.getMessageBody().toString();
            }

            MainActivity inst = MainActivity.instance();
            System.out.println("Number " + no + "msg :" + mgs);
            inst.update_notification(no, mgs);

        }

    }
}
