package com.test.devops.DevOps.Project;

import com.test.devops.DevOps.Project.Ctrls.MainCtrl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DevOpsProjectApplicationTests {

	@Autowired
	MainCtrl mainCtrl;

	@Test
	public void contextLoads() {
		assertEquals(mainCtrl.main(), "The app is running");
	}

}
