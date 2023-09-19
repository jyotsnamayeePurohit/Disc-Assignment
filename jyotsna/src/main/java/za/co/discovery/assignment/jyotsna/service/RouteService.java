package za.co.discovery.assignment.jyotsna.service;

import za.co.discovery.assignment.jyotsna.model.Routes;


import java.util.List;

public interface RouteService {
    public List<Routes> getRoutes();
    public Routes updateRoute(Long id, Routes routeDetails);
    public Routes getRoutesById(Long id);
    public void deleteRoutes(Long id);


}
