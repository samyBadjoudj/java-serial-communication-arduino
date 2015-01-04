package com.samy.serial.communication;

import com.samy.serial.communication.reader.SerialCommListener;
import com.samy.serial.communication.reader.SimplePrinter;

import javax.comm.CommPortIdentifier;
import java.util.Enumeration;

public class Main  {


    public Main() {
    }
    public static void main(String[] args) {
        CommPortIdentifier portId;
        final Enumeration portIdentifiers = CommPortIdentifier.getPortIdentifiers();
        while (portIdentifiers.hasMoreElements()) {
            portId = (CommPortIdentifier) portIdentifiers.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                if (portId.getName().contains("ACM")) {
                    System.out.println("ARDUINO device found " + portId.getName());
                    new SerialCommListener(new SimplePrinter(),portId);
                }
            }
        }
    }

}
