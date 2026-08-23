import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { LogIn } from "lucide-react";
import { login, saveSession } from "../services/authService";
import { apiError } from "../utils/formatters";

function LoginPage() {
    const navigate = useNavigate();
    const [form, setForm] = useState({ email: "", password: "" });
    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);

    const submit = async (event) => {
        event.preventDefault();
        setError("");
        setLoading(true);
        try {
            saveSession(await login(form));
            navigate("/dashboard", { replace: true });
        } catch (err) { setError(apiError(err, "Invalid email or password.")); }
        finally { setLoading(false); }
    };

    return <main className="auth-page"><form className="auth-card" onSubmit={submit}>
        <div className="auth-mark">B</div><p className="eyebrow">Business Manager</p>
        <h1>Welcome back</h1><p className="muted">Sign in to manage your business.</p>
        {error && <div className="alert error">{error}</div>}
        <label>Email<input type="email" required value={form.email} onChange={(e) => setForm({ ...form, email: e.target.value })} /></label>
        <label>Password<input type="password" required value={form.password} onChange={(e) => setForm({ ...form, password: e.target.value })} /></label>
        <button className="primary-button" disabled={loading}><LogIn size={17} />{loading ? "Signing in..." : "Sign in"}</button>
        <p className="auth-footer">Need an account? <Link to="/register">Create one</Link></p>
    </form></main>;
}

export default LoginPage;