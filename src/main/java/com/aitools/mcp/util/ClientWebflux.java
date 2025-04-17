package com.aitools.mcp.util;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.transport.WebFluxSseClientTransport;
import io.modelcontextprotocol.spec.McpSchema;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

public class ClientWebflux {
    public static void main(String[] args) {

        var transport = new WebFluxSseClientTransport(WebClient.builder().baseUrl("http://localhost:8090"));
        try (var client = McpClient.sync(transport).build()) {
            client.initialize();
            McpSchema.ListToolsResult toolsList = client.listTools();
            System.out.println("Tools = " + toolsList);

            McpSchema.CallToolResult sumResult = client.callTool(new McpSchema.CallToolRequest("sub",
                    Map.of("first", 10, "second", 2)));
            System.out.println("first - second=  " + sumResult.content().get(0));
        }
    }
}

