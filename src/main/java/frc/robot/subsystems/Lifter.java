package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.tele.TeleLift;

public class Lifter extends BadSubsystem
{
    private static final double ROTATIONS_PER_METER = 10; // TODO find this

    private CANSparkMax lifterMotor;

    public Lifter(Robot robot)
    {
        super(robot);
    }

    @Override
    protected void initComponents()
    {
        lifterMotor = new CANSparkMax(RobotMap.LIFTER_MOTOR, MotorType.kBrushless);
    }

    @Override
    protected void initLogging()
    {

    }

    @Override
    protected void initDefaultCommand()
    {
        setDefaultCommand(new TeleLift(this, robot.oi.joystick));
    }

    // This should return 0 at the lowest height
    public double getEncoderValue()
    {
        return lifterMotor.getEncoder().getPosition();
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
        lifterMotor.set(speed);
    }

    public void stopMotor()
    {
        lifterMotor.stopMotor();
    }
}