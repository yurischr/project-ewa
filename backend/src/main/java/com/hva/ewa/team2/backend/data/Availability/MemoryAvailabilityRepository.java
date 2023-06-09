package com.hva.ewa.team2.backend.data.Availability;

import com.hva.ewa.team2.backend.domain.models.availability.Availability;
import com.hva.ewa.team2.backend.domain.models.availability.CreateAvailabilityRequest;


import java.util.List;
import java.util.Optional;

@Deprecated
public interface MemoryAvailabilityRepository {

    List<Availability> fetchAllAvailabilityByUser(int userId, int weekNumber);
    Optional<Availability> fetchAvailabilityById(int id);

    Availability createAvailability(CreateAvailabilityRequest request) throws Exception;

    Availability updateAvailability(int id, Availability availabilityRegistration) throws Exception;

    Optional<Availability> deleteAvailability(int id) throws Exception;

    List<Availability> copyAvailabilityToNextWeek(int userId, int weekNumber) throws Exception;

}
