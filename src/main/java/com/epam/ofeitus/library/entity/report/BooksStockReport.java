package com.epam.ofeitus.library.entity.report;

import java.util.List;
import java.util.Objects;

public class BooksStockReport {
    private int totalCountFrom;
    private int totalCountTo;
    private List<Integer> countByCategoryFrom;
    private List<Integer> countByCategoryTo;

    public BooksStockReport() {
    }

    public BooksStockReport(int totalCountFrom, int totalCountTo, List<Integer> countByCategoryFrom, List<Integer> countByCategoryTo) {
        this.totalCountFrom = totalCountFrom;
        this.totalCountTo = totalCountTo;
        this.countByCategoryFrom = countByCategoryFrom;
        this.countByCategoryTo = countByCategoryTo;
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

    public List<Integer> getCountByCategoryFrom() {
        return countByCategoryFrom;
    }

    public void setCountByCategoryFrom(List<Integer> countByCategoryFrom) {
        this.countByCategoryFrom = countByCategoryFrom;
    }

    public List<Integer> getCountByCategoryTo() {
        return countByCategoryTo;
    }

    public void setCountByCategoryTo(List<Integer> countByCategoryTo) {
        this.countByCategoryTo = countByCategoryTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooksStockReport that = (BooksStockReport) o;
        return totalCountFrom == that.totalCountFrom && totalCountTo == that.totalCountTo && Objects.equals(countByCategoryFrom, that.countByCategoryFrom) && Objects.equals(countByCategoryTo, that.countByCategoryTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalCountFrom, totalCountTo, countByCategoryFrom, countByCategoryTo);
    }

    @Override
    public String toString() {
        return "BooksStockReport{" +
                "totalCountFrom=" + totalCountFrom +
                ", totalCountTo=" + totalCountTo +
                ", countByCategoryFrom=" + countByCategoryFrom +
                ", countByCategoryTo=" + countByCategoryTo +
                '}';
    }
}
