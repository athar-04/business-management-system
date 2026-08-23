export const money = (value) => `₹${Number(value || 0).toLocaleString("en-IN", { maximumFractionDigits: 2 })}`;

export const apiError = (error, fallback = "Something went wrong. Please try again.") => {
    if (!error.response) return "Unable to reach the server. Check that the backend is running.";
    if (error.response.status === 404) return "The requested record was not found.";
    return error.response.data?.message || error.response.data?.error || fallback;
};