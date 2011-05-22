package com.md.dm.vi.tp.entity;

import java.util.Date;

public class Weather {

	private Date date;
	private String weather;
	private Integer averageWindSpeed;
	private String windDirection;
	
	public Weather(Date date, String weather, Integer averageWindSpeed,
			String windDirection) {
		super();
		this.date = date;
		this.weather = weather;
		this.averageWindSpeed = averageWindSpeed;
		this.windDirection = windDirection;
	}

	public Date getDate() {
		return date;
	}

	public String getWeather() {
		return weather;
	}

	public Integer getAverageWindSpeed() {
		return averageWindSpeed;
	}

	public String getWindDirection() {
		return windDirection;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((averageWindSpeed == null) ? 0 : averageWindSpeed.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((weather == null) ? 0 : weather.hashCode());
		result = prime * result
				+ ((windDirection == null) ? 0 : windDirection.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Weather other = (Weather) obj;
		if (averageWindSpeed == null) {
			if (other.averageWindSpeed != null)
				return false;
		} else if (!averageWindSpeed.equals(other.averageWindSpeed))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (weather == null) {
			if (other.weather != null)
				return false;
		} else if (!weather.equals(other.weather))
			return false;
		if (windDirection == null) {
			if (other.windDirection != null)
				return false;
		} else if (!windDirection.equals(other.windDirection))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Weather [date=" + date + ", weather=" + weather
				+ ", averageWindSpeed=" + averageWindSpeed + ", windDirection="
				+ windDirection + "]";
	}

	
	
}
