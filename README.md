# 🤖 DabBot - Smart Switch Controller

<div align="center">

**Transform any button or switch into a smart device**

Control physical buttons remotely with your Android phone via Bluetooth

[![Download APK](https://img.shields.io/badge/Download-APK-brightgreen?style=for-the-badge&logo=android)](https://drive.google.com/file/d/1eNxBlgz88abVft-SN3fXUSu5Ly24oAsl/view?usp=sharing)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![GitHub Stars](https://img.shields.io/github/stars/yantra-commons/Dab-Bot?style=social)](https://github.com/yantra-commons/Dab-Bot)

</div>

---

## 🎯 What is DabBot?

DabBot is an **affordable, open-source alternative** to expensive smart button pushers (like SwitchBot). It physically presses buttons, flips switches, and controls devices that aren't "smart" - making them controllable from your phone.

**Perfect for:**
- 💡 Turning on/off lights that don't have smart switches
- ☕ Starting your coffee maker from bed
- 🔌 Controlling old appliances
- 🎮 Automating repetitive button presses
- 🏠 Budget-friendly smart home projects

---

## 📥 For End Users: Quick Install

### **1. Download the App**

Click the button below to download DabBot for Android:

[![Download APK](https://img.shields.io/badge/Download-Latest%20APK-brightgreen?style=for-the-badge&logo=android)](https://drive.google.com/file/d/1eNxBlgz88abVft-SN3fXUSu5Ly24oAsl/view?usp=sharing)

**Requirements:**
- Android 7.0 or higher
- Bluetooth capability
- ~5 MB storage space

### **2. Install the App**

1. Open the downloaded `DabBot.apk` file
2. If prompted, enable "Install from Unknown Sources"
3. Tap **Install**
4. Open the app and grant Bluetooth permissions

### **3. Get the Hardware**

You'll need a DabBot device (ESP32 + servo motor). You can either:

**Option A: Buy Pre-made** *(Coming Soon)*
- Pre-assembled and tested
- Just plug and play

**Option B: Build Your Own** (~$10 in parts)
- [See Hardware Guide](#-hardware-build-guide) below
- Great for DIY enthusiasts

### **4. Start Using**

1. **Pair** your DabBot in Android Bluetooth settings
2. **Open** the DabBot app
3. **Select** your device from the list
4. **Toggle** the switch to control!

---

## 🔧 Hardware Build Guide

### **What You Need** (~$10 total)

| Item | Price | Where to Buy |
|------|-------|--------------|
| ESP32 Dev Board | ~$5 | Amazon, AliExpress, eBay |
| SG90 Servo Motor | ~$2 | Amazon, Electronics stores |
| Jumper Wires (3x) | ~$1 | Included with ESP32 |
| USB Cable | ~$2 | Any micro-USB cable |

### **Assembly** (5 minutes)

```
Step 1: Connect 3 wires from ESP32 to Servo

ESP32          →    Servo Motor
─────               ───────────
GPIO 18       →     Signal (Orange wire)
5V            →     Power (Red wire)  
GND           →     Ground (Brown wire)

Step 2: Plug USB cable into ESP32
Step 3: Done! 🎉
```

**Visual Guide:**
```
     ┌─────────┐
     │  ESP32  │
     │  ┌───┐  │
USB──┤  │ □ │  ├──┐ GPIO 18 (Signal)
     │  └───┘  ├──┤ 5V (Power)
     └─────────┘  └ GND (Ground)
          │ │ │
          └─┴─┴──► Servo Motor
```

### **Upload Firmware** (One-time setup)

1. **Download Arduino IDE** from https://www.arduino.cc/
2. **Download firmware** from [ESP32_Firmware folder](ESP32_Firmware/)
3. **Install ESP32 boards** in Arduino IDE
4. **Upload** `DabBot_ESP32_Servo_Controller.ino` to your ESP32

[Detailed firmware upload instructions →](docs/FIRMWARE_UPLOAD.md)

---

## 💡 How It Works

1. **You toggle the switch** in the DabBot app
2. **Phone sends Bluetooth command** to ESP32
3. **Servo motor moves** to press the button
4. **Your device turns on/off** 

Simple as that! No internet required, no cloud services, no subscriptions.

---

## ✨ Features

### **For Users:**
- ✅ **Easy to use** - Just tap a switch
- ✅ **No internet needed** - Works offline via Bluetooth
- ✅ **Customizable** - Set exact button press angles
- ✅ **Clean design** - Apple-inspired interface
- ✅ **Free forever** - No subscriptions or in-app purchases

### **For Developers:**
- ✅ **100% Open Source** - Full source code available
- ✅ **Well documented** - Easy to understand and modify
- ✅ **Modern Android** - Material Design, AndroidX
- ✅ **ESP32 Arduino** - Simple firmware, easy to hack
- ✅ **MIT Licensed** - Use it however you want

---

## 🚀 Getting Started

### **Option 1: I Just Want to Use It**

1. Download the APK (link at top)
2. Get/build the hardware
3. Install and use!

### **Option 2: I Want to Develop/Modify It**

```bash
# Clone the repository
git clone https://github.com/yantra-commons/Dab-Bot.git
cd Dab-Bot

# Open the App folder in Android Studio
# File → Open → Select "Dab-Bot/App"

# Build and run
# Connect your Android device
# Click the green "Run" button
```

---

## 🎨 App Features

### **Device Discovery**
- Automatically finds nearby DabBot devices
- No need to enter MAC addresses
- One-tap connection

### **Simple Control**
- Big, easy-to-use ON/OFF switch
- Shows current position
- Instant response

### **Angle Configuration**
- Customize OFF position (e.g., 0°)
- Customize ON position (e.g., 90°)
- Visual sliders with live preview
- Settings saved automatically

### **Clean Design**
- iOS-inspired interface
- Large, readable text
- Smooth animations
- Professional appearance

---

## 🛠️ Use Cases & Ideas

### **Smart Home**
- Control lights without rewiring
- Turn on coffee maker in morning
- Press garage door button remotely
- Automate fan controls

### **Accessibility**
- Help people with limited mobility
- Reach difficult switches
- Control devices from bed

### **Automation & Hacking**
- Integrate with home automation systems
- Control old electronics
- Prototype smart home ideas
- Test button-based interfaces

### **Fun Projects**
- Automated game controller
- Remote doorbell ringer
- Automated pet feeder trigger
- Wake-up alarm turner-offer

---

## 📱 App Screenshots

> **Note:** Screenshots coming soon! The app features:
> - Clean splash screen with logo
> - Device list for easy pairing
> - Large switch control
> - Angle configuration screen
> - Connection status indicator

---

## 🤝 Contributing

We welcome everyone - whether you're a user with feedback or a developer with code!

### **As a User, You Can:**
- 🐛 Report bugs you find
- 💡 Suggest new features
- 📝 Share how you use DabBot
- ⭐ Star the project if you like it

### **As a Developer, You Can:**
- 🔧 Fix bugs and improve code
- 📚 Improve documentation
- 🎨 Enhance the UI/UX
- 🌐 Add translations
- 🔌 Add new features

[Read Contributing Guidelines →](CONTRIBUTING.md)

---

## 📖 Documentation

### **For Users:**
- [App User Guide](docs/USER_GUIDE.md)
- [Hardware Assembly](docs/HARDWARE_ASSEMBLY.md)
- [Troubleshooting](docs/TROUBLESHOOTING.md)
- [FAQ](docs/FAQ.md)

### **For Developers:**
- [Code Structure](docs/CODE_STRUCTURE.md)
- [Building from Source](docs/BUILD_GUIDE.md)
- [API Documentation](docs/API.md)
- [Contributing Guide](CONTRIBUTING.md)

---

## 🔧 Technical Specs

### **Mobile App**
- **Platform:** Android 7.0+ (API 24+)
- **Language:** Java
- **Size:** ~5 MB
- **Permissions:** Bluetooth only

### **Hardware**
- **Microcontroller:** ESP32 (any variant)
- **Motor:** SG90 9g Servo
- **Connection:** Bluetooth Classic (SPP)
- **Power:** 5V via USB

### **Communication**
- **Protocol:** Bluetooth Serial
- **Range:** ~10 meters (30 feet)
- **Latency:** <100ms response time
- **Command Format:** Simple text commands

---

## 📄 License

This project is **free and open source** under the MIT License.

```
MIT License - You can:
✅ Use commercially
✅ Modify the code
✅ Distribute
✅ Sell derivative products
✅ Use privately

❌ Just include the license and don't hold us liable
```

[Full License Text →](LICENSE)

---

## 🙏 Credits

**Created by the DabBot Community**

- Thanks to the **ESP32 community** for great hardware
- Thanks to **Android Open Source Project**
- Inspired by SwitchBot and similar products
- Built with ❤️ by makers, for makers

---

## 💬 Support & Community

### **Need Help?**
- 🐛 [Report a Bug](https://github.com/yantra-commons/Dab-Bot/issues)
- 💡 [Request a Feature](https://github.com/yantra-commons/Dab-Bot/issues)
- 💬 [Ask Questions](https://github.com/yantra-commons/Dab-Bot/discussions)

### **Stay Connected**
- ⭐ [Star on GitHub](https://github.com/yantra-commons/Dab-Bot)
- 👀 [Watch for Updates](https://github.com/yantra-commons/Dab-Bot)
- 🍴 [Fork the Project](https://github.com/yantra-commons/Dab-Bot/fork)

---

## 🗺️ Roadmap

### **Coming Soon**
- [ ] Multiple device support
- [ ] Scheduled actions/timers
- [ ] Home automation integrations
- [ ] iOS app

### **Ideas for Future**
- [ ] Voice control (Google Assistant, Alexa)
- [ ] MQTT/WiFi support
- [ ] Web dashboard
- [ ] Pre-built hardware kits

Want to help? [See our Contributing Guide](CONTRIBUTING.md)!

---

## 📊 Project Stats

- **Started:** January 2024
- **Current Version:** 2.0
- **Contributors:** Open to all!
- **Cost to Use:** $0 (Free forever)
- **Hardware Cost:** ~$10 DIY
- **Lines of Code:** ~2,000

---

<div align="center">

## ⭐ Show Your Support

If you find DabBot useful, please consider giving it a star!

It helps others discover the project and motivates us to keep improving it.

[![GitHub Stars](https://img.shields.io/github/stars/yantra-commons/Dab-Bot?style=social)](https://github.com/yantra-commons/Dab-Bot)

---

**Made with ❤️ by makers, for makers**

Licensed under MIT • Free Forever • No Tracking • No Ads

[⬆ Back to Top](#-dabbot---smart-switch-controller)

</div>
