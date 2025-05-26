**Zadanie 1** Docker

:white_check_mark: 3.0 [obraz ubuntu z Pythonem w wersji 3.10](https://github.com/rtsncs/ebiznes/commit/3828193ce7ac2bbebcbf7dbef48701e859507f25)

:white_check_mark: 3.5 [obraz ubuntu:24.02 z Javą w wersji 8 oraz Kotlinem](https://github.com/rtsncs/ebiznes/commit/3828193ce7ac2bbebcbf7dbef48701e859507f25)

:white_check_mark: 4.0 [do powyższego należy dodać najnowszego Gradle’a oraz paczkę JDBC SQLite w ramach projektu na Gradle (build.gradle)](https://github.com/rtsncs/ebiznes/commit/3828193ce7ac2bbebcbf7dbef48701e859507f25)

:white_check_mark: 4.5 [stworzyć przykład typu HelloWorld oraz uruchomienie aplikacji przez CMD oraz gradle](https://github.com/rtsncs/ebiznes/commit/3828193ce7ac2bbebcbf7dbef48701e859507f25)

:white_check_mark: 5.0 [dodać konfigurację docker-compose](https://github.com/rtsncs/ebiznes/commit/3828193ce7ac2bbebcbf7dbef48701e859507f25)

[Dockerhub](https://hub.docker.com/r/dbrzezinski/ebiznes)

[Kod](https://github.com/rtsncs/ebiznes/tree/master/1)

**Zadanie 2** Scala

:white_check_mark: 3.0 [Należy stworzyć kontroler do Produktów](https://github.com/rtsncs/ebiznes/commit/a72f6bdd1311566af56012e3f7d3e682f313e62b)

:white_check_mark: 3.5 [Do kontrolera należy stworzyć endpointy zgodnie z CRUD - dane pobierane z listy](https://github.com/rtsncs/ebiznes/commit/a72f6bdd1311566af56012e3f7d3e682f313e62b)

:white_check_mark: 4.0 [Należy stworzyć kontrolery do Kategorii oraz Koszyka + endpointy zgodnie z CRUD](https://github.com/rtsncs/ebiznes/commit/a72f6bdd1311566af56012e3f7d3e682f313e62b)

:white_check_mark: 4.5 [Należy aplikację uruchomić na dockerze (stworzyć obraz) oraz dodać skrypt uruchamiający aplikację via ngrok](https://github.com/rtsncs/ebiznes/commit/a72f6bdd1311566af56012e3f7d3e682f313e62b)

:white_check_mark: 5.0 [Należy dodać konfigurację CORS dla dwóch hostów dla metod CRUD](https://github.com/rtsncs/ebiznes/commit/a72f6bdd1311566af56012e3f7d3e682f313e62b)

[Kod](https://github.com/rtsncs/ebiznes/tree/master/2)

**Zadanie 3** Kotlin

:white_check_mark: 3.0 [Należy stworzyć aplikację kliencką w Kotlinie we frameworku Ktor, która pozwala na przesyłanie wiadomości na platformę Discord](https://github.com/rtsncs/ebiznes/commit/053d56f06b573cf302e8da4aaa95cc6c38b60165)

:white_check_mark: 3.5 [Aplikacja jest w stanie odbierać wiadomości użytkowników z platformy Discord skierowane do aplikacji (bota)](https://github.com/rtsncs/ebiznes/commit/053d56f06b573cf302e8da4aaa95cc6c38b60165)

:white_check_mark: 4.0 [Zwróci listę kategorii na określone żądanie użytkownika](https://github.com/rtsncs/ebiznes/commit/053d56f06b573cf302e8da4aaa95cc6c38b60165)

:white_check_mark: 4.5 [Zwróci listę produktów wg żądanej kategorii](https://github.com/rtsncs/ebiznes/commit/053d56f06b573cf302e8da4aaa95cc6c38b60165)

:x: 5.0 Aplikacja obsłuży dodatkowo jedną z platform: Slack, Messenger, Webex

[Kod](https://github.com/rtsncs/ebiznes/tree/master/3)

**Zadanie 4** Go

:white_check_mark: 3.0 [Należy stworzyć aplikację we frameworki echo w j. Go, która będzie miała kontroler Produktów zgodny z CRUD](https://github.com/rtsncs/ebiznes/commit/22ffa05bb83376a4fb0daa59996f1dcb7ae4f198)

:white_check_mark: 3.5 [Należy stworzyć model Produktów wykorzystując gorm oraz wykorzystać model do obsługi produktów (CRUD) w kontrolerze (zamiast listy)](https://github.com/rtsncs/ebiznes/commit/22ffa05bb83376a4fb0daa59996f1dcb7ae4f198)

:white_check_mark: 4.0 [Należy dodać model Koszyka oraz dodać odpowiedni endpoint](https://github.com/rtsncs/ebiznes/commit/22ffa05bb83376a4fb0daa59996f1dcb7ae4f198)

:x: 4.5 Należy stworzyć model kategorii i dodać relację między kategorią, a produktem

:x: 5.0 pogrupować zapytania w gorm’owe scope'y

[Kod](https://github.com/rtsncs/ebiznes/tree/master/4)

**Zadanie 5** React

:white_check_mark: 3.0 [W ramach projektu należy stworzyć dwa komponenty: Produkty oraz Płatności; Płatności powinny wysyłać do aplikacji serwerowej dane, a w Produktach powinniśmy pobierać dane o produktach z aplikacji serwerowej;](https://github.com/rtsncs/ebiznes/commit/2561deca5e717deede62ae1a999bafa7d61ebb84)

:white_check_mark: 3.5 [Należy dodać Koszyk wraz z widokiem; należy wykorzystać routing](https://github.com/rtsncs/ebiznes/commit/2561deca5e717deede62ae1a999bafa7d61ebb84)

:white_check_mark: 4.0 [Dane pomiędzy wszystkimi komponentami powinny być przesyłane za pomocą React hooks](https://github.com/rtsncs/ebiznes/commit/2561deca5e717deede62ae1a999bafa7d61ebb84)

:x: 4.5 Należy dodać skrypt uruchamiający aplikację serwerową oraz kliencką na dockerze via docker-compose

:x: 5.0 Należy wykorzystać axios’a oraz dodać nagłówki pod CORS

[Kod](https://github.com/rtsncs/ebiznes/tree/master/5)

**Zadanie 6** Cypress

:white_check_mark: 3.0 [Należy stworzyć 20 przypadków testowych w CypressJS lub Selenium (Kotlin, Python, Java, JS, Go, Scala)](https://github.com/rtsncs/ebiznes/commit/3f43c10e57f1478e851f621f73cc0239b952cc22)

:white_check_mark: 3.5 [Należy rozszerzyć testy funkcjonalne, aby zawierały minimum 50 asercji](https://github.com/rtsncs/ebiznes/commit/3f43c10e57f1478e851f621f73cc0239b952cc22)

:x: 4.0 Należy stworzyć testy jednostkowe do wybranego wcześniejszego projektu z minimum 50 asercjami

:x: 4.5 Należy dodać testy API, należy pokryć wszystkie endpointy z minimum jednym scenariuszem negatywnym per endpoint

:x: 5.0 Należy uruchomić testy funkcjonalne na Browserstacku

[Kod](https://github.com/rtsncs/ebiznes/tree/master/6)

**Zadanie 7** Sonar

:white_check_mark: 3.0 [Należy dodać litera do odpowiedniego kodu aplikacji serwerowej w hookach gita](https://github.com/rtsncs/shop-backend/commit/b87df706f8c87f878644f8290f2a0a4b4f17e994)

:white_check_mark: 3.5 [Należy wyeliminować wszystkie bugi w kodzie w Sonarze (kod aplikacji serwerowej)](https://github.com/rtsncs/shop-backend/commit/9107b86038d2d57949c082e31e6039eea4eca133)

:white_check_mark: 4.0 [Należy wyeliminować wszystkie zapaszki w kodzie w Sonarze (kod aplikacji serwerowej)](https://github.com/rtsncs/shop-backend/commit/9107b86038d2d57949c082e31e6039eea4eca133)

:white_check_mark: 4.5 [Należy wyeliminować wszystkie podatności oraz błędy bezpieczeństwa w kodzie w Sonarze (kod aplikacji serwerowej)](https://github.com/rtsncs/shop-backend/commit/9107b86038d2d57949c082e31e6039eea4eca133)

:white_check_mark: 5.0 [Należy wyeliminować wszystkie błędy oraz zapaszki w kodzie aplikacji klienckiej](https://github.com/rtsncs/shop-frontend/commit/ae102e4a79073dbe47a4709295a4f32e7daf3795)

[Kod (klient)](https://github.com/rtsncs/shop-frontend)
[Kod (serwer)](https://github.com/rtsncs/shop-backend)

**Zadanie 8** Oauth2

:white_check_mark: 3.0 [logowanie przez aplikację serwerową (bez Oauth2)](https://github.com/rtsncs/shop-backend/commit/1aea55f6c6e0d1563e6efa96332339f46f0dadd8)

:white_check_mark: 3.5 [rejestracja przez aplikację serwerową (bez Oauth2)](https://github.com/rtsncs/shop-backend/commit/1aea55f6c6e0d1563e6efa96332339f46f0dadd8)

:x: 4.0 logowanie via Google OAuth2

:x: 4.5 logowanie via Facebook lub Github OAuth2

:x: 5.0 zapisywanie danych logowania OAuth2 po stronie serwera

[Kod (klient)](https://github.com/rtsncs/shop-frontend)
[Kod (serwer)](https://github.com/rtsncs/shop-backend)

**Zadanie 9** Chatbot

:white_check_mark: 3.0 [należy stworzyć po stronie serwerowej osobny serwis do łącznia z chatGPT do usługi chat](https://github.com/rtsncs/ebiznes/commit/e4ed424416681b433f71b4bba272b74e12659674)

:white_check_mark: 3.5 [należy stworzyć interfejs frontowy dla użytkownika, który komunikuje się z serwisem; odpowiedzi powinny być wysyałen do frontendowego interfejsu](https://github.com/rtsncs/ebiznes/commit/e4ed424416681b433f71b4bba272b74e12659674)

:x: 4.0 stworzyć listę 5 różnych otwarć oraz zamknięć rozmowy

:x: 4.5 filtrowanie po zagadnieniach związanych ze sklepem (np. ograniczenie się jedynie do ubrań oraz samego sklepu) do GPT

:x: 5.0 filtrowanie odpowiedzi po sentymencie

[Kod](https://github.com/rtsncs/ebiznes/tree/master/9)
