ShowAllPorts
============

Lists all serial ports. Works with buit-in ports as well as USB-to-RS-232 adapters and virtual ports.

![image](http://blog.hani-ibrahim.de/wp-content/uploads/showallports_win10.png)
ShowAllPorts 2.0.0 on Windows 10

Features
--------

  * Cross-platform: Windows, Mac (Intel), GNU/Linux (Intel, ARM), Solaris (Intel, SPARC). 32- and 64-bit architectures.
  * GUI
  * Shows system port names (e.g. "COM1", "ttyS4") and additionally descriptive port names (e.g. "Physical Port S4", "Prolific USB-to-Serial Comm Port")
  * Shows whether ports are busy (except macOS)
  * Function which shows the last port(s) you connected to the machine. Useful if there are a lot of ports already installed. (Bluetooth, etc), especially under Windows.
  
Requirements
------------
for the current version:

  * Windows 7 or higher (x86, x86_64) or
  * GNU/Linux (x86, x86_64, ARMv6 soft and hard float, ARMv7, ARMv8_32, ARMv8_64) or
  * Solaris (x86, x86_64, SPARC8+_32, SPARC9_64) or
  * Mac OS X 10.4 or higher (x86_64) and
  * Java 6 JRE or higher on platforms mentioned above

Changelog
---------

1.1.0

  * Still Java 5 support
  * Still supports Windows® XP or higher (x86, x86_64), GNU/Linux (x86, x86_64, ARM soft & hard float), Solaris(x86, x86_64), Mac OS X(x86, x86_64, PPC, PPC64).
  * Copy & paste context menu
  * jSSC 2.8.0 library update
  * Recognizes /dev/cu.* beneath /dec/tty.* ports on Mac OS X®
  * Installer for GNU/Linux and Windows
  * App-Bundle for Mac OS X

2.0.0-alpha

  * Switch to JSerialComm serial library 1.3.11
  * Contains same functions as version 1.1.0
  * Displays beneath the system port names (e.g. "COM1"), descriptive port names (e.g. "USB-to-Serial Comm Port")
  * Saves preferences: window position/dimensions and settings at shutdown
  * Needs now Java 1.6 or higher
  * Mac OS X for PowerPC (PPC), Solaris and Windows XP are not supported

2.0.0

  * Update to JSerialComm serial library 2.6.0
  * Icons in Toolbox
  * Solaris support (x86, x86_64, SPARC8_32, SPARC9_64) reimplemented
  * Installer for GNU/Linux and Windows, app-bundle for macOS
  * Disabled "check port" function (port available or not) for macOS because of serious problems of the JSerialComm library with the Prolific driver

Acknowledgment
--------------

Thanks to Alexey Sokolov for the jSSC library, ShowAllPorts is build on up to version 1.1.0 (https://github.com/scream3r/java-simple-serial-connector).

Thanks to Will Hedgecock for the jSerialComm library, ShowAllPorts is build on since version 2.0.0 (https://fazecast.github.io/jSerialComm/).

Thanks to Icons8.de for providing [icons for the GUI](https://icons8.de/icon/pack/user-interface/windows).


Further information
-------------------

Blog: [http://blog.hani-ibrahim.de/showallportssoft.html](http://blog.hani-ibrahim.de/showallportssoft.html)
