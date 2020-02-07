
# Learn Skaffold by example

## I. Introduction

Learn Continuous development for Kubernetes-native applications with Skaffold by applying it to Java + VueJS web applications.

Back-end (WebApp) is written in Java, built by Jib. Front-end (WebUI) written in VueJS, built by Docker. Both are deployed by kubectl CLI.

This example focuses on how to achieve Continuous Development on Kubernetes platform using Skaffold. It also introduces how to run and debug Java applications on Kubernetes using the Cloud Code plugin for Intellij.

## II. Pre-requisite

Following items are required to finish this exercise:

- Skaffold 1.3.1 or later. Install intruction can be found at https://skaffold.dev/docs/install/
- Kubernetes (k8s) Cluster.
- Kubectl connected to the k8s Cluster.
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

    docker login -u <username> -p <password>

**Note:** Replace `<username>` and `<password>` by your Docker Hub username and password.

Config default repository for Skaffold.

    skaffold config set default-repo `<repository>`

Note: Replace `<repository>` by your Docker Hub username (ex: tuanvxm).

### 3. Develop Java project in dev mode - skaffold dev

Take a look on project structure:

-  **DeployDefinition.yaml** Deploy definition for kubernetes
-  **skaffold.yaml** Build and deploy definition for Skaffold.

**Note:** This project doesn't include Dockerfile because it is built with Jib, the configuration for Jib is contained in `pom.xml`.

//TODO image

Now deploy Java project using:

    skaffold dev

**Note:** By default, Skaffold will automatically clean all related resources when dev sessions is stopped, you may want to use `skaffold dev --cleanup=false` to prevent this action.

Skaffold will trigger maven to build project, push image to Docker Hub and deploy application to Kubernetes.

Now you will see skaffold build, deploy and than stream logs from kubernetes pod to local terminal.

//TODO image

Now run following command to get learn-skaffold-backend service `EXTERNAL-IP`:

    kubectl get svc

![](https://tuanvxm-mix-files.s3-ap-southeast-1.amazonaws.com/git/skaffold/84834021_178454553380896_6037797258722803712_n.png)

Check if application is running correctly by access `http://<EXTERNAL-IP>:8080/getRed`.

//TODO image

Add Blue controller as following to  `/src/main/java/com/skaffold/demo/controller/ColorController.java`

![](https://tuanvxm-mix-files.s3-ap-southeast-1.amazonaws.com/git/skaffold/84898516_3058396700845429_7877484837118935040_n.png)

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

### 5. Debug Java project remotely using Intellij

In this section, we will debug Java application remotely with `Inteillj and Cloud Code plugin`.

**Note:** Beside `Java` and `Intellij`, Cloud Code also support `Kotlin, Node.js, Go languages` and `VS Code IDE`.

Install Cloud Code plugin, from `Intellij > File > Settings > Plugin`. 

![](https://tuanvxm-mix-files.s3-ap-southeast-1.amazonaws.com/git/skaffold/84979872_2908824212493747_7825021824483196928_n.png)

Create Cloud Code config as following, specify `default image repository` (your Docker Hub account)

![](https://tuanvxm-mix-files.s3-ap-southeast-1.amazonaws.com/git/skaffold/83520518_884298008678361_4135457291572871168_n.png)

![](https://tuanvxm-mix-files.s3-ap-southeast-1.amazonaws.com/git/skaffold/83514436_487081242226308_8453958547338690560_n.png)

**Note:** You may need to down grade `apiVersion` in `skaffold.yaml` to `skaffold/v2alpha2` if you get following error:

![](https://tuanvxm-mix-files.s3-ap-southeast-1.amazonaws.com/git/skaffold/83995890_2205602299747074_6245479998593957888_n.png)

Add debug point to any controller, access the related api and see the magic.

![](https://tuanvxm-mix-files.s3-ap-southeast-1.amazonaws.com/git/skaffold/84659750_491885838182005_5119805109382938624_n.png)

### 6. Using multiple profiles for dev and prod

## Extend A: Config Jib for Maven project

Add build plugin to `pom.xml`

//TODO image

Now you can try build Docker image using `mvn compile jib:dockerBuild -Dimage=test-jib`

## References:

https://skaffold.dev/

https://cloud.google.com/code

https://itnext.io/continuous-spring-boot-deployment-in-kubernetes-using-jib-and-skaffold-11fd3c71d941

