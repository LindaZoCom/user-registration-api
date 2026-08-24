# User Registration API — basprojekt

Basprojekt för kursen **Testning, fortsättning** (Jensen YH). Ett komplett,
fungerande Spring Boot REST-API för användarregistrering. Applikationskoden
är **klar och ska inte ändras** — kursens fokus ligger helt på att skriva
tester och bygga CI/CD runt detta projekt.

## Snabbstart

Kräver bara Java 17+ — Maven behöver inte vara installerat separat, projektet
innehåller en Maven-wrapper (`mvnw`/`mvnw.cmd`) som laddar ner rätt
Maven-version automatiskt första gången den körs.

```bash
./mvnw spring-boot:run
```

På Windows (PowerShell eller cmd):

```powershell
.\mvnw.cmd spring-boot:run
```

Om `mvn` inte hittas i terminalen är det för att Maven inte är installerat
globalt på datorn — det är precis det `mvnw`/`mvnw.cmd` löser. Alternativt går
det alltid att köra `UserRegistrationApiApplication` direkt i IntelliJ, som
har sin egen inbyggda Maven.

Appen startar på `http://localhost:8080`. Databasen är H2 in-memory —
ingen installation krävs. Konsolen finns på `http://localhost:8080/h2-console`
(JDBC URL: `jdbc:h2:mem:userdb`, användare `sa`, tomt lösenord).

Bygg och köra alla (era egna) tester:

```bash
./mvnw clean test
```

(`.\mvnw.cmd clean test` på Windows.)

## API

| Metod  | Endpoint                  | Beskrivning                          |
|--------|---------------------------|---------------------------------------|
| POST   | `/api/users`               | Skapa användare → 201, 400 vid ogiltig data, 409 vid dubblett |
| GET    | `/api/users`               | Lista alla användare                  |
| GET    | `/api/users?email=x`       | Hämta användare via email → 404 om saknas |
| GET    | `/api/users/{id}`          | Hämta användare via id → 404 om saknas |
| DELETE | `/api/users/{id}`          | Ta bort användare → 204               |

Exempel:

```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"username":"anna","email":"anna@test.com","password":"password123"}'
```

## Testa API:et i Postman

En färdig Postman-collection finns i [`postman_collection.json`](./postman_collection.json) i
projektroten, med alla endpoints förifyllda (skapa användare, felfallen 400/409,
lista alla, hämta via email/id, ta bort).

**Importera:**
1. Öppna Postman → **File → Import** (eller knappen **Import** uppe till vänster).
2. Välj filen `postman_collection.json` från projektmappen.
3. Se till att appen körs (`mvn spring-boot:run`) innan du skickar requests —
   collectionen pekar mot `http://localhost:8080` som standard.

Requesten **"Skapa användare — dubblett (409 Conflict)"** måste köras efter
**"Skapa användare (201 Created)"** för att faktiskt trigga en dubblett-konflikt.
För **"Hämta/Ta bort användare via id"** behöver du byta ut `1` i URL:en mot
ett id du faktiskt fått tillbaka (t.ex. från svaret på "Skapa användare" eller
"Lista alla användare").

## Struktur

```
se.testkurs.userapi
├── model/User.java                    # Entitet: id, username, email, password
├── repository/UserRepository.java     # findByEmail, existsByEmail
├── service/UserService.java           # registerUser, findUserByEmail, ...
├── controller/UserController.java     # REST-lager
└── exception/                         # Egna exceptions + GlobalExceptionHandler
```

## Viktigt för lärare/elever — jämförelse mot Learnpoint-övningarna

Learnpoint-materialet innehåller flera exempel på en "User-app" som skiljer
sig lite i namngivning mellan dokument. Den här klassen är den gemensamma
nämnaren — om en övning använder andra namn, mappa dem så här:

| I övningen                              | I detta projekt                              |
|------------------------------------------|-----------------------------------------------|
| `createUser(name, email)`                 | `registerUser(username, email, password)`     |
| `existsByEmail(email)`                    | `existsByEmail(email)` — finns även här        |
| `findByEmail(...)` returnerar `null`      | `findUserByEmail(email)` (returnerar `null`)   |
| `findByEmail(...)` returnerar `Optional`  | `userRepository.findByEmail(email)` (Optional) |
| `User(name, email)` (utan lösenord)       | `new User(username, email, password)`         |
| `UserAlreadyExistsException`              | Finns, mappas till HTTP 409                    |
| `InvalidEmailException`                   | Finns, mappas till HTTP 400                    |

De fristående TDD-övningarna (Calculator, StringUtils/Palindrome,
DiscountCalculator, PriceCalculator/TaxService, EmailService/RegistrationService,
PasswordValidator) är medvetet **inte** en del av detta projekt — de är
bra uppvärmningsövningar vecka 36 och skrivs som egna, fristående klasser.

## CI/CD

Se `.github/workflows/README.md` — studenterna bygger upp sina egna
GitHub Actions-workflows i den mappen under kursens gång.
