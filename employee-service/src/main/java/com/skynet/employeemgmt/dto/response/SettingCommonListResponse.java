package com.skynet.employeemgmt.dto.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SettingCommonListResponse {
    private MainTableConfigs mainTableConfigs;
    private SubTableConfigs subTableConfigs;
    private Object records;

    @Data
    public static class MainTableConfigs {
        private Title titles;
        private String[] sortableColumns;
        private String[] filterableColumns;
        private String[] booleanColumns;
        private  String[] dateColumns;
    }

    @Data
    public static class SubTableConfigs {
        private SubTitle titles;
        private String[] sortableColumns;
        private String[] filterableColumns;
        private String[] booleanColumns;
        private  String[] dateColumns;
    }

    @Data
    public static class Title {
        private String roleName;
        private String description;
        private String status;
        private String createdAt;
        private String createdBy;
        private String updatedAt;
        private String updatedBy;
    }

    @Data
    public static class SubTitle {
        private String moduleName;
        private String read;
        private String create;
        private String update;
        private String delete;
    }
}


