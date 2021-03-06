# MSSCP System Introduction  
## Introduction 
* MSSCP is short for Micro Service based on Spring Cloud Project which combined by 9 micro services, different micro services organized as different model.
* Each micro service in project is a spring-boot driven project which is independent and get access to other services via their exposed RESTful APIs.

> register center <br/>
> config center  <br/>
> user center <br/>
> oauth center <br/> 
> gateway center <br/>
> backend center <br/>
> log center<br/>
> file center <br/>
> monitor center <br/>

## Technical Stack Introduction 

* In MSSCP I adopt Spring Cloud, Spring Boot, Spring Security, java8, maven, mysql, redis, rabbitmq, jenkins and postman
* MSSCP adopts front-back stage decoupling technology by Backend Center in which backend provides data querying APIs 
  and frontend provides router control and page rendering. 
* In MSSCP system's backend provides data in json type as request query results, which supports both mobile apps' and web services' query.


## Architecture Diagram 

## Micro-Service Interaction 

## System Data Flow Diagram 
 
----- 

## Register Center 
### introduction 
* Register center as its name, it it implemented based on Spring Cloud Eureka service.
* Register center monitor to specified URL which different micro services in MSSCP published their services on.
* It will cache each services, and dispatches every request to its correct services working address. 
### deploy env  
* service address: `https://local.register.com:${8761}/eureka`
* log path: logs/register-center.log 
 
### Eureka Learning Notes 

---- 

## Config Center 
### introduction 
* Config center stores all the system's config files/parameters and provides its config params all the services by exposting specified service APIs.  

### deploy env 
* service address: `http://localhost:${random_port}`
* log path: logs/config-center.log 
* local config files locates: classpath:/configs/${profile} # {dev|prod|test} 

### Spring Cloud Config Server Learning Notes 


---- 


## Gateway Center
### introduction 
* All querys to APIs in MSSCP published services will first arrive to Gateway Center, which knows which query needs to be dispatched to which services
by parsing prefix of each request's query url. 
* An IP blacklist filter function can also be added to this micro services, which request with illegal ip address will be not be redirected to specified services address,
and the IP blacklist can be managed by backend center.  

### deploy env 
* service address: `http://localhost:8080`
* log path: logs/gateway-center.log 


### Gateway Center RESTful API online Documents 
*  [swagger online APIs]()
###  `ServiceInstanceController`
#### /service-instances 
* RESTful Type: GET 
* usage: get all alive services' info like services published ip and port in MSSCP system.
 
* request  
```json 
none 
```
* response 
```json 
``` 

### `TokenController`
#### /sys/login
* RESTful Type: POST
* usage: user login system by providing only username and password.
* request
```json
{
 "username": "String",
 "password": "String" 
}
```

* response 
```json 
```

#### /sys/login-sms
* RESTful Type: POST
* usage: login system by providing phone number and sms message info 
* request 
```json 
{
  "phone": "String",
  "key": "String",
  "code": "String"
}
```

* response 
```json
{
}
```


#### /sys/login-wechat 
* RESTful Type: POST
* usage: login system by authorized on wechat(a mobile app)
* request 
```json 
{
"openid": "String",
"tempCode": "String"
}
```

* response 
```json 
{}
```

#### /sys/refresh_token
* RESTful Type: POST 
* usage: refresh current user's access_token 
* request 
```json
{
  "refresh_token" : "String"
}
```

* response 
```json 
```


#### /sys/loginout
* RESTful Type: POST 
* usage: logout current system 
* request 
```json 
{
  "access_token": "String"
}
```

* response 
```json
none 
```

 
### Database Table Design 
* no db table

### UML 
* todo 

### Spring Cloud Zuul Learning Notes


----- 


## User Center 
### introduction 
* User center provides services about allocating and revoking users' roles' privileges. 

### deploy env 
* service address: ``

### RESTful API online Documents 
 
### Database Table Design 

### UML 

-----


## OAuth Center 
OAuth Center publish services by APIs of certification based on Spring Cloud OAuth2 certification service. 


### RESTful API online Documents 
 
### Database Table Design 

### UML 

----- 
## Backend Center 
Backend center is designed mainly for managing ip blacklist, inner system mail sending/receiving and user info/privileges' add, update, remove and so on.


### RESTful API online Documents 
 
### Database Table Design 

### UML 

---- 

## Log Center 
Log center is responsible for collecting logs from all micro-services in the MSSCP system by exposing APIs to other services.
There is a rabbitmq maintained inside Log Center, rabbitmq sending data streaming from different services log stream to mysql database. 


### Log Center RESTful API online Documents
#### /logs-center/internal

* RESTful Type: POST 
* usage: post log info to log center 
* request 
```json 
{
  "id": "Long",
  "username": "String",
  "module": "String",
  "params": "String",
  "remark": "String",
  "flag": "Boolean",
  "createTime": "Long"
}
```

* response 
```json
none 
```

#### /logs

* RESTful Type: GET 
* usage: get total saved log content in list  
* request 
```json 
{
  
}
```

* response 
```json
{
 "total": "Long",
 {
   "id": "Long",
     "username": "String",
     "module": "String",
     "params": "String",
     "remark": "String",
     "flag": "Boolean",
     "createTime": "Long"
 }, 
 {
 "id": "Long",
  "username": "String",
  "module": "String",
  "params": "String",
  "remark": "String",
  "flag": "Boolean",
  "createTime": "Long"
 }
}
```

 
### Database Table Design 
* no db table 

### Log Center UML 

---- 

## File Center
* File Center provides service of files' local storage, files' uploading, downloading.
* Now file center only supports files stored on local filesystem, in future local filesystem APIs can be replaced by HDFS or other third party storage system.  

### File Center RESTful API online Documents
#### /files
* RESTful Type: POST
* usage: upload multiple files to file center 
* request 
```json 
{
 "file": "MultipartFile",
 "fileSource": "String"
} 
```

* response 
```json 
{
 "id": "String",
 "name": "String",
 "isImg": "Boolean",
 "contentType": "String",
 "size": "long",
 "path": "String",
 "url": "String"
 "source": "String",
 "createTime": "long"
}
```

#### /files/richType 
* RESTful Type: POST 
* usage: this API supports rich type files uploading
* request 
```json
{
 "file": "MultipartFile",
 "fileSource": "String",
}
```
* response 
```json
{}
```

#### /files/{id}
* RESTful Type: 
 
 
### Database Table Design 

### File Center UML 

----- 

## Monitor Center 
Monitor micro-services provider, monitor center is based on Spring Clouds Admin services. Monitor center mainly monitor system's other micro-services status to make every services under healthy state and provides stable services.

### RESTful API online Documents 
 
### Database Table Design 

### Monitor Center UML

### Spring Cloud Admin Learning Notes 

## BootStrap.yml Learning Notes 
```yaml

```
----- 
## Jenkins Deployment 
// todo

--- 
## Test Case Design 

// todo 

---

## Postman  Automatically APIs Testing 
// todo

---
### Docker online Deployment 
// todo 

--- 
 
 