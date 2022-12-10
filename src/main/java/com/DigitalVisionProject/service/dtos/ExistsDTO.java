package com.DigitalVisionProject.service.dtos;

public class ExistsDTO {

    private boolean exists;

    public ExistsDTO(boolean exists) {
        this.exists = exists;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }
}
