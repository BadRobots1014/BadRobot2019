package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.utils.hardware.CANTalonSRX;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Grabber extends Subsystem
{
    private final Robot robot;
    private CANTalonSRX grabberMotor;

    public Grabber(Robot robot)
    {
        super();
        this.robot = robot;
        grabberMotor = new CANTalonSRX(RobotMap.GRABBER_MOTOR);
    }

    public void rotate(double speed)
    {
        grabberMotor.set(speed);
    }

    public void rotateCW()
    {
        grabberMotor.set(1);
    }

    public void rotateCCW()
    {
        grabberMotor.set(-1);
    }

    public void stopMotor()
    {
        grabberMotor.stopMotor();
    }

    @Override
    protected void initDefaultCommand()
    {
    }
}