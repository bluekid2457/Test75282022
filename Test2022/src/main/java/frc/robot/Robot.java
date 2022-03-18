package frc.robot;
import static frc.robot.common.RobotMap.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Timer;


public class Robot extends TimedRobot {

	private double inWheel1;
	private double flyM;
	private double store;
    private double x; 
    private double y;
    private double z;

	public static double heading;
	private static double ang_lime = 30; // angle of limelight on robot
    private static double H_tape = 2.5; // height of tape from floor
    private static double H_lime = 0.76; // height og limlight from ground
    private static Timer m_timer = new Timer();
    private static int ball_counter = 1;


    private static boolean shootBall = false;
    private static boolean shoot = false;
    private static boolean aligndist = false;
    private static boolean intake = false;
	private static double target_angle;
	private static boolean fadeAway = false;
	@Override
	public void robotInit(){
		m_leftAft.configFactoryDefault();
		m_leftFront.configFactoryDefault();
		m_rightAft.configFactoryDefault();
		m_rightFront.configFactoryDefault();
		m_leftAft.follow(m_leftFront);
		m_rightAft.follow(m_rightFront);
		m_leftFront.setInverted(false);
		m_rightFront.setInverted(true);
		m_rightAft.setInverted(true);
		m_drive = new DifferentialDrive(m_leftFront,m_rightFront);
	}
	/*
	public static double getDistance(){ // returns distance from upper hub horizontally
		try{
			double Theta_t = limelightTable.getEntry("ty").getDouble(0); // angle that limelight sees
			
			if (Theta_t == 0){
                return 0; // this returns if limelight cant detect the tape
            }
			Math.tan(Math.toRadians(Theta_t+ang_lime)); // math
			
			double dist = (H_tape - H_lime)/Math.tan(Math.toRadians(Theta_t+ang_lime)); // more math
			
			dist = dist *39.37; // conversion from meters to inches
			dist *= 1.11; // error correction
			dist -= 2; // error correction
			System.out.println(dist + " - distance    -----    " + Theta_t + " - ang lime");

			return dist;

		} catch (Exception e) {
			System.out.println("dist error"+ e);
			return 0;
		}

	}
	public static void alignDist(double Target_distance){ // moves to align distance of robot
		double dist = getDistance();
		double error = Target_distance - dist;
		if (error > 15){
			m_drive.curvatureDrive(-0.15, 0 , false);
		}
		else if (error < -10){
			m_drive.curvatureDrive(0.15, 0 , false);
		}
		else if (error > 10.0){
			m_drive.curvatureDrive(-0.15, 0 , false);
		}
		else {
            m_timer.reset();
			shootBall = true;
            aligndist = false; // exceutes after has aligned distance correctly
		}
    }
    public static void shootBall(){ 
        // if has 2 balls than also move intake wheels
        if (m_timer.get() < 2){// run flywheel for 2 seconds
            flywheel.set(ControlMode.PercentOutput, -0.6);
            flywheel.set(ControlMode.PercentOutput, -0.6);
            if (m_timer.get() > 1){// run conveyor after 1 second
                double conveyer2 = -0.5;
                double conveyor1 = -0.5;
                storageBelt1.set(ControlMode.PercentOutput, conveyor1);
                storageBelt2.set(ControlMode.PercentOutput, conveyer2);
                intakeWheel.set(ControlMode.PercentOutput,0.5);
            }
        }
        else{
            shootBall = false;
            ball_counter = 0;
        }
        
    }
	public static void shootFadeAway(double Target_distance){
		double dist = getDistance();
		//System.out.println(dist);

		double error = Target_distance - dist;
		System.out.println(error + " - error");
		if (error > 15){
			m_drive.curvatureDrive(-0.15, 0 , false);
		}
		else if (error < -10){
			m_drive.curvatureDrive(0.15, 0 , false);
		}
		else if (error > 10.0){
			m_drive.curvatureDrive(-0.15, 0 , false);
		}
		//else if (error < -5.0){
		//	m_drive.curvatureDrive(0.20, 0 , false);
		//}
		else {
			fadeAway = false;
			m_timer.reset();
			//shootBall();
		}
	}
	public static void intakeBall(){
        //double dist = radar.getdist(); // distance of ball 
        double dist = 0;
        double close = 5;
        if (dist > close){
            intakeWheel.set(ControlMode.PercentOutput, 0.4);
        }
        else if (dist < close){ // once ball is close enough stop motors
            intake = false;
            ball_counter +=1;
        }
    }
	*/
	@Override
	public void teleopInit(){
		//target_angle = pidgey.getAngle();
		target_angle = 0;
	}
	@Override
	public void teleopPeriodic(){
		
		//Original values are stated
		inWheel1 = 0.0; //wheels not moving
		//solenoid1.set(value.kOff); //MAKE SURE TO CHECk what Dis doO
		store = 0;
		flyM = 0; //Flywheel isn't spinning
		x = 0; //SET ULTRASONIC DISTANCE FOR INTAKE IN "SECT: INTAKE"
        y = 0; //SET DISTANCE PER PULSE FOR ENCODER IN "SECT: FLYWHEEL"
        int ballcount = 0; //I didn't add this, idk who did
        z = 0; //SET ULTRASONIC DISTANCE FOR REGISTERING BALL IN "SECT: INTAKE"

		//Format all motor controllers
		//intakeWheel.configFactoryDefault();
		//storageBelt1.configFactoryDefault();
		//storageBelt2.configFactoryDefault();
		//flywheel.configFactoryDefault();
		//storageBelt1.setInverted(true);


		/*-------------------------Sect: Intake----------------------------------------*/

		//Intake control
		/*
		if (xbox.getLeftBumper()) {
			if (xbox.getAButton() && ultSonic.getRangeInches() < x) {
				inWheel1 = 0; //Wheels stop the ball in belt if there is a ball in storage
			}
			inWheel1 = -0.5; //If there is no problems with above, intakeWheel runs normal
		}
		*/
		/*
			Summary:- If there are no balls in storage, hold Left Bumper
				- If you want to store second ball, hold A and Left Bumper at the same time
				- The ultrasonic sensor should track distance
					- Distance would be used to stop ball in belt with accuracy
		*/

        // THIS IS ACHINT POORLY DOCUMENTED CODE
		/*
        if (xbox.getLeftBumper()) {
			if ((ballcount != 0) && ultSonic.getRangeInches() < x) {
				inWheel1 = 0; //Wheels stop the ball in belt if there is a ball in storage
			}
			inWheel1 = -0.5; //If there is no problems with above, intakeWheel runs normal
            if (ultSonic.getRangeInches < z){
                ballcount += 1;
            }
		}
		*/
		/* -------------------------Sect: Storage-----------------------------------------*/

		//Pneumatics, first belt is constantly pushed out
		
		//if (xbox.getYButtonPressed()) {
		//	solenoid1.set(Value.kReverse); //Solenoid retracts 
		//} else if (xbox.getXButtonPressed()) {
		//	solenoid1.set(Value.kForward); //Solenoid is constantly out (Actuate Actuators)
		//}
		
		/*
			Summary:- Pressing Y will retract pnuematics in the event of an emergency
				- Else, rest of storage is involved in "Sect: FlyWheel"
		*/
			
		/* -------------------------Sect: FlyWheel-------------------------------------------*/
		
		if (m_xbox.getRightBumper()) {
			flyM = -0.3; //Flywheels start, tune value
			System.out.println(flywheel.getSelectedSensorVelocity()+" - flywheel velocity");
			//if (flywheel.getSelectedSensorVelocity() > y) { 
			//	store = -0.2; //Elevator is on
			//}
			//flywheel.getMotorOutputPercent();
			
		}
		
		
		/*
			Summary:- Hold Right Bumper when you want to shoot/empty storage
				- Flywheels will start to spin
				- Encoder senses when Flywheels is fast enough (Supply current)
				- Elevators push ball into flywheel
				- Drivers will need to manually push second ball into storage spot by holding
					Left Bumper after the first ball has been fired

		/* -------------------------Sect: Wingadium Leviosa-----------------------------------*/

		//intakeWheel.set(ControlMode.PercentOutput, inWheel1);
		//storageBelt1.set(ControlMode.PercentOutput, store);
		//storageBelt2.set(ControlMode.PercentOutput, store);
		//flywheel.set(ControlMode.PercentOutput, flyM);
		m_drive.curvatureDrive(0, 0, true);
		if (m_joy.getRawButton(3)){
			m_drive.curvatureDrive(0, m_joy.getZ()*-0.5, true);
			//m_leftFront.set(ControlMode.PercentOutput,0.3);
			System.out.println("print this if work");
		}
        if (m_joy.getRawButton(5)) { 	
            double turn = 0;
            double k = 0.03;
            double sensitivity = 5;
            heading = pidgey.getAngle();
            target_angle += m_joy.getX()*sensitivity*m_joy.getY()*-1;
            System.out.println(target_angle);
            double error = 0;
            error = heading-target_angle;
            turn = error*k;
            m_drive.curvatureDrive(m_joy.getY(), turn , m_joy.getRawButton(2));

        }

		
	}	
}	
