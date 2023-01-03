import {NetworkClient} from "./NetworkClient.js";
import {HttpMethod} from "./HttpMethod.js";

export class UserRepository {

    #networkClient

    constructor() {
        this.#networkClient = new NetworkClient();
    }

    /**
     * Adds a client.
     * @param {String} name Name of the Client.
     * @param {String} email Chosen email of the Client.
     * @param {String} password Chosen password of the Client (plain-text)
     * @param {String|null} avatarUrl URL of the avatar of the Client.
     * @param {String|null} bannerSrc Default banner to use.
     * @return {Promise<Client|Object|null>} Added Client.
     */
    async addClient(name, email, password, avatarUrl = null, bannerSrc= null) {
        const path = `/users/add/client`;
        const body = {
            name: name,
            email: email,
            password: password,
            avatarUrl: avatarUrl,
            // bannerSrc: bannerSrc
        };

        let formData = new FormData();
        for (const [key, value] of Object.entries(body)) {
            formData.append(key, value);
        }

        for (const [key, value] of formData.entries()) {
            console.log(`key:${key}, value: ${value}`);
        }

        return await this.#networkClient.executeRequestWithFormData(path, HttpMethod.POST, formData);
    }

    /**
     * Fetches users, optionally filtered with a specified role.
     * @param {UserRole|null} role Fetches all users with a specified role, defaults to null.
     * @return {Promise<[User|Object]>} Fetched users
     */
    async fetchUsers(role= null) {
        const path = role ? `/users/role/${role}` : `/users/`;
        return await this.#networkClient.executeRequest(path);
    }
}
