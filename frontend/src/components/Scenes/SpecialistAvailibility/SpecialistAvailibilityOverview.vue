<template>
    <div>

      <div v-if="this.showingModel" @click="" class="top-0 bottom-0 right-0 left-0 absolute">
        <div class="z-90 w-full h-full bg-neutral-900 opacity-20 absolute"></div>
        <div class="z-60 w-full h-full absolute flex justify-center items-center">
          <NewAvailabilityPopup @activity-added="handleActivityAdded" @activity-cancel-clicked="showingModel = false"/>
        </div>
      </div>

        <div class="font-bold text-neutral-900 text-[32px] mb-0">Beschikbaarheid</div>
        <div class="flex">
            <div class="flex flex-col gap-[11px]">
                <div class="flex justify-between w-full">
                    <p class="text-3xl text-neutral-400 font-medium ">{{ year }}</p>
                    <button @click="handleTodayClicked" class="primary-button">Deze week</button>
                </div>

                <div class="flex justify-center gap-[11px] pt-2">
                    <div
                            @click="handlePrevWeekCLicked"
                            class="border-[1px] border-neutral-100 bg-neutral-50 rounded w-[50px] h-[50px] cursor-pointer flex justify-center items-center hover:bg-neutral-100"
                    >
                        <font-awesome-icon class="text-primary-500" icon="fa-solid fa-chevron-left"/>
                    </div>

                    <div v-for="day in week">
                      <div class="flex flex-col">
                        <div class="unselected-day-container">
                          <p class="font-medium text-neutral-800">
                            {{ day.day }}</p>
                          <p class="text-neutral-600">{{day.date}}</p>
                        </div>
                        <div class="flex flex-col gap-3 pt-4">
                          <AvailabilitySlot @click="this.showingModel = true"/>

                          <AvailabilitySlot  @click="this.showingModel = true"></AvailabilitySlot>
                          <div
                              @click="this.showingModel = true"
                              class="w-full h-[31px] bg-primary-50 rounded-[9px] text-primary-500 font-semibold flex justify-center items-center cursor-pointer hover:bg-primary-100"
                          >+ Toevoegen</div>
                        </div>
                      </div>
                    </div>

                    <div
                            @click="handleNextWeekClicked"
                            class="border-[1px] border-neutral-100 bg-neutral-50 rounded w-[50px] h-[50px] cursor-pointer flex justify-center items-center hover:bg-neutral-100"
                    >
                        <font-awesome-icon class="text-primary-500" icon="fa-solid fa-chevron-right"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import PrimaryButton from "../../Common/PrimaryButton.vue";
import CalendarDayOption from "../SpecialistHourRegistration/elements/CalendarDayOption.vue";
import AvailabilitySlot from "./elements/AvailabilitySlot.vue";
import NewAvailabilityPopup from "./elements/NewAvailabilityPopup.vue";

export default {
    name: "SpecialistAvailibilityOverview",
    components: {PrimaryButton, CalendarDayOption, AvailabilitySlot, NewAvailabilityPopup},
    inject: ['hourRegistrationRepository', 'dateService', 'projectFetchService', 'availabilityRepository'],
    data() {
        return {
            week: [],
            hourRegistrations: [],
            filteredHourRegistrations: [],
            selectedHourRegistration: null,
            projects: [],
            weekNumber: 0,
            selectedDayIndex: null,
            year: "",
            showingModel: false
        }
    },
    async created() {
        await this.loadHourRegistrationsList();
        this.selectToday();

        const data = await this.availabilityRepository.fetchAvailabilityForUserInWeek(1, this.weekNumber);
        console.log(data);
    },
    methods: {
        loadWeekBar() {
            this.week = this.dateService.isoWeekDays(this.weekNumber)
                .map(day => {
                    return {
                        day: day.date.format("dddd"),
                        date: day.date.format("DD MMM"),
                        weekDayIndex: day.weekDayIndex,
                    }
                });
            this.year = this.dateService.weekOfYear(this.weekNumber).format('YYYY');
        },

        async loadHourRegistrationsList() {
            this.hourRegistrations = await this.hourRegistrationRepository.fetchAllFor(0);
        },

        handleTodayClicked() {
            this.selectToday();
        },

        selectToday() {
            this.selectedDayIndex = this.dateService.currentDayOfWeek();
            this.weekNumber = this.dateService.currentWeekOfYear();
            this.filterHourRegistrations();
            this.loadWeekBar();
        },

        filterHourRegistrations() {
            const weekDayDate = this.dateService.dayOfWeek(this.weekNumber, this.selectedDayIndex).toDate();
            this.filteredHourRegistrations = this.hourRegistrations.filter((hourReistration => {
                return this.dateService.isSameDay(hourReistration.from, weekDayDate)
            }));
        },

        isSelectedForDay(day) {
            return this.selectedDayIndex === day.weekDayIndex;
        },

        handleNextWeekClicked() {
            this.weekNumber += 1;
            this.loadWeekBar();
            this.filterHourRegistrations();
        },

        handlePrevWeekCLicked() {
            this.weekNumber -= 1;
            this.loadWeekBar();
            this.filterHourRegistrations();
        },

        handleAddActivityClicked() {
            this.showingModel = true
        },

        handleModelBackgroundClicked() {
            this.showingModel = false
        },

        async handleDeleteHourRegistrationClicked(id) {
            console.log(id);
            await this.hourRegistrationRepository.deleteHourRegistration(id);
            await this.loadHourRegistrationsList();
            this.filterHourRegistrations();
        },

        async handleActivityAdded() {
            await this.loadHourRegistrationsList();
            this.filterHourRegistrations();
            this.showingModel = false;
        },

        handleHourRegistrationClicked(hr) {
            this.selectedHourRegistration = hr;
            this.showingModel = true;
        }
    }
}
</script>

<style scoped>

.day-container-item {
  @apply flex flex-col items-center w-[121px] rounded-[6px] transition-colors ease-in-out cursor-pointer;
}

.selected-day-container {
  @apply day-container-item bg-primary-500 border-none;
}

.unselected-day-container {
  @apply day-container-item bg-neutral-50 border-[1px] border-neutral-100;
}

.hour-registration-row-shadow {
  box-shadow: 0 4px 25px 2px rgba(0, 0, 0, 0.08);
}

</style>