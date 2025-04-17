package com.aitools.mcp.config;

import com.aitools.mcp.service.TaskMcpCallServerServices;
import com.aitools.mcp.service.OneTools;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.ToolCallbacks;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatClientConfig {

    @Bean
    public ToolCallbackProvider taskTools(TaskMcpCallServerServices taskMcpCallServerServices) {
        return MethodToolCallbackProvider.builder().toolObjects(taskMcpCallServerServices).build();
    }
    @Bean
    public List<ToolCallback> tools(OneTools tools) {
        return List.of(ToolCallbacks.from(tools));
    }

}