package deliverymanagement.deliveryDomain;

public class MenuVO {
    private String menuID;
    private String menuStoreID;
    private String menuName;
    private int menuPrice;
    private String menuDescription;

    public String getMenuID() {
        return menuID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }

    public String getMenuStoreID() {
        return menuStoreID;
    }

    public void setMenuStoreID(String menuStoreID) {
        this.menuStoreID = menuStoreID;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }
}
