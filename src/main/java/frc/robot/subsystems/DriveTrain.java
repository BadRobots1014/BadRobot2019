package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.CANTalonSRX;
import frc.robot.RobotMap;

public class DriveTrain extends Subsystem {
    private final DifferentialDrive differentialDrive;

    public DriveTrain() {
        super();
        SpeedController frontLeftMotor = new CANTalonSRX(RobotMap.FRONT_LEFT_MOTOR);
        SpeedController frontRightMotor = new CANTalonSRX(RobotMap.FRONT_RIGHT_MOTOR);
        SpeedController backLeftMotor = new CANTalonSRX(RobotMap.BACK_LEFT_MOTOR);
        SpeedController backRightMotor = new CANTalonSRX(RobotMap.BACK_RIGHT_MOTOR);

        SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(frontLeftMotor, backLeftMotor);
        SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(frontRightMotor, backRightMotor);

        differentialDrive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);
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
    protected void initDefaultCommand() {

    }
}