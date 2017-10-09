package com.betfair.dao;

import java.util.List;

import com.betfair.pojo.BetReport;
import com.betfair.pojo.Bets;
/**
 * @author KesavarajuluV
 *
 */
public interface BetsDao {

	Bets findByName(String name);
	
	int insertBets(Bets bet);

	List<BetReport> generateReport();

}