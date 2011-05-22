package event;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.vividsolutions.jts.geom.Point;

@Entity
@Table(name = "EVENTS")
public class Event {

	@Id
	@GeneratedValue
	@Column(name = "EVENT_ID")
	private Long id;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "EVENT_DATE")
	private Date date;

	@Column(name = "LOC")
	@Type(type = "org.hibernatespatial.GeometryUserType")
	private Point location;

	public Event() {
	}

	public Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Point getLocation() {
		return this.location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}
}