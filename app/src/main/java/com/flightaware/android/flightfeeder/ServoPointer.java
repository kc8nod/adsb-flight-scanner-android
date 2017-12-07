package com.flightaware.android.flightfeeder;

import android.util.Log;

/**
 * Created by tedhansen on 11/17/17.
 */

public class ServoPointer {
    private static final String TAG = ServoPointer.class.getSimpleName();

    PCA9685Servo mServo;
    int mAzimChannel;
    int mElevChannel;

    public ServoPointer(PCA9685Servo servo, int azimChannel, int elevChannel) {
        mServo = servo;
        mAzimChannel = azimChannel;
        mElevChannel = elevChannel;
    }

    public void set(double azimuth, double elevation) {
        int azimAngle;
        int elevAngle;

        if(azimuth > 180) {
            azimAngle = (int)(azimuth - 180.0);
            elevAngle = (int)(180.0 - elevation);
        } else {
            azimAngle = (int) azimuth;
            elevAngle = (int) elevation;
        }

        try {
            mServo.setServoAngle(mAzimChannel, azimAngle);
            mServo.setServoAngle(mElevChannel, elevAngle);
        } catch (Exception e) {
            Log.e(TAG, "Error setting servo angle", e);
        }
    }
}
