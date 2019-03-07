package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.RobotMap;
import frc.robot.commands.tele.TeleLift;

public class Lifter extends BadSubsystem
{
    private static final double ROTATIONS_PER_METER = -23.5; // TODO find this

    private CANSparkMax motor;

    @Override
    protected void initComponents()
    {
        motor = new CANSparkMax(RobotMap.LIFTER_MOTOR, MotorType.kBrushless);
    }

    @Override
    protected void initDefaultCommand()
    {
        setDefaultCommand(new TeleLift());
    }

    // This should return 0 at the lowest height
    public double getEncoderValue()
    {
        return motor.getEncoder().getPosition();
    }

    public void zeroEncoder()
    {
        motor.getEncoder().setPosition(0);
    }

    public double getHeight()
    {
        return rotationsToMeters(getEncoderValue());
    }

    private double rotationsToMeters(double rotations)
    {
        return rotations / ROTATIONS_PER_METER;
    }

    public void setSpeed(double speed)
    {
        motor.set(speed);
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