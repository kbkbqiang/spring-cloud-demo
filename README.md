# spring-cloud-demo

## 项目介绍
项目基于springboot2.0和springcloud Finchley.RC1

## 搭建注册中心
### 1. 在pom.xml文件中导入依赖
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.zkane</groupId>
    <artifactId>eureka-server</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <name>eureka-server</name>
    <description>Spring Cloud Eureka Server</description>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent
        </artifactId>
        <version>2.0.1.RELEASE</version>
        <relativePath/>
    </parent>

    <properties>
        <project.build.sourceencoding>UTF-8</project.build.sourceencoding>
        <project.reporting.outputencoding>UTF-8</project.reporting.outputencoding>
        <java.version>1.8</java.version>
        <spring-cloud.version>Finchley.RC1</spring-cloud.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies
                </artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

    <repositories>
        <repository>
            <id>spring-milestones</id>
            <name>Spring Milestones</name>
            <url>https://repo.spring.io/libs-milestone</url>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </repository>
    </repositories>

</project>
```

### 2. 在application.yml文件中配置
```
server:
  port: 8000
spring:
  application:
    name: eureka-server
#eureka.client.register-with-eureka ：表示是否将自己注册到Eureka Server，默认为true。
#eureka.client.fetch-registry ：表示是否从Eureka Server获取注册信息，默认为true。
#eureka.client.serviceUrl.defaultZone ：设置与Eureka Server交互的地址，查询服务和注册服务都需要依赖这个地址。默认是http://localhost:8761/eureka ；多个地址可使用 , 分隔
eureka:
  client:
    register-with-eureka: false
    fetch-registry: false
    serviceUrl:
      defaultZone: http://localhost:${server.port}/eureka/
```
### 3. 在启动类上配置注解
```
package com.zkane;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}
}
```
## 搭建服务提供者
### 1. 在pom.xml文件中导入依赖
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.zkane</groupId>
	<artifactId>producer-server</artifactId>
	<version>1.0.0</version>
	<packaging>jar</packaging>

	<name>producer-server</name>
	<description>Spring Cloud Producer Server</description>

	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent
		</artifactId>
		<version>2.0.1.RELEASE</version>
		<relativePath/>
	</parent>

	<properties>
		<project.build.sourceencoding>UTF-8</project.build.sourceencoding>
		<project.reporting.outputencoding>UTF-8</project.reporting.outputencoding>
		<java.version>1.8</java.version>
		<spring-cloud.version>Finchley.RC1</spring-cloud.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies
				</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

	<repositories>
		<repository>
			<id>spring-milestones</id>
			<name>Spring Milestones</name>
			<url>https://repo.spring.io/libs-milestone</url>
			<snapshots>
				<enabled>false</enabled>
			</snapshots>
		</repository>
	</repositories>


</project>
```
### 2. 在application.yml配置文件中配置
```
server:
  port: 8100
spring:
  application:
    name: producer-server
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8000/eureka/
```
### 3. 在启动类上添加注解
```
package com.zkane;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ProducerServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProducerServerApplication.class, args);
	}
}
```
### 4. 编写controller代码，提供restful风格的API访问
```
package com.zkane.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhaoqiang
 * @date: 2018/5/14
 */
@RestController
public class ProducerController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/get")
    public String getPort() {
        return "Producer Server port: " + port;
    }
}
```

## 搭建服务消费者
### 1. 编写pom.xml文件
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.zkane</groupId>
    <artifactId>consumer-server</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <name>consumer-server</name>
    <description>Spring Cloud Consumer Server</description>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent
        </artifactId>
        <version>2.0.1.RELEASE</version>
        <relativePath/>
    </parent>

    <properties>
        <project.build.sourceencoding>UTF-8</project.build.sourceencoding>
        <project.reporting.outputencoding>UTF-8</project.reporting.outputencoding>
        <java.version>1.8</java.version>
        <spring-cloud.version>Finchley.RC1</spring-cloud.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies
                </artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-openfeign</artifactId>
                <version>2.0.0.RC1</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

    <repositories>
        <repository>
            <id>spring-milestones</id>
            <name>Spring Milestones</name>
            <url>https://repo.spring.io/libs-milestone</url>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </repository>
    </repositories>

</project>
```
### 2. 编写application.yml配置文件
```
server:
  port: 8200
spring:
  application:
    name: consumer-server
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8000/eureka/
feign:
  hystrix:
    enabled: true
```
### 3. 在启动类上添加注解
- @EnableFeignClients注解是使用feign来调用服务提供者的API接口
```
package com.zkane;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class ConsumerServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerServerApplication.class, args);
	}
}
```
### 4. 使用feign调用服务提供者的API接口
```
package com.zkane.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: zhaoqiang
 * @date: 2018/5/14
 */
@Component
@FeignClient(value = "producer-server", fallback = ProducerRemoteHystrix.class)
public interface ProducerRemote {
    @GetMapping("/get")
    String getPort();
}
```
### 5. 使用hystrix实现服务熔断机制
- 需要在application.yml中配置feign启用hystrix
```
feign:
  hystrix:
    enabled: true
```
- 在@FeignClient注解上添加fallback属性，指定熔断后调用的本地方法
```
@FeignClient(value = "producer-server", fallback = ProducerRemoteHystrix.class)
```
```
package com.zkane.remote;

import org.springframework.stereotype.Component;

/**
 * @author: zhaoqiang
 * @date: 2018/5/14
 */
@Component
public class ProducerRemoteHystrix implements ProducerRemote {
    @Override
    public String getPort() {
        return "Producer Server 的服务调用失败";
    }
}
```