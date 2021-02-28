//package ufl.edu.wechatShop.web.shopadmin;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//import org.springframework.web.multipart.commons.CommonsMultipartFile;
//import org.springframework.web.multipart.commons.CommonsMultipartResolver;
//import ufl.edu.wechatShop.dto.ShopExecution;
//import ufl.edu.wechatShop.entity.PersonInfo;
//import ufl.edu.wechatShop.entity.Shop;
//import ufl.edu.wechatShop.enums.ShopStateEnum;
//import ufl.edu.wechatShop.service.ShopService;
//import ufl.edu.wechatShop.util.HttpServletRequestUtil;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.HashMap;
//import java.util.Map;
//
//@Controller
//@RequestMapping("/shopadmin")
//public class ShopManagementController {
//    @Autowired
//    private ShopService shopService;
//
//    @RequestMapping(value = "/registershop", method = RequestMethod.POST)
//    @ResponseBody
//    private Map<String, Object> registerShop(HttpServletRequest request) {
//        Map<String, Object> modelMap = new HashMap<>();
//
//        // receive and transfer args -- shop info and img info
//        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");  // shopStr is defined by front-end
//        ObjectMapper mapper = new ObjectMapper();
//        Shop shop = null;
//        try {
//            shop = mapper.readValue(shopStr, Shop.class);
//        }
//        catch (Exception e) {
//            modelMap.put("success", false);
//            modelMap.put("errMsg", e.getMessage());
//            return modelMap;
//        }
//        CommonsMultipartFile shopImg = null;
//        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
//                request.getSession().getServletContext());
//        if (commonsMultipartResolver.isMultipart(request)) {
//            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
//            shopImg = (CommonsMultipartFile)multipartHttpServletRequest.getFile("shopImg");
//        }
//        else {
//            modelMap.put("success", false);
//            modelMap.put("errMsg", "upload img cannot be null");
//            return modelMap;
//        }
//
//        // register shop
//        if (shop != null && shopImg != null) {
//            PersonInfo owner = new PersonInfo();
//            // TODO session
//            owner.setUserId(1L);
//            shop.setOwner(owner);
//            ShopExecution shopExecution = shopService.addShop(shop, shopImg);
//            if (shopExecution.getState() == ShopStateEnum.CHECK.getState()) {
//                modelMap.put("success", true);
//            }
//            else {
//                modelMap.put("success", false);
//                modelMap.put("errMsg", shopExecution.getStateInfo());
//                return modelMap;
//            }
//        }
//        else {
//            modelMap.put("success", false);
//            modelMap.put("errMsg", "invalid shop info");
//            return modelMap;
//        }
//
//        return modelMap;
//    }
//}
