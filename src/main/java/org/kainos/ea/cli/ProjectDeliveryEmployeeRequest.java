package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class ProjectDeliveryEmployeeRequest {

    private int projectId;
    private int deliveryEmployeeId;
    private Date startedOnDate;

    @JsonCreator
    public ProjectDeliveryEmployeeRequest(@JsonProperty("deliveryEmployeeId") int deliveryEmployeeId,
                                          @JsonProperty("startedOnDate") Date startedOnDate) {
        this.deliveryEmployeeId = deliveryEmployeeId;
        this.startedOnDate = startedOnDate;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getDeliveryEmployeeId() {
        return deliveryEmployeeId;
    }

    public void setDeliveryEmployeeId(int deliveryEmployeeId) {
        this.deliveryEmployeeId = deliveryEmployeeId;
    }

    public Date getStartedOnDate() {
        return startedOnDate;
    }

    public void setStartedOnDate(Date startedOnDate) {
        this.startedOnDate = startedOnDate;
    }
}
