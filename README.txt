# kubernetes-workshop

* references
    * https://www.manning.com/books/kubernetes-for-developers
    * https://kubernetes.io/docs/reference/command-line-tools-reference/kubelet/
    * https://www.manning.com/books/kubernetes-in-action
    * https://www.manning.com/books/learn-kubernetes-in-a-month-of-lunches
    * https://medium.com/google-cloud/understanding-kubernetes-networking-pods-7117dd28727
    * https://kubernetes.io/blog/2018/04/30/zero-downtime-deployment-kubernetes-jenkins/
    * https://kubernetes.io/docs/concepts/overview/working-with-objects/kubernetes-objects/
    * https://matthewpalmer.net/kubernetes-app-developer/articles/how-does-kubernetes-use-etcd.html
    * https://sensu.io/blog/how-kubernetes-works
    * https://kubernetes.io/docs/reference/command-line-tools-reference/kube-scheduler
    * https://dominik-tornow.medium.com/the-kubernetes-scheduler-cd429abac02f
    * https://kubernetes.io/docs/reference/command-line-tools-reference/kube-apiserver/
    * https://kubernetes.io/docs/tasks/configure-pod-container/configure-liveness-readiness-startup-probes/
    * https://www.cyberark.com/resources/threat-research-blog/using-kubelet-client-to-attack-the-kubernetes-cluster/
    * https://stackoverflow.com/questions/48956049/what-is-the-difference-between-persistent-volume-pv-and-persistent-volume-clai
    * https://medium.com/devops-mojo/kubernetes-storage-options-overview-persistent-volumes-pv-claims-pvc-and-storageclass-sc-k8s-storage-df71ca0fccc3
    * [12 Ways of the Cloud Native Warrior - Burr Sutter - part 1/2](https://www.youtube.com/watch?v=M7yNrA82eU8)
    * [12 Ways of the Cloud Native Warrior - Burr Sutter - part 2/2](https://www.youtube.com/watch?v=bujAAujYjJg)
    * [The ultimate introduction to Kubernetes - Pascal Naber](https://www.youtube.com/watch?v=HUW-VZ9OEos)
    * [Nicolas Frankel - Zero-downtime deployment with Kubernetes, Spring Boot and Flyway](https://www.youtube.com/watch?v=RvCnrBZ0DPY)
    * [Akka and Kubernetes, the beginning of a beautiful relationship - Hugh McKee](https://www.youtube.com/watch?v=CjkiznureoU)
    * [Continuous deployment to Kubernetes with the Google Container Tools by David Gageot](https://www.youtube.com/watch?v=3nfNP00Tv1k)
    * [Canary Deploys with Kubernetes and Istio by Jason Yee](https://www.youtube.com/watch?v=VU2ILSrpy_Y)
    * [2018 - Mateusz Dymiński - Zero-Downtime deployments of Java applications with Kubernetes](https://www.youtube.com/watch?v=TVB-sQfJBLc)
    * [Develop and Deploy to Kubernetes like a Googler by David Gageot](https://www.youtube.com/watch?v=YYJ4RZFw4j8)
    * [Day Two Kubernetes: Tools for Operability](https://www.youtube.com/watch?v=ujg2UuBm-Rs)
    * [Hugh Mckee - Building Stateful Clustered Microservices with Java, Actors, and Kubernetes](https://www.youtube.com/watch?v=AJD4RjYRIaU)
    * [Kubernetes Distilled - an in depth guide for the busy Java developer by Alberto Rios & Oliver Hughes](https://www.youtube.com/watch?v=l7lt6yYLvRo)
    * [Transform a Legacy Application with Kubernetes and Istio - David Gageot](https://www.youtube.com/watch?v=6jBetJgluEE)
    * [Serverless with Knative - Mete Atamel](https://www.youtube.com/watch?v=Yf8heIFnFOc)
    * [[VDZ19] Service Mesh and Sidecars with Istio and Envoy by Rafael Benevides](https://www.youtube.com/watch?v=KLBFR_rUreE)
    * [Better Canary Deploys with Kubernetes and Istio by Jason Yee](https://www.youtube.com/watch?v=R7gUDY_-cFo)
    * [Optimising Kubernetes deployments with Helm by Erwin de Gier](https://www.youtube.com/watch?v=TXZBuBQpm-Q)
    * [9 Steps to Awesome with Kubernetes by Burr Sutter](https://www.youtube.com/watch?v=ZpbXSdzp_vo)
    * [DevOpsDays Warsaw 2019 - Dawid Ziółkowski - Istio MasterClass](https://www.youtube.com/watch?v=Z0X0NmVXk0g)
    * [JDD 2019: Wprowadzenie do Kubernetes [PL], Krzysztof Dziankowski](https://www.youtube.com/watch?v=e_j4S0Qqzwk)
    * [Beyond Kubernetes by Ray Tsang, Eric Bottard](https://www.youtube.com/watch?v=HEWb1IWqT30)
    * [Just DevOps 2019 - Mariusz Gil - Service mesh with Istio [PL]](https://www.youtube.com/watch?v=kRrfXVHBZjA)
    * [WJUG #266 - Matt Jarvis - Introduction to KUDO - Kubernetes operators the easy way](https://www.youtube.com/watch?v=oHu0WvxOJ2s)
    * [WJUG #270 ONLINE - Krzysztof Suszyński - Knative, Serverless w Kubernetes oraz OpenShift](https://www.youtube.com/watch?v=eYr_E4Qmta4)
    * https://chatgpt.com/

## preface
* goals of this workshop
* workshop plan
    1. deploy `customer` and `customer-info` microservice
        * notice how `customer` is deployed wit correct `SPRING_PROFILES_ACTIVE`
            * and how it affects `application.yml` properties
        * commands
            ```
            kubectl apply -f namespace.yml
            kubectl apply -f customerinfo-configmap.yml
            kubectl apply -f customer-app-deployment.yml
            kubectl apply -f customer-app-service.yml
            kubectl apply -f customerinfo-app-deployment.yml
            kubectl apply -f customerinfo-app-service.yml
            ```
    1. verify that they are able to communicate with each other
        * curl http://localhost:8080/customer/123/short-info
        * curl http://localhost:8080/customer/123/full-info
            * to get full info, `customer` is querying `customerinfo`

## architecture
* overview
    ![alt text](img/architecture.png)
* Master (Control Plane)
    * API Server (kube-apiserver)
        * provides the frontend to the Kubernetes control plane
            * example
                * checking the status of a pod
                * scheduling a new workload
                * modifying a resource
        * all other components interact through with it
            * example
                * kubelet (on worker nodes)
                * controller-manager,
                * external clients like kubectl
        * provides RESTful interface
    * Scheduler (kube-scheduler)
        * assigns Pods to Nodes
        * scheduling is an optimization problem:
            1. determine feasible placements (placements that meet given constraints)
            1. determine viable placements (feasible placements with the highest score)
    * etcd
        * rationale: Kubernetes is distributed => it needs a distributed database
        * distributed key-value store
        * store all k8s data
            * anything you might read from a `kubectl get xyz` command is stored in etcd
            * any change you make via `kubectl create` will cause an entry in etcd to be updated
            * any node crashing or process dying causes values in etcd to be changed
        * stores actual state of the system and the desired state of the system
            * if diverge => reconciliation
        * supports `watch` natively
            * change triggers an event
            * Kubernetes uses it to monitor changes
                * controllers (e.g., Deployment, ReplicaSet controllers)
                    * ensure that the cluster's actual state matches the desired state
    * Controller Manager (controller-manager)
        * resource that manages other resources
        * examples: replication controller, endpoints controller, namespace controller, etc
        * compares current state to the desired state of its resources, and makes any changes necessary
        * use a label selector to identify the resources they manage
* Minion (Worker Node)
    * Pod
    * kubelet
    * kube-proxy


## tools
* https://github.com/mtumilowicz/helm-workshop