package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDTO {
    private String title;
    private Map<String, Object> stats;
    private Map<String, Object> charts;
    private Map<String, Object> recentActivities;
}