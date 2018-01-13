package com.team2915.POWER_UP;

import edu.wpi.first.wpilibj.SPI;

/**
 * Created by Henry on 5/2/17.
 */
public class RobotMap {

    public static final class DriveTrainMap {
        //Sensors
        public static final SPI.Port ahrs = SPI.Port.kMXP;
        //Motor Controllers
        public static final int leftMaster = 30;
        public static final int leftSlaveA = 32;
        public static final int leftSlaveB = 31;
        public static final int rightMaster = 20;
        public static final int rightSlaveA = 22;
        public static final int rightSlaveB = 21;
    }

}
