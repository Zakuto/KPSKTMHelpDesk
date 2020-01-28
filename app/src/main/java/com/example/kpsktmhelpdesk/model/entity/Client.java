package com.example.kpsktmhelpdesk.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "client_table")
public class Client {

    @PrimaryKey(autoGenerate = true)
    private int clientId;
    private String clientName;
    private String department;
    private String position;
    private String reportStatus;

    private String savedClientReportTime;

    @ColumnInfo(name = "problem_specifications")
    private String probSpec;

    @ColumnInfo(name = "problem_details")
    private String probDetails;

    public Client(String clientName, String department, String position, String probSpec, String probDetails, String savedClientReportTime, String reportStatus) {
        this.clientName = clientName;
        this.department = department;
        this.position = position;
        this.probSpec = probSpec;
        this.probDetails = probDetails;
        this.savedClientReportTime = savedClientReportTime;
        this.reportStatus =reportStatus;
    }

    public void setClientId(int clientId){
        this.clientId = clientId;
    }
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setProbSpec(String probSpec) {
        this.probSpec = probSpec;
    }

    public void setProbDetails(String probDetails) {
        this.probDetails = probDetails;
    }

    public void setSavedClientReportTime(String savedClientReportTime) {
        this.savedClientReportTime = savedClientReportTime;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    public int getClientId() {
        return clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public String getDepartment() {
        return department;
    }

    public String getPosition() {
        return position;
    }

    public String getProbSpec() {
        return probSpec;
    }

    public String getProbDetails() {
        return probDetails;
    }

    public String getSavedClientReportTime(){
        return savedClientReportTime;
    }

    public String getReportStatus() {
        return reportStatus;
    }
}
