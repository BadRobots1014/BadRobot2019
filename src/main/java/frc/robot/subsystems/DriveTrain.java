package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.TeleDrive;

public class DriveTrain extends Subsystem
{
    private final Robot robot;
    private final CANSparkMax frontLeftMotor;
    private final CANSparkMax frontRightMotor;
    private final CANSparkMax backLeftMotor;
    private final CANSparkMax backRightMotor;
    private final AHRS navx;

    private final DifferentialDrive differentialDrive;

    public DriveTrain(Robot robot)
    {
        super();

        this.robot = robot;
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

    public void tankDrive(double leftSpeed, double rightSpeed)
    {
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

    public void reverseMotor()
    {
        frontLeftMotor.setInverted(!frontLeftMotor.getInverted());
        frontRightMotor.setInverted(!frontRightMotor.getInverted());
    }

    @Override
    public void close()
    {
        frontLeftMotor.close();
        frontRightMotor.close();
        backLeftMotor.close();
        backRightMotor.close();
        super.close();
    }

    @Override
    protected void initDefaultCommand()
    {
        setDefaultCommand(new TeleDrive(robot.driveTrain, robot.oi.xboxController));
    }
}