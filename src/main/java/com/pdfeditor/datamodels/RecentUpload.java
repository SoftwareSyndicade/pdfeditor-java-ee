package com.pdfeditor.datamodels;

import java.time.Instant;
import java.time.ZonedDateTime;

public class RecentUpload {
    private int ID;
    private String FILE_NAME;
    private ZonedDateTime UPLOADED_ON;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFILE_NAME() {
        return FILE_NAME;
    }

    public void setFILE_NAME(String FILE_NAME) {
        this.FILE_NAME = FILE_NAME;
    }

    public ZonedDateTime getUPLOADED_ON() {
        return UPLOADED_ON;
    }

    public void setUPLOADED_ON(ZonedDateTime UPLOADED_ON) {
        this.UPLOADED_ON = UPLOADED_ON;
    }
}
