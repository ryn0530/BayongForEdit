package com.example.bayong;

public class OhMySalesData {
    String category;
    int sales;

    public OhMySalesData(String category, int sales) {
        this.category = category;
        this.sales = sales;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }
}
