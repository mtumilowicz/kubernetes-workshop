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
    * https://kubernetes.io
    * https://medium.com/@amroessameldin/kube-proxy-what-is-it-and-how-it-works-6def85d9bc8f
    * https://dzone.com/articles/journey-of-deployment-creation-in-kubernetes

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

## introduction
* rationale
    * problem: running containers on dedicated individual machines
    * solution: utilize a shared pool (cluster) of interconnected machines (nodes) and container orchestrator
        * container orchestrator responsibilities
            * dynamically manages and distributes container workloads across resource pool
            * optimizes resource utilization, enhancing scalability, and improving fault tolerance
* kubernetes
    * abstraction layer that sits at the workload level on top of the raw compute primitives like VMs
    (or bare metal machines) and load balancers

## architecture
* overview
    ![alt text](img/architecture.png)
* Master (Control Plane)
    * decision-making components
    * API Server (kube-apiserver)
        * provides the frontend to the Kubernetes control plane
            * example
                * checking the status of a pod
                * scheduling a new workload
                * modifying a resource
                * registration of worker nodes
        * all other components interact through with it
            * example
                * kubelet (on worker nodes)
                * controller-manager,
                * external clients like kubectl
        * provides RESTful interface
        * authenticates and authorizes
    * Scheduler (kube-scheduler)
        * assigns Pods to Nodes
        * scheduling is an optimization problem:
            1. filtering
                * goal: determine feasible placements (placements that meet given constraints)
                * filters out the worker nodes that cannot host the pod for some reason
                    * example: CPU and Memory requests
                * output: nodes on which pods can be scheduled
                    * length == 0 => pod will remain pending till this condition is remedied
                    * length == 1 => scheduling can occur without any actions
                    * length > 1 => moves to the next stages of scheduling
            1. scoring
                * goal: determine viable placements (feasible placements with the highest score)
                * example of rules used for evaluation:
                    * node affinity and anti-affinity
                    * does the node have a container image already?
                        * no image => it will take time to pull it
                    * lower workload utilization will give you a higher result
                * output: node with the highest score is selected for the scheduling of Pod
    * etcd
        * rationale: Kubernetes is distributed => it needs a distributed database
        * distributed key-value store
        * store all k8s data
            * anything you might read from a `kubectl get xyz` command is stored in etcd
            * any change you make via `kubectl create` will cause an entry in etcd to be updated
            * any node crashing or process dying causes values in etcd to be changed
        * stores actual state of the system and the desired state of the system
            * if diverge => reconciliation
        * provides events on changes happening to its keys
            * exposed over the watch API
            * Kubernetes uses it to monitor changes
                * controllers (e.g., Deployment, ReplicaSet controllers)
                    * ensure that the cluster's actual state matches the desired state
    * Controller Manager (controller-manager)
        * resource that manages other resources
        * comprises many resource controllers: replication controller, endpoints controller, namespace controller, etc
        * compares current state to the desired state of its resources, and makes any changes necessary
            * watches for changes in a specific Kubernetes resource using the watch API exposed by the api-server
            in a never-ending for loop called the control loop
        * use a label selector to identify the resources they manage
            * simple key-value pairs
            * used to loosely couple resources
* Minion (Worker Node)
    * components that execute the decisions made by Control Plane
        ![alt text](img/worker-node.png)
    * Pod
        * a unit of compute, which runs on a single node in the cluster
            * scheduled according to the resources
                * example: 2 CPU to run workload
        * an abstraction
            * just a logical grouping of containers
                * often: just a single container
                * sidecar: additional containers that should be deployed together with main application
                    * example: authorization, logging
                * Kubernetes binds them together ensuring they have common lifecycle
                    * example
                        * created together
                        * one fails => they are restarted
                        * terminated together
            * there is no notion of Pod in processes running on the node
                * just a bunch of processes from the containers
        * like a separate logical machine
            * with its own IP, hostname, processes, and so on
            * consists of 1+ containers in the same Linux namespace(s)
                * all the containers in a pod will appear to be running on the same logical machine
                    * containers in other pods, even on the same worker node, will appear to be running on a different one
        * can communicate with other Pods over virtual network, even if they’re running on different nodes
    * kubelet
        * daemon that runs on every node
            * is also deployed on the master to run the Control Plane components as pods
            * primary "node agent"
        * works in terms of a PodSpec (object that describes a pod)
            * uses a combination of polling and watching to receive updates about the desired state of resources
        * responsibilities
            * initial task: register the node it’s running on by creating a Node resource
                * sends regular heartbeats to the API server to confirm that the node is healthy and available
            * continuously checks for Pods scheduled to the node and starts them
                * digression: Kubernetes doesn’t really run containers
                    * it passes the responsibility to the container runtime installed on the node
                        * example: Docker, Podman, rkt
                            * rkt is dead: https://news.ycombinator.com/item?id=22249403
            * runs the container readiness/liveness probes, restarting containers when the probes fail
                * Pod is considered ready/live when all of its containers are ready/live
                * reports back to the API server on a timely basis
                    * API server uses these updates to
                        * track the state of the cluster
                        * make scheduling decisions
    * kube-proxy
        * runs on each node
        * overcomes the problem of Pods’ IPs being changed each time a Pod is recreated
        * agent doesn’t receive the actual traffic or do any load balancing
            * translates Service definitions into networking rules as NAT (Network Address Translation)
                * NAT rules are simply mappings from Service IP to Pod IP
                    * example: traffic sent to a Service => redirected to a backend Pod
                        * NAT rules pick one of the Pods according to used mode
                            * iptables -> random, IPVS -> algorithm
                * example
                    1. KUBE-SERVICES is a custom chain created by Kube-Proxy for the Services
                        ![alt text](img/kube-proxy/iptables-root.png)
                    1. traffic destined to the Service will enter appropriate chain
                        ![alt text](img/kube-proxy/iptables-routing.png)
                        * example: notice a specific chain created with a rule for destination IP of the Service (10.99.231.137)
                    1. NAT rules
                        ![alt text](img/kube-proxy/iptables-nat.png)
                        * notice this statistic mode random probability
                        * KUBE-SEP correspond to the Service endpoints (SEP)
                            * contains IP address of each Pod listed for each chain
        * modes
            * iptables
                * default and most widely used
                * relies on a Linux feature called iptables
                    * core feature of almost every Linux operating system
                * uses a sequential approach going through its tables
                    * it was originally designed as a packet filtering component
                    * number of lookups increases linearly by increasing the rules
                * doesn’t support specific load balancing algorithms
                    * uses a random equal-cost way of distribution
            * IPVS (IP Virtual Server)
                * Linux feature designed specifically for load balancing
                * optimized lookup algorithm with complexity of O(1)
                * supports different load balancing algorithms
                    * example: round robin, least connections, and other hashing approaches
                * might not be present in all Linux systems today

## resources
* Kubernetes objects
    * are persistent entities in the Kubernetes system
        * represent the state of the cluster
    * lifecycle can be managed with CRUD commands
        * `kubectl create`, `kubectl get`, `kubectl apply`, and `kubectl delete`
    * is a "record of intent"
        * specification for the desired state of the system
        * system will work to ensure the current state matches the desired state defined by these objects
            * example: Pod is just logical grouping - it does not exist physically
    * ownership
        * when controller is deleted, its managed objects still exist but not for long
            * Kubernetes runs a garbage collector process
        * can model a hierarchy
            * example: Pods owned by ReplicaSets, and ReplicaSets owned by Deployments
* Namespace
    * uses to group other resources
        * example
            * one namespace per product, one per team, or a single shared namespace
    * every resource lives inside a namespace
    * forms logical separation
        * provides isolation by name
            * example: pod named `web-app` in both `namespace-a` and `namespace-b`
        * do not create hard, physical boundaries
            * Role-Based Access Control (RBAC) for control access to resources at the namespace level
        * example: pods in different namespaces can communicate with each other by default
    * four initial namespaces:
        * default: objects with no other namespace
        * kube-system: objects created by the Kubernetes system
            * example: control plane (API server, controller manager, etc.)
        * kube-public: readable by all users (including those not authenticated)
            * mostly unused by default
            * use case: making cluster-wide, publicly accessible information available without authentication
        * kube-node-lease: used to manage NodeLease objects
            * each node in the cluster periodically updates its lease object
                * used by the control plane to determine the availability of nodes
            * helps improve the performance and scalability of large clusters
                * reducing load on etcd
* deploying workloads
    * Deployment
        * steps
            ![alt text](img/deployment-steps.png)
        * is a controller
            * reads the Deployment object and creates a ReplicaSet
        * technically, a deployment in Kubernetes is made of resources: Pods + Replica-Set
            * manages a ReplicaSet, which in turn manages the Pods
                ```
                spec:
                  replicas: 1
                  selector:
                    matchLabels:
                      app: customerinfo-app
                ```
            * Pods are created based on the template defined in the Deployment
                ```
                spec:
                  ...
                  template:
                    metadata:
                      labels:
                        app: customerinfo-app
                    spec:
                      containers:
                        - name: customerinfo-app
                          image: customerinfo-app:0.0.1-SNAPSHOT
                ```
    * ReplicaSet
        * is a controller
        * Deployments should be first choice for defining applications
            * don't use ReplicaSets directly
        * constantly runs a control loop: #(objects it owns) == #(replicas it should have)



    * within a namespace, Services are available using a simple domain name

## tools
* https://github.com/mtumilowicz/helm-workshop