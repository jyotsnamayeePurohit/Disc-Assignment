package za.co.discovery.assignment.jyotsna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.co.discovery.assignment.jyotsna.service.PlanetServices;


import java.util.Map;

/**
 * This class defines the REST endpoints of loading the data and finding the shortest path.
 */
@RestController
@RequestMapping("api/planet")
public class PlanetDataController {

	@Autowired
	private PlanetServices planetService;

	/**
	 * Uploads planet data from a file to the derby database.
	 *
	 * @param file The multipart file containing planet data in excel.
	 * @return A message indicating the success or failure of the data upload.
	 */
	@PostMapping("/data/load")
	public String loadPlanetsData(@RequestParam("file") MultipartFile file) {
		return planetService.loadPlanetsDataToDataBase(file);
	}

	/**
	 * Finds the shortest path between two planets.
	 *
	 * @param source      The source planet.
	 * @param destination The destination planet.
	 * @return A map containing the shortest path and distances between planets.
	 */
	@GetMapping("/findShortest/path")
	public Map<String, Double> submitForm(@RequestParam("source") String source,
			@RequestParam("destination") String destination) {
		return planetService.findShortestPath(source, destination);
	}




}
