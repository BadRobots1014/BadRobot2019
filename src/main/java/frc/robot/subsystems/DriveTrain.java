package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.TeleDrive;

public class DriveTrain extends Subsystem {
    private final CANSparkMax frontLeftMotor = new CANSparkMax(RobotMap.FRONT_LEFT_MOTOR, MotorType.kBrushless);
    private final CANSparkMax frontRightMotor = new CANSparkMax(RobotMap.FRONT_RIGHT_MOTOR, MotorType.kBrushless);
    private final CANSparkMax backLeftMotor = new CANSparkMax(RobotMap.BACK_LEFT_MOTOR, MotorType.kBrushless);
    private final CANSparkMax backRightMotor = new CANSparkMax(RobotMap.BACK_RIGHT_MOTOR, MotorType.kBrushless);

    private final DifferentialDrive differentialDrive;

    public DriveTrain() {
        super();

        backRightMotor.follow(frontRightMotor);
        backLeftMotor.follow(frontLeftMotor);

        differentialDrive = new DifferentialDrive(frontLeftMotor, frontRightMotor);
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        differentialDrive.tankDrive(leftSpeed, rightSpeed);
    }

    public void arcadeDrive(double xSpeed, double zRotation) {
        differentialDrive.arcadeDrive(xSpeed, zRotation);
    }

    public void curvatureDrive(double xSpeed, double zRotation, boolean isQuickTurn) {
        differentialDrive.curvatureDrive(xSpeed, zRotation, isQuickTurn);
    }

    public void stopMotor() {
        differentialDrive.stopMotor();
    }

    @Override
    public void close() {
        frontLeftMotor.close();
        frontRightMotor.close();
        backLeftMotor.close();
        backRightMotor.close();
        super.close();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new TeleDrive());
    }
}