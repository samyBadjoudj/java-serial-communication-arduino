java-serial-communication-arduino
=================================

java-serial-communication-arduino : A piece of code to read serial port value from Arduino in java (using linux 32 bit naitve lib)


* 1 - with comm.jar in your CLASS_PATH
* 2 - with javax.comm.properties  in your CLASS_PATH as well
* 3 - put the .so library where the OS can load it ( consult /etc/ld.so.conf or LD_LIBRARY_PATH)

If you have this error you must prevent the stack library to contain executable code with this binary

execstack -c /path to your lib/

Java HotSpot(TM) Client VM warning: You have loaded library /usr/lib/libLinuxSerialParallel.so which might have disabled stack guard. The VM will try to fix the stack guard now.
It's highly recommended that you fix the library with 'execstack -c <libfile>', or link it with '-z noexecstack'.


```
ARDUINO device found /dev/ttyACM0
Owner: SimpleReadApp
Name: /dev/ttyACM0
Type (1 Serial, 2 Parallel): 1
received value: 703
received value: 700
received value: 670
received value: 550
received value: 477
received value: 476
received value: 556
received value: 677
received value: 861
received value: 986
received value: 1023
received value: 1023
received value: 913
received value: 667
received value: 503
received value: 325
received value: 154
received value: 0
received value: 0
```


Samy Badjoudj
