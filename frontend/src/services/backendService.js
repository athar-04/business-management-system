const BACKEND_URL = "https://business-management-system-hplr.onrender.com";

export async function wakeBackend() {
    for (let attempt = 1; attempt <= 6; attempt++) {
        try {
            const response = await fetch(`${BACKEND_URL}/health`, {
                method: "GET",
                cache: "no-cache",
            });

            if (response.ok) {
                console.log("Backend is ready");
                return true;
            }
        } catch (error) {
            console.log(`Backend wake-up attempt ${attempt} failed`);
        }

        await new Promise((resolve) => setTimeout(resolve, 10000));
    }

    console.error("Backend could not be reached");
    return false;
}