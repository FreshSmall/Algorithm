package com.excel.work;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.compress.utils.Lists;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ClueDataListener extends AnalysisEventListener<WdClueData> {

    private Long clueId;
    private String sceneId;
    private Long taskPackageId;
    private static OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS).build();
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final Gson gson = new Gson();
    private static final String url = "http://api-tmk-crm.baijia.com/external/saveUsers";

    public ClueDataListener(String sceneId, Long clueId, Long taskPackageId) {
        this.clueId = clueId;
        this.sceneId = sceneId;
        this.taskPackageId = taskPackageId;
    }

    private final List<WdClueData> wdClueDataList = Lists.newArrayList();
    private static final int COUNT = 6000;
    private static int NUMBER = 0;

    @SneakyThrows
    @Override
    public void invoke(WdClueData wdClueData, AnalysisContext analysisContext) {
        if (wdClueDataList.size() >= COUNT) {
            sendHttpRequest(wdClueDataList);
            NUMBER += wdClueDataList.size();
            wdClueDataList.clear();
        }
        wdClueDataList.add(wdClueData);

    }

    @SneakyThrows
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        sendHttpRequest(wdClueDataList);
        NUMBER += wdClueDataList.size();
        System.out.println("sheet数据量:" + NUMBER);
    }

    private String buildRequest(List<WdClueData> wdClueDataList) {
        ClueRequest request = new ClueRequest();
        request.setSceneId(sceneId);
        request.setPoolId(clueId);
        request.setTaskPackageId(taskPackageId);
        request.setUserList(wdClueDataList);
        return gson.toJson(request);
    }

    private String buildRequest_ratio(List<WdClueData> wdClueDataList, String sceneId, Long clueId, Long taskPackageId) {
        ClueRequest request = new ClueRequest();
        request.setSceneId(sceneId);
        request.setPoolId(clueId);
        request.setTaskPackageId(taskPackageId);
        request.setUserList(wdClueDataList);
        return gson.toJson(request);
    }

    private void sendHttpRequest(List<WdClueData> wdClueDataList) throws IOException {
        int count = wdClueDataList.size();
        int begin = (int) (0.5 * count);
        List<WdClueData> list1 = wdClueDataList.subList(0, begin);
        List<WdClueData> list2 = wdClueDataList.subList(begin, count);
        String param1 = buildRequest_ratio(list1, "3aaa", 147L, 233L);
        System.out.println("param1:" + param1);
        String param2 = buildRequest_ratio(list2, "dd6b", 146L, 232L);
        System.out.println("param2:" + param2);

        sendHttpRequest_core(param1);

        sendHttpRequest_core(param2);
    }

    private void sendHttpRequest_core(String param) throws IOException {
        RequestBody body = RequestBody.create(param, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        }
    }
}
