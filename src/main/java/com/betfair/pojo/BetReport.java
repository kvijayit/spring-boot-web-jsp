/**
 * 
 */
package com.betfair.pojo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author KesavarajuluV
 *
 */
@XmlRootElement
public class BetReport {
	
	private String selectionName;
	private int noOfBets;
	private String currency;
	private Double totalStakes;
	private Double totalPayout; 
	
	
	/**
	 * @return the selectionName
	 */
	public String getSelectionName() {
		return selectionName;
	}
	/**
	 * @param selectionName the selectionName to set
	 */
	public void setSelectionName(String selectionName) {
		this.selectionName = selectionName;
	}
	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	/**
	 * @return the noOfBets
	 */
	public int getNoOfBets() {
		return noOfBets;
	}
	/**
	 * @param noOfBets the noOfBets to set
	 */
	public void setNoOfBets(int noOfBets) {
		this.noOfBets = noOfBets;
	}
	/**
	 * @return the totalStakes
	 */
	public Double getTotalStakes() {
		return totalStakes;
	}
	/**
	 * @param totalStakes the totalStakes to set
	 */
	public void setTotalStakes(Double totalStakes) {
		this.totalStakes = totalStakes;
	}
	/**
	 * @return the totalPayout
	 */
	public Double getTotalPayout() {
		return totalPayout;
	}
	/**
	 * @param totalPayout the totalPayout to set
	 */
	public void setTotalPayout(Double totalPayout) {
		this.totalPayout = totalPayout;
	}
	
	
}