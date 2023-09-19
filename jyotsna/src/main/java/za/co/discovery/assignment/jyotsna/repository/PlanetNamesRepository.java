package za.co.discovery.assignment.jyotsna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.discovery.assignment.jyotsna.model.PlanetNames;


@Repository
public interface PlanetNamesRepository extends JpaRepository<PlanetNames, Long> {

}
