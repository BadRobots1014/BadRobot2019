package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.CANTalonSRX;
import frc.robot.Robot;

public class Claw extends Subsystem
{
    private final Robot robot;
    private CANTalonSRX clawMotor;

    public Claw(Robot robot)
    {
        super();
        this.robot = robot;
        // TODO clawMotor = new CANTalonSRX(RobotMap.CLAW_MOTOR);
    }

    public void open()
    {
        clawMotor.set(1.0);
    }

    public void close()
    {
        clawMotor.set(-1.0);
    }

    @Override
    protected void initDefaultCommand()
    {

    }
}