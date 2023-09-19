package za.co.discovery.assignment.jyotsna.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.discovery.assignment.jyotsna.exception.ResourceNotFoundException;
import za.co.discovery.assignment.jyotsna.model.Routes;
import za.co.discovery.assignment.jyotsna.repository.RoutesRepository;
import za.co.discovery.assignment.jyotsna.service.RouteService;


import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RoutesRepository routesRepository;
    @Override
    public List<Routes> getRoutes() {
        return routesRepository.findAll();
    }

    @Override
    public Routes updateRoute(Long id, Routes routeDetails) {
        Routes route = routesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Routes not found with id " + id));

        route.setRouteId(routeDetails.getRouteId());
        route.setDistance(routeDetails.getDistance());
        route.setPlanetDestination(routeDetails.getPlanetDestination());
        route.setPlanetOrigin(routeDetails.getPlanetOrigin());
      return   routesRepository.save(route);
    }

    @Override
    public Routes getRoutesById(Long id) {
        return routesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Routes not found with id " + id));
    }

    @Override
    public void deleteRoutes(Long id) {
        Routes route = routesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Routes not found with id " + id));
        routesRepository.delete(route);
    }

}
