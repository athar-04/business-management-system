import api from "./api";

const resource = (path) => ({
    list: async () => (await api.get(path)).data,
    create: async (data) => (await api.post(path, data)).data,
    update: async (id, data) => (await api.put(`${path}/${id}`, data)).data,
    remove: async (id) => api.delete(`${path}/${id}`),
});

export const categoryService = resource("/categories");
export const brandService = resource("/brands");
export const godownService = resource("/godowns");
export const customerService = resource("/customers");
export const supplierService = resource("/suppliers");
export const expenseService = resource("/expenses");
export const inventoryService = resource("/inventory");
export const productService = resource("/products");

export const purchaseService = {
    list: async () => (await api.get("/purchases")).data,
    create: async (data) => (await api.post("/purchases", data)).data,
    remove: async (id) => api.delete(`/purchases/${id}`),
};
export const saleService = {
    list: async () => (await api.get("/sales")).data,
    create: async (data) => (await api.post("/sales", data)).data,
    remove: async (id) => api.delete(`/sales/${id}`),
};
export const reportService = {
    business: async () => (await api.get("/reports/business")).data,
    customers: async () => (await api.get("/reports/customer-outstandings")).data,
    suppliers: async () => (await api.get("/reports/supplier-outstandings")).data,
};
export const paymentService = {
    customer: async (data) => (await api.post("/customer-payments", data)).data,
    supplier: async (data) => (await api.post("/supplier-payments", data)).data,
    customerHistory: async (saleId) => (await api.get(`/customer-payments/sale/${saleId}`)).data,
    supplierHistory: async (purchaseId) => (await api.get(`/supplier-payments/purchase/${purchaseId}`)).data,
};