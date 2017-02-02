
package de.haniibrahim.showallports;

import com.fazecast.jSerialComm.SerialPort;
import javax.swing.ImageIcon;


class SerialFunctions {
    
    private ImageIcon icon; // for "New Ports Message boxes"
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
    
    /**
     * Procedure to detect new serial ports plugged-in
     *
     * @return newPorts String of all new serial ports plugged-in (E.g: COM5)
     */
//    private String newPorts() {
//
//        SerialPort[] oldPortNames;
//        SerialPort[] newPortNames;
//        int oldPortLength;
//        int newPortLength;
//        int confirmResult;
//
//        icon = new ImageIcon(ShowAllPorts.class.getResource("interface.png"));
//
//        confirmResult = JOptionPane.showOptionDialog(this,
//                "If USB-to-Serial adapters are plugged-in,\npull them out now and press OK",
//                "Pull out USB-to-Serial adapter",
//                JOptionPane.OK_CANCEL_OPTION,
//                JOptionPane.INFORMATION_MESSAGE,
//                icon, null, null);
//        if (confirmResult == JOptionPane.CANCEL_OPTION || confirmResult == JOptionPane.CLOSED_OPTION) {
//            return newPort = "Procedure cancelled";
//        }
//
//        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//        oldPortNames = SerialPort.getCommPorts();
//        oldPortLength = oldPortNames.length;
//        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
//
//        confirmResult = JOptionPane.showOptionDialog(this,
//                "Plug in USB-to-RS232 adapter(s) and\npress OK to proceed",
//                "Plug-in USB-to-Serial adapter",
//                JOptionPane.OK_CANCEL_OPTION,
//                JOptionPane.INFORMATION_MESSAGE,
//                icon, null, null);
//        if (confirmResult == JOptionPane.CANCEL_OPTION || confirmResult == JOptionPane.CLOSED_OPTION) {
//            return newPort = "Procedure cancelled";
//        }
//
//        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//        newPortNames = SerialPort.getCommPorts();
//        newPortLength = newPortNames.length;
//        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
//
//        newPort = "";
//
//        if (oldPortLength >= newPortLength) {
//            newPort = "No new port detected";
//        } else {
//            /* Wrong results */
////            for (int i = 0; i < oldPortLength; i++) {
////                for (int j = 0; j < newPortLength; j++) {
////                    if (!oldPortNames[i].equals(newPortNames[j])) {
////                        newPort += newPortNames[j] + "\n";
////                    } else {
////                        continue;
////                    }
////                }
////            }
//            for (int i = 0; i < newPortLength; i++) {
//                if (!Arrays.asList(oldPortNames).contains(newPortNames[i])) {
//                    newPort += newPortNames[i].getSystemPortName() + "\n";
//                } else {
//                    continue;
//                }
//            }
//        }
//        System.out.println(newPort);
//        return newPort;
//    }
}
