package ufl.edu.wechatShop.service;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import ufl.edu.wechatShop.BaseTest;
import ufl.edu.wechatShop.entity.Area;

import java.util.List;

public class AreaServiceTest extends BaseTest {
    @Autowired
    private AreaService areaService;

    @Test
    public void testGetAreaList() {
        List<Area> areaList = areaService.getAreaList();
        assertEquals(2, areaList.size());
    }
}
