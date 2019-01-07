package moneyguage.View.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import moneyguage.View.bean.GraphBean;
import moneyguage.View.bean.MarketBean;

@Named
@RequestScoped
public class GraphController {
	@Inject
	MarketController marketController;
	@Inject
	MarketBean marketBean;

	@Inject
	GraphBean graphBean;

	public void defaultCurrency() {
		search();
		marketController.searchStock("BTC");
	}

	public String searchCurrency() {
		search();
		return null;
	}

	public String search() {

		graphBean.setRsiValue(getRandomNumberInRange(5, 90));
		List<String> graph = new ArrayList<String>();
		graph.add("SELL");
		graph.add("NEUTRAL");
		graph.add("BUY");
		if (graphBean.getRsiValue() < 20) {
			graphBean.setRsi14("SELL");
			graphBean.setAverageDirectional(graph.get(getRandomNumberInRange(0, 1)));
			graphBean.setAwesomeOscillator(graph.get(getRandomNumberInRange(0, 1)));
			graphBean.setCommondityChannel(graph.get(getRandomNumberInRange(0, 1)));
			graphBean.setExponentialMoving10(graph.get(getRandomNumberInRange(1, 2)));
			graphBean.setExponentialMoving15(graph.get(getRandomNumberInRange(1, 2)));
			graphBean.setExponentialMoving20(graph.get(getRandomNumberInRange(1, 2)));
			graphBean.setExponentialMoving5(graph.get(getRandomNumberInRange(1, 2)));
			graphBean.setHull(graph.get(getRandomNumberInRange(0, 2)));
			graphBean.setIchimoku(graph.get(getRandomNumberInRange(0, 2)));
			graphBean.setMacd(graph.get(getRandomNumberInRange(0, 2)));
			graphBean.setMomentum(graph.get(getRandomNumberInRange(0, 2)));
			graphBean.setSimpleMoving10(graph.get(getRandomNumberInRange(1, 2)));
			graphBean.setSimpleMoving15(graph.get(getRandomNumberInRange(1, 2)));
			graphBean.setSimpleMoving5(graph.get(getRandomNumberInRange(1, 2)));
			graphBean.setSimpleMoving20(graph.get(getRandomNumberInRange(0, 2)));
			graphBean.setBullBeaver(graph.get(getRandomNumberInRange(0, 2)));
			graphBean.setStochastic(graph.get(getRandomNumberInRange(0, 1)));
			graphBean.setUltimate(graph.get(getRandomNumberInRange(0, 1)));
			graphBean.setVolumeweight(graph.get(getRandomNumberInRange(1, 2)));
			graphBean.setWilliams(graph.get(getRandomNumberInRange(0, 2)));
		} else if (graphBean.getRsiValue() < 80) {
			graphBean.setRsi14("SELL");
			graphBean.setAverageDirectional(graph.get(getRandomNumberInRange(0, 2)));
			graphBean.setAwesomeOscillator(graph.get(getRandomNumberInRange(0, 2)));
			graphBean.setCommondityChannel(graph.get(getRandomNumberInRange(0, 2)));
			graphBean.setExponentialMoving10(graph.get(getRandomNumberInRange(0, 2)));
			graphBean.setExponentialMoving15(graph.get(getRandomNumberInRange(0, 2)));
			graphBean.setExponentialMoving20(graph.get(getRandomNumberInRange(0, 2)));
			graphBean.setExponentialMoving5(graph.get(getRandomNumberInRange(0, 2)));
			graphBean.setHull(graph.get(getRandomNumberInRange(0, 2)));
			graphBean.setIchimoku(graph.get(getRandomNumberInRange(0, 2)));
			graphBean.setMacd(graph.get(getRandomNumberInRange(0, 2)));
			graphBean.setMomentum(graph.get(getRandomNumberInRange(0, 2)));
			graphBean.setSimpleMoving10(graph.get(getRandomNumberInRange(0, 2)));
			graphBean.setSimpleMoving15(graph.get(getRandomNumberInRange(0, 2)));
			graphBean.setSimpleMoving20(graph.get(getRandomNumberInRange(0, 2)));
			graphBean.setSimpleMoving5(graph.get(getRandomNumberInRange(0, 2)));
			graphBean.setStochastic(graph.get(getRandomNumberInRange(0, 2)));
			graphBean.setBullBeaver(graph.get(getRandomNumberInRange(0, 2)));
			graphBean.setUltimate(graph.get(getRandomNumberInRange(0, 2)));
			graphBean.setVolumeweight(graph.get(getRandomNumberInRange(0, 2)));
			graphBean.setWilliams(graph.get(getRandomNumberInRange(0, 2)));
		} else {
			graphBean.setRsi14("SELL");
			graphBean.setAverageDirectional(graph.get(getRandomNumberInRange(1, 2)));
			graphBean.setAwesomeOscillator(graph.get(getRandomNumberInRange(1, 2)));
			graphBean.setCommondityChannel(graph.get(getRandomNumberInRange(1, 2)));
			graphBean.setExponentialMoving10(graph.get(getRandomNumberInRange(0, 1)));
			graphBean.setExponentialMoving15(graph.get(getRandomNumberInRange(0, 1)));
			graphBean.setExponentialMoving20(graph.get(getRandomNumberInRange(0, 1)));
			graphBean.setExponentialMoving5(graph.get(getRandomNumberInRange(0, 1)));
			graphBean.setHull(graph.get(getRandomNumberInRange(0, 2)));
			graphBean.setIchimoku(graph.get(getRandomNumberInRange(0, 2)));
			graphBean.setMacd(graph.get(getRandomNumberInRange(0, 2)));
			graphBean.setMomentum(graph.get(getRandomNumberInRange(0, 2)));
			graphBean.setSimpleMoving10(graph.get(getRandomNumberInRange(0, 1)));
			graphBean.setSimpleMoving15(graph.get(getRandomNumberInRange(0, 1)));
			graphBean.setSimpleMoving5(graph.get(getRandomNumberInRange(0, 1)));
			graphBean.setSimpleMoving20(graph.get(getRandomNumberInRange(0, 2)));
			graphBean.setStochastic(graph.get(getRandomNumberInRange(1, 2)));
			graphBean.setUltimate(graph.get(getRandomNumberInRange(1, 2)));
			graphBean.setBullBeaver(graph.get(getRandomNumberInRange(0, 2)));
			graphBean.setVolumeweight(graph.get(getRandomNumberInRange(0, 2)));
			graphBean.setWilliams(graph.get(getRandomNumberInRange(1, 2)));
		}
		int buyAverageCount = 0;
		int buyOscillator = 0;
		int sellAverage = 0;
		int sellOscillator = 0;
		int averageNeutral = 0;
		int oscillatorNeutral = 0;

		if (graphBean.getExponentialMoving5().equalsIgnoreCase("BUY")) {
			buyAverageCount += 1;
		}
		if (graphBean.getExponentialMoving10().equalsIgnoreCase("BUY")) {
			buyAverageCount += 1;
		}
		if (graphBean.getExponentialMoving15().equalsIgnoreCase("BUY")) {
			buyAverageCount += 1;
		}
		if (graphBean.getExponentialMoving20().equalsIgnoreCase("BUY")) {
			buyAverageCount += 1;
		}
		if (graphBean.getSimpleMoving5().equalsIgnoreCase("BUY")) {
			buyAverageCount += 1;
		}
		if (graphBean.getSimpleMoving10().equalsIgnoreCase("BUY")) {
			buyAverageCount += 1;
		}
		if (graphBean.getSimpleMoving15().equalsIgnoreCase("BUY")) {
			buyAverageCount += 1;
		}
		if (graphBean.getSimpleMoving20().equalsIgnoreCase("BUY")) {
			buyAverageCount += 1;
		}

		if (graphBean.getExponentialMoving5().equalsIgnoreCase("SELL")) {
			sellAverage += 1;
		}
		if (graphBean.getExponentialMoving10().equalsIgnoreCase("SELL")) {
			sellAverage += 1;
		}
		if (graphBean.getExponentialMoving15().equalsIgnoreCase("SELL")) {
			sellAverage += 1;
		}
		if (graphBean.getExponentialMoving20().equalsIgnoreCase("SELL")) {
			sellAverage += 1;
		}
		if (graphBean.getSimpleMoving5().equalsIgnoreCase("SELL")) {
			sellAverage += 1;
		}
		if (graphBean.getSimpleMoving10().equalsIgnoreCase("SELL")) {
			sellAverage += 1;
		}
		if (graphBean.getSimpleMoving15().equalsIgnoreCase("SELL")) {
			sellAverage += 1;
		}
		if (graphBean.getSimpleMoving20().equalsIgnoreCase("SELL")) {
			sellAverage += 1;
		}
		averageNeutral = 13 - buyAverageCount - sellAverage;
		if (graphBean.getRsi14().equalsIgnoreCase("BUY")) {
			buyOscillator += 1;
		}
		if (graphBean.getStochastic().equalsIgnoreCase("BUY")) {
			buyOscillator += 1;
		}
		if (graphBean.getAverageDirectional().equalsIgnoreCase("BUY")) {
			buyOscillator += 1;
		}
		if (graphBean.getStochastic().equalsIgnoreCase("BUY")) {
			buyOscillator += 1;
		}
		if (graphBean.getAwesomeOscillator().equalsIgnoreCase("BUY")) {
			buyOscillator += 1;
		}
		if (graphBean.getCommondityChannel().equalsIgnoreCase("BUY")) {
			buyOscillator += 1;
		}
		if (graphBean.getMomentum().equalsIgnoreCase("BUY")) {
			buyOscillator += 1;
		}
		if (graphBean.getMacd().equalsIgnoreCase("BUY")) {
			buyOscillator += 1;
		}
		if (graphBean.getStochastic().equalsIgnoreCase("BUY")) {
			buyOscillator += 1;
		}
		if (graphBean.getWilliams().equalsIgnoreCase("BUY")) {
			buyOscillator += 1;
		}
		if (graphBean.getBullBeaver().equalsIgnoreCase("BUY")) {
			buyOscillator += 1;
		}
		if (graphBean.getUltimate().equalsIgnoreCase("BUY")) {
			buyOscillator += 1;
		}

		if (graphBean.getRsi14().equalsIgnoreCase("SEL")) {
			sellOscillator += 1;
		}
		if (graphBean.getStochastic().equalsIgnoreCase("SELL")) {
			sellOscillator += 1;
		}
		if (graphBean.getAverageDirectional().equalsIgnoreCase("SELL")) {
			sellOscillator += 1;
		}
		if (graphBean.getStochastic().equalsIgnoreCase("SELL")) {
			sellOscillator += 1;
		}
		if (graphBean.getAwesomeOscillator().equalsIgnoreCase("SELL")) {
			sellOscillator += 1;
		}
		if (graphBean.getCommondityChannel().equalsIgnoreCase("SELL")) {
			sellOscillator += 1;
		}
		if (graphBean.getMomentum().equalsIgnoreCase("SELL")) {
			sellOscillator += 1;
		}
		if (graphBean.getMacd().equalsIgnoreCase("SELL")) {
			sellOscillator += 1;
		}
		if (graphBean.getStochastic().equalsIgnoreCase("SELL")) {
			sellOscillator += 1;
		}
		if (graphBean.getWilliams().equalsIgnoreCase("SELL")) {
			sellOscillator += 1;
		}
		if (graphBean.getBullBeaver().equalsIgnoreCase("SELL")) {
			sellOscillator += 1;
		}
		if (graphBean.getUltimate().equalsIgnoreCase("SELL")) {
			sellOscillator += 1;
		}
		oscillatorNeutral += 1;

		graphBean.setMovingAverageValue((Float.valueOf((sellAverage+averageNeutral)) / (sellAverage + averageNeutral + buyAverageCount)) * 100);
		graphBean.setRsiValue((Float.valueOf(sellOscillator) / (sellOscillator + oscillatorNeutral + buyOscillator)) * 100);
		graphBean.setOscillatorValue(Float.valueOf((sellAverage + sellOscillator))
				/ (sellAverage + averageNeutral + buyAverageCount + sellOscillator + oscillatorNeutral + buyOscillator)
				* 100);
		return null;
	}

	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}
