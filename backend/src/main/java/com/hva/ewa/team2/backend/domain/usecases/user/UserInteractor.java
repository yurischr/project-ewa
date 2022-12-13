package com.hva.ewa.team2.backend.domain.usecases.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hva.ewa.team2.backend.common.services.asset.AssetService;
import com.hva.ewa.team2.backend.data.user.UserRepository;
import com.hva.ewa.team2.backend.domain.models.user.*;
import com.hva.ewa.team2.backend.rest.user.json.JsonUserData;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class UserInteractor implements UserBusinessLogic {

    private final UserRepository userRepo;

    private final AssetService assetService;

    @Autowired
    public UserInteractor(UserRepository userRepo, AssetService assetService) {
        this.userRepo = userRepo;
        this.assetService = assetService;
    }

    @Override
    public User getUserById(int id) {
        return this.userRepo.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepo.findAll();
    }

    @Override
    public List<User> getUsersByRole(User.Role role) {
        return this.userRepo.findUsersByRole(role);
    }

    @Override
    public User updateUser(int id, JsonNode body) {
        System.out.println(body);
        Optional<User> found = this.userRepo.findById(id);
    public User updateUser(int id, JsonUserData body) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        User user = this.userRepo.getUserById(id);

        if (user instanceof Admin admin) {

        } else if (user instanceof Specialist specialist) {
//            Specialist newSpecialistData = mapper.readValue(body.getUser(), Specialist.class);
            System.out.println(body);
        } else if (user instanceof Client client) {

        }

        System.out.println(user.getRole());

//        Address newAddress;
//        newAddress = mapper.readValue(body.getAddress(), Address.class);
//

//        User userData;
//        userData = mapper.readValue(body.getUser(), User.class);



//        if(body.getAvatarFile().isPresent()) {
//            String extension = FilenameUtils.getExtension(body.getAvatarFile().get().getOriginalFilename());
//            System.out.println(body.getAvatarFile().get().getOriginalFilename());
//            assetService.uploadAsset(body.getAvatarFile().get(),"users/avatars/" + userData.getId() + "." + extension, true);
//        }
//
//
//
//        if (user == null) throw new IllegalStateException("There is no user found with that id!");


        return null;


//        user.setEmail(userData.getEmail());
//        user.setAvatarUrl(userData.getAvatarUrl());
//        user.setPassword(body.get("password").asText());



//        if (user instanceof Admin admin) {
//            if (body.get("firstName") == null || body.get("lastName") == null)
//                throw new IllegalStateException("The fields firstName and/or lastName isn't found!");
//            admin.setFirstName(body.get("firstName").asText());
//            admin.setLastName(body.get("lastName").asText());
//        } else if (user instanceof Specialist specialist) {
//            if (body.get("firstName") == null || body.get("lastName") == null)
//                throw new IllegalStateException("The fields firstName and/or lastName isn't found!");
//            specialist.setFirstName(body.get("firstName").asText());
//            specialist.setLastName(body.get("lastName").asText());
//            JsonNode newAddress = body.get("address");
//            Address address = new Address(
//                    newAddress.get("place").asText(),
//                    newAddress.get("street").asText(),
//                    newAddress.get("houseNumber").asInt(),
//                    newAddress.get("houseNumberAddition").asText(),
//                    newAddress.get("postalCode").asText()
//            );
//
//            specialist.setAddress(address);
//        } else if (user instanceof Client client) {
//            if (body.get("name") == null || body.get("bannerSrc") == null)
//                throw new IllegalStateException("The fields name and/or bannerURL isn't found!");
//            client.setName(body.get("name").asText());
//            client.setBannerSrc(body.get("bannerSrc").asText());
//        }

//        return this.userRepo.updateUser(user);
    }

    @Override
    public User addUser(String role, JsonNode body) {
        if (body.get("email") == null || body.get("password") == null || body.get("avatarUrl") == null)
            throw new IllegalStateException("The fields email and/or password and/or avatarUrl isn't found!");

        String email = body.get("email").asText();
        String password = body.get("password").asText();
        String avatarUrl = body.get("avatarUrl").asText();

        User user = switch (role) {
            case "admin" -> {
                if (body.get("firstName") == null || body.get("lastName") == null)
                    throw new IllegalStateException("The fields firstName and/or lastName isn't found!");
                yield new Admin(-1, email, password, avatarUrl,
                        body.get("firstName").asText(), body.get("lastName").asText());
            }
            case "client" -> {
                if (body.get("name") == null || body.get("bannerSrc") == null)
                    throw new IllegalStateException("The fields name and/or bannerURL isn't found!");
                yield new Client(-1, email, password, avatarUrl, body.get("name").asText(),
                        body.get("bannerSrc").asText());
            }
            default -> {
                if (body.get("firstName") == null || body.get("lastName") == null)
                    throw new IllegalStateException("The fields firstName and/or lastName isn't found!");
                yield new Specialist(-1, email, password, avatarUrl, body.get("firstName").asText(),
                        body.get("lastName").asText());
            }
        };
        return this.userRepo.save(user);
    }

    @Override
    public User deleteUserById(int id) {
        final Optional<User> found = this.userRepo.findById(id);

        if (found.isEmpty()) throw new IllegalStateException("There is no user found with that id!");

        this.userRepo.deleteById(id);
        return found.get();
    }

    @Override
    public Address getUsersAddressById(int id) {
        User user = this.userRepo.findById(id).orElse(null);
        if (!(user instanceof Specialist specialist)) return null;

        return specialist.getAddress();
    }

}
