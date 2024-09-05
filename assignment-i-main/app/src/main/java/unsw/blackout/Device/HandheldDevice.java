package unsw.blackout.Device;

import unsw.utils.Angle;

public class HandheldDevice extends Device {
    public HandheldDevice(String deviceId, String type, Angle position) {
        super(deviceId, type, position, 50000);
    }
}
