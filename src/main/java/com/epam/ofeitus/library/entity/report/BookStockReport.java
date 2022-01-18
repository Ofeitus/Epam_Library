package com.epam.ofeitus.library.entity.report;

import java.util.List;
import java.util.Objects;

public class BookStockReport {
    private int totalBooksCount;
    private List<Integer> countByCategory;

    public BookStockReport() {
    }

    public BookStockReport(int totalBooksCount, List<Integer> countByCategory) {
        this.totalBooksCount = totalBooksCount;
        this.countByCategory = countByCategory;
    }

    public int getTotalBooksCount() {
        return totalBooksCount;
    }

    public void setTotalBooksCount(int totalBooksCount) {
        this.totalBooksCount = totalBooksCount;
    }

    public List<Integer> getCountByCategory() {
        return countByCategory;
    }

    public void setCountByCategory(List<Integer> countByCategory) {
        this.countByCategory = countByCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookStockReport that = (BookStockReport) o;
        return totalBooksCount == that.totalBooksCount && Objects.equals(countByCategory, that.countByCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalBooksCount, countByCategory);
    }

    @Override
    public String toString() {
        return "BookStockReport{" +
                "totalBooksCount=" + totalBooksCount +
                ", countByCategory=" + countByCategory +
                '}';
    }
}
