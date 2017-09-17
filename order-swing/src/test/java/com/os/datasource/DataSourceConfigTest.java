package com.os.datasource;

import com.os.beans.entities.DishEntity;
import com.os.order.repository.DishRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * Created by jian zhu on 06/01/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= {CustomDataSourceAutoConfiguration.class,PrimaryJPAConfiguration.class})
@SpringBootApplication
public class DataSourceConfigTest {
    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testInsert(){
        jdbcTemplate.update("insert into BUS_TB_DISH(dish_no,dish_name,version) values (?,?,1)","002","test2");
        Assert.assertEquals("1", jdbcTemplate.queryForObject("select count(1) from BUS_TB_DISH", String.class));

    }

    @Autowired
    private DishRepository dishRepository;

    @Test
    public void testInsertDish(){
        DishEntity dishEntity = new DishEntity();
        dishEntity.setDishNo("002");
        dishEntity.setDishName("Pepsi");
        dishRepository.saveAndFlush(dishEntity);
    }

    @Test
    public void testUpdateDish() throws Exception{
        DishEntity dishEntity = dishRepository.findOne(201L);
        dishEntity.setDishPrice(new BigDecimal(7));
        dishRepository.saveAndFlush(dishEntity);
//        TimeUnit.MINUTES.sleep(10);
    }
}
