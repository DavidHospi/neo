### Une fois que le projet est clone , les premières étapes:

- Vérifier que Docker est bien installé sur votre machine et que la commande line "make" aussi (ou sinon aller dans le Makefile et copier les commandes de base dans le shell)
- Aller sur le drive pour télécharger la base (pas possible de mettre sur GIT : trop volumineux). Lien : `https://drive.google.com/drive/u/1/folders/1WP7EulMXkRcW2XAAQH0qlN62_0QEXeGF` 
  - Le copier à la racine du projet
    
- Compiler la solution et ses dépendances : `mvn clean package`

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
