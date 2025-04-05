
# Omoda wisselgeld calculator

### De applicatie lokaal draaien in docker

Zorg dat je Docker en Docker Compose hebt geïnstalleerd.

```bash
docker compose up --build
```

Frontend draait op: http://localhost:3000

Backend draait op: http://localhost:8000

## Development

### Backend

#### Vereisten

- Java 17 of 21 (getest met JDK 17)
- Maven 3.8+
- Node.js 18+
- npm 9+


#### Backend starten (Spring Boot)

(Indien maven niet is geinstalleerd op je machine kun je ook de maven wrapper gebruiken in het project. Vervang `mvn` door `./mvnw`)

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

De backend draait op: http://localhost:8000

#### Tests draaien

Het project beschikt over tests geschreven met behulp van junit. Voer de tests als volgt uit:

```bash
mvn test
```

### Frontend

```bash
cd frontend
npm install
npm start
```

Zorg dat in frontend/.env staat:

```bash
REACT_APP_API_URL=http://localhost:8000
```
