package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.CANTalonSRX;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.TeleLift;

public class Lifter extends Subsystem
{
    private static final double ROTATIONS_PER_METER = 10; // TODO find this

    private final Robot robot;

    private final CANTalonSRX lifterMotor1;
    private final CANTalonSRX lifterMotor2;

    public Lifter(Robot robot)
    {
        super();
        this.robot = robot;
        lifterMotor1 = new CANTalonSRX(RobotMap.LIFTER_MOTOR_1);
        lifterMotor2 = new CANTalonSRX(RobotMap.LIFTER_MOTOR_2);
        lifterMotor2.follow(lifterMotor1);
        lifterMotor2.setInverted(true);
    }

    // This should return 0 at the lowest height
    public double getEncoderValue()
    {
        return 0; // TODO lifterMotor.getEncoder().getPosition();
    }

    public double getHeight()
    {
        return 0; // TODO rotationsToMeters(getEncoderValue());
    }

    private double rotationsToMeters(double rotations)
    {
        return rotations / ROTATIONS_PER_METER;
    }

    public void setSpeed(double speed)
    {
        lifterMotor1.set(speed);
    }

    public void stopMotor()
    {
        lifterMotor1.stopMotor();
    }

    @Override
    protected void initDefaultCommand()
    {
        setDefaultCommand(new TeleLift(robot.lifter, robot.oi.joystick));
    }
}