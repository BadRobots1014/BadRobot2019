package frc.robot.subsystems;

import frc.robot.utils.hardware.CANTalonSRX;
import frc.robot.RobotMap;

public class Grabber extends BadSubsystem
{
    private CANTalonSRX motor;

    @Override
    protected void initComponents()
    {
        motor = new CANTalonSRX(RobotMap.GRABBER_MOTOR);
    }

    public void rotate(double speed)
    {
        motor.set(speed);
    }

    public void rotateCW()
    {
        motor.set(1);
    }

    public void rotateCCW()
    {
        motor.set(-1);
    }

    public void stopMotor()
    {
        motor.stopMotor();
    }

    public static boolean isEnabled()
    {
        return true;
    }
}