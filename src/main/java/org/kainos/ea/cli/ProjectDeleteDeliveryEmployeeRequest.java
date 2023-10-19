package org.kainos.ea.cli;

import java.sql.Date;

public class ProjectDeleteDeliveryEmployeeRequest {
    private Date startedOnDate;
    private Date endedOnDate;

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
