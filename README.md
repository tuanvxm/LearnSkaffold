
# Learn Skaffold by example

## I. Introduction

Learn Skaffold by applying to simple webapp written by Java and VueJS. 

Back-end is written by Java, build by Jib. Front-end is written by VueJS, build by Docker. Both are deployed by kubectl CLI.

## II. Pre-requisite

Following items are required to finish this exercise:

- Kubernetes (k8s) Cluster.
- Kubectl connected to the k8s Cluster.
- Skaffold.
- Docker Hub account.
- Intellij to debug Java appliction.
- Clone this repository.

## III. Steps
### 1. Setup kubernetes namespace

    kubectl create ns <namespace>
    kubectl config set-context --current --namespace <namespace>

**Note:** Replace `<namespace>` by your namespace (ex: learn-skaffold-tuanvxm).

### 2. Setup default Docker reposity
Login to your docker hub account.

    docker login

Than input your username and password.

skaffold config set default-repo `<repository>`

Note: Replate `<repository>` by your Docker hub username (ex: tuanvxm).

### 3. Develop Java project in dev mode - skaffold dev
Take a look on project structure
-  **Dockerfile** Build definition for Docker
-  **DeployDefinition.yaml** Deploy definition for kubernetes
-  **skaffold.yaml ** Build and deploy definition for Skaffold.

Now deploy using

    skaffold dev

Skaffold will trigger maven to build project using Jib, push image to Docker Hub and deploy application to Kubernetes.

Now you will see skaffold build, deploy and than stream logs from kubernetes pod to local terminal.

Now run following command to get learn-skaffold-backend service `EXTERNAL-IP`

    kubectl get svc

Check if application is running correctly by access `http://<EXTERNAL-IP>:8080/getRed`.

//TODO image

Add Blue controller as follow to  `/src/main/java/com/skaffold/demo/controller/ColorController.java`

//TODO image

You will see Skaffold automatically re-deploy application when code is saved. Check if new API is deployed correctly by access `http://<EXTERNAL-IP>:8080/getBlue`.

### 4. Deploy VueJS one time - skaffold run

**Note:** Due to VueJS long build duration, you may consider using skaffold run instead of skaffold dev.

Init Skaffold

Run `skaffold init` and type `y -> Enter` to accept.

Skaffold will generate a simple `skaffold.yaml` automatically based on `Dockerfile` and `DeployDefinition.yaml`

**Note:** skaffold.yaml is definition file which instruct Skaffold to build and deploy our project.

We will need to modify this file a bit before we can use it.

Now deploy using `skaffold run -- tail`

**Note:** `--tail` is option to get logs of deployed application.

### 5. Try debug Java project remotely using Intellij

### 6. Using multiple profiles for dev and prod

## Extend A: Config Jib for Maven project

Add build plugin to `pom.xml`

//TODO image

Now you can try build Docker image using `mvn compile jib:dockerBuild -Dimage=test-jib`

## References:

https://skaffold.dev/

https://itnext.io/continuous-spring-boot-deployment-in-kubernetes-using-jib-and-skaffold-11fd3c71d941

