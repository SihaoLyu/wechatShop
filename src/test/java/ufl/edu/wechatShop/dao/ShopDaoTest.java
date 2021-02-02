package ufl.edu.wechatShop.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ufl.edu.wechatShop.BaseTest;
import ufl.edu.wechatShop.entity.Area;
import ufl.edu.wechatShop.entity.PersonInfo;
import ufl.edu.wechatShop.entity.Shop;
import ufl.edu.wechatShop.entity.ShopCategory;

import static org.junit.Assert.assertEquals;

import java.util.Date;

public class ShopDaoTest extends BaseTest {
    @Autowired
    private ShopDao shopDao;
    @Test
    public void testInsertShop(){
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(32609);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("test");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setShopImg("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("test");
        int effectedNum = shopDao.insertShop(shop);
        assertEquals(1, effectedNum);
    }
}
