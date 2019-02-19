package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.tele.TeleLift;

public class Lifter extends Subsystem
{
    private static final double ROTATIONS_PER_METER = 10; // TODO find this

    private final Robot robot;

    private final CANSparkMax lifterMotor;

    public Lifter(Robot robot)
    {
        super();
        this.robot = robot;
        lifterMotor = new CANSparkMax(RobotMap.LIFTER_MOTOR, MotorType.kBrushless);
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

    @Override
    protected void initDefaultCommand()
    {
        setDefaultCommand(new TeleLift(this, robot.oi.joystick));
    }
}