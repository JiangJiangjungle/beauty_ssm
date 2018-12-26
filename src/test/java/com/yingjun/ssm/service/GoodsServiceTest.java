package com.yingjun.ssm.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/*.xml")
public class GoodsServiceTest {
    @Autowired
    private GoodsService goodsService;

    @Transactional(propagation = Propagation.REQUIRED)
    @Test
    public void testbuyGoods() throws Exception {
        goodsService.buyGoods(18768128888L, 1L, false);
        throw new Exception("测试事务回滚");
    }


}
