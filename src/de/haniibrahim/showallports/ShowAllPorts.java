package de.haniibrahim.showallports;

import com.fazecast.jSerialComm.*;
import static de.haniibrahim.showallports.SerialFunctions.*;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;

/**
 * ShowAllPorts: Shows all serial (RS-232) ports\navailable on this machine
 *
 * @author Hani Andreas Ibrahim
 * @version 2.0.0b
 *
 */
public class ShowAllPorts extends javax.swing.JFrame {

    private Preferences prefs;
    private ImageIcon icon; // for "New Ports Message boxes"

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
//            cb_checkports.setVisible(false); // Because JSerialComm has a problem w/ closePorts on macOS
        }

        setPrefs();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    storePrefs();
                } catch (BackingStoreException ex) {
                    // do nothing
                }
            }
        }));

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

        tb_toolbar = new javax.swing.JToolBar();
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

        tb_toolbar.setFloatable(false);
        tb_toolbar.setRollover(true);

        btn_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/haniibrahim/showallports/icons/clear.png"))); // NOI18N
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
        tb_toolbar.add(btn_clear);

        btn_refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/haniibrahim/showallports/icons/reload.png"))); // NOI18N
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
        tb_toolbar.add(btn_refresh);
        tb_toolbar.add(sep_1);

        btn_newports.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/haniibrahim/showallports/icons/unknown.png"))); // NOI18N
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
        tb_toolbar.add(btn_newports);
        tb_toolbar.add(sep_2);

        cb_checkports.setFont(cb_checkports.getFont());
        cb_checkports.setText("Check ports");
        cb_checkports.setToolTipText("Check whether ports are busy or not  (can take a while)");
        cb_checkports.setFocusable(false);
        cb_checkports.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        tb_toolbar.add(cb_checkports);
        tb_toolbar.add(sep_3);

        btn_info.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/haniibrahim/showallports/icons/info.png"))); // NOI18N
        btn_info.setText("  Info  ");
        btn_info.setFocusable(false);
        btn_info.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_info.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_info.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_infoActionPerformed(evt);
            }
        });
        tb_toolbar.add(btn_info);

        getContentPane().add(tb_toolbar, java.awt.BorderLayout.PAGE_START);

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
     * Output of serial ports (SysPortList) in GUI and console
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

        SwingWorker<String[], Void> worker = new SwingWorker<String[], Void>() {
            int numOfPorts;
            boolean checkPorts = cb_checkports.isSelected(); // Check ports?

            @Override
            protected String[] doInBackground() {
                numOfPorts = getNumOfPorts();
                String[] sP = getSysPortNames();
                String[] dP = getDesPortNames();
                String[] ports = new String[numOfPorts];

                if (checkPorts) {
                    String[] pM = getPortStatusMessages();
                    for (int i = 0; i < numOfPorts; i++) {
                        ports[i] = sP[i] + " - " + dP[i] + pM[i];
                    }
                } else {
                    for (int i = 0; i < numOfPorts; i++) {
                        ports[i] = sP[i] + " - " + dP[i];
                    }
                }
                return ports;
            }

            @Override
            protected void done() {
                try {
                    String[] ports = get();
                    if (ports.length == 0) {
                        // Output GUI
                        textarea.setText("");
                        String noPorts = "No ports found\n";
                        textarea.setText(noPorts);
                        // Output CLI
                        System.out.print(noPorts);
                    } else {
                        textarea.setText("");
                        for (int i = 0; i < numOfPorts; i++) {
                            textarea.append(ports[i] + System.getProperty("line.separator")); // Output GUI
                            System.out.print(ports[i] + System.getProperty("line.separator")); // Output CLI
                        }
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(ShowAllPorts.class.getName()).log(Level.SEVERE, "GET: Interrupted", ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(ShowAllPorts.class.getName()).log(Level.SEVERE, "GET: Excecution", ex);
                }
                btn_clear.setEnabled(true);
                btn_refresh.setEnabled(true);
                btn_info.setEnabled(true);
                cb_checkports.setEnabled(true);
                btn_newports.setEnabled(true);
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        };
        worker.execute();
    }

    /**
     * Procedure to detect new serial ports plugged-in
     *
     * @return newPorts as SerialPort[] array of all new serial ports plugged-in
     * (e.g: COM5)
     */
    private SerialPort[] getNewPorts() {

        SerialPort[] initPorts; // Array of ports before plug-in adapter
        SerialPort[] allPorts;  // Array of ports after plug-in adapter
        SerialPort[] newPorts = null; // Array of the pluged-in adapter(s)
        int initPortsLen; // Length of the array of initPorts
        int allPortsLen;  // Length of the array of allPorts
        int confirmResult; // Result of JOptionPanels

        icon = new ImageIcon(ShowAllPorts.class.getResource("interface.png"));

        confirmResult = JOptionPane.showOptionDialog(this,
                "If USB-to-Serial adapters are plugged-in,\npull them out now and press OK",
                "Pull out USB-to-Serial adapter",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                icon, null, null);
        if (confirmResult == JOptionPane.CANCEL_OPTION || confirmResult == JOptionPane.CLOSED_OPTION) {
            JOptionPane.showMessageDialog(this,
                    "Process canncelled by user",
                    "Info", JOptionPane.INFORMATION_MESSAGE);
            return newPorts = null;
        }

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        initPorts = getPortList();
        initPortsLen = initPorts.length;
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        confirmResult = JOptionPane.showOptionDialog(this,
                "Plug in USB-to-RS232 adapter(s) and\npress OK to proceed",
                "Plug-in USB-to-Serial adapter",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                icon, null, null);
        if (confirmResult == JOptionPane.CANCEL_OPTION || confirmResult == JOptionPane.CLOSED_OPTION) {
            JOptionPane.showMessageDialog(this,
                    "Process canncelled by user",
                    "Info", JOptionPane.INFORMATION_MESSAGE);
            return newPorts = null;
        }

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        allPorts = getPortList();
        allPortsLen = allPorts.length;
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        if (initPortsLen >= allPortsLen) {
            newPorts = null;
        } else {
            // Because new ports are added at the end of the array
            // the new ports are stored behind the initPorts in the array
            newPorts = new SerialPort[allPortsLen - initPortsLen];
            for (int i = initPortsLen; i < allPortsLen; i++) {
                newPorts[i - initPortsLen] = allPorts[i];
            }
        }
        return newPorts;
    }

    private void storePrefs() throws BackingStoreException {
        // Get node
        prefs = Preferences.userNodeForPackage(getClass());

        // Save window position
        prefs.putInt("xpos", getLocation().x);
        prefs.putInt("ypos", getLocation().y);

        // Save Window size
        prefs.putInt("width", getSize().width);
        prefs.putInt("height", getSize().height);

        // App settings
        prefs.putBoolean("checkports", cb_checkports.isSelected());

        prefs.flush(); // Made sure that all preferences are stored
    }

    private void setPrefs() {
        // Get node
        prefs = Preferences.userNodeForPackage(getClass());

        // Calculate screen-centered windows position
        final Dimension d = this.getToolkit().getScreenSize();
        int win_x = (int) ((d.getWidth() - this.getWidth()) / 2);
        int win_y = (int) ((d.getHeight() - this.getHeight()) / 2);

        // Set window position
        setLocation(prefs.getInt("xpos", win_x),
                prefs.getInt("ypos", win_y));

        // Set window size
        setSize(prefs.getInt("width", 637),
                prefs.getInt("height", 380));

        // App settings
        boolean checkPorts = prefs.getBoolean("checkports", false);
        cb_checkports.setSelected(checkPorts);
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

    private void btn_newportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_newportsActionPerformed
//        JOptionPane.showMessageDialog(this,
//                "<html><span style=\"font-size:large;\"><b>New Port(s):</b></span></html>\n\n" + newPorts() + "\n\n",
//                "New Port(s)",
//                JOptionPane.INFORMATION_MESSAGE, icon);
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        new SwingWorker<SerialPort[], Void>() {
            @Override
            protected SerialPort[] doInBackground() throws Exception {
                SerialPort[] newPorts = getNewPorts();
                return newPorts;
            }

            @Override
            protected void done() {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                try {
                    SerialPort[] newPorts = get();
                    if (newPorts != null) {
                        String newPortsText = "";
                        for (int i = 0; i < newPorts.length; i++) {
                            newPortsText += newPorts[i].getSystemPortName() + "\n";
                        }
                        JOptionPane.showMessageDialog(ShowAllPorts.getFrames()[0],
                                "New Ports:\n\n" + newPortsText + "\n",
                                "New detected ports", JOptionPane.INFORMATION_MESSAGE);
                        printPorts();
                    } else {
                        JOptionPane.showMessageDialog(ShowAllPorts.getFrames()[0],
                                "No ports found\n",
                                "New detected ports", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(ShowAllPorts.class.getName()).log(Level.SEVERE, "New Port", ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(ShowAllPorts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }.execute();
    }//GEN-LAST:event_btn_newportsActionPerformed

    private void textareaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textareaMouseClicked
        //textarea.addMouseListener(new ContextMenuMouseListener());

    }//GEN-LAST:event_textareaMouseClicked

    public static String getOS() {
        String osname = System.getProperty("os.name");
        if (osname != null && osname.toLowerCase().contains("mac")) {
            return "mac";
        }
        if (osname != null && osname.toLowerCase().contains("windows")) {
            return "win";
        }
        return "noarch";
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        System.getProperties().put("apple.laf.useScreenMenuBar", "true");

        //<editor-fold defaultstate="collapsed" desc="Look and Feel">
        // Try GTK-LaF on GNU/Linux first, then System-LaF. System-LaF on all other platforms
        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            } catch (Exception e1) {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e2) {
                    System.err.println("Look & Feel Error\n" + e2.getMessage());
                }
            }
        } else {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e2) {
                System.err.println("Look & Feel Error\n" + e2.getMessage());
            }
        }
        //</editor-fold>

        /* Create and display the form */
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ShowAllPorts prg = new ShowAllPorts();
                prg.setVisible(true);
                if (getOS().equals("mac")) {
                    MacImpl macImpl = new MacImpl();
                }
            }
        });
    }
    //<editor-fold defaultstate="collapsed" desc=" GUI variables declaration">
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
    private javax.swing.JToolBar tb_toolbar;
    private javax.swing.JTextArea textarea;
    // End of variables declaration//GEN-END:variables
//</editor-fold>
}
