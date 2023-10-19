package org.kainos.ea.cli;

import java.sql.Date;

public class ProjectDeliveryEmployee {
    private int projectId;
    private int deliveryEmployeeId;
    private Date startedOnDate;
    private Date endedOnDate;

    public ProjectDeliveryEmployee(int projectId, int deliveryEmployeeId, Date startedOnDate, Date endedOnDate) {
        this.projectId = projectId;
        this.deliveryEmployeeId = deliveryEmployeeId;
        this.startedOnDate = startedOnDate;
        this.endedOnDate = endedOnDate;
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

    public Date getEndedOnDate() {
        return endedOnDate;
    }

    public void setEndedOnDate(Date endedOnDate) {
        this.endedOnDate = endedOnDate;
    }
}
