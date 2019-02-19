package frc.robot.subsystems;

import frc.robot.utils.hardware.CANTalonSRX;
import frc.robot.RobotMap;
import frc.robot.commands.tele.TeleBackHatchCam;

public class BackHatchCam extends BadSubsystem
{
    private CANTalonSRX motor;

    @Override
    protected void initComponents()
    {
        motor = new CANTalonSRX(RobotMap.CAM_MOTOR);
        motor.setInverted(true);
    }

    @Override
    protected void initDefaultCommand()
    {
        setDefaultCommand(new TeleBackHatchCam());
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
}