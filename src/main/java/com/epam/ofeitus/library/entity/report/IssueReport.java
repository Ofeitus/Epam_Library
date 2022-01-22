package com.epam.ofeitus.library.entity.report;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Issue report bean class.
 */
public class IssueReport {
    private int totalIssuedFrom;
    private int totalIssuedTo;
    private int totalIssuedReservedFrom;
    private int totalIssuedReservedTo;
    private int totalAvailable;
    private int totalReserved;
    private int totalLoaned;
    private List<Date> dynamicsDates;
    private List<Integer> dynamicsValues;

    public IssueReport() {
    }

    public IssueReport(int totalIssuedFrom, int totalIssuedTo, int totalIssuedReservedFrom, int totalIssuedReservedTo, int totalAvailable, int totalReserved, int totalLoaned, List<Date> dynamicsDates, List<Integer> dynamicsValues) {
        this.totalIssuedFrom = totalIssuedFrom;
        this.totalIssuedTo = totalIssuedTo;
        this.totalIssuedReservedFrom = totalIssuedReservedFrom;
        this.totalIssuedReservedTo = totalIssuedReservedTo;
        this.totalAvailable = totalAvailable;
        this.totalReserved = totalReserved;
        this.totalLoaned = totalLoaned;
        this.dynamicsDates = dynamicsDates;
        this.dynamicsValues = dynamicsValues;
    }

    public int getTotalIssuedFrom() {
        return totalIssuedFrom;
    }

    public void setTotalIssuedFrom(int totalIssuedFrom) {
        this.totalIssuedFrom = totalIssuedFrom;
    }

    public int getTotalIssuedTo() {
        return totalIssuedTo;
    }

    public void setTotalIssuedTo(int totalIssuedTo) {
        this.totalIssuedTo = totalIssuedTo;
    }

    public int getTotalIssuedReservedFrom() {
        return totalIssuedReservedFrom;
    }

    public void setTotalIssuedReservedFrom(int totalIssuedReservedFrom) {
        this.totalIssuedReservedFrom = totalIssuedReservedFrom;
    }

    public int getTotalIssuedReservedTo() {
        return totalIssuedReservedTo;
    }

    public void setTotalIssuedReservedTo(int totalIssuedReservedTo) {
        this.totalIssuedReservedTo = totalIssuedReservedTo;
    }

    public int getTotalAvailable() {
        return totalAvailable;
    }

    public void setTotalAvailable(int totalAvailable) {
        this.totalAvailable = totalAvailable;
    }

    public int getTotalReserved() {
        return totalReserved;
    }

    public void setTotalReserved(int totalReserved) {
        this.totalReserved = totalReserved;
    }

    public int getTotalLoaned() {
        return totalLoaned;
    }

    public void setTotalLoaned(int totalLoaned) {
        this.totalLoaned = totalLoaned;
    }

    public List<Date> getDynamicsDates() {
        return dynamicsDates;
    }

    public void setDynamicsDates(List<Date> dynamicsDates) {
        this.dynamicsDates = dynamicsDates;
    }

    public List<Integer> getDynamicsValues() {
        return dynamicsValues;
    }

    public void setDynamicsValues(List<Integer> dynamicsValues) {
        this.dynamicsValues = dynamicsValues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssueReport that = (IssueReport) o;
        return totalIssuedFrom == that.totalIssuedFrom && totalIssuedTo == that.totalIssuedTo && totalIssuedReservedFrom == that.totalIssuedReservedFrom && totalIssuedReservedTo == that.totalIssuedReservedTo && totalAvailable == that.totalAvailable && totalReserved == that.totalReserved && totalLoaned == that.totalLoaned && Objects.equals(dynamicsDates, that.dynamicsDates) && Objects.equals(dynamicsValues, that.dynamicsValues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalIssuedFrom, totalIssuedTo, totalIssuedReservedFrom, totalIssuedReservedTo, totalAvailable, totalReserved, totalLoaned, dynamicsDates, dynamicsValues);
    }

    @Override
    public String toString() {
        return "IssueReport{" +
                "totalIssuedFrom=" + totalIssuedFrom +
                ", totalIssuedTo=" + totalIssuedTo +
                ", totalIssuedReservedFrom=" + totalIssuedReservedFrom +
                ", totalIssuedReservedTo=" + totalIssuedReservedTo +
                ", totalAvailable=" + totalAvailable +
                ", totalReserved=" + totalReserved +
                ", totalLoaned=" + totalLoaned +
                ", datesDynamics=" + dynamicsDates +
                ", issueDynamics=" + dynamicsValues +
                '}';
    }
}
