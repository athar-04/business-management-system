import api from "./api";

export const getAllProducts = async () => {
    const response = await api.get("/products");
    return response.data;
};

export const createProduct = async (product) => (await api.post("/products", product)).data;
export const updateProduct = async (id, product) => (await api.put(`/products/${id}`, product)).data;
export const deleteProduct = async (id) => api.delete(`/products/${id}`);