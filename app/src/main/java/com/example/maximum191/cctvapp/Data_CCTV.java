package com.example.maximum191.cctvapp;

/**
 * Created by Maximum191 on 12/12/2560.
 */

public class Data_CCTV {

    String dataId;
    String dataName;
    String dataAddress;
    String dataOwner;
    String dataNumber;
    String dataLat;
    String dataLng;
    String dataType;
    public Data_CCTV(){}

    public Data_CCTV(String dataId, String dataName, String dataAddress, String dataOwner, String dataNumber, String dataLat, String dataLng, String dataType) {
        this.dataId = dataId;
        this.dataName = dataName;
        this.dataAddress = dataAddress;
        this.dataOwner = dataOwner;
        this.dataNumber = dataNumber;
        this.dataLat = dataLat;
        this.dataLng = dataLng;
        this.dataType = dataType;
    }

    public String getDataId() {
        return dataId;
    }

    public String getDataName() {
        return dataName;
    }

    public String getDataAddress() {
        return dataAddress;
    }

    public String getDataOwner() {
        return dataOwner;
    }

    public String getDataNumber() {
        return dataNumber;
    }

    public String getDataLat() {
        return dataLat;
    }

    public String getDataLng() {
        return dataLng;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public void setDataAddress(String dataAddress) {
        this.dataAddress = dataAddress;
    }

    public void setDataOwner(String dataOwner) {
        this.dataOwner = dataOwner;
    }

    public void setDataNumber(String dataNumber) {
        this.dataNumber = dataNumber;
    }

    public void setDataLat(String dataLat) {
        this.dataLat = dataLat;
    }

    public void setDataLng(String dataLng) {
        this.dataLng = dataLng;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
