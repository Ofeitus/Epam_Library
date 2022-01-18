package com.epam.ofeitus.library.entity.report;

import java.util.List;
import java.util.Objects;

public class IssueReport {
    private int totalIssued;
    private int reserved;
    private List<Integer> issueDynamics;

    public IssueReport() {
    }

    public IssueReport(int totalIssued, int reserved, List<Integer> issueDynamics) {
        this.totalIssued = totalIssued;
        this.reserved = reserved;
        this.issueDynamics = issueDynamics;
    }

    public int getTotalIssued() {
        return totalIssued;
    }

    public void setTotalIssued(int totalIssued) {
        this.totalIssued = totalIssued;
    }

    public int getReserved() {
        return reserved;
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
    }

    public List<Integer> getIssueDynamics() {
        return issueDynamics;
    }

    public void setIssueDynamics(List<Integer> issueDynamics) {
        this.issueDynamics = issueDynamics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssueReport that = (IssueReport) o;
        return totalIssued == that.totalIssued && reserved == that.reserved && Objects.equals(issueDynamics, that.issueDynamics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalIssued, reserved, issueDynamics);
    }

    @Override
    public String toString() {
        return "IssueReport{" +
                "totalIssued=" + totalIssued +
                ", reserved=" + reserved +
                ", issueDynamics=" + issueDynamics +
                '}';
    }
}
