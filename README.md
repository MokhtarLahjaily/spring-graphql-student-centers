# 🎓 Spring Data REST & GraphQL — Gestion des Étudiants et Centres

## 🚀 Prérequis
- JDK 17+
- Maven 3.9+
- IDE ou éditeur (VS Code)
- Navigateur pour GraphiQL & Console H2

## ▶️ Lancer et tester
```bash
mvn clean verify
mvn spring-boot:run
```

- GraphQL UI : http://localhost:8090/graphiql?path=/graphql  
- H2 Console : http://localhost:8090/h2-console (JDBC URL : `jdbc:h2:mem:testdb`)

## 📦 Structure
- `entity/` : Centre, Etudiant
- `repository/` : Spring Data JPA
- `web/EtudiantGraphQLController` : queries & mutations
- `dto/EtudiantDTO` : payload GraphQL

## 🧪 Dataset initial
Deux centres + deux étudiants créés au démarrage via `CommandLineRunner`.

## 🧠 Exemples GraphQL

**Query**
```graphql
query {
  listEtudiants {
    id nom prenom genre
    centre { nom }
  }
}
```

**Mutations**
```graphql
mutation {
  addEtudiant(etudiant:{ nom:"Lahjaily", prenom:"Mokhtar", genre:Homme, centreId:1 }) {
    id nom prenom centre { nom }
  }
}
```

```graphql
mutation {
  updateEtudiant(id:1, etudiant:{ nom:"Lahjaily", prenom:"Mokhtar", genre:Homme, centreId:2 }) {
    id nom prenom centre { nom }
  }
}
```

```graphql
mutation { deleteEtudiant(id:1) }
```

## 🧾 Licence
Projet éducatif EMSI 2025/2026.