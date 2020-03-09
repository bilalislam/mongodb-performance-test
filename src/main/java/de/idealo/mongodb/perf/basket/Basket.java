package de.idealo.mongodb.perf.basket;

import java.util.ArrayList;

public class Basket {
    private String id;
    Merchant MerchantObject;
    Customer CustomerObject;
    TotalPrice TotalPriceObject;
    TotalTax TotalTaxObject;
    ArrayList<Object> items = new ArrayList<Object>();


    // Getter Methods

    public String getId() {
        return id;
    }

    public Merchant getMerchant() {
        return MerchantObject;
    }

    public Customer getCustomer() {
        return CustomerObject;
    }

    public TotalPrice getTotalPrice() {
        return TotalPriceObject;
    }

    public TotalTax getTotalTax() {
        return TotalTaxObject;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setMerchant(Merchant merchantObject) {
        this.MerchantObject = merchantObject;
    }

    public void setCustomer(Customer customerObject) {
        this.CustomerObject = customerObject;
    }

    public void setTotalPrice(TotalPrice totalPriceObject) {
        this.TotalPriceObject = totalPriceObject;
    }

    public void setTotalTax(TotalTax totalTaxObject) {
        this.TotalTaxObject = totalTaxObject;
    }
}

