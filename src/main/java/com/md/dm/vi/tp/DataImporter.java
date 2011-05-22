package com.md.dm.vi.tp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.hibernate.Session;

import util.HibernateUtil;

import com.md.dm.vi.tp.entity.Microblog;
import com.md.dm.vi.tp.entity.Population;
import com.md.dm.vi.tp.entity.Weather;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

public class DataImporter {

	public static void main(String[] args) throws Exception {
		DataImporter dataImporter = new DataImporter();
		dataImporter.processWeatherInformation();
		dataImporter.processPopulationInformation();
		dataImporter.processMicroblogsInformation();
	}

	public void process(String filename) throws Exception {

		// Get the object of DataInputStream
		DataInputStream in = null;
		try {
			FileInputStream fstream = new FileInputStream(filename);
			in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// while ((strLine = br.readLine()) != null) {
			// System.out.println(strLine);
			// }

			System.out.println(br.readLine());
			System.out.println(br.readLine());
			System.out.println(br.readLine());
		} finally {
			if (in != null)
				in.close();
		}

	}

	public void processWeatherInformation() throws Exception {
		DataInputStream in = null;
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();

			session.beginTransaction();

			FileInputStream fstream = new FileInputStream("data/Weather.csv");
			in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine(); // Process head file
			while ((strLine = br.readLine()) != null) {
				String[] fields = strLine.split(",");

				Weather weather = new Weather(formatter.parse(fields[0]),
						fields[1], Integer.valueOf(fields[2]), fields[3]);
				// System.out.println(weather);
				session.save(weather);
			}
			session.getTransaction().commit();
		} finally {
			if (in != null)
				in.close();
		}

	}

	public void processPopulationInformation() throws Exception {

		DataInputStream in = null;

		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();

			session.beginTransaction();

			FileInputStream fstream = new FileInputStream("data/Population.csv");
			in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine(); // Process head file
			while ((strLine = br.readLine()) != null) {
				String[] fields = strLine.split(",");

				Population population = new Population(fields[0],
						Integer.valueOf(fields[1]), Integer.valueOf(fields[2]));
				// System.out.println(population);
				session.save(population);
			}

			session.getTransaction().commit();

		} finally {
			if (in != null)
				in.close();
		}
	}

	public void processMicroblogsInformation() throws Exception {
		DataInputStream in = null;
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();

			session.beginTransaction();

			FileInputStream fstream = new FileInputStream("data/Microblogs.csv");
			in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine(); // Process head file

			while ((strLine = br.readLine()) != null) {
				String[] fields = strLine.split(",");
				String[] latLon = fields[2].split("\\ ");

				String wktPoint = "POINT(" + fields[2] + ")";

				// First interpret the WKT string to a point
				WKTReader fromText = new WKTReader();
				Geometry geom = null;
				try {
					geom = fromText.read(wktPoint);
				} catch (ParseException e) {
					throw new RuntimeException("Not a WKT string:" + wktPoint);
				}
				if (!geom.getGeometryType().equals("Point")) {
					throw new RuntimeException(
							"Geometry must be a point. Got a "
									+ geom.getGeometryType());
				}
				Microblog microblog = new Microblog(Long.valueOf(fields[0]),
						formatter.parse(fields[1]), Double.valueOf(latLon[0]),
						Double.valueOf(latLon[1]), (Point) geom, fields[3]);
				// System.out.println(microblog);

				session.save(microblog);
			}

			session.getTransaction().commit();

		} finally {
			if (in != null)
				in.close();
		}
	}
}
