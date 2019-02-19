package frc.robot.subsystems;

import frc.robot.utils.hardware.CANTalonSRX;
import frc.robot.RobotMap;

public class Grabber extends BadSubsystem
{
    private CANTalonSRX grabberMotor;

    @Override
    protected void initComponents()
    {
        grabberMotor = new CANTalonSRX(RobotMap.GRABBER_MOTOR);
    }

    @Override
    protected void initLogging()
    {

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

    public static boolean isEnabled()
    {
        return false;
    }
}