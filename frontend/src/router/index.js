import {createRouter, createWebHashHistory} from "vue-router";

import ProjectLayout from "../components/Scenes/Project/Scenes/ProjectLayout.vue";
import LogIn from "../components/Scenes/Authentication/LogIn.vue";
import ForgotPassword from "../components/Scenes/Authentication/ForgotPassword.vue";
import ProjectList from "../components/Scenes/Project/Scenes/ProjectList.vue";
import ChangePassword from "../components/Scenes/Authentication/ChangePassword.vue";
import AdminPanel from "../components/Scenes/Adminpanel/Adminpanel.vue";
import AddClient from "../components/Scenes/Adminpanel/AddClient.vue";
import CustomerList from "../components/Scenes/Adminpanel/CustomerList.vue";
import EmployeeList from "../components/Scenes/Adminpanel/EmployeeList.vue";
import SpecialistHourRegistrationOverview from "../components/Scenes/SpecialistHourRegistration/SpecialistHourRegistrationOverview.vue";
import SpecialistAvailibilityOverview from "../components/Scenes/SpecialistAvailibility/SpecialistAvailibilityOverview.vue";

// Profile components
import Profile from "../components/Scenes/Profile/Profile.vue";
import PublicProfile from "../components/Scenes/Profile/PublicProfile.vue";
import PersonalInfo from "../components/Scenes/Profile/PersonalInfo.vue";
import SkillsOverview from "../components/Scenes/Profile/SkillsOverview.vue";
import Resume from "../components/Scenes/Profile/Resume.vue";
import AddParticipants from "../components/Scenes/AddParticipants/AddParticipants.vue";
import ProjectOverview from "../components/Scenes/Project/Scenes/ProjectOverview.vue";
import CreateProject from "../components/Scenes/Project/Scenes/CreateProject.vue";
import BaseHome from "../components/Scenes/Home/BaseHome.vue";


const routes = [
    {path: '/', component: BaseHome, name: 'home'},
    {path: '/login', component: LogIn, name: "login"},
    {path: '/login/forgotpassword', component: ForgotPassword},
    {path: '/login/forgotpassword/cp', component: ChangePassword},
    {
        path: "/profile",
        component: Profile,
        children: [
            {
                path: '',
                name: "profile",
                redirect: '/profile/personal-info', // default child path
            },
            {
                path: 'personal-info',
                component: PersonalInfo,
            },
            {
                path: 'available-hours',
                component: SpecialistAvailibilityOverview,
            },
            {
                path: "hour-registration",
                name: "hour-registration",
                component: SpecialistHourRegistrationOverview
            },
            {
                path: "skills",
                component: SkillsOverview,
            },
            {
                path: 'resume',
                component: Resume,
            },
        ]
    },
    {
        path: '/profile/public/:Id',
        component: PublicProfile,
    }, {
        path: '/adminpanel',
        name: "adminpanel",
        component: AdminPanel,
        children: [
            {
                path: "/adminpanel",
                redirect: "/adminpanel/customer-list",
            },
            {
                path: "customer-list",
                component: CustomerList,
            },
            {
                path: "employee-list",
                component: EmployeeList,
            },
        ]
    }, {
        path: "/adminpanel/add-client",
        component: AddClient,
    }, {
        path: "/projects",
        name: "projects",
        component: ProjectList
    }, {
        path: "/projects/new",
        name: "new-project",
        component: CreateProject,
        props: {
            newProject: true,
        }
    }, {
        path: "/projects/:projectId(\\d+)",
        props: true,
        name: "project",
        component: ProjectLayout,
        children: [
            {
                path: '',
                name: "project-overview",
                component: ProjectOverview
            }, {
                path: "participants",
                name: "project-participants",
                component: AddParticipants,
            }
        ]
    }, {
        path: "/projects/:projectId(\\d+)/edit",
        name: "edit-project",
        component: CreateProject,
        props: route => ({
            newProject: false,
            projectId: route.params.projectId,
        }),
    },
    { path: '/:pathMatch(.*)*', redirect: '/login'},
];

export const router = createRouter({
    history: createWebHashHistory(),
    routes
});