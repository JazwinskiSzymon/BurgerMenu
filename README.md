# 🔴 in-progress
# 🍔 Aplikacja do Zamawiania Burgerów – Android App z Room, Retrofit i MVVM 🍟

Aplikacja mobilna na Androida umożliwiająca przeglądanie burgerów, dodawanie ich do koszyka, składanie zamówień oraz przeglądanie historii zamówień. Projekt wykorzystuje nowoczesne technologie Androida, takie jak Room, Retrofit, RecyclerView, ViewModel, LiveData, Navigation Component i MVVM Architecture.

🛠️ Technologie i biblioteki

* Kotlin – główny język aplikacji
* Room Database – lokalna baza danych do przechowywania pozycji koszyka i historii zamówień
* Retrofit – komunikacja z backendem / API pobierającym dane z zewnętrznego API
 ```api
https://my-burger-api.herokuapp.com/
```
* RecyclerView – dynamiczne listy produktów i historii zamówień
* ViewModel + LiveData – zarządzanie stanem i reakcja na zmiany danych
* Navigation Component – obsługa nawigacji między fragmentami
* Material Design Components – nowoczesny wygląd UI
* Data Binding – powiązanie danych z widokami w XML

📱 Kluczowe funkcjonalności

* 🍔 **Lista burgerów** – pobierana z API przy użyciu Retrofit
* 🛒 **Koszyk** – zapisywany lokalnie przy użyciu Room, z możliwością modyfikacji pozycji
* 💳 **Podsumowanie zamówienia** – dynamiczne wyliczanie kosztu na podstawie zawartości koszyka
* 📦 **Składanie zamówienia** – zapis zamówienia do lokalnej historii
* 📜 **Historia zamówień** – przegląd wcześniej złożonych zamówień
* 🌙 **Motyw aplikacji** – spójny interfejs z Material Design

🧱 Architektura

Projekt został zbudowany w oparciu o architekturę MVVM (Model-View-ViewModel):

```graphql
View (Fragment/Activity)
   ↕
ViewModel
   ↕
Repository
   ↕
Room / Retrofit
```

🚀 Jak uruchomić

Sklonuj repozytorium:

```bash
git clone [https://github.com/JazwinskiSzymon/BurgerMenu](https://github.com/JazwinskiSzymon/BurgerMenu)
```
Otwórz projekt w Android Studio.

Uruchom aplikację na emulatorze lub fizycznym urządzeniu z Androidem 5.0+.




https://github.com/user-attachments/assets/b557fae3-9fd9-4779-9499-9346276cdd0a

