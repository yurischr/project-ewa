<template>
  <div class="page-main-mw">
    <div class="p-4 ">
      <div class="profile-container flex-col">
        <div class="buttoncontainer">
          <router-link to="/adminpanel/add-client">
            <button id="addbutton" class="bg-primary-500 font-bold border-[1px] h-[38px] w-[180px] rounded-md text-neutral-0" @click="navigate" role="link">+ Klant toevoegen</button>
          </router-link>
        </div>
        <div class="container grow">
          <customer-list-details v-for="client in clients" :key="client.id" :client="client"></customer-list-details>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CustomerListDetails from "./ClientRow.vue";
import {UserRole} from "../../models/UserRole.js";

export default {
  name: "ClientList",
  components: {
    CustomerListDetails
  },
  inject: ['userRepository'],
  data() {
    return {
      clients: [],
      sideBarLinks: [
        {
          icon: 'fa-solid fa-share-nodes',
          name: 'Klanten',
          href: 'customer-list',
        },
        {
          icon: 'fa-solid fa-user',
          name: 'Specialisten',
          href: 'employee-list',
        }
      ],
    }
  },
  async created() {
    await this.loadClients();
  },
  methods: {
   async loadClients() {
     this.clients = await this.userRepository.fetchUsers(UserRole.client);
   }
  }
}
</script>

<style scoped>
.buttoncontainer{
  display: flex;
  justify-content: flex-end;
}
</style>