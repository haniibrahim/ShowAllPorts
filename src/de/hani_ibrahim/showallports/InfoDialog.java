package de.hani_ibrahim.showallports;

import java.awt.Cursor;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HI
 */
public class InfoDialog extends javax.swing.JDialog {

    URI uri;

    /**
     * Creates new form InfoDialog
     */
    public InfoDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {
            uri = new URI("mailto:hani.ibrahim@gmx.de");
        } catch (URISyntaxException ex) {
            Logger.getLogger(InfoDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        
        // Set OK-Button to default
        this.getRootPane().setDefaultButton(btn_ok);
        
        // Set version in Infobox
        lb_version.setText("2.0");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_img = new javax.swing.JLabel();
        btn_ok = new javax.swing.JButton();
        lb_title = new javax.swing.JLabel();
        lb_subtitle = new javax.swing.JLabel();
        lb_copyright = new javax.swing.JLabel();
        lb_email = new javax.swing.JLabel();
        lb_version = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ShowAllPorts - Info");
        setModal(true);
        setResizable(false);

        lb_img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/hani_ibrahim/showallports/interface.png"))); // NOI18N

        btn_ok.setText("OK");
        btn_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_okActionPerformed(evt);
            }
        });

        lb_title.setFont(lb_title.getFont().deriveFont(lb_title.getFont().getStyle() | java.awt.Font.BOLD, lb_title.getFont().getSize()+7));
        lb_title.setText("ShowAllPorts");

        lb_subtitle.setText("Lists all serial ports available on this machine");

        lb_copyright.setText(" © 2013 by Hani Ibrahim · Apache License 2.0");

        lb_email.setText("<html><font color=blue><u>hani.ibrahim@gmx.de</u></font></html>");
        lb_email.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_emailMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_emailMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_emailMouseEntered(evt);
            }
        });

        lb_version.setText("<version>");
        lb_version.setToolTipText("");
        lb_version.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(lb_img)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lb_copyright)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lb_title)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lb_version, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lb_subtitle))
                        .addGap(0, 31, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(lb_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_img)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb_title, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_version))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_subtitle)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb_copyright)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_ok)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_okActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_okActionPerformed

    private void lb_emailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_emailMouseClicked
        openUri(uri);
    }//GEN-LAST:event_lb_emailMouseClicked

    private void lb_emailMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_emailMouseEntered
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lb_emailMouseEntered

    private void lb_emailMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_emailMouseExited
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_lb_emailMouseExited

    private static void openUri(URI uri) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(uri);
            } catch (IOException e) { System.err.println(e.toString()); }
        } else { System.err.println("ERROR: 'Desktop.isDesktopSupported()' is 'FALSE'"); }
    }
    
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(InfoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(InfoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(InfoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(InfoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                InfoDialog dialog = new InfoDialog(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ok;
    private javax.swing.JLabel lb_copyright;
    private javax.swing.JLabel lb_email;
    private javax.swing.JLabel lb_img;
    private javax.swing.JLabel lb_subtitle;
    private javax.swing.JLabel lb_title;
    private javax.swing.JLabel lb_version;
    // End of variables declaration//GEN-END:variables
}
