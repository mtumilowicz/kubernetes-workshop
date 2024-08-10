curl http://localhost:8080/customer/123/short-info
curl http://localhost:8080/customer/123/full-info

kubectl apply -f namespace.yml
kubectl apply -f customerinfo-configmap.yml
kubectl apply -f customer-app-deployment.yml
kubectl apply -f customer-app-service.yml
kubectl apply -f customerinfo-app-deployment.yml
kubectl apply -f customerinfo-app-service.yml