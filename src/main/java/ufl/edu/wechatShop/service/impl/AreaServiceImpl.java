package ufl.edu.wechatShop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufl.edu.wechatShop.dao.AreaDao;
import ufl.edu.wechatShop.entity.Area;
import ufl.edu.wechatShop.service.AreaService;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao areaDao;

    @Override
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }
}
