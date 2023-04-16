package de.haniibrahim.showallports;

import com.apple.eawt.AboutHandler;
import com.apple.eawt.AppEvent.AboutEvent;
import com.apple.eawt.AppEvent.PreferencesEvent;
import com.apple.eawt.AppEvent.QuitEvent;
import com.apple.eawt.Application;
import com.apple.eawt.PreferencesHandler;
import com.apple.eawt.QuitHandler;
import com.apple.eawt.QuitResponse;
import javax.swing.ImageIcon;

/**
 *
 * @author Hani Ibrahim
 */
final class MacImpl implements ClassSelector, AboutHandler, PreferencesHandler, QuitHandler {

    Application application;

    public MacImpl() {
        handleOS();
    }

    @Override
    public void handleOS() {

        try {
            application = Application.getApplication();
            application.setAboutHandler(this);
            // PreferenceHandler not used yet
            application.setPreferencesHandler(this); 
        } catch (Throwable e) {
            System.err.println("setupMacOSXApplicationListener failed: "
                    + e.getMessage());
        }

        // Set dock icon
        application.setDockIconImage(new ImageIcon(getClass().getResource("showallports.png")).getImage());

    }

    @Override
    public void handleAbout(AboutEvent arg0) {
        InfoDialog info = new InfoDialog(ShowAllPorts.getFrames()[0], true);
//        info.setLocationRelativeTo(null);
        info.setLocationRelativeTo(null /*ShowAllPorts.getFrames()[0]*/);
        info.setVisible(true);
        // System.out.println("handleAbout()"); 
    }

    // not used yet
    @Override
    public void handlePreferences(PreferencesEvent arg0) {
        Options optionsDialog = new Options(ShowAllPorts.getFrames()[0],true);
        optionsDialog.setLocationRelativeTo(null);
        optionsDialog.setVisible(true);
    }

    @Override
    public void handleQuitRequestWith(QuitEvent arg0, QuitResponse arg1) {
        System.exit(0);
    }
}

interface ClassSelector {

    public void handleOS();
}
