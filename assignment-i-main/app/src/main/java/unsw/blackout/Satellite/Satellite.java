package unsw.blackout.Satellite;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import unsw.utils.Angle;
import unsw.utils.MathsHelper;
import unsw.blackout.Device.Device;
import unsw.blackout.File.*;
import unsw.response.models.EntityInfoResponse;
import unsw.response.models.FileInfoResponse;

public abstract class Satellite {
    private String satelliteId;
    private String type;
    private double height;
    private Angle position;
    private int maxRange;
    private int speed;
    private int availableStorage;
    private int availableFiles;
    private int bandwidthIn;
    private int bandwidthOut;
    private int maxStorage;
    private int maxFiles;
    private boolean direction; //true for clockwise or otherwise
    private Map<String, File> files = new HashMap<>();
    private int downloadNum;
    private int uploadNum;

    public Satellite(double height, Angle position, String satelliteId, String type, int maxRange, int speed,
            int bandwidthIn, int bandwidthOut) {
        this.height = height;
        this.position = position;
        this.satelliteId = satelliteId;
        this.type = type;
        this.maxRange = maxRange;
        this.speed = speed;
        this.availableStorage = 0;
        this.bandwidthIn = bandwidthIn;
        this.bandwidthOut = bandwidthOut;
        this.direction = true;
        this.downloadNum = 0;
        this.uploadNum = 0;
        this.availableFiles = 0;
        this.maxStorage = 0;
        this.maxFiles = 0;
    }

    public int getAvailableFiles() {
        return availableFiles;
    }

    public void setAvailableFiles(int availableFiles) {
        this.availableFiles = availableFiles;
    }

    public int getBandwidthIn() {
        return bandwidthIn;
    }

    public void setBandwidthIn(int bandwidthIn) {
        this.bandwidthIn = bandwidthIn;
    }

    public int getBandwidthOut() {
        return bandwidthOut;
    }

    public void setBandwidthOut(int bandwidthOut) {
        this.bandwidthOut = bandwidthOut;
    }

    public int getMaxStorage() {
        return maxStorage;
    }

    public void setMaxStorage(int maxStorage) {
        this.maxStorage = maxStorage;
    }

    public int getMaxFiles() {
        return maxFiles;
    }

    public void setMaxFiles(int maxFiles) {
        this.maxFiles = maxFiles;
    }

    public Map<String, File> getFiles() {
        return files;
    }

    public void setFiles(Map<String, File> files) {
        this.files = files;
    }

    public int getDownloadNum() {
        return downloadNum;
    }

    public void setDownloadNum(int downloadNum) {
        this.downloadNum = downloadNum;
    }

    public int getUploadNum() {
        return uploadNum;
    }

    public void setUploadNum(int uploadNum) {
        this.uploadNum = uploadNum;
    }

    public int getAvailableStorage() {
        return availableStorage;
    }

    public void setAvailableStorage(int availableStorage) {
        this.availableStorage = availableStorage;
    }

    public int getbandwidthIn() {
        return bandwidthIn;
    }

    public void setbandwidthIn(int bandwidthIn) {
        this.bandwidthIn = bandwidthIn;
    }

    public int getbandwidthOut() {
        return bandwidthOut;
    }

    public void setbandwidthOut(int bandwidthOut) {
        this.bandwidthOut = bandwidthOut;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(int maxRange) {
        this.maxRange = maxRange;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getSatelliteId() {
        return satelliteId;
    }

    public void setSatelliteId(String satelliteId) {
        this.satelliteId = satelliteId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
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

    public EntityInfoResponse getSatelliteInfoResponse() {
        Map<String, FileInfoResponse> fileSatelliteInfoResponseMap = files.values().stream()
                .collect(Collectors.toMap(File::getFileName, file -> new FileInfoResponse(file.getFileName(),
                        file.getContent(), file.getFileSize(), file.isTransferComplete())));
        return new EntityInfoResponse(satelliteId, position, height, type, fileSatelliteInfoResponseMap);
    }

    public boolean getDirection() {
        return direction;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public abstract void moveToNextPos();

    public boolean canCommunicateWith(Device device) {
        if (device.getType().equals("DesktopDevice") && this.getType().equals("StandardSatellite")) {
            return false;
        }
        return MathsHelper.isVisible(this.getHeight(), this.getPosition(), device.getPosition()) && MathsHelper
                .getDistance(this.getHeight(), this.getPosition(), device.getPosition()) <= this.getMaxRange();
    }

    public boolean canCommunicateWith(Satellite satellite) {
        return MathsHelper.isVisible(satellite.getHeight(), satellite.getPosition(), this.getPosition()) && MathsHelper
                .getDistance(satellite.getHeight(), satellite.getPosition(), this.getPosition()) <= this.getMaxRange();
    }
}
