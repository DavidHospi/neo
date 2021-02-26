### Une fois que le projet est clone , les premières étapes :

- `mvn clean package`

### Start Database :
`make start-database`

### Importer les données:

Aller à `http://localhost:7474/browser/` 
Identifiant : `neo4j`
Password : `david`

Faire tourner le script dans `/neo4j-quickstart/src/main/resources/import.cypher` (Le copier / coller)

### Stop Database:
`make stop-database`

### Rerun DB :
`make rerun-database`

### Lancer le bash du docker neo :
`make bash-neo`

### Lancer le projet (sur le port 8089) 
`make dev`
