package org.powertac.common.timeseries;

import org.joda.time.DateTime;

public interface LoadForecast {

	public LoadTimeSeries calculateLoadForecast(LoadTimeSeries historicLoad, DateTime fromDate, DateTime toDate);
}
