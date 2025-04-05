# Omoda Wisselgeld Calculator

Een full-stack applicatie in React + Spring Boot waarmee wisselgeld berekend wordt op basis van een aankoopbedrag en contante betaling.

---

## Applicatie lokaal draaien met Docker

Zorg dat Docker en Docker Compose zijn geïnstalleerd.

```bash
docker compose up --build
```

- Frontend: http://localhost:3000  
- Backend: http://localhost:8000

---

## Development

### Vereisten

- Java 17 of 21 (getest met JDK 17)
- Maven 3.8+ of `./mvnw` (Maven wrapper)
- Node.js 18+
- npm 9+

---

### Backend starten (Spring Boot)

```bash
cd backend
mvn clean install     # of ./mvnw clean install
mvn spring-boot:run   # of ./mvnw spring-boot:run
```

Server draait op: http://localhost:8000

---

### Backend tests draaien

```bash
mvn test   # of ./mvnw test
```

De tests zijn geschreven met JUnit 5 en testen onder andere de `ChangeCalculator`.

---

### Frontend starten (React)

```bash
cd frontend
npm install
npm start
```

Zorg dat je `.env` bestand in `frontend/` de volgende regel bevat:

```env
REACT_APP_API_URL=http://localhost:8000
```

---

## API-endpoint

```
POST /api/v1/change
Content-Type: application/json

{
  "amount": 4575,
  "paid": 5000
}
```

**Response:**

```json
{
  "200": 2,
  "25": 1
}
```
