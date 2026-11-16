# 🏘️ BAG API Simulator

The **BAG API Simulator** is a lightweight Spring Boot application that mimics the official [BAG Individuele Bevragingen API](https://www.kadaster.nl/zakelijk/producten/adressen-en-gebouwen/bag-api-individuele-bevragingen).
It allows consumers to query test address data using a REST endpoint. No authentication is required.

This simulator is ideal for developers who want to test BAG API integrations without hitting live Kadaster endpoints.

---

## ✨ Features

- ✅ `GET /haalcentraal/api/bag/v1/adressen`
    - Query address data using:
        - `postcode` (required)
        - `huisnummer` (required)
        - `huisletter` (optional)
        - `huisnummertoevoeging` (optional)
- 🧪 Comes with realistic test address data
- 🐳 Dockerized for fast local or CI usage

---

## 📌 Example Use Case

A frontend or backend integration developer needs to:
- Test a UI that queries the BAG API
- Simulate user scenarios like missing data or edge case addresses
- Run contract tests against predictable test data

This simulator allows such tests to be run **locally or in CI**, without depending on live Kadaster infrastructure.

---

## 🚀 How to Run It

### Option 1: Run Locally

1. Clone the repository:
   ```bash
   git clone https://github.com/generiekzaakafhandelcomponent/bag-api-simulator.git
   cd bag-api-simulator

2. Build and run the Spring Boot app:
   ```bash
   ./gradlew bootRun

3. Call the endpoint with curl or Postman:
      ```bash
      curl "http://localhost:5020/haalcentraal/api/bag/v1/adressen?postcode=1017DK&huisnummer=453"

### Option 2: Run via Docker

Pull and run the prebuilt image from the GitHub Container Registry:
```bash
docker run -p 5020:5020 ghcr.io/generiekzaakafhandelcomponent/bag-api-simulator:latest
   ```

Then test it with:
```bash
curl "http://localhost:5020/haalcentraal/api/bag/v1/adressen?postcode=1017DK&huisnummer=453"
   ```
---

## 📂 Project Structure (key components)

* 	`AddressController.kt` – REST endpoint logic
* 	`AddressService.kt` – Handles address retrieval and matching
* 	`fixtures/address.json` – Static test data used by the simulator

---

## 🧩 Contributions

Want to help simulate more endpoints or improve the address matching logic?
1.	Fork the repo
2.	Create a feature branch
3.	Make your changes
4.	Open a PR describing your enhancement

---

## 🛡 License

This simulator is provided for testing purposes. Not affiliated with or endorsed by Kadaster or HaalCentraal.

---

## 📬 Contact
Have questions or feedback? Open an issue or reach out via email: ayub.abdulkader@ritense.com.
