# User Registration API — basprojekt

Basprojekt för kursen **Testning, fortsättning**. Det här är ett komplett, fungerande Spring Boot REST-API för användarregistrering.

> **Viktigt:** Applikationskoden är klar och ska inte ändras om en uppgift inte uttryckligen säger något annat. Kursens fokus ligger på att skriva tester och bygga CI/CD runt projektet.

## Börja här: skapa ditt eget repository från templaten

Detta repository är en GitHub-template. När du använder templaten får du ett **eget, fristående repository** med samma filer och mappstruktur som basprojektet. Din egen version har en separat Git-historik och dina ändringar påverkar inte lärarens repository. [GitHub Docs](https://docs.github.com/en/repositories/creating-and-managing-repositories/creating-a-repository-from-a-template)

### Skapa din kopia på GitHub

1. Gå till lärarens template-repository på GitHub.
2. Klicka på **Use this template**.
3. Välj **Create a new repository**.
4. Välj ditt eget GitHub-konto som Owner.
5. Ge repositoryt ett tydligt namn, exempelvis `user-registration-api-ditt-namn`.
6. Välj synlighet enligt lärarens instruktioner.
7. Klicka på **Create repository**.

### Klona din egen kopia

Klona sedan **din egen repository-URL**, inte lärarens template-URL.

I IntelliJ:

1. Välj **File → New → Project from Version Control**.
2. Välj **Git**.
3. Klistra in HTTPS-adressen till ditt eget repository.
4. Välj en lokal mapp på datorn.
5. Klicka på **Clone**.
6. Välj **Trust Project** om IntelliJ frågar.
7. Låt IntelliJ importera Maven-projektet och synka beroenden.

### Viktigt att tänka på

- Arbeta i din **egen** kopia av repositoryt.
- Pusha aldrig ändringar till lärarens template-repository.
- Ändra inte projektets Java-version, Spring Boot-version eller `pom.xml` om en uppgift inte uttryckligen ber dig göra det.
- Om IntelliJ frågar om Maven-ändringar: välj **Load Maven Changes** eller **Sync**.
- Om IntelliJ inte hittar Git: kontrollera **File → Settings → Version Control → Git**, välj sökvägen till `git.exe` och klicka på **Test**.

## Krav och versioner

Projektet är konfigurerat för **Java 21** och ska användas med Java 21 genomgående.

| Del | Version/val |
|---|---|
| Java/JDK | Java 21 (LTS) |
| Spring Boot | 3.3.4 |
| Build tool | Maven Wrapper som följer med projektet |
| Databas | H2 in-memory |

Spring Boot 3.3 kräver minst Java 17 och stöder Java 21. Detta projekt använder Java 21 via `<maven.compiler.release>21</maven.compiler.release>` i `pom.xml`. [Spring Boot dokumentation](https://docs.spring.io/spring-boot/docs/3.3.0-M2/reference/html/getting-started.html)

### Kontrollera Java i IntelliJ

1. Gå till **File → Project Structure → Project**.
2. Kontrollera att **SDK** är en JDK 21.
3. Kontrollera att **Language level** är 21 eller **SDK default**.
4. Gå till **File → Settings → Build, Execution, Deployment → Build Tools → Maven → Runner**.
5. Kontrollera att **JRE** är **Use Project JDK** eller explicit JDK 21.

Använd inte Java 17 i `pom.xml` och Java 21 i IntelliJ, eller Java 26 i terminalen. Välj Java 21 konsekvent för just detta projekt.

## Snabbstart

Du behöver **inte** ha Maven installerat globalt. Projektet innehåller Maven Wrapper:

```text
mvnw       # macOS/Linux
mvnw.cmd   # Windows
.mvn/      # Maven Wrapper-konfiguration
```

Maven Wrapper laddar ner den Maven-version som projektet behöver första gången den körs. [Apache Maven Wrapper](https://maven.apache.org/tools/wrapper/)

### 1. Öppna terminalen i projektroten

Du ska stå i samma mapp som `pom.xml`, `mvnw` och `mvnw.cmd`.

### 2. Kontrollera att wrappern fungerar

**Windows PowerShell:**

```powershell
.\mvnw.cmd -version
```

**macOS/Linux Terminal:**

```bash
./mvnw -version
```

Utdata ska visa en Maven-version och Java 21.

### 3. Starta applikationen

**Windows PowerShell:**

```powershell
.\mvnw.cmd spring-boot:run
```

**macOS/Linux Terminal:**

```bash
./mvnw spring-boot:run
```

Alternativ i IntelliJ: öppna klassen `UserRegistrationApiApplication` och klicka på den gröna körpilen bredvid `main`-metoden.

Appen startar på [http://localhost:8080](http://localhost:8080).

### 4. Kör alla tester

**Windows PowerShell:**

```powershell
.\mvnw.cmd test
```

**macOS/Linux Terminal:**

```bash
./mvnw test
```

Om du vill rensa tidigare byggresultat först:

**Windows PowerShell:**

```powershell
.\mvnw.cmd clean test
```

**macOS/Linux Terminal:**

```bash
./mvnw clean test
```

Maven hittar automatiskt testklasser under `src/test/java` och kör dem när du kör `test`.

## Vanliga problem

| Problem | Kontrollera/lösning |
|---|---|
| `mvn` känns inte igen | Använd projektets wrapper: `./mvnw` på macOS/Linux eller `.\mvnw.cmd` i PowerShell på Windows. Du behöver inte installera Maven globalt. |
| Fel Java-version i Maven eller IntelliJ | Kontrollera Project SDK, Maven Runner JRE och att `mvnw -version`/`.\mvnw.cmd -version` visar Java 21. Ändra inte `pom.xml` från Java 21. |
| IntelliJ visar röda imports efter kloning | Vänta på Maven-importen. Välj sedan **Load Maven Changes** eller **Sync**. Om det behövs: öppna Maven-fönstret och klicka på Reload All Maven Projects. |
| IntelliJ kan inte committa/pusha och visar Git-fel | Kontrollera att Git är installerat. I IntelliJ: **File → Settings → Version Control → Git**. Välj sökvägen till `git.exe` och klicka på **Test**. Ett repository på GitHub ersätter inte Git-programmet på datorn. |
| Port 8080 används redan | Stoppa den tidigare Spring Boot-processen i IntelliJ/terminalen, eller ändra `server.port` tillfälligt enligt lärarens instruktion. |
| H2-data verkar saknas efter omstart | Det är förväntat. H2 används i minnet och data försvinner när applikationen stoppas. |

## H2-databas

Projektet använder H2 som en in-memory-databas. Ingen separat databasinstallation krävs.

När appen körs finns H2 Console på:

[http://localhost:8080/h2-console](http://localhost:8080/h2-console)

Använd dessa värden om inloggningssidan visas:

| Fält | Värde |
|---|---|
| JDBC URL | `jdbc:h2:mem:userdb` |
| User Name | `sa` |
| Password | Lämna tomt |

> Data finns bara medan applikationen körs. När appen stoppas försvinner data från H2-databasen.

## API

| Metod | Endpoint | Beskrivning |
|---|---|---|
| POST | `/api/users` | Skapa användare → 201, 400 vid ogiltig data, 409 vid dubblett |
| GET | `/api/users` | Lista alla användare |
| GET | `/api/users?email=x` | Hämta användare via e-post → 404 om användaren saknas |
| GET | `/api/users/{id}` | Hämta användare via id → 404 om användaren saknas |
| DELETE | `/api/users/{id}` | Ta bort användare → 204 |

### Exempel: skapa användare med curl

**macOS/Linux/Git Bash:**

```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"username":"anna","email":"anna@test.com","password":"password123"}'
```

Om du använder PowerShell kan det vara enklare att använda Postman i stället för `curl`.

## Testa API:et i Postman

En färdig Postman-collection finns i `postman_collection.json` i projektroten. Den innehåller exempel för alla endpoints: skapa användare, felfallen 400/409, lista alla, hämta via e-post/id och ta bort.

### Importera collectionen

1. Öppna Postman.
2. Välj **Import**.
3. Välj filen `postman_collection.json` från projektmappen.
4. Kontrollera att appen körs innan du skickar requests:

   ```text
   http://localhost:8080
   ```

### Att tänka på i Postman

- Kör **”Skapa användare (201 Created)”** före **”Skapa användare — dubblett (409 Conflict)”** om du vill trigga en dubblett-konflikt.
- För requesterna som använder id behöver du byta ut `1` i URL:en mot ett id som faktiskt finns, till exempel id:t från svaret när du skapade en användare eller från listan över användare.
- Eftersom H2 körs i minnet försvinner användare när applikationen stoppas och startas igen.

## Projektstruktur

```text
src/
├── main/
│   ├── java/
│   │   └── se/testkurs/userapi/
│   │       ├── model/User.java
│   │       ├── repository/UserRepository.java
│   │       ├── service/UserService.java
│   │       ├── controller/UserController.java
│   │       └── exception/
│   │           ├── ...
│   │           └── GlobalExceptionHandler.java
│   └── resources/
│       └── application.properties
└── test/
    ├── java/
    │   └── se/testkurs/userapi/
    │       └── UserRegistrationApiApplicationTests.java
    └── resources/
```

### Var ska nya tester ligga?

Lägg testklasser under `src/test/java` i samma paketstruktur som koden de testar.

Exempel:

```text
src/main/java/se/testkurs/userapi/service/UserService.java
src/test/java/se/testkurs/userapi/service/UserServiceTest.java
```

Exempel på framtida testklasser:

```text
UserServiceTest.java
UserControllerTest.java
UserRepositoryTest.java
```

Kör sedan alla tester med Maven Wrapper:

```bash
./mvnw test
```

eller på Windows:

```powershell
.\mvnw.cmd test
```

## Viktigt: jämförelse med Learnpoint-övningarna

Learnpoint-materialet innehåller flera exempel på en ”User-app” som skiljer sig lite i namngivning mellan dokument. Den här klassen är den gemensamma nämnaren. Om en övning använder andra namn, mappa dem så här:

| I övningen | I detta projekt |
|---|---|
| `createUser(name, email)` | `registerUser(username, email, password)` |
| `existsByEmail(email)` | `existsByEmail(email)` — finns även här |
| `findByEmail(...)` returnerar `null` | `findUserByEmail(email)` returnerar `null` |
| `findByEmail(...)` returnerar `Optional` | `userRepository.findByEmail(email)` returnerar `Optional` |
| `User(name, email)` utan lösenord | `new User(username, email, password)` |
| `UserAlreadyExistsException` | Finns och mappas till HTTP 409 |
| `InvalidEmailException` | Finns och mappas till HTTP 400 |

De fristående TDD-övningarna — Calculator, StringUtils/Palindrome, DiscountCalculator, PriceCalculator/TaxService, EmailService/RegistrationService och PasswordValidator — är medvetet **inte** en del av detta basprojekt. De används som fristående uppvärmningsövningar under vecka 36.

## CI/CD

Se `.github/workflows/README.md`. Under kursen bygger studenterna stegvis sina egna GitHub Actions-workflows i den mappen.
