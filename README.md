# InciManagement_e5b
InciManagement

[![Build Status](https://travis-ci.org/Arquisoft/InciManager_e5b.svg?branch=master)](https://travis-ci.org/Arquisoft/InciManager_e5b)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/97d6326cbcbb4c638d59879facacaf32)](https://www.codacy.com/app/jelabra/InciManager_e5b?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Arquisoft/InciManager_e5b&amp;utm_campaign=Badge_Grade)[![codecov](https://codecov.io/gh/Arquisoft/InciManager_e5b/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/InciManager_e5b)

Para ejecutar el proyecto:
1-Descargar Spring-boot STS desde el siguiente enlace
https://spring.io/tools
2-Descarga la base de datos desde el siguiente enlace, usamos la version 2.4.0
https://sourceforge.net/projects/hsqldb/files/
3-Importar como maven este proyecto y el modulo 2 del otro equipo que puedes obtener a traves del siguiente enlace
https://github.com/Arquisoft/Agents_e5b
4-Para ejecutar el proyecto es necesario tener arrancado el apache kafka. Para ello descargarlo de aqui:
https://kafka.apache.org/
5-Una vez descargardo ejecutar desde la ventana de comandos en windows, para hacer desde mac contactar con los desarrolladores
>bin\windows\zookeeper-server-start.bat config\zookeeper.properties
6-Desde otra ventana, ya que la anterior va a quedar corriendo
>bin\windows\kafka-server-start.bat config\server.properties
7-Ejecutar como java application las dos aplicaciones con la bbdd encendida
8-Si no has modificado los puertos bastaria con entrar en:
localhost:8090/incidence/add

InciManager module

# 2017-2018

Mateo Juarez Verdugo (@UO251123)

Manuel Mori Álvarez (@ManuelMori)

Javier Gonzalez Fernandez (@UO245020)

Marcos Machado Menéndez (@UO238688)
