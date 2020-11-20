## Kubernetes-Horizontal-Pod-Autoscaler-Demo

This demo code can be referred to autosclae pods based on the CPU load

## Concepts:

```
1) SetUp Metric server
2) Build and deploy sample workload using quarkus with reactive APIs
3) SetUp Horizontal Pod Autoscaler (HPA)
4) Test the HPA
5) Destroy the setup
```


## SetUp Metric server

```
Setting Up Kubernetes Latest Metric server
 
  kubectl apply -f https://github.com/kubernetes-sigs/metrics-server/releases/latest/download/components.yaml

```

## Build and deploy sample workload

```
step 1: Get the source code
  
  git clone https://github.com/mosesalphonse/Kubernetes-Horizontal-Pod-Autoscaler-Demo.git
  cd Kubernetes-Horizontal-Pod-Autoscaler-Demo/workload
  

Step 2: build the application
(make sure the properties values are correct)

mvn clean compile package -Dquarkus.container-image.push=true

Step 3: modify the kubernetes resources 

  Add resources under 'containers' in target/kubernetes/kubernetes.yml as below
          resources:
          limits:
            cpu: 500m
          requests:
            cpu: 200m
  
  Refer the sample sample_kubernetes.yml file under refDocuments folder
  
Step 4: Deploy the resources into kubernetes

kubectl create -f target/kubernetes/kubernetes.yml

```

## SetUp Horizontal Pod Autoscaler (HPA)

```
Setting Up Kubernetes Latest Metric server
 
  kubectl autoscale deployment sash-quarkus-async --cpu-percent=40 --min=1 --max=8

```

## Test the HPA

```
Generate some traffic using the external load balancer
 
  while sleep 0.01; do wget -q -O- http://{external-lb-ip}:8080/sash/async/Moses; done
  while sleep 0.01; do wget -q -O- http://{external-lb-ip}:8080/sash/async/Quarkus; done
  while sleep 0.01; do wget -q -O- http://{external-lb-ip}:8080/sash; done

```
