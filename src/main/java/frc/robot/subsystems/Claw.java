package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.CANTalonSRX;

public class Claw extends Subsystem
{
    private CANTalonSRX clawMotor;

    public Claw()
    {
        super();
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