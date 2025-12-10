package deliverymanagement.deliveryDomain;

public class StoreVO {
    private String storeId;
    private String storeOwnerId;
    private String storeName;
    private String storeAddress;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreOwnerId() {
        return storeOwnerId;
    }

    public void setStoreOwnerId(String storeOwnerId) {
        this.storeOwnerId = storeOwnerId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }
}
