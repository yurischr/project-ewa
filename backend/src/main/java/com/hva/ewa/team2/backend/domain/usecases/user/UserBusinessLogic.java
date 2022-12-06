package com.hva.ewa.team2.backend.domain.usecases.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.hva.ewa.team2.backend.domain.models.user.Address;
import com.hva.ewa.team2.backend.domain.models.user.User;
import com.hva.ewa.team2.backend.rest.user.json.JsonUserData;

import java.io.IOException;
import java.util.List;

public interface UserBusinessLogic {
    User getUserById(int id);

    List<User> getAllUsers();

    List<User> getUsersByRole(String role);

    User updateUser(int id, JsonUserData body) throws IOException;

    User addUser(String role, JsonNode body);

    User deleteUserById(int id);

    Address getUsersAddressById(int id);
}
