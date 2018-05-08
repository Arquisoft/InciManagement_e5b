# InciManager_e5b

[![Build Status](https://travis-ci.org/Arquisoft/InciManager_e5b.svg?branch=master)](https://travis-ci.org/Arquisoft/InciManager_e5b)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/97d6326cbcbb4c638d59879facacaf32)](https://www.codacy.com/app/jelabra/InciManager_e5b?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Arquisoft/InciManager_e5b&amp;utm_campaign=Badge_Grade)[![codecov](https://codecov.io/gh/Arquisoft/InciManager_e5b/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/InciManager_e5b)

# Autores
- Mateo Juarez Verdugo (@UO251123)
- Manuel Mori Álvarez (@ManuelMori)
- Javier Gonzalez Fernandez (@UO245020)
- Marcos Machado Menéndez (@UO238688)

# Funcionamiento:

Para ejecutar el proyecto:

 1. Descargar el módulo 2 del otro equipo que puedes obtener a traves del siguiente enlace: https://github.com/Arquisoft/Agents_e5b
 2. Para ejecutar el proyecto es necesario tener arrancado Apache Kafka. Para ello descargarlo de aqui: https://kafka.apache.org/
 3. Arrancar HSQLDB
   * Para ello, acceder a la carpeta hsqldb/bin y lanzar el runServer.bat (o runServer.sh en caso de Linux/Mac).
 4. Es necesario tener instalado Apache Kafka. 
   * Las instrucciones para su instalación y despliegue se encuentran en https://kafka.apache.org/quickstart.
 5. Arrancar Apache Zookeeper
   * Para lanzarlo en Mac/Linux: ``bin/zookeeper-server-start.sh config/zookeeper.properties``
   * Para lanzarlo en Windows: ``bin\windows\zookeeper-server-start.bat config\zookeeper.properties``
 6. Arrancar Apache Kafka
   * Para lanzarlo en Mac/Linux: ``bin/kafka-server-start.sh config/server.properties``
   * Para lanzarlo en Windows: ``bin\windows\kafka-server-start.bat config\server.properties``
 7. Arrancar Agents
   * Ejecutar el siguiente comando, estando situado en la carpeta Agents_e5b: ``mvn spring-boot:run``
 8. Arrancar Manager
   * Ejecutar el siguiente comando, estando situado en la carpeta InciManager_e5b: ``mvn spring-boot:run``
 9. Acceder a localhost:8090/incidence/add
