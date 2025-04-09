# 📊 Scala Project

Projet développé dans le cadre du cours **MOSEF**.  
Il s'agit d'une application Scala basée sur **Apache Spark**, organisée selon une architecture modulaire, avec une chaîne **CI/CD automatisée** via **Maven** et **GitHub Actions**.

---

## 🛠️ Environnement de développement

- **Langage** : Scala  
- **Framework** : Apache Spark  
- **IDE utilisé** : IntelliJ IDEA  
- **Build Tool** : Maven  
- **CI/CD** : GitHub Actions  
- **Version Java requise** : Java 8 **ou** Java 11

---

## 📂 Structure du projet

Le projet est découpé en trois modules principaux :

- `Reader` : lecture des données (CSV et Parquet)
- `Processor` : transformation des données
- `Writer` : écriture du résultat dans le format souhaité

Le fichier `Main.scala` est le point d’entrée du programme. Il orchestre l'ensemble des étapes en respectant la structure définie dans le `trait Job`.

---

## 🚀 Exécution du projet

### 1. Téléchargement du fichier `.jar`

Récupérer le fichier `project_scala-1.9-jar-with-dependencies.jar` dans l'onglet **Packages** du repository GitHub.

### 2. Lancement de l'application

Exemple :

```bash
java -cp project_scala-1.9-jar-with-dependencies.jar fr.mosef.scala.template.Main local <emplacement_fichier_source> <emplacement_fichier_destination> <colonne_group_by>
```

local : mode d'exécution Spark

- <emplacement_fichier_source> : chemin du fichier d'entrée (CSV ou Parquet)
- <emplacement_fichier_destination> : dossier de sortie
- <colonne_group_by> : nom de la colonne sur laquelle effectuer l'agrégation
