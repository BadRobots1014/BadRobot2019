package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.tele.TeleArticulate;
import frc.robot.utils.hardware.CANTalonSRX;

public class Articulator extends BadSubsystem
{
    private CANTalonSRX motor;

    @Override
    protected void initComponents()
    {
        motor = new CANTalonSRX(RobotMap.ARTICULATOR_MOTOR);
    }

    @Override
    protected void initDefaultCommand()
    {
        setDefaultCommand(new TeleArticulate());
    }

    public void rotate(double speed)
    {
        motor.set(speed);
    }

    public void stopMotor()
    {
        motor.stopMotor();
    }

    public static boolean isEnabled()
    {
        return false;
    }
}