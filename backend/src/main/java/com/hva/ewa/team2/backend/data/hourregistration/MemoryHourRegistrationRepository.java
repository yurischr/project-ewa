package com.hva.ewa.team2.backend.data.hourregistration;

import com.hva.ewa.team2.backend.domain.models.hourregistration.CreateHourRegistrationRequest;
import com.hva.ewa.team2.backend.domain.models.hourregistration.HourRegistration;
import com.hva.ewa.team2.backend.domain.models.project.Project;

import java.util.List;
import java.util.Optional;

@Deprecated
public interface MemoryHourRegistrationRepository {

    List<HourRegistration> fetchAllHourRegistrations();

    List<HourRegistration> fetchAllHourRegistrationsByUser(int userId);
    Optional<HourRegistration> fetchHourRegistrationById(int id);
    List<HourRegistration> fetchAllHourRegistrationByProject(int projectId);
    List<HourRegistration> fetchAllHourRegistrationByProjectUser(Integer projectId, Integer userId);

    List<HourRegistration> fetchAllAcceptedHoursForProject(Integer projectId);

    HourRegistration createHourRegistration(CreateHourRegistrationRequest request, Project project) throws Exception;

    HourRegistration updateHourRegistration(int id, HourRegistration hourRegistration) throws Exception;
    Optional<HourRegistration> deleteHourRegistration(int id) throws Exception;
}
