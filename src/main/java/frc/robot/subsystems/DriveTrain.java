package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import badlog.lib.BadLog;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.tele.TeleDrive;

public class DriveTrain extends BadSubsystem
{
    private CANSparkMax frontLeftMotor;
    private CANSparkMax frontRightMotor;
    private CANSparkMax backLeftMotor;
    private CANSparkMax backRightMotor;
    private DifferentialDrive differentialDrive;
    private AHRS navx;

    private boolean reversed;

    public DriveTrain(Robot robot)
    {
        super(robot);
    }

    @Override
    protected void initComponents()
    {
        frontLeftMotor = new CANSparkMax(RobotMap.FRONT_LEFT_MOTOR, MotorType.kBrushless);
        frontRightMotor = new CANSparkMax(RobotMap.FRONT_RIGHT_MOTOR, MotorType.kBrushless);
        backLeftMotor = new CANSparkMax(RobotMap.BACK_LEFT_MOTOR, MotorType.kBrushless);
        backRightMotor = new CANSparkMax(RobotMap.BACK_RIGHT_MOTOR, MotorType.kBrushless);
        navx = new AHRS(SPI.Port.kMXP);

        frontLeftMotor.setIdleMode(IdleMode.kCoast);
        frontRightMotor.setIdleMode(IdleMode.kCoast);
        backLeftMotor.setIdleMode(IdleMode.kCoast);
        backRightMotor.setIdleMode(IdleMode.kCoast);

        frontLeftMotor.setInverted(true);
        frontRightMotor.setInverted(true);

        backLeftMotor.follow(frontLeftMotor);
        backRightMotor.follow(frontRightMotor);

        differentialDrive = new DifferentialDrive(frontLeftMotor, frontRightMotor);
    }

    @Override
    protected void initLogging()
    {
        BadLog.createValue("Drivetrain/Right Front Firmware", frontRightMotor.getFirmwareString());
        BadLog.createValue("Drivetrain/Left Front Firmware", frontLeftMotor.getFirmwareString());
        BadLog.createValue("Drivetrain/Right Back Firmware", backRightMotor.getFirmwareString());
        BadLog.createValue("Drivetrain/Left Back Firmware", backLeftMotor.getFirmwareString());

        BadLog.createTopic("Drivetrain/Right Output Percent", BadLog.UNITLESS, () -> frontRightMotor.get(), "hide",
                "join:Drivetrain/Output Percents");
        BadLog.createTopic("Drivetrain/Left Output Percent", BadLog.UNITLESS, () -> frontLeftMotor.get(), "hide",
                "join:Drivetrain/Output Percents");

        BadLog.createTopic("Drivetrain/Right Front Current", "A", () -> frontRightMotor.getOutputCurrent(), "hide",
                "join:Drivetrain/Output Currents");
        BadLog.createTopic("Drivetrain/Right Back Current", "A", () -> backRightMotor.getOutputCurrent(), "hide",
                "join:Drivetrain/Output Currents");
        BadLog.createTopic("Drivetrain/Left Front Current", "A", () -> frontLeftMotor.getOutputCurrent(), "hide",
                "join:Drivetrain/Output Currents");
        BadLog.createTopic("Drivetrain/Left Back Current", "A", () -> backLeftMotor.getOutputCurrent(), "hide",
                "join:Drivetrain/Output Currents");

        BadLog.createTopic("Drivetrain/Right Front Temperature", "C", () -> frontRightMotor.getMotorTemperature(),
                "hide", "join:Drivetrain/Output Temperatures");
        BadLog.createTopic("Drivetrain/Right Back Temperature", "C", () -> backRightMotor.getMotorTemperature(), "hide",
                "join:Drivetrain/Output Temperatures");
        BadLog.createTopic("Drivetrain/Left Front Temperature", "C", () -> frontLeftMotor.getMotorTemperature(), "hide",
                "join:Drivetrain/Output Temperatures");
        BadLog.createTopic("Drivetrain/Left Back Temperature", "C", () -> backLeftMotor.getMotorTemperature(), "hide",
                "join:Drivetrain/Output Temperatures");
    }

    @Override
    protected void initDefaultCommand()
    {
        setDefaultCommand(new TeleDrive(this, robot.oi.xboxController));
    }

    public void tankDrive(double leftSpeed, double rightSpeed)
    {
        if (reversed)
            differentialDrive.tankDrive(-rightSpeed, -leftSpeed);
        else
            differentialDrive.tankDrive(leftSpeed, rightSpeed);
    }

    public void arcadeDrive(double xSpeed, double zRotation)
    {
        differentialDrive.arcadeDrive(xSpeed, zRotation);
    }

    public void curvatureDrive(double xSpeed, double zRotation, boolean isQuickTurn)
    {
        differentialDrive.curvatureDrive(xSpeed, zRotation, isQuickTurn);
    }

    public double getAngle()
    {
        return navx.getAngle();
    }

    public double getDisplacement()
    {
        return navx.getDisplacementX();
    }

    public void stopMotor()
    {
        differentialDrive.stopMotor();
    }

    public boolean isReversed()
    {
        return reversed;
    }

    public void toggleReversed()
    {
        reversed = !reversed;
    }

    @Override
    public void close()
    {
        frontLeftMotor.close();
        frontRightMotor.close();
        backLeftMotor.close();
        backRightMotor.close();
        differentialDrive.close();
        navx.close();
        super.close();
    }
}