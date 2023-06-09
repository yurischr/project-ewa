package com.hva.ewa.team2.backend.domain.usecases.availability;

import com.hva.ewa.team2.backend.domain.models.availability.Availability;
import com.hva.ewa.team2.backend.domain.models.availability.CreateAvailabilityRequest;
import com.hva.ewa.team2.backend.domain.models.availability.UpdateAvailabilityRequest;

import java.util.List;
import java.util.Optional;

public interface AvailabilityBusinessLogic {

    List<Availability> handleFetchAvailabilityByUser(int userId, Optional<Integer> weekNumber, Optional<Integer> year);

    Availability handleCreateAvailability(CreateAvailabilityRequest request) throws Exception;

    Optional<Availability> handleDeleteAvailability(int id) throws Exception;

    Availability handleUpdateAvailability(int id, UpdateAvailabilityRequest request) throws Exception;

Optional<Availability> handleGetAvailabilityById(int id) throws Exception;

    List<Availability> handleCopyAvailabilityToNextWeek(int userId, Optional<Integer> week, Optional<Integer> year) throws Exception;

}
