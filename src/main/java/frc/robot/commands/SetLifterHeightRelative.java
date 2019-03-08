package frc.robot.commands;

public class SetLifterHeightRelative extends SetLifterHeight
{
    private double offset;

    public SetLifterHeightRelative(double offset)
    {
        super(0);
        this.offset = offset;
    }

    @Override
    protected void initialize()
    {
        super.initialize();
        height = lifter.getEncoderValue() - offset;
    }
}