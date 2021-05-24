### Une fois que le projet est clone , les premières étapes:

- Vérifier que Docker est bien installé sur votre machine et que la commande line "make" aussi (ou sinon aller dans le Makefile et copier les commandes de base dans le shell)
- Si vous êtes sous windows, pensez à être en LF et non CRLF sur les fichiers Makefile, docker-compose.yaml et neo4j.conf
- Si vous êtes sous windows, utilisez un chemin absolu à la place du $HOME dans le Makefile, c'est le plus simple
- Aller sur le drive pour télécharger la base (pas possible de mettre sur GIT : trop volumineux). Lien : `https://drive.google.com/drive/u/1/folders/1WP7EulMXkRcW2XAAQH0qlN62_0QEXeGF` 
  - Le copier dans un dossier neo4j-quickstart à la racine du projet.
    
- Compiler la solution et ses dépendances : `mvn clean package`

### Start Database :
`make start-database`

### Importer les données:

Aller à `http://localhost:7474/browser/` 
Identifiant : `neo4j`
Password : `david`

Ou bien utilisez votre Neo4J Desktop (Recommandé)

Faire tourner le script dans `/neo4j-quickstart/src/main/resources/import.cypher` (Le copier / coller)

### Stop Database:
`make stop-database`

### Rerun DB :
`make rerun-database`

### Lancer le bash du docker neo :
`make bash-neo`

### Lancer le projet (sur le port 8089) 
`make dev`

### Tester le projet
- Rendez-vous sur le swagger de l'application : http://localhost:8089/swagger-ui/

- Installez l'addon NeoMap sur votre Neo4j Desktop, il vous permettra de visualiser les données possédant des information de géolocalisation :

#### - Configuration
<img src="https://zupimages.net/up/21/21/6pdn.png" alt="" width="375" height="450"/>

#### - Rendu
<img src="https://zupimages.net/up/21/21/ud7n.png" alt="" width="500" height="500"/>

