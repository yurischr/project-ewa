<template>
    <div>
        <NavBar></NavBar>
        <router-view></router-view>
    </div>
</template>

<script>
import {DateService} from "./Services/DateService.js";
import FetchService from "./Services/FetchService.js";
import {StoredTokenRepository} from "./Networking/Authentication/StoredTokenRepository.js";
import {AuthenticationRepository} from "./Networking/Authentication/AuthenticationRepository.js";
import {HourRegistrationRepository} from "./Networking/HourRegistration/HourRegistrationRepository.js";
import {AvailabilityRepository} from "./Networking/Availibility/AvailabilityRepository.js";
import Holidays from "date-holidays";
import {ProjectRepository} from "./Networking/Project/ProjectRepository.js";
import {UserRepository} from "./Networking/UserRepository.js";
import {AssetsService} from "./Networking/AssetsService.js";
import SkillsRepository from "./Networking/User/SkillsRepository.js";
import {StringService} from "./Services/StringService.js";

export default {
    name: "App",
    provide() {
        const storedTokenRepository = StoredTokenRepository.shared

        return {
            hourRegistrationRepository: new HourRegistrationRepository(),
            dateService: new DateService(),
            projectFetchService: new FetchService("projects"),
            skillsRepository: new SkillsRepository(),
            specialistFetchService: new FetchService("/specialists"),
            userFetchService: new FetchService("users"),
            fetchService: new FetchService(""),
            availabilityRepository: new AvailabilityRepository(),
            storedTokenRepository: storedTokenRepository,
            authenticationRepository: new AuthenticationRepository(storedTokenRepository),
            holidays: new Holidays('NL'),
            projectRepository: new ProjectRepository(),
            userRepository: new UserRepository(),
            assetsService: new AssetsService(),
            stringService: new StringService(),
        }
    },
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap');
</style>