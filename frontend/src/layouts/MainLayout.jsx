import { NavLink, Outlet } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { logout } from "../services/authService";
import {
    LayoutDashboard,
    Package,
    Warehouse,
    Boxes,
    ShoppingCart,
    ReceiptText,
    Users,
    Truck,
    CreditCard,
    Wallet,
    BarChart3,
    LogOut,
    Menu,
} from "lucide-react";

const menuItems = [
    { name: "Dashboard", path: "/dashboard", icon: LayoutDashboard },
    { name: "Products", path: "/products", icon: Package },
    { name: "Categories", path: "/categories", icon: Package },
    { name: "Brands", path: "/brands", icon: Package },
    { name: "Godowns", path: "/godowns", icon: Warehouse },
    { name: "Inventory", path: "/inventory", icon: Boxes },
    { name: "Purchases", path: "/purchases", icon: ShoppingCart },
    { name: "Sales", path: "/sales", icon: ReceiptText },
    { name: "Customers", path: "/customers", icon: Users },
    { name: "Suppliers", path: "/suppliers", icon: Truck },
    { name: "Customer Payments", path: "/customer-payments", icon: CreditCard },
    { name: "Supplier Payments", path: "/supplier-payments", icon: Wallet },
    { name: "Expenses", path: "/expenses", icon: Wallet },
    { name: "Reports", path: "/reports", icon: BarChart3 },
];

function MainLayout() {
    const navigate = useNavigate();
    return (
        <div className="app-layout">
            <aside className="sidebar">
                <div className="logo">
                    <div className="logo-icon">B</div>
                    <div>
                        <h2>BMS</h2>
                        <span>Business Manager</span>
                    </div>
                </div>

                <nav className="sidebar-nav">
                    {menuItems.map((item) => {
                        const Icon = item.icon;

                        return (
                            <NavLink
                                key={item.path}
                                to={item.path}
                                className={({ isActive }) =>
                                    `nav-item ${isActive ? "active" : ""}`
                                }
                            >
                                <Icon size={20} />
                                <span>{item.name}</span>
                            </NavLink>
                        );
                    })}
                </nav>

                <div className="sidebar-footer">
                    <button className="logout-button" onClick={() => { logout(); navigate("/login", { replace: true }); }}>
                        <LogOut size={20} />
                        <span>Logout</span>
                    </button>
                </div>
            </aside>

            <main className="main-content">
                <header className="topbar">
                    <div className="page-title">
                        <Menu size={22} />
                        <h1>Business Management System</h1>
                    </div>

                    <div className="user-profile">
                        <div className="user-avatar">A</div>
                        <span>Admin</span>
                    </div>
                </header>

                <div className="page-content">
                    <Outlet />
                </div>
            </main>
        </div>
    );
}

export default MainLayout;