package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Switch;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * DabBot Controller - Main Activity
 *
 * Apple-inspired design with Bluetooth device discovery
 * Features configurable ON/OFF angles for switch control
 *
 * @author DabBot Open Source Community
 * @version 2.0
 */
public class MainActivity extends AppCompatActivity {

    // Bluetooth components
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothSocket bluetoothSocket;
    private OutputStream outputStream;
    private BluetoothDevice selectedDevice;

    // Standard UUID for SPP (Serial Port Profile)
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    // Request codes
    private static final int REQUEST_ENABLE_BT = 1;
    private static final int REQUEST_BLUETOOTH_PERMISSIONS = 2;

    // UI Components - Device Discovery
    private LinearLayout layoutDeviceDiscovery;
    private ScrollView layoutMainControl;
    private RecyclerView recyclerDevices;
    private Button btnRefreshDevices;
    private TextView tvDiscoveryStatus;
    private DeviceListAdapter deviceAdapter;

    // UI Components - Main Control
    private TextView tvConnectedDevice;
    private ImageButton btnDisconnect;
    private Switch switchControl;
    private TextView tvSwitchStatus;
    private LinearLayout btnConfigureAngles;

    // UI Components - Angle Configuration
    private LinearLayout layoutAngleConfig;
    private SeekBar seekBarOffAngle;
    private SeekBar seekBarOnAngle;
    private TextView tvOffAngleValue;
    private TextView tvOnAngleValue;
    private Button btnSaveAngles;
    private Button btnCancelConfig;

    // Connection state
    private boolean isConnected = false;

    // Configurable angles (stored in preferences)
    private int offAngle = 0;    // Default OFF position
    private int onAngle = 90;    // Default ON position
    private SharedPreferences preferences;

    // Current switch state
    private boolean isSwitchOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize preferences for storing angles
        preferences = getSharedPreferences("DabBotPrefs", MODE_PRIVATE);
        loadSavedAngles();

        // Initialize Bluetooth adapter
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // Check if device supports Bluetooth
        if (bluetoothAdapter == null) {
            showToast("Device doesn't support Bluetooth");
            finish();
            return;
        }

        // Initialize UI components
        initializeViews();

        // Set up listeners
        setupListeners();

        // Check and request permissions
        checkBluetoothPermissions();

        // Show device discovery screen initially
        showDeviceDiscovery();
    }

    /**
     * Initialize all UI components
     */
    private void initializeViews() {
        // Device Discovery Views
        layoutDeviceDiscovery = findViewById(R.id.layoutDeviceDiscovery);
        layoutMainControl = findViewById(R.id.layoutMainControl);
        recyclerDevices = findViewById(R.id.recyclerDevices);
        btnRefreshDevices = findViewById(R.id.btnRefreshDevices);
        tvDiscoveryStatus = findViewById(R.id.tvDiscoveryStatus);

        // Main Control Views
        tvConnectedDevice = findViewById(R.id.tvConnectedDevice);
        btnDisconnect = findViewById(R.id.btnDisconnect);
        switchControl = findViewById(R.id.switchControl);
        tvSwitchStatus = findViewById(R.id.tvSwitchStatus);
        btnConfigureAngles = findViewById(R.id.btnConfigureAngles);

        // Angle Configuration Views
        layoutAngleConfig = findViewById(R.id.layoutAngleConfig);
        seekBarOffAngle = findViewById(R.id.seekBarOffAngle);
        seekBarOnAngle = findViewById(R.id.seekBarOnAngle);
        tvOffAngleValue = findViewById(R.id.tvOffAngleValue);
        tvOnAngleValue = findViewById(R.id.tvOnAngleValue);
        btnSaveAngles = findViewById(R.id.btnSaveAngles);
        btnCancelConfig = findViewById(R.id.btnCancelConfig);

        // Setup RecyclerView for device list
        recyclerDevices.setLayoutManager(new LinearLayoutManager(this));
        deviceAdapter = new DeviceListAdapter(new ArrayList<>(), this::onDeviceSelected);
        recyclerDevices.setAdapter(deviceAdapter);

        // Initialize seekbars
        seekBarOffAngle.setMax(180);
        seekBarOnAngle.setMax(180);
        seekBarOffAngle.setProgress(offAngle);
        seekBarOnAngle.setProgress(onAngle);
        updateAngleLabels();
    }

    /**
     * Set up all click listeners and callbacks
     */
    private void setupListeners() {
        // Refresh devices button
        btnRefreshDevices.setOnClickListener(v -> scanForDevices());

        // Disconnect button
        btnDisconnect.setOnClickListener(v -> {
            disconnectBluetooth();
            showDeviceDiscovery();
        });

        // Switch control - toggles between OFF and ON angles
        switchControl.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isConnected) {
                isSwitchOn = isChecked;
                int targetAngle = isChecked ? onAngle : offAngle;
                sendServoCommand(targetAngle);
                updateSwitchStatus();
            } else {
                // Prevent toggle if not connected
                buttonView.setChecked(!isChecked);
                showToast("Not connected to device");
            }
        });

        // Configure angles button
        btnConfigureAngles.setOnClickListener(v -> showAngleConfiguration());

        // Angle configuration seekbars
        seekBarOffAngle.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvOffAngleValue.setText(progress + "°");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        seekBarOnAngle.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvOnAngleValue.setText(progress + "°");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Save angles button
        btnSaveAngles.setOnClickListener(v -> saveAngles());

        // Cancel configuration button
        btnCancelConfig.setOnClickListener(v -> hideAngleConfiguration());
    }

    /**
     * Check and request Bluetooth permissions
     */
    private void checkBluetoothPermissions() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            // Android 12 and above
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{
                                Manifest.permission.BLUETOOTH_CONNECT,
                                Manifest.permission.BLUETOOTH_SCAN
                        },
                        REQUEST_BLUETOOTH_PERMISSIONS);
            }
        } else {
            // Android 11 and below
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{
                                Manifest.permission.BLUETOOTH,
                                Manifest.permission.BLUETOOTH_ADMIN,
                                Manifest.permission.ACCESS_FINE_LOCATION
                        },
                        REQUEST_BLUETOOTH_PERMISSIONS);
            }
        }
    }

    /**
     * Show device discovery screen
     */
    private void showDeviceDiscovery() {
        layoutDeviceDiscovery.setVisibility(View.VISIBLE);
        layoutMainControl.setVisibility(View.GONE);
        layoutAngleConfig.setVisibility(View.GONE);
        scanForDevices();
    }

    /**
     * Show main control screen
     */
    private void showMainControl() {
        layoutDeviceDiscovery.setVisibility(View.GONE);
        layoutMainControl.setVisibility(View.VISIBLE);
        layoutAngleConfig.setVisibility(View.GONE);

        if (selectedDevice != null) {
            try {
                String deviceName = selectedDevice.getName();
                tvConnectedDevice.setText(deviceName != null ? deviceName : "Unknown Device");
            } catch (SecurityException e) {
                tvConnectedDevice.setText("Connected Device");
            }
        }
        updateSwitchStatus();
    }

    /**
     * Show angle configuration screen
     */
    private void showAngleConfiguration() {
        layoutAngleConfig.setVisibility(View.VISIBLE);
        seekBarOffAngle.setProgress(offAngle);
        seekBarOnAngle.setProgress(onAngle);
        updateAngleLabels();
    }

    /**
     * Hide angle configuration screen
     */
    private void hideAngleConfiguration() {
        layoutAngleConfig.setVisibility(View.GONE);
    }

    /**
     * Scan for paired Bluetooth devices
     */
    private void scanForDevices() {
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT)
                    == PackageManager.PERMISSION_GRANTED) {
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
            return;
        }

        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT)
                    == PackageManager.PERMISSION_GRANTED) {

                Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
                List<BluetoothDevice> deviceList = new ArrayList<>();

                // Filter for ESP32/DabBot devices (or show all)
                for (BluetoothDevice device : pairedDevices) {
                    String name = device.getName();
                    if (name != null && (name.contains("ESP") || name.contains("DabBot") ||
                            name.contains("HC-") || name.contains("BT"))) {
                        deviceList.add(device);
                    }
                }

                // If no filtered devices, show all paired devices
                if (deviceList.isEmpty()) {
                    deviceList.addAll(pairedDevices);
                }

                deviceAdapter.updateDevices(deviceList);

                if (deviceList.isEmpty()) {
                    tvDiscoveryStatus.setText("\n\nNo paired devices found.\nPlease pair your DabBot in Bluetooth settings.");
                } else {
                    tvDiscoveryStatus.setText("Select a device to connect:");
                }
            }
        } catch (SecurityException e) {
            showToast("Bluetooth permission denied");
        }
    }

    /**
     * Called when user selects a device from the list
     */
    private void onDeviceSelected(BluetoothDevice device) {
        selectedDevice = device;
        connectToDevice(device);
    }

    /**
     * Connect to selected Bluetooth device
     */
    private void connectToDevice(BluetoothDevice device) {
        showToast("Connecting...");

        new Thread(() -> {
            try {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT)
                        == PackageManager.PERMISSION_GRANTED) {

                    // Create socket and connect
                    bluetoothSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
                    bluetoothSocket.connect();
                    outputStream = bluetoothSocket.getOutputStream();

                    isConnected = true;

                    // Update UI on main thread
                    runOnUiThread(() -> {
                        showToast("Connected!");
                        showMainControl();
                        // Set switch to OFF position on connect
                        switchControl.setChecked(false);
                        sendServoCommand(offAngle);
                    });
                }
            } catch (IOException e) {
                runOnUiThread(() -> {
                    showToast("Connection failed: " + e.getMessage());
                    isConnected = false;
                });
            } catch (SecurityException e) {
                runOnUiThread(() -> showToast("Permission denied"));
            }
        }).start();
    }

    /**
     * Disconnect from Bluetooth device
     */
    private void disconnectBluetooth() {
        try {
            if (outputStream != null) {
                outputStream.close();
            }
            if (bluetoothSocket != null) {
                bluetoothSocket.close();
            }
            isConnected = false;
            showToast("Disconnected");
        } catch (IOException e) {
            showToast("Error disconnecting: " + e.getMessage());
        }
    }

    /**
     * Send servo angle command to ESP32
     */
    private void sendServoCommand(int angle) {
        if (!isConnected || outputStream == null) {
            return;
        }

        new Thread(() -> {
            try {
                String command = "ANGLE:" + angle + "\n";
                outputStream.write(command.getBytes());
                outputStream.flush();
            } catch (IOException e) {
                runOnUiThread(() -> {
                    showToast("Error sending command");
                    isConnected = false;
                });
            }
        }).start();
    }

    /**
     * Update switch status text
     */
    private void updateSwitchStatus() {
        if (isSwitchOn) {
            tvSwitchStatus.setText("ON (" + onAngle + "°)");
        } else {
            tvSwitchStatus.setText("OFF (" + offAngle + "°)");
        }
    }

    /**
     * Update angle configuration labels
     */
    private void updateAngleLabels() {
        tvOffAngleValue.setText(offAngle + "°");
        tvOnAngleValue.setText(onAngle + "°");
    }

    /**
     * Save configured angles
     */
    private void saveAngles() {
        offAngle = seekBarOffAngle.getProgress();
        onAngle = seekBarOnAngle.getProgress();

        // Save to preferences
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("offAngle", offAngle);
        editor.putInt("onAngle", onAngle);
        editor.apply();

        updateSwitchStatus();
        hideAngleConfiguration();
        showToast("Angles saved");

        // Apply current switch state with new angles
        int currentAngle = isSwitchOn ? onAngle : offAngle;
        sendServoCommand(currentAngle);
    }

    /**
     * Load saved angles from preferences
     */
    private void loadSavedAngles() {
        offAngle = preferences.getInt("offAngle", 0);
        onAngle = preferences.getInt("onAngle", 90);
    }

    /**
     * Show toast message
     */
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disconnectBluetooth();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_BLUETOOTH_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                scanForDevices();
            } else {
                showToast("Bluetooth permissions required");
            }
        }
    }
}