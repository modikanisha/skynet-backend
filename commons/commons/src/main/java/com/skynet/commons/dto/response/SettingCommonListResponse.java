package com.skynet.commons.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

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
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String name;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String emailId;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String contactNumber;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String address;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String description;
        @JsonInclude(JsonInclude.Include.NON_NULL)
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


