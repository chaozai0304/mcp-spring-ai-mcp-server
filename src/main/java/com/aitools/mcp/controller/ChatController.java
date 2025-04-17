package com.aitools.mcp.controller;

import jakarta.annotation.Resource;

import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/chat")
public class ChatController {


    @Autowired
    @Qualifier("taskTools")
    private ToolCallbackProvider toolCallbackProvider;


    @GetMapping("/call")
    public String callByName(@RequestParam String name) {
        return toolCallbackProvider.getToolCallbacks().toString();
    }
}