package com.aitools.mcp.service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

@Service
public class OneTools {
    @Tool(description = "减法")
    public Integer sub(@ToolParam(description = "第一个参数") int first,
                       @ToolParam(description = "第二个参数") int second) {

        return first + second;
    }
    @Tool(description = "加法")
    public Integer add(@ToolParam(description = "第一个参数") int first,
                       @ToolParam(description = "第二个参数") int second) {

        return first + second;
    }
}
