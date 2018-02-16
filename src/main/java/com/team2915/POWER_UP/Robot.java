package com.team2915.POWER_UP;


import com.team2915.POWER_UP.subsystems.Chassis;
import com.team2915.POWER_UP.subsystems.Climber;
import com.team2915.POWER_UP.subsystems.Elevator;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * Created by Henry on 5/2/17.
 */
public class Robot extends IterativeRobot {

    public static final Chassis chassis = new Chassis(); //THE WHEELS THAT MAKE THE ROBOT DRIVE
    public static final Elevator elevator = new Elevator(); //The elevator that lifts stuff
    public static final Climber climber = new Climber(); //The WAY TO HEAVY spool that jake made

    public static final IO io = new IO(); //IO manages the xbox controllers and joysticks

    public static final SmartDashboardManager smartDashboardManager = new SmartDashboardManager(); //This is used to manage communication with the Smart Dashboard
    @Override
    public void robotInit() {
        super.robotInit();

    }

    @Override
    public void disabledPeriodic() {
        super.disabledPeriodic();
        Scheduler.getInstance().run();
        smartDashboardManager.updateSmartDashboard();
    }

    @Override
    public void teleopInit() {
        super.teleopInit();
        smartDashboardManager.updateSmartDashboard();
    }

    @Override
    public void teleopPeriodic() {
        super.teleopPeriodic();
        Scheduler.getInstance().run();
        smartDashboardManager.updateSmartDashboard();
    }


    @Override
    public void autonomousInit() {
        super.autonomousInit();
        //TODO: This is where we pick auto and do it
        smartDashboardManager.updateSmartDashboard();
    }

    @Override
    public void autonomousPeriodic() {
        super.autonomousPeriodic();
        Scheduler.getInstance().run();
        smartDashboardManager.updateSmartDashboard();
    }
}
