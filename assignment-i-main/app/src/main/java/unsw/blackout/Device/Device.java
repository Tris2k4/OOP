package unsw.blackout.Device;

import unsw.utils.Angle;
import unsw.utils.MathsHelper;

import static unsw.utils.MathsHelper.RADIUS_OF_JUPITER;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import unsw.blackout.File.*;
import unsw.blackout.Satellite.Satellite;
import unsw.response.models.EntityInfoResponse;
import unsw.response.models.FileInfoResponse;

public abstract class Device {
    private String deviceId;
    private String type;
    private Angle position;
    private Map<String, File> files = new HashMap<>();
    private int maxRange;
    private double height;

    public Device(String deviceId, String type, Angle position, int maxRange) {
        this.deviceId = deviceId;
        this.type = type;
        this.position = position;
        this.maxRange = maxRange;
        this.height = RADIUS_OF_JUPITER;
    }

    public Map<String, File> getFiles() {
        return files;
    }

    public void setFiles(Map<String, File> files) {
        this.files = files;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(int maxRange) {
        this.maxRange = maxRange;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Angle getPosition() {
        return position;
    }

    public void setPosition(Angle position) {
        this.position = position;
    }

    public void addFile(File file) {
        files.put(file.getFileName(), file);
    }

    public File getFile(String fileName) {
        return files.get(fileName);
    }

    public String getContentInFile(String fileName) {
        return this.getFile(fileName).getContent();
    }

    public EntityInfoResponse getDeviceInfoResponse() {
        Map<String, FileInfoResponse> fileDeviceInfoResponseMap = files.values().stream()
                .collect(Collectors.toMap(File::getFileName, file -> new FileInfoResponse(file.getFileName(),
                        file.getContent(), file.getFileSize(), file.isTransferComplete())));
        return new EntityInfoResponse(deviceId, position, height, type, fileDeviceInfoResponseMap);
    }

    public boolean canCommunicateWith(Satellite satellite) {
        return MathsHelper.isVisible(satellite.getHeight(), satellite.getPosition(), this.getPosition()) && MathsHelper
                .getDistance(satellite.getHeight(), satellite.getPosition(), this.getPosition()) <= this.getMaxRange();
    }
}
