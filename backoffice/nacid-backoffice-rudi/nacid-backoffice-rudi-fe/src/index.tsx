import React from "react";
import ReactDOM from "react-dom";
import { messagesBg, messagesEn } from "./i18n";
import App from "./App";
import reportWebVitals from "./reportWebVitals";
import { BrowserRouter } from "react-router-dom";
import { Provider } from "react-redux";
import { store } from "./store/redux/store";
import { AppI18nProvider, AppSecurityProvider } from "@duosoftbg/nacid-components";
import { keycloakInitObject, ProcessEnvironments } from "@duosoftbg/nacid-backoffice-components";

ReactDOM.render(
  <React.StrictMode>
    <BrowserRouter basename={ProcessEnvironments.PublicUrl}>
      <AppSecurityProvider
        authClient={keycloakInitObject}
        initOptions={{ onLoad: "login-required", pkceMethod: "S256", checkLoginIframe: false }}
      >
        <Provider store={store}>
          <AppI18nProvider messagesBg={messagesBg} messagesEn={messagesEn}>
            <App />
          </AppI18nProvider>
        </Provider>
      </AppSecurityProvider>
    </BrowserRouter>
  </React.StrictMode>,
  document.getElementById("root"),
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
