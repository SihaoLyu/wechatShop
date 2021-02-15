package ufl.edu.wechatShop.util;

import java.util.Locale;

public class PathUtil {
    private static String separator = System.getProperty("file.separator");
    public static String getImageBasePath(){
        String os = System.getProperty("os.name");
        String basePath = "";
        if(os.toLowerCase().startsWith("win")){
            basePath = "D:/projectdev/image";
        }
        else{
            basePath = "/Users/sihaolyu/Documents/image";
        }
        basePath = basePath.replace("/", separator);
        return basePath;
    }
    public static String getShopImagePath(long shopId){
        String imagePath = "/upload/item/shop/" + shopId + "/";
        return imagePath.replace("/", separator);
    }
}
