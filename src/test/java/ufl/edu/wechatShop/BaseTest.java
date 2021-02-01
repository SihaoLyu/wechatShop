package ufl.edu.wechatShop;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * config spring and junit; load springIOC container when junit starts
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)     // where spring config file in junit
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class BaseTest {
}
