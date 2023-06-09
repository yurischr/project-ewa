package com.hva.ewa.team2.backend.data.hourregistration;

import com.hva.ewa.team2.backend.domain.models.hourregistration.HourRegistration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface HourRegistrationRepository extends CrudRepository<HourRegistration, Integer> {

    @Query(value = """
            SELECT DISTINCT *
                FROM hour_registration h
                WHERE h.project_id = :projectId
                ORDER BY `status` IS NULL DESC, `from` DESC""",
            nativeQuery = true)
    List<HourRegistration> findAllByProject(int projectId);

    @Query(value = """
            SELECT hr FROM HourRegistration hr
            JOIN hr.projectParticipant pp
            JOIN pp.specialist s
            JOIN pp.project p
            WHERE s.id = :userId OR p.client.id = :userId
            ORDER BY hr.from DESC
            """)
    List<HourRegistration> findAllByUser(int userId);

    @Query(value = """
            SELECT DISTINCT h.*
                FROM hour_registration h
                INNER JOIN project_participant p
                    ON (h.project_participant_id=p.id)
                WHERE h.project_id = :projectId
                    AND p.user_id = :userId
                ORDER BY `status` IS NULL DESC, `from` DESC""",
            nativeQuery = true)
    List<HourRegistration> findAllByProjectParticipant(int projectId, int userId);

    @Query(value = """
            SELECT COALESCE(SUM(time_to_sec(timediff(h.to, h.from)) / 3600), 0)
                FROM hour_registration h
                WHERE h.project_id = :projectId
                    AND h.status <> 'REJECTED'""",
            nativeQuery = true)
    Double getTotalHoursForProject(int projectId);

    @Query(value = """
            SELECT COALESCE(SUM(time_to_sec(timediff(h.to, h.from)) / 3600),0)
                FROM hour_registration h
                INNER JOIN project_participant pp
                    ON (pp.project_id = h.project_id
                    AND pp.id = h.project_participant_id)
                WHERE h.project_id = :projectId
                    AND pp.user_id = :userId
                    AND h.status <> 'REJECTED'""",
            nativeQuery = true)
    Double getTotalHoursForProject(int projectId, int userId);

    @Query(value = """
            SELECT COALESCE(SUM(time_to_sec(timediff(h.to, h.from)) / 3600), 0)
                FROM hour_registration h
                WHERE h.project_id = :projectId
                    AND h.status <> 'REJECTED'
                    AND h.`from` BETWEEN :from AND :to""",
            nativeQuery = true)
    Double getTotalHoursForProjectBetween(int projectId, LocalDateTime from, LocalDateTime to);


    @Query(value = """
            SELECT COALESCE(SUM(time_to_sec(timediff(h.to, h.from)) / 3600),0)
                FROM hour_registration h
                INNER JOIN project_participant pp
                    ON (pp.project_id = h.project_id
                    AND pp.id = h.project_participant_id)
                WHERE h.project_id = :projectId
                    AND pp.user_id = :userId
                    AND h.status <> 'REJECTED'
                    AND h.`from` BETWEEN :from AND :to""",
            nativeQuery = true)
    Double getTotalHoursForProjectBetween(int projectId, int userId, LocalDateTime from, LocalDateTime to);

    @Query(value = """
            SELECT COALESCE(SUM(time_to_sec(timediff(h.to, h.from)) / 3600 * pp.hourly_rate), 0)
                FROM hour_registration h
                INNER JOIN project_participant pp
                    ON (pp.project_id = h.project_id
                    AND pp.id = h.project_participant_id)
                WHERE h.project_id = :projectId
                    AND h.status <> 'REJECTED'""",
            nativeQuery = true)
    Double getTotalCostsForProject(int projectId);


    @Query(value = """
            SELECT COALESCE(SUM(time_to_sec(timediff(h.to, h.from)) / 3600 * pp.hourly_rate), 0)
                FROM hour_registration h
                INNER JOIN project_participant pp
                    ON (pp.project_id = h.project_id
                    AND pp.id = h.project_participant_id)
                WHERE h.project_id = :projectId 
                    AND pp.user_id = :userId 
                    AND h.status <> 'REJECTED'""",
            nativeQuery = true)
    Double getTotalRevenueForProject(int projectId, int userId);

    @Query(value = """
            SELECT COALESCE(SUM(time_to_sec(timediff(h.to, h.from)) / 3600 * pp.hourly_rate), 0)
                FROM hour_registration h
                INNER JOIN project_participant pp
                    ON (pp.project_id = h.project_id
                    AND pp.id = h.project_participant_id)
                LEFT JOIN project p
                    ON (h.project_id=p.id)
                WHERE (pp.user_id = :userId OR p.client_id = :userId)
                    AND h.status <> 'REJECTED'""",
            nativeQuery = true)
    Double getTotalRevenueForUser(Integer userId);

    @Query(value = """
            SELECT COALESCE(SUM(time_to_sec(timediff(h.to, h.from)) / 3600),0)
                FROM hour_registration h
                INNER JOIN project_participant pp
                    ON (pp.project_id = h.project_id
                    AND pp.id = h.project_participant_id)
                LEFT JOIN project p
                    ON (h.project_id=p.id)
                WHERE (pp.user_id = :userId OR p.client_id = :userId)
                    AND h.status <> 'REJECTED'""",
            nativeQuery = true)
    Double getTotalHoursForUser(Integer userId);

}
