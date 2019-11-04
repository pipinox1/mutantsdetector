## El ejercicio planteado a continuacion da solucion al siguiente problema:

Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar
contra los X-Mens y nos ha demandado el siguiente problema:

Se recibirá como parámetro un array de Strings que representan cada fila de una tabla
de (NxN) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G), las
cuales representa cada base nitrogenada del ADN, sabremos si un humano es mutante, si encuentras más de una secuencia de cuatro letras
iguales​, de forma oblicua, horizontal o vertical

## Tecnologias aplicadas para el desarollo:
- [SpringBoot](https://spring.io/projects/spring-boot)
- [MongoDB](https://www.mongodb.com)
- [Mockito](https://site.mockito.org/)
- [JUnit](http://junit.org/junit5/)

## Tecnologias aplicadas para el despligue:
- [Docker](https://www.docker.com/)
- [AWS - EC2](https://aws.amazon.com/es/ec2/)
- [Fabric8](https://fabric8.io/)


## Setup y Deployment del proyecto

Para poder desplegar esta aplicacion necesitamos simplemente tener instalado Docker y Docker Compose, debido a que se genero una Imagen con la aplicacion Java realizada y se utiliza la imagen de Mongo oficial.

Una vez hemos instalado estas herramientas, procedemos a crear un archivo llamado docker-compose.yml con el siguiente contendio:
```
version: '3'
services:
  mutant:
    image: pipinox1/mutantsdetector
    restart: always
    container_name: mutant
    ports:
      - 8080:8085
    environment:
      SPRING_DATA_MONGODB_URI: mongodb://mongo-mutant/mutant
    depends_on:
      - mongo-mutant
  mongo-mutant:
    image: mongo
    restart: always
    container_name: mongo-mutant
    ports:
      - 27017:27017
    environment:
      MONGO_INITDB_DATABASE: mutant
    volumes:
      - $HOME/data/mutant:/data/db
```
Como el ejercicio planteado es a fines practicos no se seteo password en la base datos ni en las propiedas de la aplicacion, en un ambiente productivo seria optimo definir estas propiedas desde un archivo externo y leerlas a traves del docker compose.

Una vez creado este archivo debemos ponerlo en la carpeta donde se genero y ejecutar el comando:
```
docker-compose up
```

Y listo! Nuestra aplicación fue deployada.


## Api

La api brinda 2 endpoint que permiten:

- Vericar si una cadena de ADN corresponde a un mutante o a un humano
Este metodo retorna un status 403 si el ADN corresponde a un humano y un 200 en caso de que sea mutante.

POST 
```
localhost:8080/mutant
```
Body
```
{
	"dna":["ATGAGA","CCGTGC","TCATGT","AGAAGG","CAACTA","TCACTG"]
}
```
Este body se encuentra validado y exige que las letras sean solo las permitidas, ademas que la matriz sera cuadra y que sea de una dimension mayor o igual a 4x4.


- Estadisticas
Este metodo retorna el numero de mutantes y humanos que se han detectado y el ratio de mutantes con respecto a humanos.

GET 
```
localhost:8080/stats
```
Response
```https://fabric8.io/
{
    "ratio": 1.0,
    "count_mutant_dna": 2,
    "count_human_dna": 2
}
```

## Implementacion en AWS

POST 
```
http://ec2-3-87-162-226.compute-1.amazonaws.com:8080/mutant
```

GET 
```
http://ec2-3-87-162-226.compute-1.amazonaws.com:8080/stats
```

## Extras
Para testear la performance de la Api se utilizo el siguiente comando para ejecutar en paralelo los script y simular concurrencia.

```
seq 100000 | parallel -n0 -j1000 "curl --write-out '%{http_code}\n'  -H 'Content-Type: application/json' \
--data @body.json  \
http://localhost:8080/mutant --silent"
```
Contenido del archivo body.json
```
{"dna":["ATGCGT","CAGAGC","TCATGT","AGATGG","CACATA","TCACTG"]}
```


