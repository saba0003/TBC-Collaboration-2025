# TBC Collaboration 2025 - Authentication Module

This repository contains the authentication module for the TBC Collaboration 2025 project. It
handles user onboarding, session persistence, and credential validation using modern Android
development practices.

## 🏛 Architecture Overview

The project is built using **Clean Architecture** principles and **MVI**, ensuring a strict
separation of concerns:

### 1. Presentation Layer (MVVM + MVI-like State)

- **Fragments & ViewBinding**: UI is implemented using XML and ViewBinding.
- **State Management**: ViewModels use a `Contract` pattern (`State`, `Event`, `SideEffect`) to
  manage the UI.
- **Communication**: Uses `StateFlow` for persistent state and `Channels` for one-time side
  effects (e.g., navigation, showing Snackbars).

### 2. Domain Layer

- **Use Cases**: Contains isolated business logic (e.g., `SignInUseCase`, `SignUpUseCase`).
- **Validators**: Specialized logic for validating emails and password complexity.
- **Interfaces**: Defines repository abstractions to keep the business logic independent of data
  sources.

### 3. Data Layer

- **Repositories**: Implementation of domain interfaces that coordinate data flow.
- **Remote**: Retrofit services and Moshi DTOs for network communication.
- **Local**: Jetpack **DataStore** for persistent storage of the JWT `auth_token`.
- **Mappers**: Comprehensive mapping between Data DTOs, Domain Models, and Presentation Models.

---

## 🌐 API Endpoints Used

The application communicates with the following endpoints (Base URL: `http://35.205.47.8/api/`):

| Feature          | Method | Endpoint        | Description                                           |
|:-----------------|:-------|:----------------|:------------------------------------------------------|
| **Login**        | `POST` | `Auth/login`    | Validates credentials and returns a session token.    |
| **Register**     | `POST` | `Auth/register` | Creates a new user account.                           |
| **User Profile** | `GET`  | `Auth/me`       | Fetches details for the currently authenticated user. |

---

## ⚠️ Known Limitations

* **Scope**: Only the Authentication flow (Login, Register, Session Management) is implemented.
* **Event Hub**: The `EventHubFragment` is a skeleton landing page used to demonstrate successful
  login and logout; no event browsing features exist yet.
* **Forgot Password**: The UI and navigation are complete, but there is no backend integration for
  sending recovery emails.
* **Input Fields**: Some fields in the Registration form (Phone Number, Department Dropdown, OTP)
  are visual placeholders and are not yet tied to backend validation logic.
* **Error Handling**: Error messages are currently displayed via a generic `MessagePopup` (Snackbar)
  rather than inline form validation errors.

---

## 🚀 Future Improvement Suggestions

1. **Full Feature Integration**: Implement the `Event` and `Analytics` services defined in the
   `BuildConfig`.
2. **Token Refresh**: Add an `Authenticator` or `Interceptor` logic to handle automatic JWT token
   refreshing when tokens expire.
3. **Enhanced Security**: Migrate the `TOKEN_KEY` storage to **EncryptedDataStore** or integrate
   Biometric prompts for session access.
4. **Unit & UI Testing**: Implement tests for `SignInValidator` and `SignUpValidator` using JUnit,
   and UI tests using Espresso.
5. **Form UX**: Add real-time inline validation feedback for `EditText` fields (e.g., "Email is
   required" appearing immediately).
6. **Edge-to-Edge**: Fully optimize the XML layouts for the `enableEdgeToEdge()` configuration to
   handle system bars more gracefully across all screens.
