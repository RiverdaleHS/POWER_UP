package com.team2915.POWER_UP;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI;


import java.util.ArrayList;

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
        public static final double wheel_base_width = 0.5969;
        public static final int encoder_ticks_per_rev = 2048;
        public static final double max_velocity = 0.5; // m/s
        public static final double max_acceleration = 0.5; // m/s^2
        public static final double max_jerk = 60;// m/s^3 ?
        public static final double wheel_diameter = 0.165;
        public static final double proportional_gain = 1.0;
        public static final double derivative_gain = 0.0;
        public static final double acceleration_gain = 0.0;

    }


}
