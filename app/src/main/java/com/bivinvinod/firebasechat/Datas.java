package com.bivinvinod.firebasechat;

public class Datas {
    String dataId;
    String dataName;
    String dataValue;

    public Datas() {
    }

    public Datas(String dataId, String dataName, String dataValue) {
        this.dataId = dataId;
        this.dataName = dataName;
        this.dataValue = dataValue;
    }

    public String getDataId() {
        return dataId;
    }

    public String getDataName() {
        return dataName;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }
}
