package com.carryit.base.xmuu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XmuuApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	DataSourceProperties dataSourceProperties;

	@Test
	public void testDataSource() throws Exception {
		// 获取配置的数据源
		DataSource dataSource = applicationContext.getBean(DataSource.class);
		// 查看配置数据源信息
		System.out.println(dataSource);
		System.out.println(dataSource.getClass().getName());
		System.out.println(dataSourceProperties);
	}

}
