/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.File;

/**
 *
 * @author cathy
 */
public class DetectOS {
    //Reference: Stack Overflow - detects the OS of the user
    private static boolean isWindows = false;
    private static boolean isLinux = false;
    private static boolean isMac = false;

    static {
        String os = System.getProperty("os.name").toLowerCase();
        isWindows = os.contains("win");
        isLinux = os.contains("nux") || os.contains("nix");
        isMac = os.contains("mac");
    }

    public static boolean isWindows() {
        return isWindows;
    }

    public static boolean isLinux() {
        return isLinux;
    }

    public static boolean isMac() {
        return isMac;
    }

    ;
    
    
    public static boolean open(String file) {
        try {
            if (DetectOS.isWindows()) {
                Runtime.getRuntime().exec(new String[]{"rundll32", "url.dll,FileProtocolHandler",
                    file});
                return true;
            } else if (DetectOS.isLinux() || DetectOS.isMac()) {
                Runtime.getRuntime().exec(new String[]{"/usr/bin/open",
                    file});
                return true;
            } else {
                
                    return false;
                
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
            return false;
        }
    }
}
