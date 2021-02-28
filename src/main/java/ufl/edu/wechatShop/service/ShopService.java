package ufl.edu.wechatShop.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
import ufl.edu.wechatShop.dto.ShopExecution;
import ufl.edu.wechatShop.entity.Shop;

import java.io.File;

public interface ShopService {
    ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg);
}
