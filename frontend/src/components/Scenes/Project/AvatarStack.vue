<template>
<div class="flex -space-x-2 overflow-hidden">
  <Asset :src="client.avatarUrl" alt="test"
       class="inline-block h-8 w-8 rounded-full border"/>

  <Asset v-for="participant in participants.slice(0,4)" :key="participant" :src="participant.user.avatarUrl" alt="test"
       class="inline-block h-8 w-8 rounded-full border"/>


  <p class="pl-3 pt-2 text-sm font-medium" v-if="participants.length > 4"> + {{ participants.length - 4 }} </p>
</div>
</template>

<script>

import Asset from "../../Common/Asset.vue";

export default {
  name: "AvatarStack",
  components: {Asset},
  props: {
    participants: {
      type: Array,
      required: true,
    },
    client: {
      type: Object,
      required: false,
    }
  },
}
</script>

<style scoped>
.border {
  border: 1px solid #fff;
}
</style>