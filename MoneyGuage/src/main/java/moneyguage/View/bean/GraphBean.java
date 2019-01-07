package moneyguage.View.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class GraphBean implements Serializable{
	private String rsi14;
	private float rsiValue;
	private String stochastic;
	private String commondityChannel;
	private String averageDirectional;
	private String awesomeOscillator;
	private String momentum;
	private String macd;
	private String williams;
	private String bullBeaver;
	private String ultimate;
	private float movingAverageValue;
	private String simpleMoving5;
	private String exponentialMoving5;
	private String simpleMoving10;
	private String exponentialMoving10;
	private String simpleMoving15;
	private String exponentialMoving15;
	private String simpleMoving20;
	private String exponentialMoving20;
	private String ichimoku;
	private String volumeweight;
	private String hull;
	private float oscillatorValue;
	
	public String getRsi14() {
		return rsi14;
	}
	public void setRsi14(String rsi14) {
		this.rsi14 = rsi14;
	}
	public String getStochastic() {
		return stochastic;
	}
	public void setStochastic(String stochastic) {
		this.stochastic = stochastic;
	}
	public String getCommondityChannel() {
		return commondityChannel;
	}
	public void setCommondityChannel(String commondityChannel) {
		this.commondityChannel = commondityChannel;
	}
	public String getAverageDirectional() {
		return averageDirectional;
	}
	public void setAverageDirectional(String averageDirectional) {
		this.averageDirectional = averageDirectional;
	}
	public String getAwesomeOscillator() {
		return awesomeOscillator;
	}
	public void setAwesomeOscillator(String awesomeOscillator) {
		this.awesomeOscillator = awesomeOscillator;
	}
	public String getMomentum() {
		return momentum;
	}
	public void setMomentum(String momentum) {
		this.momentum = momentum;
	}
	public String getMacd() {
		return macd;
	}
	public void setMacd(String macd) {
		this.macd = macd;
	}
	public String getWilliams() {
		return williams;
	}
	public void setWilliams(String williams) {
		this.williams = williams;
	}
	public String getBullBeaver() {
		return bullBeaver;
	}
	public void setBullBeaver(String bullBeaver) {
		this.bullBeaver = bullBeaver;
	}
	public String getUltimate() {
		return ultimate;
	}
	public void setUltimate(String ultimate) {
		this.ultimate = ultimate;
	}
	public String getSimpleMoving5() {
		return simpleMoving5;
	}
	public void setSimpleMoving5(String simpleMoving5) {
		this.simpleMoving5 = simpleMoving5;
	}
	public String getExponentialMoving5() {
		return exponentialMoving5;
	}
	public void setExponentialMoving5(String exponentialMoving5) {
		this.exponentialMoving5 = exponentialMoving5;
	}
	public String getSimpleMoving10() {
		return simpleMoving10;
	}
	public void setSimpleMoving10(String simpleMoving10) {
		this.simpleMoving10 = simpleMoving10;
	}
	public String getExponentialMoving10() {
		return exponentialMoving10;
	}
	public void setExponentialMoving10(String exponentialMoving10) {
		this.exponentialMoving10 = exponentialMoving10;
	}
	public String getSimpleMoving15() {
		return simpleMoving15;
	}
	public void setSimpleMoving15(String simpleMoving15) {
		this.simpleMoving15 = simpleMoving15;
	}
	public String getExponentialMoving15() {
		return exponentialMoving15;
	}
	public void setExponentialMoving15(String exponentialMoving15) {
		this.exponentialMoving15 = exponentialMoving15;
	}
	public String getSimpleMoving20() {
		return simpleMoving20;
	}
	public void setSimpleMoving20(String simpleMoving20) {
		this.simpleMoving20 = simpleMoving20;
	}
	public String getExponentialMoving20() {
		return exponentialMoving20;
	}
	public void setExponentialMoving20(String exponentialMoving20) {
		this.exponentialMoving20 = exponentialMoving20;
	}
	public String getIchimoku() {
		return ichimoku;
	}
	public void setIchimoku(String ichimoku) {
		this.ichimoku = ichimoku;
	}
	public String getVolumeweight() {
		return volumeweight;
	}
	public void setVolumeweight(String volumeweight) {
		this.volumeweight = volumeweight;
	}
	public String getHull() {
		return hull;
	}
	public void setHull(String hull) {
		this.hull = hull;
	}
	public float getRsiValue() {
		return rsiValue;
	}
	public void setRsiValue(float rsiValue) {
		this.rsiValue = rsiValue;
	}
	public float getMovingAverageValue() {
		return movingAverageValue;
	}
	public void setMovingAverageValue(float movingAverageValue) {
		this.movingAverageValue = movingAverageValue;
	}
	public float getOscillatorValue() {
		return oscillatorValue;
	}
	public void setOscillatorValue(float oscillatorValue) {
		this.oscillatorValue = oscillatorValue;
	}
}
