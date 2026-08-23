import { useEffect, useState } from "react";
import ManagementPage from "../components/ManagementPage";
import { categoryService, brandService } from "../services/entityService";
import { getAllProducts, createProduct, updateProduct, deleteProduct } from "../services/productService";

const productFields = [
    { name: "name", label: "Product name" }, { name: "sku", label: "SKU" }, { name: "description", label: "Description", type: "textarea", required: false },
    { name: "purchasePrice", label: "Purchase price", type: "number", min: "0.01", step: "0.01" }, { name: "sellingPrice", label: "Selling price", type: "number", min: "0.01", step: "0.01" },
    { name: "unit", label: "Unit" }, { name: "minimumStock", label: "Minimum stock", type: "number", min: "0" }, { name: "categoryId", label: "Category", type: "select" }, { name: "brandId", label: "Brand", type: "select" },
];

function ProductsPage() {
    const [options, setOptions] = useState({ categories: [], brands: [] });
    useEffect(() => { Promise.all([categoryService.list(), brandService.list()]).then(([categories, brands]) => setOptions({ categories, brands })).catch(() => {}); }, []);
    const fields = productFields.map((field) => field.name === "categoryId" ? { ...field, options: options.categories } : field.name === "brandId" ? { ...field, options: options.brands } : field);
    return <ManagementPage title="Products" description="Track pricing, stock thresholds, and product relationships." service={{ list: getAllProducts, create: createProduct, update: updateProduct, remove: deleteProduct }} fields={fields} moneyColumns={["purchasePrice", "sellingPrice"]} columns={[{ key: "name", label: "Product" }, { key: "sku", label: "SKU" }, { key: "categoryName", label: "Category" }, { key: "brandName", label: "Brand" }, { key: "purchasePrice", label: "Purchase" }, { key: "sellingPrice", label: "Selling" }, { key: "unit", label: "Unit" }, { key: "minimumStock", label: "Min stock" }, { key: "isActive", label: "Status" }]} />;
}

export default ProductsPage;