
package de.haniibrahim.showallports;

import com.fazecast.jSerialComm.SerialPort;


class SerialFunctions {
    
    protected static SerialPort[] portList;
    
    private static int numOfPorts;
    
    /**
     * Get the serial ports on the machine as an SerialPort array
     *
     * @return SerialPort-Array of the serial ports found
     */
    protected static SerialPort[] getPortList() {
        portList = SerialPort.getCommPorts();
        numOfPorts = portList.length; // Global static variable
        return portList;
    }
    
    protected  static int getNumOfPorts(){
        getPortList();
        return numOfPorts;
    }
}
