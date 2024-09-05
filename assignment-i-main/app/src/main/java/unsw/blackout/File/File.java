package unsw.blackout.File;


public class File {
    private String fileName;
    private String content;
    private int fileSize;
    private int transferSize;
    private String transferContent;
    private int availableSize;
    private String availableContent;
    private String sender;
    private String receiver;
    public File(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;
        this.fileSize = content.length();
        this.transferSize = 0;
        this.transferContent = "";
        this.availableSize = 0;
        this.availableContent = "";
        this.sender = "";
        this.receiver = "";
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public int getTransferSize() {
        return transferSize;
    }

    public void setTransferSize(int transferSize) {
        this.transferSize = transferSize;
    }

    public String getTransferContent() {
        return transferContent;
    }

    public void setTransferContent(String transferContent) {
        this.transferContent = transferContent;
    }

    public int getAvailableSize() {
        return availableContent.length();
    }

    public void setAvailableSize(int availableSize) {
        if (content.length() < availableSize) {
            availableSize = content.length();
        }
        this.availableSize = availableSize;
        setAvailableContent();
    }

    public String getAvailableContent() {
        return availableContent;
    }

    public boolean isTransferComplete() {
        return content.equals(availableContent);
    }

    public void setAvailableContent() {
        availableContent = content.substring(0, availableSize);
    }

    public void setAvailableContent(String content) {
        this.availableContent = content;
    }

    public String getLeftContent() {
        return this.getContent().substring(getAvailableSize());
    }
}
