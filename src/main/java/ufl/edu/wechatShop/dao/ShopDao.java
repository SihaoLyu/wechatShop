package ufl.edu.wechatShop.dao;

import ufl.edu.wechatShop.entity.Shop;

public interface ShopDao {
    /**
     * add shop
     * @param shop
     * @return
     */
    public int insertShop(Shop shop);

    /**
     * update shop
     * @param shop
     * @return
     */
    public int updateShop(Shop shop);

}
