package frc.robot.utils;

import java.nio.ByteBuffer;

import edu.wpi.first.wpilibj.I2C;

public class MultiplexedI2C extends I2C
{
    protected final int sensorAddress;

    public MultiplexedI2C(Port port, int multiplexerAddress, int sensorAddress)
    {
        super(port, multiplexerAddress);
        this.sensorAddress = sensorAddress;
    }

    protected void selectSensor()
    {
        super.write(1 << sensorAddress, 1);
    }

    @Override
    public synchronized boolean transaction(ByteBuffer dataToSend, int sendSize, ByteBuffer dataReceived,
            int receiveSize)
    {
        selectSensor();
        return super.transaction(dataToSend, sendSize, dataReceived, receiveSize);
    }

    @Override
    public synchronized boolean transaction(byte[] dataToSend, int sendSize, byte[] dataReceived, int receiveSize)
    {
        selectSensor();
        return super.transaction(dataToSend, sendSize, dataReceived, receiveSize);
    }

    @Override
    public boolean addressOnly()
    {
        selectSensor();
        return super.addressOnly();
    }

    @Override
    public synchronized boolean write(int registerAddress, int data)
    {
        selectSensor();
        return super.write(registerAddress, data);
    }

    @Override
    public synchronized boolean writeBulk(ByteBuffer data, int size)
    {
        selectSensor();
        return super.writeBulk(data, size);
    }

    @Override
    public synchronized boolean writeBulk(byte[] data)
    {
        selectSensor();
        return super.writeBulk(data);
    }

    @Override
    public synchronized boolean writeBulk(byte[] data, int size)
    {
        selectSensor();
        return super.writeBulk(data, size);
    }

    @Override
    public boolean read(int registerAddress, int count, ByteBuffer buffer)
    {
        selectSensor();
        return super.read(registerAddress, count, buffer);
    }

    @Override
    public boolean read(int registerAddress, int count, byte[] buffer)
    {
        selectSensor();
        return super.read(registerAddress, count, buffer);
    }

    @Override
    public boolean readOnly(ByteBuffer buffer, int count)
    {
        selectSensor();
        return super.readOnly(buffer, count);
    }

    @Override
    public boolean readOnly(byte[] buffer, int count)
    {
        selectSensor();
        return super.readOnly(buffer, count);
    }

    @Override
    public boolean verifySensor(int registerAddress, int count, byte[] expected)
    {
        selectSensor();
        return super.verifySensor(registerAddress, count, expected);
    }
}