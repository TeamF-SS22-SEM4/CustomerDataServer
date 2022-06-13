docker-compose -f docker/docker-compose.yml -f docker/docker-compose.local.yml down
./gradlew build
docker-compose -f docker/docker-compose.yml -f docker/docker-compose.local.yml build
docker-compose -f docker/docker-compose.yml -f docker/docker-compose.local.yml up -d