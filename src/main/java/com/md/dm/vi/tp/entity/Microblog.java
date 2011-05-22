package com.md.dm.vi.tp.entity;

import java.util.Date;

import com.vividsolutions.jts.geom.Point;

public class Microblog {
	private Long id;
	private Date date;
	private String latitude;
	private String longitud;
	private Point location;
	private String text;
	
	public Microblog(Long id, Date date, String latitude, String longitud,
			Point location, String text) {
		super();
		this.id = id;
		this.date = date;
		this.latitude = latitude;
		this.longitud = longitud;
		this.location = location;
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitud() {
		return longitud;
	}

	public Point getLocation() {
		return location;
	}

	public String getText() {
		return text;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result
				+ ((longitud == null) ? 0 : longitud.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
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
		Microblog other = (Microblog) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (longitud == null) {
			if (other.longitud != null)
				return false;
		} else if (!longitud.equals(other.longitud))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Microblog [id=" + id + ", date=" + date + ", latitude="
				+ latitude + ", longitud=" + longitud + ", location="
				+ location + ", text=" + text + "]";
	}
}
