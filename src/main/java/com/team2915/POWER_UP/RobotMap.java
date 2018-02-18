package com.team2915.POWER_UP;

import edu.wpi.first.wpilibj.SPI;


/**
 * Created by Henry on 5/2/17.
 */
public class RobotMap {

    public static final class ChassisMap {
        //Sensors
        public static final SPI.Port ahrs = SPI.Port.kMXP;
        //Motor Controllers
        public static final int leftMaster = 30;
        public static final int leftSlave = 32;
        public static final int rightMaster = 20;
        public static final int rightSlave = 22;
        //Encoder DIO ports
        public static final int leftEncoderTop = 0;
        public static final int leftEncoderBottom = 1;
        public static final int rightEncoderTop = 2;
        public static final int rightEncoderBottom = 3;
    }



    public static final class ControlsMap {
        public static final int throttleAxis = 1;
        public static final int turnAxis = 4;
        public static final int quickTurn = 6;

        public static final int executeTrajectory = 1;

        public static final int characterizationIncreaseOutput = 6;
        public static final int characterizationDecreaseOutput = 5;
        public static final int characterizationSavePoint = 3;


    }
}
