./mvnw clean package -DskipTests && cp target/desafio-sogo-0.0.1-SNAPSHOT.jar ./docker/app/app.jar

cd docker && docker-compose up --build -d && cd ..
