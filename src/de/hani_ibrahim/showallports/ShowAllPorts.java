package de.hani_ibrahim.showallports;

import java.awt.Cursor;
import java.awt.Toolkit;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 * ShowAllPorts: Shows all serial (RS-232) ports\navailable on this machine
 *
 * @author Hani Andreas Ibrahim
 * @version 1.0.1
 *
 */
public class ShowAllPorts extends javax.swing.JFrame {

    String[] portNames;
    int portLength;
    boolean checkPorts;
    String newPort = "";
    ImageIcon icon;

    /**
     * Creates new form
     */
    public ShowAllPorts() {
        // Programm Icon setzen
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("interface.png")));
        initComponents();
        textarea.addMouseListener(new ContextMenuMouseListener());

        // Hide Info-button and last seperator for Mac users
        if (getOS().equals("mac")) {
            sep_3.setVisible(false);
            btn_info.setVisible(false);
        }
        printPorts();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    //@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolbar = new javax.swing.JToolBar();
        btn_clear = new javax.swing.JButton();
        btn_refresh = new javax.swing.JButton();
        sep_1 = new javax.swing.JToolBar.Separator();
        btn_newports = new javax.swing.JButton();
        sep_2 = new javax.swing.JToolBar.Separator();
        cb_checkports = new javax.swing.JCheckBox();
        sep_3 = new javax.swing.JToolBar.Separator();
        btn_info = new javax.swing.JButton();
        scrollpane_text = new javax.swing.JScrollPane();
        textarea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ShowAllPorts");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocationByPlatform(true);
        setPreferredSize(new java.awt.Dimension(500, 300));

        toolbar.setFloatable(false);
        toolbar.setRollover(true);

        btn_clear.setText("  Clear  ");
        btn_clear.setToolTipText("Clear Screen");
        btn_clear.setFocusable(false);
        btn_clear.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_clear.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });
        toolbar.add(btn_clear);

        btn_refresh.setText("Refresh");
        btn_refresh.setToolTipText("Rescan Ports");
        btn_refresh.setFocusable(false);
        btn_refresh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_refresh.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });
        toolbar.add(btn_refresh);
        toolbar.add(sep_1);

        btn_newports.setText("New Ports");
        btn_newports.setToolTipText("Find new serial ports");
        btn_newports.setFocusable(false);
        btn_newports.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_newports.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_newports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_newportsActionPerformed(evt);
            }
        });
        toolbar.add(btn_newports);
        toolbar.add(sep_2);

        cb_checkports.setFont(cb_checkports.getFont());
        cb_checkports.setText("Check ports");
        cb_checkports.setToolTipText("Check whether ports are busy or not  (can take a while)");
        cb_checkports.setFocusable(false);
        cb_checkports.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cb_checkports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_checkportsActionPerformed(evt);
            }
        });
        toolbar.add(cb_checkports);
        toolbar.add(sep_3);

        btn_info.setText("  Info  ");
        btn_info.setFocusable(false);
        btn_info.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_info.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_info.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_infoActionPerformed(evt);
            }
        });
        toolbar.add(btn_info);

        getContentPane().add(toolbar, java.awt.BorderLayout.PAGE_START);

        textarea.setEditable(false);
        textarea.setColumns(20);
        textarea.setRows(5);
        textarea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textareaMouseClicked(evt);
            }
        });
        scrollpane_text.setViewportView(textarea);

        getContentPane().add(scrollpane_text, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Procedure to detect new serial ports plugged-in
     *
     * @return newPorts String of all new serial ports plugged-in (E.g: COM5)
     */
    private String newPorts() {

        String[] oldPortNames;
        String[] newPortNames;
        int oldPortLength;
        int newPortLength;
        int confirmResult;

        icon = new ImageIcon(ShowAllPorts.class.getResource("interface.png"));

        confirmResult = JOptionPane.showOptionDialog(this,
                "If USB-to-Serial adapters are plugged-in,\npull them out now and press OK",
                "Pull out USB-to-Serial adapter",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                icon, null, null);
        if (confirmResult == JOptionPane.CANCEL_OPTION || confirmResult == JOptionPane.CLOSED_OPTION) {
            return newPort = "Procedure cancelled";
        }

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        oldPortNames = SerialPortList.getPortNames();
        oldPortLength = oldPortNames.length;
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        confirmResult = JOptionPane.showOptionDialog(this,
                "Plug in USB-to-RS232 adapter(s) and\npress OK to proceed",
                "Plug-in USB-to-Serial adapter",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                icon, null, null);
        if (confirmResult == JOptionPane.CANCEL_OPTION || confirmResult == JOptionPane.CLOSED_OPTION) {
            return newPort = "Procedure cancelled";
        }

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        newPortNames = SerialPortList.getPortNames();
        newPortLength = newPortNames.length;
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        newPort = "";

        if (oldPortLength >= newPortLength) {
            newPort = "No new port detected";
        } else {
            /* Wrong results */
//            for (int i = 0; i < oldPortLength; i++) {
//                for (int j = 0; j < newPortLength; j++) {
//                    if (!oldPortNames[i].equals(newPortNames[j])) {
//                        newPort += newPortNames[j] + "\n";
//                    } else {
//                        continue;
//                    }
//                }
//            }
            for (int i = 0; i < newPortLength; i++) {
                if (!Arrays.asList(oldPortNames).contains(newPortNames[i])) {
                    newPort += newPortNames[i] + "\n";
                } else {
                    continue;
                }
            }
        }
        System.out.println(newPort);
        return newPort;
    }

    /**
     * Returns list of serial ports and check if they are busy or not when
     * variable checkPorts == TRUE
     *
     * @return portList String of serialPorts delimited with linefeed (\n).
     * OPTIONAL: status of ports if checkPorts == TRUE.
     */
    private String getPorts() {

        String portList = "";
        SerialPort port;
        String portStatus = "";

        for (int i = 0; i < portLength; i++) {
            port = new SerialPort(portNames[i]);
            if (checkPorts) {
                try {
                    port.openPort();
                    port.closePort();
                    portStatus = portNames[i] /* + " - Port available" */;
                } catch (SerialPortException ex) {
                    portStatus = portNames[i] + " - " + ex.getExceptionType();
                }
                portList += portStatus + "\n";
            } else {
                portList += portNames[i] + "\n";
            }
        }
        return portList;
    }

    /**
     * Output of serial ports (portList) in GUI and console
     */
    private void printPorts() {

        // Disable all neccesary items while searching for ports 
        textarea.setText("Searching ports ...");
        btn_clear.setEnabled(false);
        btn_refresh.setEnabled(false);
        btn_info.setEnabled(false);
        cb_checkports.setEnabled(false);
        btn_newports.setEnabled(false);

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        new Thread(new Runnable() {
            @Override
            public void run() {

                // Look for available ports
                portNames = SerialPortList.getPortNames();
                portLength = portNames.length;

                SwingUtilities.invokeLater( // Write to GUI
                        new Runnable() {
                            @Override
                            public void run() {
                                if (portLength == 0) {
                                    // Ausgabe GUI
                                    textarea.setText("");
                                    textarea.setText("No ports found");
                                    // Ausgabe auch auf der Console
                                    System.out.print("No ports found");
                                } else {
                                    // Ausgabe GUI
                                    textarea.setText("");
                                    textarea.append(getPorts());
                                    // Ausgabe auch auf der Console
                                    System.out.print(getPorts());
                                }

                                btn_clear.setEnabled(true);
                                btn_refresh.setEnabled(true);
                                btn_info.setEnabled(true);
                                cb_checkports.setEnabled(true);
                                btn_newports.setEnabled(true);

                                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                            }
                        });
            }
        }).start();
    }

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        printPorts();
    }//GEN-LAST:event_btn_refreshActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        textarea.setText("");
    }//GEN-LAST:event_btn_clearActionPerformed

    private void btn_infoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_infoActionPerformed
//        JOptionPane.showMessageDialog(this,
//                "<html><span style=\"font-size:large;\"><b>ShowAllPorts</b></span></html>\n"
//                + "Shows all serial (RS-232) Ports\navailable on this machine\n\n"
//                + "(c) Freeware 2013\n" + "by Hani Ibrahim <hani.ibrahim@gmx.de>\n\n",
//                "Info", JOptionPane.INFORMATION_MESSAGE);

        InfoDialog info = new InfoDialog(this, true);
        info.setLocationRelativeTo(this);
        info.setVisible(true);
    }//GEN-LAST:event_btn_infoActionPerformed

    private void cb_checkportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_checkportsActionPerformed
        if (cb_checkports.isSelected()) {
            checkPorts = true;
        } else {
            checkPorts = false;
        }
    }//GEN-LAST:event_cb_checkportsActionPerformed

    private void btn_newportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_newportsActionPerformed
        JOptionPane.showMessageDialog(this,
                "<html><span style=\"font-size:large;\"><b>New Port(s):</b></span></html>\n\n" + newPorts() + "\n\n",
                "New Port(s)",
                JOptionPane.INFORMATION_MESSAGE, icon);
        printPorts();
    }//GEN-LAST:event_btn_newportsActionPerformed

    private void textareaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textareaMouseClicked
        //textarea.addMouseListener(new ContextMenuMouseListener());

    }//GEN-LAST:event_textareaMouseClicked

    public static String getOS() {
        String osname = System.getProperty("os.name");
        if (osname != null && osname.toLowerCase().indexOf("mac") != -1) {
            return "mac";
        }
        if (osname != null && osname.toLowerCase().indexOf("windows") != -1) {
            return "win";
        }
        return "noarch";
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        System.getProperties().put("apple.laf.useScreenMenuBar", "true");

        /* Set the System look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Windows".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ShowAllPorts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ShowAllPorts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ShowAllPorts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ShowAllPorts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        // Look and Feel
        // Versuche zuerst GTK-L&F, dann System-L&F
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e1) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e2) {
                System.err.println("Look & Feel Error\n" + e2.getMessage());
            }
        }

        //</editor-fold>

        /* Create and display the form */
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
        ShowAllPorts prg = new ShowAllPorts();
        prg.setVisible(true);
        if (getOS().equals("mac")) {
            MacImpl macImpl = new MacImpl();
        }

//            }
//        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_info;
    private javax.swing.JButton btn_newports;
    private javax.swing.JButton btn_refresh;
    private javax.swing.JCheckBox cb_checkports;
    private javax.swing.JScrollPane scrollpane_text;
    private javax.swing.JToolBar.Separator sep_1;
    private javax.swing.JToolBar.Separator sep_2;
    private javax.swing.JToolBar.Separator sep_3;
    private javax.swing.JTextArea textarea;
    private javax.swing.JToolBar toolbar;
    // End of variables declaration//GEN-END:variables
}
