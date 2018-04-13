package com.schedule.availability.models.data;

import com.schedule.availability.models.Availability;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface AvailabilityDao extends CrudRepository<Availability, Integer> {

}