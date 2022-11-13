package com.hva.ewa.team2.backend.domain.models.Project;

import com.hva.ewa.team2.backend.domain.models.user.Client;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Project {

    @Getter
    private final Client client;
    @Getter @Setter
    private final int id;
    @Getter @Setter
    private final String title;
    @Getter @Setter
    private final String description;
    @Getter @Setter
    private List<ProjectParticipant> participants;

    public Project(int id, String title, String description, Client client) {
        this(id, title, description, client, new ArrayList<>());
    }

    public Project(int id, String title, String description, Client client, List<ProjectParticipant> specialists) {
        this.id = id;
        this.title = title;
        this.client = client;
        this.description = description;
        this.participants = specialists;
    }

    public void addSpecialist(ProjectParticipant specialist) {
        participants.add(specialist);
    }

    public void removeSpecialist(ProjectParticipant specialist) {
        participants.remove(specialist);
    }

}
