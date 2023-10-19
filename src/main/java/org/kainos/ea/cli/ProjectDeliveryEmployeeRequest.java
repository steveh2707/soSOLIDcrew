package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class ProjectDeliveryEmployeeRequest {

    private int projectId;
    private int[] deliveryEmployeeIds;
    private Date startedOnDate;

    @JsonCreator
    public ProjectDeliveryEmployeeRequest(@JsonProperty("deliveryEmployeeIds") int[] deliveryEmployeeIds,
                                          @JsonProperty("startedOnDate") Date startedOnDate) {
        this.deliveryEmployeeIds = deliveryEmployeeIds;
        this.startedOnDate = startedOnDate;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int[] getDeliveryEmployeeIds() {
        return deliveryEmployeeIds;
    }

    public void setDeliveryEmployeeIds(int[] deliveryEmployeeIds) {
        this.deliveryEmployeeIds = deliveryEmployeeIds;
    }

    public Date getStartedOnDate() {
        return startedOnDate;
    }

    public void setStartedOnDate(Date startedOnDate) {
        this.startedOnDate = startedOnDate;
    }
}
