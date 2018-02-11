package com.team2915.POWER_UP.TrajectoryGeneration;
import com.team2915.POWER_UP.Robot;
import com.team2915.POWER_UP.RobotMap;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;



public class TrajectoryGenerator {

    public TrajectoryGenerator(){

    }
//    new Waypoint(0, 0, 0),
//                new Waypoint(3.048, -1.3208, 0)

    public Trajectory generateTrajectory(){
        //I think degrees are in radians
        Waypoint[] waypoints = new Waypoint[] {
                new Waypoint(0, 0, 0),
                new Waypoint(3.048, 0, 0)
        };
        Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, Robot.smartDashboardManager.max_velocity, Robot.smartDashboardManager.max_acceleration, Robot.smartDashboardManager.max_jerk);
        Trajectory trajectory = Pathfinder.generate(waypoints, config);
        return trajectory;
    }
}

//cs::UsbCamera camera1;
//        cs::UsbCamera camera2;
//        cs::CvSink cvsink1;
//        cs::CvSink cvsink2;
//        cs::VideoSink server;
//        frc::Joystick joy1{0};
//        bool prevTrigger = false;
//        void RobotInit() override {
//        camera1 = CameraServer::GetInstance()->StartAutomaticCapture(0);
//        camera2 = CameraServer::GetInstance()->StartAutomaticCapture(1);
//        server = CameraServer::GetInstance()->GetServer();
//        // dummy sinks to keep camera connections open
//        cvsink1 = new cs::CvSink("cam1cv");
//        cvsink1.SetSource(camera1);
//        cvsink1.SetEnabled(true);
//        cvsink2 = new cs::CvSink("cam2cv");
//        cvsink2.SetSource(camera2);
//        cvsink2.SetEnabled(true);
//        }
//        void TeleopPeriodic() override {
//        if (joy1.GetTrigger() && !prevTrigger) {
//        printf("Setting camera 2\n");
//        server.SetSource(camera2);
//        } else if (!joy1.GetTrigger() && prevTrigger) {
//        printf("Setting camera 1\n");
//        server.SetSource(camera1);
//        }
//        prevTrigger = joy1.GetTrigger();
//        }
