package frc.robot.common;

	/*------------------------Imports------------------------------------*/
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import com.ctre.phoenix.sensors.WPI_PigeonIMU;
import edu.wpi.first.wpilibj.Joystick;


public class RobotMap { 
	//Initialize
	// laft - 1
	// lfront 2
	// raft 3
	// rfront 4
	public static WPI_VictorSPX m_leftFront = new WPI_VictorSPX(1);
	public static WPI_VictorSPX m_leftAft = new WPI_VictorSPX(0);
	public static WPI_VictorSPX m_rightAft = new WPI_VictorSPX(2);
	public static WPI_VictorSPX m_rightFront = new WPI_VictorSPX(3);
	public static WPI_VictorSPX intakeWheel = new WPI_VictorSPX(4);
	public static WPI_VictorSPX storageLeft = new WPI_VictorSPX(5);
	public static WPI_VictorSPX storageRight = new WPI_VictorSPX(6);
	public static WPI_TalonFX flywheel = new WPI_TalonFX(7);

	public static WPI_PigeonIMU  pidgey = new WPI_PigeonIMU(3);

	public static DoubleSolenoid solenoid1 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,1,2);
    public static DoubleSolenoid solenoid2 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 6, 7);
	public static Compressor compressor = new Compressor(PneumaticsModuleType.CTREPCM);
	
	public static Joystick m_joy = new Joystick(0);
	public static XboxController m_xbox = new XboxController(1);

	public static MotorControllerGroup leftGroup = new MotorControllerGroup(m_leftAft, m_leftFront);
	public static MotorControllerGroup rightGroup = new MotorControllerGroup(m_rightAft, m_rightFront);
	public static MotorControllerGroup storageGroup = new MotorControllerGroup(storageLeft, storageRight);

	public static DifferentialDrive m_drive = new DifferentialDrive(leftGroup, rightGroup);
	
	//public static Ultrasonic ultSonic = new Ultrasonic(0, 0); //filler ints
	//public static NetworkTable limelightTable = NetworkTableInstance.getDefault().getTable("limelight");


	//Groups
	//public static MotorControllerGroup storageGroup = new MotorControllerGroup(storageBelt1, storageBelt2);
}
