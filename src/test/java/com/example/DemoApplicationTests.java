package com.example;

import com.example.youyuan.dao.TestDao;
import com.example.youyuan.pojo.Test01;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Resource
	TestDao testDao;

	@Test
	public void test_insert() {
		Test01 test01 = new Test01();
		test01.setName("王小锤");
		test01.setAddTime(new Date());
		test01.setAge(45);
		testDao.insertTest01(test01);
	}

	@Test
	public void test_select(){
		List<Test01> list = testDao.getTest01List();
		System.out.println(list);
	}

	@Test
	public void test_update(){
		testDao.updateTestO1("小米",6500);
	}

	@Test
	public void test_update_time(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH,4);
		testDao.updateTest01_time(calendar.getTime(),6501);
	}

	@Test
	public void test_log4j(){
		Logger logger = Logger.getLogger("error");
		logger.error("hello spring xxx"+new Date());
	}

}
