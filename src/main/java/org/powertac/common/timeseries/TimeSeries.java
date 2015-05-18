package org.powertac.common.timeseries;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.LocalDateTime;

public class TimeSeries {
	private List<TimeSeriesDay> days;
	private DateTime fromDate;
	private DateTime toDate;

	public TimeSeries(List<TimeSeriesDay> days, DateTime fromDate,
			DateTime toDate) {
		super();
		this.days = days;
		this.setFromDate(fromDate);
		this.setToDate(toDate);
	}

	public List<TimeSeriesDay> getDays() {
		return days;
	}

	public void setDays(List<TimeSeriesDay> days) {
		this.days = days;
	}	

	public void outputFile(String filename) {
		try {
			FileWriter writer = new FileWriter(filename);

			writer.append("Date");
			writer.append(';');
			writer.append("Load");
			writer.append('\n');

			for (TimeSeriesDay d : days) {
				for (int i = 0; i < d.getHourvalues().size(); i++) {
					LocalDateTime tmp = new LocalDateTime(d.getDate())
							.withHourOfDay(i); 
					writer.append(tmp.toString("dd.MM.yyyy HH:mm"));
					writer.append(';');
					writer.append(d.getHourvalues().get(i).toString());
					writer.append('\n');
				}
			}

			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public DateTime getFromDate() {
		return fromDate;
	}

	public void setFromDate(DateTime fromDate) {
		this.fromDate = fromDate;
	}

	public DateTime getToDate() {
		return toDate;
	}

	public void setToDate(DateTime toDate) {
		this.toDate = toDate;
	}
	
	public void addValue(DateTime timestamp, double value){
		TimeSeriesDay td=findDayRecord(timestamp);
		
		if(td==null){
			td = new TimeSeriesDay(Daytype.getDaytypeFromDate(timestamp), timestamp.withHourOfDay(0), new ArrayList<Double>());
		}
		td.getHourvalues().set(timestamp.getHourOfDay(), value);
	}
	
	public void sumValue(DateTime timestamp, double value){
		TimeSeriesDay td=findDayRecord(timestamp);
		
		if(td==null){
			td = new TimeSeriesDay(Daytype.getDaytypeFromDate(timestamp), timestamp.withHourOfDay(0), new ArrayList<Double>());
		}
		td.getHourvalues().set(timestamp.getHourOfDay(), value+td.getHourvalues().get(timestamp.getHourOfDay()));
	}
	
	public TimeSeriesDay findDayRecord(DateTime timestamp){
		
		for(TimeSeriesDay d: days){
			Interval i = new Interval(d.getDate(), d.getDate().plusHours(24));
			if(i.contains(timestamp)){
				return d;
			}
		}
		return null;
	}
	
	public double getValue(DateTime timestamp){
		TimeSeriesDay d = findDayRecord(timestamp);
		if(d == null){
			return -1;
		}
		return d.getHourvalues().get(timestamp.getHourOfDay());
	}

}
