eval $(minikube docker-env)

kubectl delete -f ./kubernetes/customer-db-service-deployment.yml
kubectl delete -f ./kubernetes/mongo-db-deployment.yml
kubectl delete -f ./kubernetes/user-ldap-server-deployment.yml
kubectl delete -f ./kubernetes/mongo-data-provider.yml

./gradlew build
docker build -f Dockerfile -t team-f-customer-data-server .
docker build -f ./mongo-data-provider/Dockerfile -t team-f-mongo-data-provider .

kubectl apply -f ./kubernetes/customer-db-service-deployment.yml
kubectl apply -f ./kubernetes/mongo-db-deployment.yml
kubectl apply -f ./kubernetes/user-ldap-server-deployment.yml
kubectl apply -f ./kubernetes/mongo-data-provider.yml
