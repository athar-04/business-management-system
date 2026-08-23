import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import MainLayout from "./layouts/MainLayout";
import DashboardPage from "./pages/DashboardPage";
import ProductsPage from "./pages/ProductsPage";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import { isAuthenticated } from "./services/authService";
import { CategoriesPage, BrandsPage, GodownsPage, InventoryPage, CustomersPage, SuppliersPage, ExpensesPage, ReportsPage } from "./pages/ResourcePages";
import { PurchasesPage, SalesPage } from "./pages/TransactionsPage";
import { CustomerPaymentsPage, SupplierPaymentsPage } from "./pages/PaymentsPage";

function ProtectedRoute({ children }) { return isAuthenticated() ? children : <Navigate to="/login" replace />; }

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route element={<ProtectedRoute><MainLayout /></ProtectedRoute>}>
          <Route path="/dashboard" element={<DashboardPage />} />

          <Route path="/products" element={<ProductsPage />} />

          <Route path="/categories" element={<CategoriesPage />} />
          <Route path="/brands" element={<BrandsPage />} />
          <Route path="/godowns" element={<GodownsPage />} />

          <Route
            path="/inventory"
            element={<InventoryPage />}
          />

          <Route
            path="/purchases"
            element={<PurchasesPage />}
          />

          <Route
            path="/sales"
            element={<SalesPage />}
          />

          <Route
            path="/customers"
            element={<CustomersPage />}
          />

          <Route
            path="/suppliers"
            element={<SuppliersPage />}
          />

          <Route
            path="/customer-payments"
            element={<CustomerPaymentsPage />}
          />

          <Route
            path="/supplier-payments"
            element={<SupplierPaymentsPage />}
          />

          <Route
            path="/expenses"
            element={<ExpensesPage />}
          />

          <Route
            path="/reports"
            element={<ReportsPage />}
          />
        </Route>

        <Route path="/" element={<Navigate to={isAuthenticated() ? "/dashboard" : "/login"} replace />} />
        <Route path="*" element={<Navigate to={isAuthenticated() ? "/dashboard" : "/login"} replace />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;