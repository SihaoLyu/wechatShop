package ufl.edu.wechatShop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ufl.edu.wechatShop.dao.ShopDao;
import ufl.edu.wechatShop.dto.ShopExecution;
import ufl.edu.wechatShop.entity.Shop;
import ufl.edu.wechatShop.enums.ShopStateEnum;
import ufl.edu.wechatShop.exceptions.ShopOperationException;
import ufl.edu.wechatShop.service.ShopService;
import ufl.edu.wechatShop.util.ImageUtil;
import ufl.edu.wechatShop.util.PathUtil;

import java.io.File;
import java.util.Date;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;
    
    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, File shopImg) {
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            // set default value for shop info
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            // add shop
            int effectedNum = shopDao.insertShop(shop);
            if (effectedNum <= 0) {
                // transactional management needs to throw runtime exception
                throw new ShopOperationException("fail to create a shop!");
            }
            else {
                if (shopImg != null) {
                    // store img
                    try {
                        addShopImg(shop, shopImg);
                    }
                    catch (Exception e) {
                        throw new ShopOperationException("fail to add shop img: " + e.getMessage());
                    }
                    // update addr of shop img
                     effectedNum= shopDao.updateShop(shop);
                    if (effectedNum <= 0) {
                        throw new ShopOperationException("fail to update shop img addr");
                    }
                }
            }
        }
        catch (Exception e) {
            throw new ShopOperationException("addShop error: " + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

    private void addShopImg(Shop shop, File shopImg) {
        // get relative addr of shop img category
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(shopImg, dest);
        shop.setShopImg(shopImgAddr);
    }
}
