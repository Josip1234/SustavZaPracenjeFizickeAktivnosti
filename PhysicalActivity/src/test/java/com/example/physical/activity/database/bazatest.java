package com.example.physical.activity.database;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=BazaConfig.class)
public class bazatest {
    @Autowired
    private PhysicalActivityDatabase db;
	@Test
	public void databaseNotNull() {
		assertNotNull(db);
	}

}