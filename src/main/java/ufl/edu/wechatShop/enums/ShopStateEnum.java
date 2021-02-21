package ufl.edu.wechatShop.enums;

public enum ShopStateEnum {
    CHECK(0, "checking"), OFFLINE(-1, "illegal shop"), SUCCESS(1, "successful operation"),
    PASS(2, "verified"), INNER_ERROR(-1001, "internal error"),
    NULL_SHOPID(-1002, "invalid shopid"), NULL_SHOP(-1003, "shop is null");
    private int state;
    private String stateInfo;

    ShopStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static ShopStateEnum stateOf(int state) {
        for (ShopStateEnum shopStateEnum: values()) {
            if (shopStateEnum.getState() == state) {
                return shopStateEnum;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
}
