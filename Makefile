start-database:
	docker run \
        --name tpneo4j \
        -p7474:7474 -p7687:7687 \
        -d \
        -v $HOME/neo/neo4j-quickstart/data:/data \
        -v $HOME/neo/neo4j-quickstart/logs:/logs \
        -v $HOME/neo/neo4j-quickstart/import:/var/lib/neo4j/import \
        -v $HOME/neo/neo4j-quickstart/plugins:/plugins \
        --env NEO4J_AUTH=neo4j/david \
        neo4j:latest

stop-database:
	docker stop tpneo4j

rerun-database:
	docker start tpneo4j

bash-neo:
	docker exec -it tpneo4j bash

# Se connecter pour requête cypher : cypher-shell -u neo4j -p david

dev:
	mvn compile quarkus:dev