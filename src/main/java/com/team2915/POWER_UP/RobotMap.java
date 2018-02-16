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
        public static final int leftSlaveA = 32;
        public static final int leftSlaveB = 31;
        public static final int rightMaster = 20;
        public static final int rightSlaveA = 22;
        public static final int rightSlaveB = 21;
        //Encoder DIO ports
        public static final int leftEncoderTop = 0;
        public static final int leftEncoderBottom = 1;
        public static final int rightEncoderTop = 2;
        public static final int rightEncoderBottom = 3;
        //Solenoids
        public static final int shifterA = 2;
        public static final int shifterB = 1;
    }

    public static final class ElevaotrMap {
        public static final int leftMotor = 6;
        public static final int rightMotor = 7;
        public static final int limitTopA = 3;
        public static final int limitTopB = 4;
        public static final int limitBottomA = 5;
        public static final int limitBottomB = 6;
    }
}
