import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import "./index.css";
import { wakeBackend } from "./services/backendService";

async function startApplication() {
  await wakeBackend();

  ReactDOM.createRoot(document.getElementById("root")).render(
    <React.StrictMode>
      <App />
    </React.StrictMode>
  );
}

startApplication();