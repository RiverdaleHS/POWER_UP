package com.team2915.POWER_UP.TrajectoryGeneration;
import com.team2915.POWER_UP.Robot;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;



public class TrajectoryGenerator {

    public TrajectoryGenerator(){

    }

    public Trajectory generateTrajectory(){
        //I think degrees are in radians
        Waypoint[] waypoints = new Waypoint[] {
                new Waypoint(0, 0, 0),
                new Waypoint(3.048, 0, 0)
        };
        Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, Robot.smartDashboardManager.velocity_feedforward, Robot.smartDashboardManager.max_acceleration, Robot.smartDashboardManager.max_jerk);
        Trajectory trajectory = Pathfinder.generate(waypoints, config);
        return trajectory;
    }
}
