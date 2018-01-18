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
        public static final int shifterA = 0;
        public static final int shifterB = 1;
        //Stats for Profiling in meters
        public static final double wheel_base_width = 0.5;
        public static final int encoder_ticks_per_rev = 1000;
        public static final double wheel_diameter = 0.3;
        public static final double proportional_gain = 1.0;
        public static final double derivative_gain = 0.0;
        public static final double acceleration_gain = 0.0;

    }

}
