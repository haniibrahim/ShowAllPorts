
package de.haniibrahim.showallports;

import com.fazecast.jSerialComm.SerialPort;
import javax.swing.ImageIcon;


final class SerialFunctions {

    private static SerialPort[] portList;
    private static int numOfPorts;
    
    /**
     * Get the serial ports on the machine as an SerialPort array
     *
     * @return SerialPort-Array of the serial ports found
     */
    protected static SerialPort[] getPortList() {
        portList = SerialPort.getCommPorts();
        return portList;
    }
    
    protected  static int getNumOfPorts(){
        numOfPorts = getPortList().length;
        return numOfPorts;
    }
    
    /**
     * Returns system port names, e.g. COM1
     *
     * @return String array of system port names found (e.g. COM1, /dev/ttyS1)
     */
    protected static String[] getSysPortNames() {
        String[] sysPortNames = new String[numOfPorts];
        for (int i = 0; i < numOfPorts; i++) {
            sysPortNames[i] = portList[i].getSystemPortName();
        }
        return sysPortNames;
    }
    
    /**
     * Returns descriptive port names, e.g. "USB-to-Serial CommPort"
     *
     * @return String array of descriptive port names 
     */
    protected static String[] getDesPortNames() {
        String[] desPortNames = new String[numOfPorts];
        for (int i = 0; i < numOfPorts; i++) {
            desPortNames[i] = portList[i].getDescriptivePortName();
        }
        return desPortNames;
    }

    /**
     * Determine whether ports are busy or not
     *
     * @return Boolean array 
     */
    protected static Boolean[] getPortStatus() {
        Boolean[] portStatus = new Boolean[numOfPorts];
        for (int i = 0; i < numOfPorts; i++) {
            if (portList[i].openPort()) {
                portStatus[i] = true; // Port is free
                portList[i].closePort();
            } else {
                portStatus[i] = false; // Port is busy
            }
        }
        return portStatus;
    }

    /**
     * Returns a status message dependend on getPortStatus()
     *
     * @return
     */
    protected static String[] getPortStatusMessages() {
        String[] statusMessage = new String[numOfPorts];
        Boolean[] portStatus = getPortStatus();
        for (int i = 0; i < numOfPorts; i++) {
            if (portStatus[i]) {
                statusMessage[i] = " - Port available";
            } else {
                statusMessage[i] = " - Port busy";
            }
        }
        return statusMessage;
    }
}
