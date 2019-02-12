package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.CANTalonSRX;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.TeleGrab;

public class BackHatchCam extends Subsystem
{
    private final Robot robot;
    private final CANTalonSRX motor;

    public BackHatchCam(Robot robot)
    {
        super();
        this.robot = robot;
        motor = new CANTalonSRX(RobotMap.GRABBER_MOTOR);
        motor.setInverted(true);
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

    public boolean isCamInBackPosition()
    {
        return motor.getSensorCollection().isFwdLimitSwitchClosed();
    }

    @Override
    protected void initDefaultCommand()
    {
        setDefaultCommand(new TeleGrab(robot.grabber, robot.oi.xboxController));
    }
}