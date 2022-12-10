package com.hva.ewa.team2.backend.domain.usecases.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.hva.ewa.team2.backend.domain.models.user.Address;
import com.hva.ewa.team2.backend.domain.models.user.User;

import java.util.List;

public interface UserBusinessLogic {
    User getUserById(int id);

    Iterable<User> getAllUsers();

    List<User> getUsersByRole(User.Role role);

    User updateUser(int id, JsonNode body);

    User addUser(String role, JsonNode body);

    User deleteUserById(int id);

    Address getUsersAddressById(int id);
}