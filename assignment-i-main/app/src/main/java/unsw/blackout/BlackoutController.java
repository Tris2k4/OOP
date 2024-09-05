package unsw.blackout;
import java.util.*;
import unsw.blackout.Device.*;
import unsw.blackout.Satellite.*;
import unsw.blackout.File.*;
import unsw.response.models.EntityInfoResponse;
import unsw.utils.Angle;

/**
 * The controller for the Blackout system.
 *
 * WARNING: Do not move this file or modify any of the existing method
 * signatures
 */
public class BlackoutController {
    private Map<String, Device> devices = new HashMap<>();
    private Map<String, Satellite> satellites = new HashMap<>();

    public void createDevice(String deviceId, String type, Angle position) {
        // TODO: Task 1a)
        switch (type) {
        case "DesktopDevice":
            devices.put(deviceId, new DesktopDevice(deviceId, type, position));
            break;
        case "HandheldDevice":
            devices.put(deviceId, new HandheldDevice(deviceId, type, position));
            break;
        case "LaptopDevice":
            devices.put(deviceId, new LaptopDevice(deviceId, type, position));
            break;
        default:
            return;
        }
    }

    public void removeDevice(String deviceId) {
        // TODO: Task 1b)
        devices.remove(deviceId);
    }

    public void createSatellite(String satelliteId, String type, double height, Angle position) {
        // TODO: Task 1c)
        switch (type) {
        case "StandardSatellite":
            satellites.put(satelliteId, new StandardSatellite(height, position, satelliteId, type));
            break;
        case "TeleportingSatellite":
            satellites.put(satelliteId, new TeleportingSatellite(height, position, satelliteId, type));
            break;
        case "RelaySatellite":
            satellites.put(satelliteId, new RelaySatellite(height, position, satelliteId, type));
            break;
        default:
            return;
        }
    }

    public void removeSatellite(String satelliteId) {
        // TODO: Task 1d)
        satellites.remove(satelliteId);
    }

    public List<String> listDeviceIds() {
        // TODO: Task 1e)
        return new ArrayList<>(devices.keySet());
    }

    public List<String> listSatelliteIds() {
        // TODO: Task 1f)
        return new ArrayList<>(satellites.keySet());
    }

    public void addFileToDevice(String deviceId, String filename, String content) {
        // TODO: Task 1g)
        File file = new File(filename, content);
        file.setAvailableSize(content.length());
        Device device = devices.get(deviceId);
        device.addFile(file);
    }

    public EntityInfoResponse getInfo(String id) {
        // TODO: Task 1h)
        Device device = devices.get(id);
        Satellite satellite = satellites.get(id);
        return (device != null ? device.getDeviceInfoResponse() : satellite.getSatelliteInfoResponse());
    }

    public void simulate() {
        // TODO: Task 2a)
        satellites.values().forEach(Satellite::moveToNextPos);
    }

    /**
     * Simulate for the specified number of minutes. You shouldn't need to modify
     * this function.
     */
    public void simulate(int numberOfMinutes) {
        for (int i = 0; i < numberOfMinutes; i++) {
            simulate();
        }
    }

    public List<String> communicableEntitiesInRange(String id) {
        Set<String> entitiesInRange = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Device device = devices.get(id);
        Satellite satellite = satellites.get(id);

        if (device != null) {
            queue.add(id);
            visited.add(id);

            while (!queue.isEmpty()) {
                String currentId = queue.poll();
                Device currentDevice = devices.get(currentId);
                Satellite currentSatellite = satellites.get(currentId);

                if (currentDevice != null) {
                    for (Satellite s : satellites.values()) {
                        if (!visited.contains(s.getSatelliteId()) && currentDevice.canCommunicateWith(s)) {
                            entitiesInRange.add(s.getSatelliteId());
                            if (s.getType().equals("RelaySatellite")) {
                                queue.add(s.getSatelliteId());
                                visited.add(s.getSatelliteId());
                            }
                        }
                    }
                } else if (currentSatellite != null) {
                    for (Satellite s : satellites.values()) {
                        if (!visited.contains(s.getSatelliteId()) && currentSatellite.canCommunicateWith(s)) {
                            entitiesInRange.add(s.getSatelliteId());
                            queue.add(s.getSatelliteId());
                            visited.add(s.getSatelliteId());
                        }
                    }
                    for (Device d : devices.values()) {
                        if (!visited.contains(d.getDeviceId()) && currentSatellite.canCommunicateWith(d)) {
                            entitiesInRange.add(d.getDeviceId());
                            visited.add(d.getDeviceId());
                        }
                    }
                }
            }
        } else if (satellite != null) {
            queue.add(id);
            visited.add(id);

            while (!queue.isEmpty()) {
                String currentId = queue.poll();
                Satellite currentSatellite = satellites.get(currentId);

                for (Satellite s : satellites.values()) {
                    if (!visited.contains(s.getSatelliteId()) && currentSatellite.canCommunicateWith(s)) {
                        entitiesInRange.add(s.getSatelliteId());
                        queue.add(s.getSatelliteId());
                        visited.add(s.getSatelliteId());
                    }
                }

                for (Device d : devices.values()) {
                    if (!visited.contains(d.getDeviceId()) && currentSatellite.canCommunicateWith(d)) {
                        entitiesInRange.add(d.getDeviceId());
                        visited.add(d.getDeviceId());
                    }
                }
            }
        }
        entitiesInRange.remove(id); // Remove the original id from the result
        return new ArrayList<>(entitiesInRange);
    }

    public void sendFile(String fileName, String fromId, String toId) throws FileTransferException {

    }

    public void createDevice(String deviceId, String type, Angle position, boolean isMoving) {
        createDevice(deviceId, type, position);
        // TODO: Task 3
    }

    public void createSlope(int startAngle, int endAngle, int gradient) {
        // TODO: Task 3
        // If you are not completing Task 3 you can leave this method blank :)
    }
}
