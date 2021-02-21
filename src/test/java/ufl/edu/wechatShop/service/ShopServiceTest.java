package ufl.edu.wechatShop.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ufl.edu.wechatShop.BaseTest;
import ufl.edu.wechatShop.dto.ShopExecution;
import ufl.edu.wechatShop.entity.Area;
import ufl.edu.wechatShop.entity.PersonInfo;
import ufl.edu.wechatShop.entity.Shop;
import ufl.edu.wechatShop.entity.ShopCategory;
import ufl.edu.wechatShop.enums.ShopStateEnum;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Date;

public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;

    @Test
    public void testAddShop() {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(1);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("test_service");
        shop.setShopDesc("test_service");
        shop.setShopAddr("test_service");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("test_service");
        File shopImg = new File("/Users/sihaolyu/Documents/testImage/fishman.jpg");
        ShopExecution shopExecution = shopService.addShop(shop, shopImg);
        assertEquals(ShopStateEnum.CHECK.getState(), shopExecution.getState());
    }
}
