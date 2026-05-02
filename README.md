# 🌟 Stars Gallery — LAB 7

Application Android permettant d'afficher une galerie de stars (personnalités célèbres) sous forme de liste avec images circulaires, notes, filtrage par nom et animations d'introduction.

---
<img width="480" height="968" alt="demo" src="https://github.com/user-attachments/assets/a2cb23c3-5d8a-43e4-adbf-887a83f10a01" />

## 📱 Aperçu

| Splash Screen | Liste des Stars | Recherche | Modifier la note |
|:---:|:---:|:---:|:---:|
| Animation du logo au démarrage | RecyclerView avec CardView | Filtrage dynamique en temps réel | Popup AlertDialog avec RatingBar |

---

## 🎯 Objectifs pédagogiques

- Implémenter un **RecyclerView** avec le pattern **ViewHolder**
- Créer un **Adapter personnalisé** avec `Filterable`
- Utiliser les **animations** Android (`rotation`, `scaleX`, `alpha`, `translationY`)
- Charger des images distantes avec **Glide**
- Mettre en œuvre un **filtrage dynamique** via `SearchView`
- Intégrer un menu avec **partage** via `ShareCompat`
- Afficher un **popup de modification** avec `AlertDialog`

---

## 🏗️ Architecture du projet

```
app/src/main/
├── java/com/example/starsgallery/
│   ├── Star.java               # Modèle de données
│   ├── IDao.java               # Interface CRUD générique
│   ├── StarService.java        # Singleton — couche service
│   ├── StarAdapter.java        # Adapter RecyclerView + Filterable
│   ├── SplashActivity.java     # Écran de démarrage animé
│   └── ListActivity.java       # Activité principale
│
└── res/
    ├── layout/
    │   ├── activity_splash.xml     # Layout splash
    │   ├── activity_list.xml       # Layout liste
    │   ├── star_item.xml           # Item RecyclerView (CardView)
    │   └── star_edit_item.xml      # Layout popup modification
    ├── menu/
    │   └── menu.xml                # SearchView + Partager
    └── values/
        ├── strings.xml
        └── themes.xml
```

---

## 🔧 Technologies utilisées

| Bibliothèque | Version | Utilisation |
|---|---|---|
| `RecyclerView` | 1.3.2 | Affichage de la liste |
| `CardView` | 1.0.0 | Style des items |
| `CircleImageView` | 3.1.0 | Photos circulaires |
| `Glide` | 4.16.0 | Chargement d'images distantes |
| `SearchView` | AndroidX | Filtrage dynamique |
| `ShareCompat` | AndroidX | Partage de l'application |
| `AlertDialog` | AndroidX | Popup de modification |

---

## 🚀 Installation

1. Cloner le dépôt :
```bash
git clone https://github.com/votre-username/StarsGallery.git
```

2. Ouvrir dans **Android Studio**

3. Ajouter les dépendances dans `app/build.gradle.kts` :
```kotlin
implementation("androidx.cardview:cardview:1.0.0")
implementation("androidx.recyclerview:recyclerview:1.3.2")
implementation("de.hdodenhof:circleimageview:3.1.0")
implementation("com.github.bumptech.glide:glide:4.16.0")
annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")
```

4. Vérifier la permission Internet dans `AndroidManifest.xml` :
```xml
<uses-permission android:name="android.permission.INTERNET" />
```

5. **Sync Gradle** puis lancer sur émulateur ou appareil physique

---

## ✨ Fonctionnalités

### 🎬 Écran de démarrage (SplashActivity)
- Animation de rotation 360° du logo
- Réduction progressive de taille
- Translation vers le bas
- Disparition en fondu (alpha)
- Redirection automatique vers la liste après 5 secondes

### 📋 Liste des stars (ListActivity)
- Affichage en `RecyclerView` avec `LinearLayoutManager`
- Chaque item affiche : photo circulaire, nom en majuscules, note sur 5 étoiles
- Design en `CardView` avec coins arrondis et ombre

### 🔍 Recherche et filtrage
- `SearchView` intégré dans la `Toolbar`
- Filtrage en temps réel à chaque caractère saisi
- Recherche par **début du nom** (case-insensitive)
- Liste complète restaurée quand le champ est effacé

### 📤 Partage
- Bouton de partage dans la `Toolbar`
- Ouvre le sélecteur d'applications Android (WhatsApp, Gmail, etc.)

### ✏️ Modification de la note
- Clic sur un item → popup `AlertDialog`
- Affiche la photo et la note actuelle
- Modification via `RatingBar` (précision 0.1)
- Mise à jour instantanée dans le `RecyclerView`

---

## 🧱 Patterns et concepts abordés

- **Singleton** — `StarService.getInstance()`
- **DAO générique** — interface `IDao<T>` avec CRUD complet
- **ViewHolder pattern** — optimisation du RecyclerView
- **Filterable** — interface pour connecter SearchView à l'Adapter
- **Observer** — `notifyItemChanged()` pour rafraîchir un item

---

## 👨‍💻 Auteur

Réalisé dans le cadre du **LAB 7 — Développement Mobile Android**

---

## 📄 Licence

Ce projet est à usage pédagogique.
