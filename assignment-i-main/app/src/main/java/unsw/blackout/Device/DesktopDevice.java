package unsw.blackout.Device;

import unsw.utils.Angle;

public class DesktopDevice extends Device {
    public DesktopDevice(String deviceId, String type, Angle position) {
        super(deviceId, type, position, 200000);
    }
}
