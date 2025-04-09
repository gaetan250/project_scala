# 📊 Scala Project

Projet développé dans le cadre du cours MOSEF.  
Il s'agit d'une application Scala basée sur Apache Spark, organisée selon une architecture modulaire, avec une chaîne CI/CD automatisée via Maven et GitHub Actions.

---

## 🛠️ Environnement de développement

- **Langage** : Scala  
- **Framework** : Apache Spark  
- **IDE utilisé** : IntelliJ IDEA  
- **Build Tool** : Maven  
- **CI/CD** : GitHub Actions

---

## 📂 Structure du projet

Le projet est découpé en trois modules principaux :

- `Reader` : lecture des données (CSV et Parquet )
- `Processor` : transformation des données 
- `Writer` : écriture du résultat dans le format souhaité

Le fichier `Main.scala` est le point d’entrée du programme. Il orchestre l'ensemble des étapes en respectant la structure définie dans le `trait Job`.


