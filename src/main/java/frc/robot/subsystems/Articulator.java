package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.utils.hardware.CANTalonSRX;

public class Articulator extends BadSubsystem
{
    private CANTalonSRX motor;

    @Override
    protected void initComponents()
    {
        motor = new CANTalonSRX(RobotMap.ARTICULATOR_MOTOR);
    }

    public void rotate(double speed)
    {
        motor.set(speed);
    }

    public void stopMotor()
    {
        motor.stopMotor();
    }
}