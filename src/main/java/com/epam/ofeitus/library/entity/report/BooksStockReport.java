package com.epam.ofeitus.library.entity.report;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class BooksStockReport {
    private int totalCountFrom;
    private int totalCountTo;
    private BigDecimal totalPrice;
    private List<Integer> countByCategoryFrom;
    private List<Integer> countByCategoryTo;
    private List<BigDecimal> priceByCategory;

    public BooksStockReport() {
    }

    public BooksStockReport(int totalCountFrom, int totalCountTo, BigDecimal totalPrice, List<Integer> countByCategoryFrom, List<Integer> countByCategoryTo, List<BigDecimal> priceByCategory) {
        this.totalCountFrom = totalCountFrom;
        this.totalCountTo = totalCountTo;
        this.totalPrice = totalPrice;
        this.countByCategoryFrom = countByCategoryFrom;
        this.countByCategoryTo = countByCategoryTo;
        this.priceByCategory = priceByCategory;
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
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

    public List<BigDecimal> getPriceByCategory() {
        return priceByCategory;
    }

    public void setPriceByCategory(List<BigDecimal> priceByCategory) {
        this.priceByCategory = priceByCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooksStockReport that = (BooksStockReport) o;
        return totalCountFrom == that.totalCountFrom && totalCountTo == that.totalCountTo && totalPrice == that.totalPrice && Objects.equals(countByCategoryFrom, that.countByCategoryFrom) && Objects.equals(countByCategoryTo, that.countByCategoryTo) && Objects.equals(priceByCategory, that.priceByCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalCountFrom, totalCountTo, totalPrice, countByCategoryFrom, countByCategoryTo, priceByCategory);
    }

    @Override
    public String toString() {
        return "BooksStockReport{" +
                "totalCountFrom=" + totalCountFrom +
                ", totalCountTo=" + totalCountTo +
                ", totalPrice=" + totalPrice +
                ", countByCategoryFrom=" + countByCategoryFrom +
                ", countByCategoryTo=" + countByCategoryTo +
                ", priceByCategory=" + priceByCategory +
                '}';
    }
}
