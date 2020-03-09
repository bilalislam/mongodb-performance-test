package de.idealo.mongodb.perf.basket;

public class Merchant {
    private String merchantId;
    private String vendorId;
    ShippingAddress ShippingAddressObject;


    // Getter Methods

    public String getMerchantId() {
        return merchantId;
    }

    public String getVendorId() {
        return vendorId;
    }

    public ShippingAddress getShippingAddress() {
        return ShippingAddressObject;
    }

    // Setter Methods

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public void setShippingAddress(ShippingAddress shippingAddressObject) {
        this.ShippingAddressObject = shippingAddressObject;
    }
}
