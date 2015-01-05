package com.samy.serial.communication.reader;

import javax.comm.CommPortIdentifier;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Samy Badjoudj on 04.01.15.
 */
public class SerialCommListener implements SerialPortEventListener, Runnable {

    private static final int BAUD_RATE = 9600;
    private static final String CONNECTION_NAME = "SimpleReadApp";
    private static final int TIMEOUT = 2000;
    private CommPortIdentifier portId;
    private BufferedReader inputStream;
    private SerialPort serialPort;
    private Thread readThread;
    private Command command;

    public SerialCommListener(Command command, CommPortIdentifier portId) {
        this.command = command;
        this.portId = portId;
        try {
            serialPort = (SerialPort) this.portId.open(CONNECTION_NAME, TIMEOUT);
            inputStream = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
            serialPort.setSerialPortParams(BAUD_RATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
        } catch (Exception e) {
            e.printStackTrace();
            // here we receive a IO exception it's a known problem

        }
        printPortInformation(portId);
        readThread = new Thread(this);
        readThread.start();
    }

    private void printPortInformation(CommPortIdentifier portId) {
        System.out.println("Owner: " +  portId.getCurrentOwner());
        System.out.println("Name: " +  portId.getName());
        System.out.println("Type (1 Serial, 2 Parallel): " + portId.getPortType());
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        switch (serialPortEvent.getEventType()) {
            case SerialPortEvent.BI:
            case SerialPortEvent.OE:
            case SerialPortEvent.FE:
            case SerialPortEvent.PE:
            case SerialPortEvent.CD:
            case SerialPortEvent.CTS:
            case SerialPortEvent.DSR:
            case SerialPortEvent.RI:
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                break;
            case SerialPortEvent.DATA_AVAILABLE:
                String line;
                try {
                    while ((line = inputStream.readLine()) != null) {
                        if (!"".equals(line)) {
                            command.execute(Integer.parseInt(line));
                        }
                    }
                } catch (IOException iOException) {
                    iOException.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }
}
