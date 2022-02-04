package com.epam.ofeitus.library.entity.report;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Books stock report bean class.
 */
public class BooksStockReport implements Serializable {
    private int totalCountFrom;
    private int totalCountTo;
    private BigDecimal totalPriceFrom;
    private BigDecimal totalPriceTo;
    private List<Integer> countByCategoryFrom;
    private List<Integer> countByCategoryTo;
    private List<BigDecimal> priceByCategoryFrom;
    private List<BigDecimal> priceByCategoryTo;

    public BooksStockReport() {
    }

    public BooksStockReport(int totalCountFrom, int totalCountTo, BigDecimal totalPriceFrom, BigDecimal totalPriceTo, List<Integer> countByCategoryFrom, List<Integer> countByCategoryTo, List<BigDecimal> priceByCategoryFrom, List<BigDecimal> priceByCategoryTo) {
        this.totalCountFrom = totalCountFrom;
        this.totalCountTo = totalCountTo;
        this.totalPriceFrom = totalPriceFrom;
        this.totalPriceTo = totalPriceTo;
        this.countByCategoryFrom = countByCategoryFrom;
        this.countByCategoryTo = countByCategoryTo;
        this.priceByCategoryFrom = priceByCategoryFrom;
        this.priceByCategoryTo = priceByCategoryTo;
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

    public BigDecimal getTotalPriceFrom() {
        return totalPriceFrom;
    }

    public void setTotalPriceFrom(BigDecimal totalPriceFrom) {
        this.totalPriceFrom = totalPriceFrom;
    }

    public BigDecimal getTotalPriceTo() {
        return totalPriceTo;
    }

    public void setTotalPriceTo(BigDecimal totalPriceTo) {
        this.totalPriceTo = totalPriceTo;
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

    public List<BigDecimal> getPriceByCategoryFrom() {
        return priceByCategoryFrom;
    }

    public void setPriceByCategoryFrom(List<BigDecimal> priceByCategoryFrom) {
        this.priceByCategoryFrom = priceByCategoryFrom;
    }

    public List<BigDecimal> getPriceByCategoryTo() {
        return priceByCategoryTo;
    }

    public void setPriceByCategoryTo(List<BigDecimal> priceByCategoryTo) {
        this.priceByCategoryTo = priceByCategoryTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooksStockReport that = (BooksStockReport) o;
        return totalCountFrom == that.totalCountFrom && totalCountTo == that.totalCountTo && Objects.equals(totalPriceFrom, that.totalPriceFrom) && Objects.equals(totalPriceTo, that.totalPriceTo) && Objects.equals(countByCategoryFrom, that.countByCategoryFrom) && Objects.equals(countByCategoryTo, that.countByCategoryTo) && Objects.equals(priceByCategoryFrom, that.priceByCategoryFrom) && Objects.equals(priceByCategoryTo, that.priceByCategoryTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalCountFrom, totalCountTo, totalPriceFrom, totalPriceTo, countByCategoryFrom, countByCategoryTo, priceByCategoryFrom, priceByCategoryTo);
    }

    @Override
    public String toString() {
        return "BooksStockReport{" +
                "totalCountFrom=" + totalCountFrom +
                ", totalCountTo=" + totalCountTo +
                ", totalPriceFrom=" + totalPriceFrom +
                ", totalPriceTo=" + totalPriceTo +
                ", countByCategoryFrom=" + countByCategoryFrom +
                ", countByCategoryTo=" + countByCategoryTo +
                ", priceByCategoryFrom=" + priceByCategoryFrom +
                ", priceByCategoryTo=" + priceByCategoryTo +
                '}';
    }
}
