package com.excel.work;

import lombok.Data;

import java.util.List;

@Data
public class ClueRequest {

    private int fromWolong;
    private String sceneId;
    private Long poolId;
    private Long taskPackageId;
    private List<WdClueData> userList;
}
