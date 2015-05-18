package org.powertac.common.repo;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.powertac.common.timeseries.LoadTimeSeries;
import org.springframework.stereotype.Repository;
/**
 * Stores TimeSeries per Customer
 * @author Simon Rehker
 *
 */
@Repository
public class TimeSeriesRepo implements DomainRepo {

	static private Logger log = Logger.getLogger(TimeslotRepo.class.getName());

	
	private HashMap<Long, LoadTimeSeries> historicLoad;
	private HashMap<Long, LoadTimeSeries> forecastLoad;


	/** standard constructor */
	public TimeSeriesRepo() {
		super();
		historicLoad = new HashMap<Long, LoadTimeSeries>();
		forecastLoad = new HashMap<Long, LoadTimeSeries>();
	}
	
	public LoadTimeSeries findHistoricLoadByCustomerId(long customerId){
		return historicLoad.get(customerId);
	}
	
	public LoadTimeSeries findForecastLoadByCustomerId(long customerId){
		return forecastLoad.get(customerId);
	}
	
	public synchronized void addHistoricLoadTimeSeries(long customerId, LoadTimeSeries timeSeries) {
		historicLoad.put(customerId, timeSeries);
	}
	
	public synchronized void addForecastLoadTimeSeries(long customerId, LoadTimeSeries timeSeries) {
		forecastLoad.put(customerId, timeSeries);
	}

	@Override
	public void recycle() {
		historicLoad.clear();
		forecastLoad.clear();

	}

}
