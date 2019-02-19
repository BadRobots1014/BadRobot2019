package frc.robot.subsystems;

import java.util.Arrays;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.utils.hardware.AdafruitMultiplexer;
import frc.robot.utils.hardware.RevColorSensorV2;

public class MulticolorSensor extends BadSubsystem
{
    private AdafruitMultiplexer multiplexer;
    private RevColorSensorV2 colorSensor;
    private short[][] buffer;

    public MulticolorSensor(Robot robot)
    {
        super(robot);
    }

    @Override
    protected void initComponents()
    {
        multiplexer = new AdafruitMultiplexer(Port.kOnboard, 0x70);
        colorSensor = new RevColorSensorV2(Port.kOnboard);
        buffer = new short[2][4];
        multiplexer.selectChannel(0);
    }

    @Override
    protected void initLogging()
    {

    }

    @Override
    protected void initDefaultCommand()
    {
        setDefaultCommand(new Command()
        {
            {
                requires(MulticolorSensor.this);
            }

            @Override
            protected void execute()
            {
                // for (int i = 0; i < buffer.length; i++)
                readRGB(0);
                readRGB(1);

                System.out.println("Reading Values");
            }

            @Override
            protected boolean isFinished()
            {
                return false;
            }
        });
    }

    private void readRGB(int channel)
    {
        multiplexer.selectChannel(channel);

        try
        {
            Thread.sleep(10);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        colorSensor.init();
        buffer[channel] = colorSensor.getRGBA();
        System.out.println(multiplexer.getCurrentChannel() + " : " + colorSensor.getStatus() + " | "
                + Arrays.toString(buffer[channel]) + " | " + colorSensor.getControl());

        try
        {
            Thread.sleep(100);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public short[] getRGB(int channel)
    {
        return buffer[channel];
    }
}