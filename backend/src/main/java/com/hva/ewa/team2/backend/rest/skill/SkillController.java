package com.hva.ewa.team2.backend.rest.skill;

import com.hva.ewa.team2.backend.domain.models.skill.Expertise;
import com.hva.ewa.team2.backend.domain.models.skill.SkillGroup;
import com.hva.ewa.team2.backend.domain.models.skill.UserSkill;
import com.hva.ewa.team2.backend.domain.models.user.User;
import com.hva.ewa.team2.backend.domain.usecases.skill.SkillBusinessLogic;
import com.hva.ewa.team2.backend.rest.skill.json.JsonUserSkill;
import com.hva.ewa.team2.backend.rest.skill.json.JsonUserSkillGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/skills")
public class SkillController {

    private final SkillBusinessLogic skillBusinessLogic;

    @Autowired
    public SkillController(SkillBusinessLogic skillBusinessLogic) {
        this.skillBusinessLogic = skillBusinessLogic;
    }

    @GetMapping(path = "/groups", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SkillGroup>> getSkillGroups() {
        return ResponseEntity.ok(skillBusinessLogic.getAllSkillGroups());
    }

    @GetMapping(path = "/user-skills/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserSkill>> getUserSkills(@PathVariable int userId) {
        return ResponseEntity.ok(skillBusinessLogic.getUserSkills(userId));
    }

    @PutMapping(path = "/update-user-skill/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserSkill> updateUserSkill(@PathVariable int userId, @RequestBody JsonUserSkill body) {
        return ResponseEntity.ok(skillBusinessLogic.updateUserSkill(userId, body));
    }

    @PutMapping(path = "/update-user-skill-group/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserSkill>> updateUserSkillGroup(@PathVariable int userId, @RequestBody List<JsonUserSkill> body) {
        return ResponseEntity.ok(skillBusinessLogic.updateUserSkillGroup(userId, body));
    }

    @GetMapping(path = "/expertises", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Expertise>> getAllExpertises() {
        return ResponseEntity.ok(skillBusinessLogic.getAllExpertises());
    }

    @GetMapping(path = "/expertise/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Expertise> getExpertise(@PathVariable int expertiseId) {
        return ResponseEntity.ok(skillBusinessLogic.getExpertise(expertiseId));
    }

    @GetMapping(path = "/user-expertises/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Expertise>> getUserExpertises(@PathVariable int userId) {
        return ResponseEntity.ok(skillBusinessLogic.getUserExpertises(userId));
    }

    @PutMapping(path = "/update-user-expertise/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Expertise>> updateUserExpertise(@PathVariable int userId, @RequestBody ArrayList<Expertise> body) {
        return ResponseEntity.ok(skillBusinessLogic.updateUserExpertise(userId, body));
    }
}
