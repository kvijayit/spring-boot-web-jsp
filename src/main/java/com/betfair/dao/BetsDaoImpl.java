package com.betfair.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.betfair.pojo.BetReport;
import com.betfair.pojo.Bets;
/**
 * @author KesavarajuluV
 *
 */
@Repository
public class BetsDaoImpl implements BetsDao {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Value("${group.by}")
	String groupByParam;
	
	@Value("${order.by}")
	String orderByParam;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public Bets findByName(String betid) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("betid", betid);

		String sql = "SELECT * FROM BET_DETAILS WHERE betid=:betid";

		Bets result = namedParameterJdbcTemplate.queryForObject(
				sql,
				params,
				new BetMapper());

		return result;

	}

	@Override
	public List<BetReport> generateReport() {

		Map<String, Object> params = new HashMap<String, Object>();
		String[] groupByList = groupByParam.split(",") ;
		String sql = "SELECT SELECTIONNAME,CURRENCY,COUNT(BETID),SUM(STAKE) as TOTALSTAKES,ROUND(SUM(PAYOUT),2) as TOTALPAYOUTS,ROUND(SUM(EUROPRICE),2) as TOTALEUROPAYOUTS,ROUND(SUM(EUROSTAKE),2) as TOTALEUROSTAKES FROM BET_DETAILS";
	    String groupBy = "GROUP BY";
	    String orderBy = "ORDER BY";
	    
	    for(String groupByParam: groupByList){
	    	groupBy = groupBy+ " "+ groupByParam +",";
	    }
	    groupBy = groupBy.substring(0, groupBy.length()-1);
	    orderBy = orderBy+" "+orderByParam;
	    
	    sql = sql+" "+groupBy+" "+orderBy;
	    
		List<BetReport> result = namedParameterJdbcTemplate.query(sql, params, new BetReportMapper());

		return result;

	}

	@Override
	public int insertBets(Bets bet) {

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("betid", bet.getBetId());
		params.put("timestamp", bet.getTimestamp());
		params.put("selectionid", bet.getSelectionId());
		params.put("selectionname", bet.getSelectionName());
		params.put("stake", bet.getStake());
		params.put("price", bet.getPrice());
		params.put("currency", bet.getCurrency());
		params.put("payout", bet.getPayout());
		params.put("europrice", bet.getEuroPrice());
		params.put("eurostake", bet.getEuroStake());

		String sql = "INSERT INTO BET_DETAILS(betid,bettimestamp,selectionid,selectionname,stake,price,currency,payout,europrice,eurostake) "
				+ "VALUES (:betid,:timestamp,:selectionid,:selectionname,:stake,:price,:currency,:payout,:europrice,:eurostake)";

		int result = namedParameterJdbcTemplate.update(sql,params);
		
		return result;

	}

	private static final class BetMapper implements RowMapper<Bets> {

		public Bets mapRow(ResultSet rs, int rowNum) throws SQLException {
			Bets bet = new Bets();
			bet.setBetId(rs.getString(1));
            bet.setTimestamp(Long.parseLong(rs.getString(2)));
            bet.setSelectionId(Long.parseLong(rs.getString(3)));
            bet.setSelectionName(rs.getString(4));
            bet.setStake(Double.parseDouble(rs.getString(5)));
            bet.setPrice(Double.parseDouble(rs.getString(6)));
            bet.setCurrency(rs.getString(7));
            bet.setPayout(Double.parseDouble(rs.getString(8)));
			return bet;
		}
	}
	
	private static final class BetReportMapper implements RowMapper<BetReport> {

		public BetReport mapRow(ResultSet rs, int rowNum) throws SQLException {
			BetReport bet = new BetReport();
			bet.setSelectionName(rs.getString(1));
            bet.setCurrency(rs.getString(2));
            bet.setNoOfBets(Integer.parseInt(rs.getString(3)));
            bet.setTotalStakes(Double.parseDouble(rs.getString(4)));
            bet.setTotalPayout(Double.parseDouble(rs.getString(5)));
			return bet;
		}
	}

}