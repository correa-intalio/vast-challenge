package event;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernatespatial.criterion.SpatialRestrictions;

import util.HibernateUtil;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

public class EventManager {

	public static void main(String[] args) {
		EventManager mgr = new EventManager();

		mgr.createAndStoreEvent("My Event", new Date(), "POINT(10 15)");

		List events = mgr.find("POLYGON((1 1,20 1,20 20,1 20,1 1))");

		for (int i = 0; i < events.size(); i++) {
			Event event = (Event) events.get(i);
			System.out.println("Event: " + event.getTitle() + ", Time: "
					+ event.getDate() + ", Location: " + event.getLocation());
		}
		HibernateUtil.getSessionFactory().close();
	}

	private void createAndStoreEvent(String title, Date theDate, String wktPoint) {

		// First interpret the WKT string to a point
		WKTReader fromText = new WKTReader();
		Geometry geom = null;
		try {
			geom = fromText.read(wktPoint);
		} catch (ParseException e) {
			throw new RuntimeException("Not a WKT string:" + wktPoint);
		}
		if (!geom.getGeometryType().equals("Point")) {
			throw new RuntimeException("Geometry must be a point. Got a "
					+ geom.getGeometryType());
		}

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();

		Event theEvent = new Event();
		theEvent.setTitle(title);
		theEvent.setDate(theDate);
		theEvent.setLocation((Point) geom);
		session.save(theEvent);

		session.getTransaction().commit();
	}

	private List find(String wktFilter) {
		WKTReader fromText = new WKTReader();
		Geometry filter = null;
		try {
			filter = fromText.read(wktFilter);
		} catch (ParseException e) {
			throw new RuntimeException("Not a WKT String:" + wktFilter);
		}
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		System.out.println("Filter is : " + filter);
		Criteria testCriteria = session.createCriteria(Event.class);
		testCriteria.add(SpatialRestrictions.within("location", filter));
		List results = testCriteria.list();
		session.getTransaction().commit();
		return results;
	}
}
