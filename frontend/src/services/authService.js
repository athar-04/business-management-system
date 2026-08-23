import api from "./api";

export const login = async (credentials) => (await api.post("/auth/login", credentials)).data;
export const register = async (details) => (await api.post("/auth/register", details)).data;

export const saveSession = (response) => {
    if (response.token) localStorage.setItem("token", response.token);
};

export const logout = () => localStorage.removeItem("token");
export const isAuthenticated = () => Boolean(localStorage.getItem("token"));