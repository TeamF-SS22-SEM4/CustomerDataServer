eval $(minikube docker-env)

kubectl delete -f ./kubernetes/customer-db-service-deployment.yml
kubectl delete deployments customer-db-service
kubectl delete service customer-db-service

./gradlew build
docker build -f Dockerfile -t team-f-customer-data-server .
kubectl apply -f ./kubernetes/customer-db-service-deployment.yml
kubectl expose deployment customer-db-service --type=LoadBalancer --name=customer-db-service