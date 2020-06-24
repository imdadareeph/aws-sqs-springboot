# aws-sqs-springboot
aws-sqs-springboot

## Environment for Development
 Those projects were developed with followings.

 * Java SDK 8 (1.8.0_112)
 * Spring-boot (2.3.0.RELEASE)
 * Apache Maven (3.6.2)
 
 ## Run the demo
The whole application has been packaged to be run as mvn spring-boot:run followed by h2-console query to :
```
CREATE TABLE QUOTE_ENTITY (
id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
text VARCHAR(400) NOT NULL,
author VARCHAR(100),
aws_message_id VARCHAR(100) UNIQUE NOT NULL,
date_received TIMESTAMP
);
SELECT * FROM QUOTE_ENTITY;
```

### Clone the application codes
 You need a new folder to clone the codes and you can get the codes from git repo.
 ```
 git clone https://github.com/imdadareeph/aws-sqs-springboot.git
 ```
 
 ### Screenshots
 
 ![alt text](https://raw.githubusercontent.com/imdadareeph/aws-sqs-springboot/master/src/main/resources/static/screenshots/1.png "preview1")
 ![alt text](https://raw.githubusercontent.com/imdadareeph/aws-sqs-springboot/master/src/main/resources/static/screenshots/2.png "preview2")
 ![alt text](https://raw.githubusercontent.com/imdadareeph/aws-sqs-springboot/master/src/main/resources/static/screenshots/3.png "preview3")
 ![alt text](https://raw.githubusercontent.com/imdadareeph/aws-sqs-springboot/master/src/main/resources/static/screenshots/4.png "preview4")
 ![alt text](https://raw.githubusercontent.com/imdadareeph/aws-sqs-springboot/master/src/main/resources/static/screenshots/5.png "preview5")
 ![alt text](https://raw.githubusercontent.com/imdadareeph/aws-sqs-springboot/master/src/main/resources/static/screenshots/6.png "preview6")
 ![alt text](https://raw.githubusercontent.com/imdadareeph/aws-sqs-springboot/master/src/main/resources/static/screenshots/7.png "preview7")
