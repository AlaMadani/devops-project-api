# **DevOps API Project 🚀**

A robust REST API service designed to demonstrate end-to-end DevOps practices. This project includes CI/CD automation, containerization, orchestration with Kubernetes, observability (Prometheus/Grafana), and security scanning.

## **🛠 Tech Stack**

* **Language:** Java 17 (Spring Boot 3\)  
* **Build Tool:** Maven  
* **Containerization:** Docker  
* **Orchestration:** Kubernetes (Kind)  
* **CI/CD:** GitHub Actions  
* **Observability:** Prometheus, Grafana, Micrometer  
* **Security:** Trivy (SAST), OWASP ZAP (DAST)

## **🚀 Getting Started**

### **Prerequisites**

* Java 17+  
* Maven  
* Docker  
* Kind (Kubernetes in Docker)  
* Kubectl

### **1\. Run Locally (Java)**

To run the application directly on your machine:
```
mvn clean package  
java -jar target/devops-api-0.0.1-SNAPSHOT.jar
```

Access the API at: http://localhost:8080

### **2\. Run with Docker**

You can pull the pre-built image from Docker Hub:
```
docker pull alamadani/mon-api-devops:latest  
docker run -p 8080:8080 alamadani/mon-api-devops:latest
```
## **☸️ Kubernetes Deployment (Kind)**

### **Step 1: Initialize Cluster**

If you haven't created a cluster yet:
```
kind create cluster --name devops-cluster
```
### **Step 2: Deploy the Stack**

Deploy the Application, Prometheus, and Grafana using the manifest files:

\# Apply all configurations  
```
kubectl apply -f k8s/
```
### **Step 3: Access Services (Port Forwarding)**

Since Kind runs in a container, you need to forward ports to access services from your browser. Open separate terminals for each:

**1\. Access the API:**
```
kubectl port-forward service/devops-api-service 8080:80
```
URL: http://localhost:8080

**2\. Access Prometheus:**
```
kubectl port-forward service/prometheus-service 9090:9090
```
URL: http://localhost:9090

**3\. Access Grafana:**
```
kubectl port-forward service/grafana-service 3000:3000
```
URL: http://localhost:3000  
Credentials: admin / admin

## **📡 API Endpoints**

| Method | Endpoint | Description |
| :---- | :---- | :---- |
| GET | / | Home page |
| GET | /api/hello | Returns a Hello World message |
| GET | /api/message | **Dynamic:** Get the current "Message of the Day" |
| POST | /api/message | **Dynamic:** Update the message (text body) |
| GET | /api/error | Simulates a 500 Server Error (for testing logs) |

### **Testing Examples (Curl)**

**Get the current message:**
```
curl http://localhost:8080/api/message
```
**Update the message:**
```
curl -X POST -H "Content-Type: text/plain" -d "DevOps is awesome\!" http://localhost:8080/api/message
```
## **📊 Observability**

This project implements a full monitoring stack:

1. **Metrics (Actuator):** Raw metrics available at /actuator/prometheus.  
2. **Prometheus:** Scrapes metrics every 5 seconds. Use queries like jvm\_memory\_used\_bytes or rate(http\_server\_requests\_seconds\_count\[1m\]).  
3. **Grafana:** Visualizes data. Import dashboard ID **11378** (JVM Micrometer) for instant visualization.

## **🔒 Security**

* **SAST:** GitHub Actions runs **Trivy** on every push to scan the filesystem for vulnerabilities.  
* **DAST:** Runtime scanning is performed using **OWASP ZAP**.

## **🔄 CI/CD Pipeline**

The project uses GitHub Actions (.github/workflows/main.yml) to automate:

1. **Checkout** code.  
2. **Setup** Java JDK 17\.  
3. **Build** & Test with Maven.  
4. **Scan** vulnerabilities (Trivy).  
5. **Build** Docker Image.  
6. **Push** to Docker Hub (alamadani/mon-api-devops).