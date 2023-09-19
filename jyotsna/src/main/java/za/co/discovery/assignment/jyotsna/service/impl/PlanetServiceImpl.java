package za.co.discovery.assignment.jyotsna.service.impl;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import za.co.discovery.assignment.jyotsna.model.Edge;
import za.co.discovery.assignment.jyotsna.model.PlanetNames;
import za.co.discovery.assignment.jyotsna.model.Routes;
import za.co.discovery.assignment.jyotsna.model.Traffic;
import za.co.discovery.assignment.jyotsna.repository.PlanetNamesRepository;
import za.co.discovery.assignment.jyotsna.repository.RoutesRepository;
import za.co.discovery.assignment.jyotsna.repository.TrafficRepository;
import za.co.discovery.assignment.jyotsna.service.PlanetServices;


import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlanetServiceImpl implements PlanetServices {

	@Autowired
	private PlanetNamesRepository planetNamesRepo;

	@Autowired
	private RoutesRepository routesRepo;

	@Autowired
	private TrafficRepository trafficRepo;

	/**
	 * Loads planet data into the database from an Excel file.
	 *
	 * @param file The Excel file containing planet data.
	 * @return A message indicating the success or failure of the data upload.
	 */

	@Override
	public String loadPlanetsDataToDataBase(MultipartFile file) {

		XSSFWorkbook workbook = null;

		try {
			workbook = new XSSFWorkbook(file.getInputStream());
		} catch (IOException e) {

			e.printStackTrace();
		}

		for (Sheet sheet : workbook) {

			populateAndLoadData(sheet);

		}

		return "Data Successfully uploaded into the Database";

	}

	private void populateAndLoadData(Sheet sheet) {
		PlanetNames planetNames;
		Routes routes;
		Traffic traffic;
		Iterator<Row> rows = sheet.iterator();
		List<Traffic> trafficData = new ArrayList<>();
		List<Routes> routesData = new ArrayList<>();
		List<PlanetNames> planetNamesData = new ArrayList<>();

		String sheetName = sheet.getSheetName();

		if (sheetName.equalsIgnoreCase("Traffic")) {
			while (rows.hasNext()) {
				traffic = new Traffic();
				Row row = rows.next();
				if (row.getRowNum() == 0) {
					continue; // Skip header row
				}
				traffic = Traffic.builder().routeId((long) row.getCell(0).getNumericCellValue())
						.planetOrgin(row.getCell(1).getStringCellValue())
						.planetDestination(row.getCell(2).getStringCellValue())
						.trafficDelay((float) row.getCell(3).getNumericCellValue()).build();
				trafficData.add(traffic);

			}
			trafficRepo.saveAll(trafficData);

		}
		if (sheetName.equalsIgnoreCase("Routes")) {

			while (rows.hasNext()) {

				Row row = rows.next();
				if (row.getRowNum() == 0) {
					continue; // Skip header row
				}
				routes = Routes.builder().routeId((long) row.getCell(0).getNumericCellValue())
						.planetOrigin(row.getCell(1).getStringCellValue())
						.planetDestination(row.getCell(2).getStringCellValue())
						.distance((float) row.getCell(3).getNumericCellValue()).build();

				routesData.add(routes);

			}
			routesRepo.saveAll(routesData);

		}
		if (sheetName.equalsIgnoreCase("Planet Names")) {
			while (rows.hasNext()) {

				Row row = rows.next();
				if (row.getRowNum() == 0) {
					continue; // Skip header row
				}

				planetNames = new PlanetNames();
				planetNames.setPlanetNode(row.getCell(0).getStringCellValue());
				planetNames.setPlanetName(row.getCell(1).getStringCellValue());

				planetNamesData.add(planetNames);

			}

			planetNamesRepo.saveAll(planetNamesData);

		}

	}

	/**
	 * Retrieves a list of all planet names.
	 *
	 * @return A list of planet names.
	 */
	@Override
	public List<PlanetNames> getAllPlanetsData() {

		return planetNamesRepo.findAll();

	}

	public Edge populateNode(Routes route) {
		return new Edge(route.getPlanetDestination(), route.getDistance());

	}

	/**
	 * Finds the shortest path between two planets.
	 *
	 * @param source      The source planet.
	 * @param destination The destination planet.
	 * @return A map containing the shortest path and distances between planets.
	 */

	@Override
	public Map<String, Double> findShortestPath(String source, String destination) {

		List<Routes> routesList = routesRepo.findAll();

		Map<String, List<Edge>> edgeGraph = routesList.stream().collect(Collectors.groupingBy(Routes::getPlanetOrigin,
				Collectors.mapping(node -> populateNode(node), Collectors.toList())));

		Map<String, Double> shortestDistances = dijkstra(edgeGraph, source, destination);
		return shortestDistances;

	}

	private Map<String, Double> dijkstra(Map<String, List<Edge>> edgeGraph, String origin, String destination) {

		Map<String, Double> distances = new HashMap<>();
		Map<String, Double> shortestDistances = new HashMap<>();
		PriorityQueue<String> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));

		for (String planet : edgeGraph.keySet()) {
			distances.put(planet, Double.POSITIVE_INFINITY);
		}

		distances.put(origin, 0.0);
		priorityQueue.add(origin);

		while (!priorityQueue.isEmpty()) {
			String currentPlanet = priorityQueue.poll();
			if (edgeGraph.get(currentPlanet) != null) {
				for (Edge edge : edgeGraph.get(currentPlanet)) {
					String neighbor = edge.getDestination();
					double newDistance = distances.get(currentPlanet) + edge.getDistance();

					if (distances.get(neighbor) != null && newDistance < distances.get(neighbor)) {
						distances.put(neighbor, newDistance);
						priorityQueue.add(neighbor);
					}
				}
			}
		}

		if (distances.containsKey(destination)) {
			shortestDistances.put(destination, distances.get(destination));
		}

		return shortestDistances;


	}

}
