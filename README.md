# 🤖 DabBot - Smart Switch Controller

<div align="center">

![DabBot Logo](docs/images/logo.png)

**Open-source Bluetooth-controlled servo motor for automated button pressing**

[![Download APK](https://img.shields.io/badge/Download-APK-blue.svg)](https://drive.google.com/file/d/1eNxBlgz88abVft-SN3fXUSu5Ly24oAsl/view?usp=sharing)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Platform](https://img.shields.io/badge/Platform-Android-green.svg)](https://www.android.com/)
[![Min SDK](https://img.shields.io/badge/Min%20SDK-24-orange.svg)](https://developer.android.com/about/versions/nougat)

[Features](#features) • [Download](#download) • [Setup](#setup) • [Usage](#usage) • [Hardware](#hardware) • [Contributing](#contributing)

</div>

---

## 📱 Screenshots

<div align="center">

| Splash Screen | Device Discovery | Main Control |
|:---:|:---:|:---:|
| ![Splash](docs/screenshots/splash.png) | ![Discovery](docs/screenshots/discovery.png) | ![Control](docs/screenshots/control.png) |

| Switch Control | Angle Configuration | Connected Status |
|:---:|:---:|:---:|
| ![Switch](docs/screenshots/switch.png) | ![Configure](docs/screenshots/configure.png) | ![Status](docs/screenshots/status.png) |

</div>

---

## 🌟 Features

### 📱 **Mobile App**
- **🔍 Automatic Device Discovery** - No MAC address needed, just select from paired devices
- **🎚️ Simple Switch Control** - Toggle between OFF and ON positions
- **⚙️ Configurable Angles** - Set custom servo positions (0-180°)
- **💾 Settings Persistence** - Your angle configurations are saved automatically
- **🎨 Apple-inspired Design** - Clean, minimal, iOS-style interface
- **🚀 Splash Screen** - Professional branding on startup

### 🤖 **Hardware Control**
- **🎯 Precise Servo Control** - Accurate angle positioning
- **📶 Bluetooth Connectivity** - Reliable wireless communication
- **🔄 Smooth Movement** - Gradual servo transitions
- **⚡ Real-time Response** - Instant command execution
- **🛡️ Error Handling** - Robust connection management

---

## 🎯 What is DabBot?

DabBot is an **open-source alternative to expensive fingerbot solutions** like SwitchBot. It uses an ESP32 microcontroller and a servo motor to physically press buttons, switches, and other controls remotely via a Bluetooth-connected Android app.

### Use Cases:
- 💡 **Smart Home Automation** - Control non-smart devices
- 🚪 **Physical Button Pressing** - Lights, coffee makers, fans
- 🔘 **Switch Toggling** - Wall switches, appliances
- 🎮 **Gaming** - Automated button presses
- 🧪 **DIY Projects** - Custom automation solutions

---

## 📥 Download

### **APK Download**
[![Download APK](https://img.shields.io/badge/Download-Latest%20APK-brightgreen?style=for-the-badge&logo=android)](https://drive.google.com/file/d/1eNxBlgz88abVft-SN3fXUSu5Ly24oAsl/view?usp=sharing)

**Current Version:** v2.0  
**File Size:** ~5 MB  
**Min Android:** 7.0 (API 24)  
**Target Android:** 14 (API 34)

### **Installation Steps**
1. Download the APK from the link above
2. Enable "Install from Unknown Sources" in Android settings
3. Open the downloaded APK file
4. Tap "Install"
5. Grant Bluetooth permissions when prompted

---

## 🛠️ Hardware Setup

### **Required Components**

| Component | Specification | Quantity |
|-----------|--------------|----------|
| ESP32 Dev Board | Any ESP32 variant | 1 |
| Servo Motor | SG90 or MG90S (9g) | 1 |
| Jumper Wires | Male-to-Female | 3 |
| USB Cable | Micro-USB or USB-C | 1 |
| Power Supply | 5V, 1A minimum | 1 |

### **Wiring Diagram**

```
ESP32                    Servo Motor
-----                    -----------
GPIO 18 --------------->  Signal (Orange/Yellow)
5V      --------------->  VCC (Red)
GND     --------------->  GND (Brown/Black)
```

### **Circuit Diagram**

```
                    ┌──────────────┐
                    │    ESP32     │
                    │              │
      USB Power ────┤ USB          │
                    │              │
                    │         GPIO18├─────┐
                    │              │      │
                    │          5V  ├──┐   │
                    │              │  │   │
                    │         GND  ├┐ │   │
                    └──────────────┘│ │   │
                                    │ │   │
                              ┌─────┴─┴───┴────┐
                              │  Servo Motor   │
                              │                │
                              │  GND VCC  SIG  │
                              └────────────────┘
```

---

## 🚀 Quick Start Guide

### **Step 1: Hardware Setup**
1. Connect servo motor to ESP32 (see wiring diagram above)
2. Upload firmware to ESP32 ([download here](ESP32_Firmware/DabBot_ESP32_Servo_Controller.ino))
3. Power on ESP32

### **Step 2: Pair Device**
1. Open Android **Settings → Bluetooth**
2. Look for "**DabBot**" or "**ESP32**"
3. Tap to pair (no PIN required)

### **Step 3: Install App**
1. Download APK from link above
2. Install on your Android device
3. Grant Bluetooth permissions

### **Step 4: Connect & Control**
1. Open DabBot app
2. Select your device from the list
3. Tap to connect
4. Toggle the switch to control!

---

## 📖 Detailed Usage

### **🔍 Device Discovery Screen**

<img src="docs/screenshots/discovery.png" width="300" alt="Device Discovery">

1. **Launch the app** - Shows splash screen, then device list
2. **View paired devices** - All paired Bluetooth devices appear
3. **Select your DabBot** - Tap on the device to connect
4. **Refresh** - Tap refresh button if device doesn't appear

### **🎛️ Main Control Screen**

<img src="docs/screenshots/control.png" width="300" alt="Main Control">

1. **View connection status** - Green indicator when connected
2. **Toggle switch** - Move servo between OFF and ON positions
3. **Check current angle** - Displays current position below switch
4. **Configure angles** - Tap to customize OFF/ON positions

### **⚙️ Angle Configuration**

<img src="docs/screenshots/configure.png" width="300" alt="Configure Angles">

1. **OFF Position** - Set the angle for switch OFF state (default: 0°)
2. **ON Position** - Set the angle for switch ON state (default: 90°)
3. **Adjust sliders** - Drag to set desired angles (0-180°)
4. **Preview** - Large number shows selected angle in real-time
5. **Save** - Tap "Save" to apply changes
6. **Cancel** - Tap "Cancel" to discard changes

---

## 🎨 Design Philosophy

DabBot follows **Apple's iOS Human Interface Guidelines**:

- ✨ **Clarity** - Clear visual hierarchy and legible text
- 🎯 **Deference** - Content-focused with subtle UI elements
- 🏔️ **Depth** - Layered design creates visual hierarchy

### **Color Palette**
- **Primary Blue** (#007AFF) - Actions, OFF position
- **Green** (#34C759) - ON position, success states
- **Gray Tones** (#8E8E93, #F2F2F7) - Secondary text and backgrounds
- **White** (#FFFFFF) - Card backgrounds and primary surfaces

---

## 🔧 Technical Details

### **Android App Specifications**
- **Language:** Java
- **Min SDK:** 24 (Android 7.0 Nougat)
- **Target SDK:** 34 (Android 14)
- **Architecture:** Single Activity with multiple views
- **Design Pattern:** MVC (Model-View-Controller)
- **Libraries:** AndroidX, Material Design, RecyclerView

### **ESP32 Firmware Specifications**
- **Platform:** Arduino/ESP-IDF
- **Protocol:** Bluetooth Serial (SPP)
- **UUID:** 00001101-0000-1000-8000-00805F9B34FB
- **Command Format:** `ANGLE:XXX\n` (XXX = 0-180)
- **Baud Rate:** 115200

### **Communication Protocol**
```
Mobile App              ESP32
----------              -----
ANGLE:45\n  ───────►   Receive command
            ◄───────    Parse angle value
                        Move servo to 45°
            ◄───────    Send confirmation
```

---

## 📁 Project Structure

```
DabBot/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/example/myapplication/
│   │       │   ├── MainActivity.java
│   │       │   ├── SplashActivity.java
│   │       │   └── DeviceListAdapter.java
│   │       ├── res/
│   │       │   ├── layout/
│   │       │   │   ├── activity_main.xml
│   │       │   │   ├── activity_splash.xml
│   │       │   │   └── item_device.xml
│   │       │   ├── drawable/
│   │       │   └── values/
│   │       └── AndroidManifest.xml
│   └── build.gradle.kts
├── ESP32_Firmware/
│   └── DabBot_ESP32_Servo_Controller.ino
├── docs/
│   ├── screenshots/
│   ├── HARDWARE_ASSEMBLY.md
│   └── USER_GUIDE.md
├── LICENSE
└── README.md
```

---

## 🤝 Contributing

We welcome contributions! Here's how you can help:

### **Ways to Contribute**
- 🐛 Report bugs
- 💡 Suggest features
- 📝 Improve documentation
- 🎨 Design improvements
- 🔧 Code contributions
- 🌐 Translations

### **Development Setup**
1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/dabbot.git
   cd dabbot
   ```

2. **Open in Android Studio**
   - File → Open
   - Select the `DabBot` folder

3. **Sync Gradle**
   - File → Sync Project with Gradle Files

4. **Run on device**
   - Build → Build APK
   - Connect device and click Run

### **Code Style**
- Follow standard Java naming conventions
- Use 4 spaces for indentation
- Add comments for complex logic
- Keep methods focused and concise

---

## 📄 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

```
MIT License

Copyright (c) 2024 DabBot Open Source Community

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
```

---

## 🙏 Acknowledgments

- **ESP32 Community** - For excellent hardware and libraries
- **Android Open Source Project** - For the mobile platform
- **Material Design** - For design guidelines
- **Contributors** - Everyone who has contributed to this project

---

## 📞 Support & Community

### **Get Help**
- 📖 [Documentation](docs/)
- 💬 [GitHub Discussions](https://github.com/yourusername/dabbot/discussions)
- 🐛 [Report Issues](https://github.com/yourusername/dabbot/issues)
- 📧 [Email Support](mailto:support@dabbot.org)

### **Follow Us**
- 🐦 Twitter: [@DabBotProject](https://twitter.com/dabbotproject)
- 📘 Facebook: [DabBot Community](https://facebook.com/dabbot)
- 📷 Instagram: [@dabbot.official](https://instagram.com/dabbot.official)

---

## 🗺️ Roadmap

### **Version 2.1** (Coming Soon)
- [ ] Multiple device support
- [ ] Scheduled actions
- [ ] Widget support
- [ ] Voice control integration

### **Version 2.5** (Future)
- [ ] MQTT integration
- [ ] Home Assistant integration
- [ ] Battery level monitoring
- [ ] iOS app

### **Version 3.0** (Long-term)
- [ ] Web interface
- [ ] Cloud sync
- [ ] Multiple servo control
- [ ] Automation recipes

---

## ⭐ Star History

If you find this project useful, please consider giving it a star!

[![Star History Chart](https://api.star-history.com/svg?repos=yourusername/dabbot&type=Date)](https://star-history.com/#yourusername/dabbot&Date)

---

## 💖 Sponsor This Project

If you'd like to support the development of DabBot:

[![Buy Me A Coffee](https://img.shields.io/badge/Buy%20Me%20A%20Coffee-Support-yellow?style=for-the-badge&logo=buy-me-a-coffee)](https://buymeacoffee.com/dabbot)
[![PayPal](https://img.shields.io/badge/PayPal-Donate-blue?style=for-the-badge&logo=paypal)](https://paypal.me/dabbot)

---

<div align="center">

**Made with ❤️ by the DabBot Open Source Community**

[⬆ Back to Top](#-dabbot---smart-switch-controller)

</div>
