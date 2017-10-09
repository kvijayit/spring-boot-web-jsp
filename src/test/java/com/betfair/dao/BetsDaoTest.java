package com.betfair.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.betfair.dao.BetsDao;
import com.betfair.dao.BetsDaoImpl;
import com.betfair.pojo.Bets;
/**
 * @author KesavarajuluV
 *
 */
public class BetsDaoTest {

    private EmbeddedDatabase db;

    BetsDao betsDao;
    
    @Before
    public void setUp() {
        //db = new EmbeddedDatabaseBuilder().addDefaultScripts().build();
    	db = new EmbeddedDatabaseBuilder()
    		.setType(EmbeddedDatabaseType.HSQL)
    		.addScript("db/sql/create-db.sql")
    		.addScript("db/sql/insert-data.sql")
    		.build();
    }

    @Test
    public void testFindByname() {
    	NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(db);
    	BetsDaoImpl betsDao = new BetsDaoImpl();
    	betsDao.setNamedParameterJdbcTemplate(template);
    	
    	Bets bet = betsDao.findByName("Bet-Test");
  
    	Assert.assertNotNull(bet);
    	Assert.assertEquals("Bet-Test", bet.getBetId());
    	Assert.assertEquals("Bet-TestSelection", bet.getSelectionName());

    }

    @After
    public void tearDown() {
        db.shutdown();
    }

}