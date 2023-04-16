/*
 * Copyright 2023 hi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.haniibrahim.showallports;

/**
 *
 * @author Hani Ibrahim
 */
final class Helper {
    
        /**
     * Detect platform (Windows, macOS, ...)
     *
     * @return platform name as short string: win, mac, linux, noarch. If
     * platform cannot detected "noarch" is returned
     */
    static String getOS() {
        String osname = System.getProperty("os.name");
        if (osname != null && osname.toLowerCase().contains("mac")) {
            return "mac";
        }
        if (osname != null && osname.toLowerCase().contains("windows")) {
            return "win";
        }
        if (osname != null && osname.toLowerCase().contains("linux")) {
            return "linux";
        }
        return "noarch";
    }
    
    /**
     * Returns the current integer Java version
     *
     * Examples: Java 1.8 => 8, Java 17 => 17
     *
     * @return Integer Java version
     */
     static int getJavaVersion() {
        String version = System.getProperty("java.version");
        if (version.startsWith("1.")) {
            version = version.substring(2, 3);
        } else {
            int dot = version.indexOf(".");
            if (dot != -1) {
                version = version.substring(0, dot);
            }
        }
        return Integer.parseInt(version);
    }
}
