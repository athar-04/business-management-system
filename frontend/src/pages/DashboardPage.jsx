import { useEffect, useState } from "react";
import {
    DollarSign,
    ShoppingCart,
    TrendingDown,
    Wallet,
    CreditCard,
    Package,
    Warehouse,
    Boxes,
} from "lucide-react";
import { getDashboardSummary } from "../services/dashboardService";

const DashboardPage = () => {
    const [summary, setSummary] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");

    useEffect(() => {
        const fetchDashboard = async () => {
            try {
                setLoading(true);
                const data = await getDashboardSummary();
                setSummary(data);
            } catch (err) {
                console.error("Failed to fetch dashboard summary:", err);
                setError("Failed to load dashboard data.");
            } finally {
                setLoading(false);
            }
        };

        fetchDashboard();
    }, []);

    if (loading) {
        return <div className="page-container">Loading dashboard...</div>;
    }

    if (error) {
        return <div className="page-container">{error}</div>;
    }

    const cards = [
        {
            title: "Total Sales",
            value: summary?.totalSales ?? 0,
            icon: <DollarSign size={28} />,
        },
        {
            title: "Total Purchases",
            value: summary?.totalPurchases ?? 0,
            icon: <ShoppingCart size={28} />,
        },
        {
            title: "Total Expenses",
            value: summary?.totalExpenses ?? 0,
            icon: <TrendingDown size={28} />,
        },
        {
            title: "Receivables",
            value: summary?.totalReceivables ?? 0,
            icon: <Wallet size={28} />,
        },
        {
            title: "Payables",
            value: summary?.totalPayables ?? 0,
            icon: <CreditCard size={28} />,
        },
        {
            title: "Products",
            value: summary?.totalProducts ?? 0,
            icon: <Package size={28} />,
        },
        {
            title: "Godowns",
            value: summary?.totalGodowns ?? 0,
            icon: <Warehouse size={28} />,
        },
        {
            title: "Inventory Quantity",
            value: summary?.totalInventoryQuantity ?? 0,
            icon: <Boxes size={28} />,
        },
    ];

    return (
        <div className="page-container">
            <div className="page-header">
                <h1>Dashboard</h1>
                <p>Overview of your business.</p>
            </div>

            <div className="dashboard-grid">
                {cards.map((card) => (
                    <div className="dashboard-card" key={card.title}>
                        <div className="card-icon">{card.icon}</div>

                        <div className="card-content">
                            <p>{card.title}</p>
                            <h2>
                                {typeof card.value === "number" &&
                                    !["Products", "Godowns", "Inventory Quantity"].includes(
                                        card.title
                                    )
                                    ? `₹${card.value.toLocaleString()}`
                                    : card.value}
                            </h2>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default DashboardPage;