package daoTest;

import org.imooc.bean.Ad;
import org.imooc.dao.AdDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class adDaoTest extends BaseTest {


    @Autowired
    private AdDao adDao;

    @Test
    public void testSelectByPage() throws Exception{
        Ad ad = new Ad();

        List<Ad> adList  = adDao.selectAll(ad);

    }



   /* public void testAInsertShop() throws Exception {
        Shop shop = new Shop();
        shop.setOwnerId(1L);
        Area area = new Area();
        area.setAreaId(1L);
        ShopCategory sc = new ShopCategory();
        sc.setShopCategoryId(1L);
        shop.setShopName("mytest1");
        shop.setShopDesc("mytest1");
        shop.setShopAddr("testaddr1");
        shop.setPhone("13810524526");
        shop.setShopImg("test1");
        shop.setLongitude(1D);
        shop.setLatitude(1D);
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());
        shop.setEnableStatus(0);
        shop.setAdvice("审核中");
        shop.setArea(area);
        shop.setShopCategory(sc);
        int effectedNum = shopDao.insertShop(shop);
        assertEquals(1, effectedNum);
    }*/




  /*  public void testBQueryShopList() throws Exception {
        Shop shop = new Shop();
        List<Shop> shopList = shopDao.queryShopList(shop, 0, 2);
        assertEquals(2, shopList.size());
        int count = shopDao.queryShopCount(shop);
        assertEquals(3, count);
        shop.setShopName("花");
        shopList = shopDao.queryShopList(shop, 0, 3);
        assertEquals(2, shopList.size());
        count = shopDao.queryShopCount(shop);
        assertEquals(2, count);
        shop.setShopId(1L);
        shopList = shopDao.queryShopList(shop, 0, 3);
        assertEquals(1, shopList.size());
        count = shopDao.queryShopCount(shop);
        assertEquals(1, count);

    }*/



}
