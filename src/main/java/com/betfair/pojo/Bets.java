/**
 * 
 */
package com.betfair.pojo;

/**
 * @author KesavarajuluV
 *
 */
public class Bets {
	
	private String betId;
	private long timestamp;
	private long selectionId;
	private String selectionName;
	private Double stake;
	private Double price;
	private String currency;
	private Double payout; // stake * price
	private Double euroPrice; // euro value of payout price
	private Double euroStake; // euro value of stake
	private int noOfBets; //Added this to save the total no of bets for report
	
	/**
	 * @return the betId
	 */
	public String getBetId() {
		return betId;
	}
	/**
	 * @param betId the betId to set
	 */
	public void setBetId(String betId) {
		this.betId = betId;
	}
	/**
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * @return the selectionId
	 */
	public long getSelectionId() {
		return selectionId;
	}
	/**
	 * @param selectionId the selectionId to set
	 */
	public void setSelectionId(long selectionId) {
		this.selectionId = selectionId;
	}
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
	 * @return the stake
	 */
	public Double getStake() {
		return stake;
	}
	/**
	 * @param stake the stake to set
	 */
	public void setStake(Double stake) {
		this.stake = stake;
	}
	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
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
	 * @return the payout
	 */
	public Double getPayout() {
		return payout;
	}
	/**
	 * @param payout the payout to set
	 */
	public void setPayout(Double payout) {
		this.payout = payout;
	}
	/**
	 * @return the euroPrice
	 */
	public Double getEuroPrice() {
		return euroPrice;
	}
	/**
	 * @param euroPrice the euroPrice to set
	 */
	public void setEuroPrice(Double euroPrice) {
		this.euroPrice = euroPrice;
	}
	/**
	 * @return the euroStake
	 */
	public Double getEuroStake() {
		return euroStake;
	}
	/**
	 * @param euroStake the euroStake to set
	 */
	public void setEuroStake(Double euroStake) {
		this.euroStake = euroStake;
	}
	
}
