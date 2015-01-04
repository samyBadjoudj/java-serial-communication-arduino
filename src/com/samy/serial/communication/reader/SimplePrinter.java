package com.samy.serial.communication.reader;

/**
 * Created by bibi on 04.01.15.
 */
public class SimplePrinter implements Command {
    @Override
    public void execute(Integer value) {
        System.out.println("received value: " + value);
    }
}
