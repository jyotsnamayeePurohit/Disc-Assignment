package za.co.discovery.assignment.jyotsna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.discovery.assignment.jyotsna.model.Traffic;

@Repository
public interface TrafficRepository extends JpaRepository<Traffic, Long> {

}
