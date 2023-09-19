package za.co.discovery.assignment.jyotsna.service;

import org.springframework.web.multipart.MultipartFile;
import za.co.discovery.assignment.jyotsna.model.PlanetNames;


import java.util.List;
import java.util.Map;

public interface PlanetServices {

	public String loadPlanetsDataToDataBase(MultipartFile file);

	public List<PlanetNames> getAllPlanetsData();

	public Map<String, Double> findShortestPath(String source, String destination);

}
