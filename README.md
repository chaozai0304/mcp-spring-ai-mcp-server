# mcp server demo
## 如何通信
```
1.http://localhost:8090/sse
输出：
event:endpoint
data:/mcp/message?sessionId=8a57c258-068f-4c52-b08d-d69ac85c583e
此时内页会进入等待通道已经建立
2.获取tools列表【路径是上面返回data】
curl --location --request POST 'http://localhost:8090/mcp/message?sessionId=8a57c258-068f-4c52-b08d-d69ac85c583e' \
--header 'Content-Type: application/json' \
--data-raw '{
    "jsonrpc": "2.0",
    "method": "notifications/initialized"
}'
或
curl --location --request POST 'http://localhost:8090/mcp/message?sessionId=8a57c258-068f-4c52-b08d-d69ac85c583e' \
--header 'Content-Type: application/json' \
--data-raw '{
    "jsonrpc": "2.0",
    "method": "tools/list",
    "id": "e60983d1-1",
    "params": {}
}'
3.sse页面返回内容
event:endpoint
data:/mcp/message?sessionId=8a57c258-068f-4c52-b08d-d69ac85c583e

event:message
data:{"jsonrpc":"2.0","id":"e60983d1-1","result":{"tools":[{"name":"add","description":"加法","inputSchema":{"type":"object","properties":{"a":{"type":"integer","format":"int32","description":"第一个参数"},"b":{"type":"integer","format":"int32","description":"第二个参数"}},"required":["a","b"],"additionalProperties":false}},{"name":"owner_getByUserName","description":"根据名称返回年龄","inputSchema":{"type":"object","properties":{"name":{"type":"string","description":"名称"}},"required":["name"],"additionalProperties":false}}]}}
```
## 重点pom
`````
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.4.4</version>
		<relativePath/>
	</parent>
	<groupId>com.aitools</groupId>
	<artifactId>mcp</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>mcp</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>17</java.version>
		<maven.compiler.source>17</maven.compiler.source>
		<maven.compiler.target>17</maven.compiler.target>
		<spring-ai.version>1.0.0-M7</spring-ai.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.ai</groupId>
			<artifactId>spring-ai-starter-mcp-server-webflux</artifactId>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
		</dependency>

	</dependencies>
	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.ai</groupId>
				<artifactId>spring-ai-bom</artifactId>
				<version>${spring-ai.version}</version>
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
			<name>Central Portal Snapshots</name>
			<id>central-portal-snapshots</id>
			<url>https://central.sonatype.com/repository/maven-snapshots/</url>
			<releases>
				<enabled>false</enabled>
			</releases>
			<snapshots>
				<enabled>true</enabled>
			</snapshots>
		</repository>
		<repository>
			<id>spring-milestones</id>
			<name>Spring Milestones</name>
			<url>https://repo.spring.io/libs-milestone-local</url>
			<snapshots>
				<enabled>false</enabled>
			</snapshots>
		</repository>
		<repository>
			<id>spring-snapshots</id>
			<name>Spring Snapshots</name>
			<url>https://repo.spring.io/snapshot</url>
			<releases>
				<enabled>false</enabled>
			</releases>
		</repository>
	</repositories>
</project>
`````
## yml参数说明
## 配置属性

所有属性都以 为前缀`spring.ai.mcp.server`：

| 参数                           | 描述                                                         | 默认           |
| :----------------------------- | :----------------------------------------------------------- | :------------- |
| `enabled`                      | 启用/禁用 MCP 服务器                                         | `true`         |
| `stdio`                        | 启用/禁用 stdio 传输                                         | `false`        |
| `name`                         | 用于识别的服务器名称                                         | `mcp-server`   |
| `version`                      | 服务器版本                                                   | `1.0.0`        |
| `type`                         | 服务器类型（同步/异步）                                      | `SYNC`         |
| `resource-change-notification` | 启用资源更改通知                                             | `true`         |
| `prompt-change-notification`   | 启用提示更改通知                                             | `true`         |
| `tool-change-notification`     | 启用工具更改通知                                             | `true`         |
| `tool-response-mime-type`      | （可选）每个工具名称的响应 MIME 类型。例如，将MIME 类型与工具名称`spring.ai.mcp.server.tool-response-mime-type.generateImage=image/png`关联`image/png``generateImage()` | `-`            |
| `sse-message-endpoint`         | 客户端用来发送消息的 Web 传输的自定义 SSE 消息端点路径       | `/mcp/message` |
| `sse-endpoint`                 | 用于 Web 传输的自定义 SSE 端点路径                           | `/sse`         |
| `base-url`                     | 可选 URL 前缀。例如，表示客户端应访问位于+ 的`base-url=/api/v1`SSE 端点，而消息端点位于+`/api/v1``sse-endpoint``/api/v1``sse-message-endpoint` | -              |
## 官方地址
```
https://docs.spring.io/spring-ai/reference/api/mcp/mcp-server-boot-starter-docs.html#_webflux_server_transport

```
 
