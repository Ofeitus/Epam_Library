package com.epam.ofeitus.library.entity.report;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * User composition report bean class.
 */
public class UserCompositionReport implements Serializable {
    private int totalCountFrom;
    private int totalCountTo;
    private int adminCountFrom;
    private int adminCountTo;
    private int managerCountFrom;
    private int managerCountTo;
    private int memberCountFrom;
    private int memberCountTo;
    private List<Date> dynamicsDates;
    private List<Integer> dynamicsValues;

    public UserCompositionReport() {
    }

    public UserCompositionReport(int totalCountFrom, int totalCountTo, int adminCountFrom, int adminCountTo, int managerCountFrom, int managerCountTo, int memberCountFrom, int memberCountTo, List<Date> dynamicsDates, List<Integer> dynamicsValues) {
        this.totalCountFrom = totalCountFrom;
        this.totalCountTo = totalCountTo;
        this.adminCountFrom = adminCountFrom;
        this.adminCountTo = adminCountTo;
        this.managerCountFrom = managerCountFrom;
        this.managerCountTo = managerCountTo;
        this.memberCountFrom = memberCountFrom;
        this.memberCountTo = memberCountTo;
        this.dynamicsDates = dynamicsDates;
        this.dynamicsValues = dynamicsValues;
    }

    public int getTotalCountFrom() {
        return totalCountFrom;
    }

    public void setTotalCountFrom(int totalCountFrom) {
        this.totalCountFrom = totalCountFrom;
    }

    public int getTotalCountTo() {
        return totalCountTo;
    }

    public void setTotalCountTo(int totalCountTo) {
        this.totalCountTo = totalCountTo;
    }

    public int getAdminCountFrom() {
        return adminCountFrom;
    }

    public void setAdminCountFrom(int adminCountFrom) {
        this.adminCountFrom = adminCountFrom;
    }

    public int getAdminCountTo() {
        return adminCountTo;
    }

    public void setAdminCountTo(int adminCountTo) {
        this.adminCountTo = adminCountTo;
    }

    public int getManagerCountFrom() {
        return managerCountFrom;
    }

    public void setManagerCountFrom(int managerCountFrom) {
        this.managerCountFrom = managerCountFrom;
    }

    public int getManagerCountTo() {
        return managerCountTo;
    }

    public void setManagerCountTo(int managerCountTo) {
        this.managerCountTo = managerCountTo;
    }

    public int getMemberCountFrom() {
        return memberCountFrom;
    }

    public void setMemberCountFrom(int memberCountFrom) {
        this.memberCountFrom = memberCountFrom;
    }

    public int getMemberCountTo() {
        return memberCountTo;
    }

    public void setMemberCountTo(int memberCountTo) {
        this.memberCountTo = memberCountTo;
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
        UserCompositionReport that = (UserCompositionReport) o;
        return totalCountFrom == that.totalCountFrom && totalCountTo == that.totalCountTo && adminCountFrom == that.adminCountFrom && adminCountTo == that.adminCountTo && managerCountFrom == that.managerCountFrom && managerCountTo == that.managerCountTo && memberCountFrom == that.memberCountFrom && memberCountTo == that.memberCountTo && Objects.equals(dynamicsDates, that.dynamicsDates) && Objects.equals(dynamicsValues, that.dynamicsValues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalCountFrom, totalCountTo, adminCountFrom, adminCountTo, managerCountFrom, managerCountTo, memberCountFrom, memberCountTo, dynamicsDates, dynamicsValues);
    }

    @Override
    public String toString() {
        return "UserCompositionReport{" +
                "totalCountFrom=" + totalCountFrom +
                ", totalCountTo=" + totalCountTo +
                ", adminCountFrom=" + adminCountFrom +
                ", adminCountTo=" + adminCountTo +
                ", managerCountFrom=" + managerCountFrom +
                ", managerCountTo=" + managerCountTo +
                ", memberCountFrom=" + memberCountFrom +
                ", memberCountTo=" + memberCountTo +
                ", dynamicsDates=" + dynamicsDates +
                ", dynamicsValues=" + dynamicsValues +
                '}';
    }
}
