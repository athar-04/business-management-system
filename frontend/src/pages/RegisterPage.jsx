import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { UserPlus } from "lucide-react";
import { register, saveSession } from "../services/authService";
import { apiError } from "../utils/formatters";

function RegisterPage() {
    const navigate = useNavigate();
    const [form, setForm] = useState({ firstName: "", lastName: "", email: "", password: "", businessName: "" });
    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);
    const submit = async (event) => {
        event.preventDefault(); setError(""); setLoading(true);
        try { const response = await register(form); saveSession(response); navigate(response.token ? "/dashboard" : "/login", { replace: true }); }
        catch (err) { setError(apiError(err, "Registration failed.")); }
        finally { setLoading(false); }
    };
    return <main className="auth-page"><form className="auth-card" onSubmit={submit}>
        <div className="auth-mark">B</div><p className="eyebrow">Business Manager</p><h1>Create account</h1>
        {error && <div className="alert error">{error}</div>}
        <div className="form-grid two"><label>First name<input required value={form.firstName} onChange={(e) => setForm({ ...form, firstName: e.target.value })} /></label><label>Last name<input required value={form.lastName} onChange={(e) => setForm({ ...form, lastName: e.target.value })} /></label></div>
        <label>Business name<input required value={form.businessName} onChange={(e) => setForm({ ...form, businessName: e.target.value })} /></label>
        <label>Email<input type="email" required value={form.email} onChange={(e) => setForm({ ...form, email: e.target.value })} /></label>
        <label>Password<input type="password" required minLength="6" value={form.password} onChange={(e) => setForm({ ...form, password: e.target.value })} /></label>
        <button className="primary-button" disabled={loading}><UserPlus size={17} />{loading ? "Creating..." : "Create account"}</button>
        <p className="auth-footer">Already registered? <Link to="/login">Sign in</Link></p>
    </form></main>;
}
export default RegisterPage;