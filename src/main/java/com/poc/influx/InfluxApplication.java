package com.poc.influx;

import com.poc.influx.config.InfluxDBTemplate;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class InfluxApplication implements CommandLineRunner {

	private static Logger logger = LoggerFactory.getLogger(InfluxApplication.class);

	@Autowired
	private InfluxDBTemplate<Point> influxDBTemplate;

	@Override
	public void run(final String... args) throws Exception
	{
		// Create database...
		influxDBTemplate.createDatabase();

		// Create some data...
		final Point p1 = Point.measurement("cpu")
				.time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
				.tag("tenant", "default")
				.addField("idle", 90L)
				.addField("user", 9L)
				.addField("system", 1L)
				.build();
		final Point p2 = Point.measurement("disk")
				.time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
				.tag("tenant", "default")
				.addField("used", 80L)
				.addField("free", 1L)
				.build();
		influxDBTemplate.write(p1, p2);

		// ... and query the latest data
		final Query q = new Query("SELECT * FROM disk GROUP BY tenant", influxDBTemplate.getDatabase());
		influxDBTemplate.query(q, 10, queryResult -> logger.info(queryResult.toString()));
	}

	public static void main(String[] args) {
		SpringApplication.run(InfluxApplication.class, args);
	}

}
