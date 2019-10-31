package com.main.protocol;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

public class Hello  implements Serializable {
    private long fileSize;
    private String fileName;

    public void setFileName(String fileName) throws UnsupportedEncodingException {
        this.fileName = new String(fileName.getBytes("UTF-8"), "UTF-8");
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
    public long getFileSize() { return fileSize; }
    public String getFileName() { return fileName; }
}
