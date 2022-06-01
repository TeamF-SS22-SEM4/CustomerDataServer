./gradlew build
docker build -f Dockerfile -t team-f-customer-data-server .
kubectl rollout restart deployment/customer-db-service