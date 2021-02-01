package ufl.edu.wechatShop.dao;

import ufl.edu.wechatShop.entity.Area;

import java.util.List;

public interface AreaDao {
    /**
     * List areas
     * @return areaList
     */
    List<Area> queryArea();
}
