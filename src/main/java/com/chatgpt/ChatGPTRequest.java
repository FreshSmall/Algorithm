package com.chatgpt;

import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
//import com.jiaoyan.common.core.util.SnowFlakeUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Data
public class ChatGPTRequest {
    private List<Input> input;
    private String model_type = "chat";
    private String request_id = "j";
    private String caller = "";
    private String key = "sk-9f79766b955742159e55b2e241cecaa1";
    private ApiArgs api_args = new ApiArgs();
    private Integer max_length = 131072;
    private Integer n_try = 5;
    private Integer retry_sleep = 1;
    private String prefer = "openai";
    private Integer timeout = 1000;

    public ChatGPTRequest(String content){
        Input input = new Input();
        input.setContent(content);
        this.input = Arrays.asList(input);
    }

    public ChatGPTRequest(List<Input> inputList){
        this.input = inputList;
    }

    public ChatGPTRequest(String sessionId, ChatGPTRequest chatGPTRequest, List<Input> inputList){
        BeanUtils.copyProperties(chatGPTRequest, this);
        if (!StringUtils.isBlank(sessionId)) {
            request_id = sessionId;
        }
        this.input = inputList;
    }

    public void addInput(String content){
        Input input = new Input();
        input.setContent(content);
        if (this.input == null){
            this.input = Arrays.asList(input);
            return;
        }
        this.input.add(input);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input{
        private String role = "user";
        private String content;
    }

    @Data
    private static class ApiArgs{
        private Double temperature = 0.0;
        private Integer max_tokens = 2000;
        private String model = "gpt-4-turbo";
    }



    public static List<String> doSendPromte(String promteTepm, List<String> input) {
        List<String> promtes = getPromtes(promteTepm, input);
        // promtes的字符串是否超过8192个字符
        if (promtes.stream().mapToInt(String::length).anyMatch(s -> s > 201047)) {
            throw new RuntimeException("请求字符超过8192个字符,最大字符size:" + promtes.stream().mapToInt(String::length).max().getAsInt());
        }
        return doSend(promtes);
    }

    public static List<String> doSendPromte(String promteTepm, List<String> input,List<String> extraInput) {
        List<String> promtes = getPromtes(promteTepm, input, extraInput);
        int maxLength = promtes.stream().mapToInt(String::length).max().getAsInt();
        System.out.println("最大字符size:" + maxLength);
        // promtes的字符串是否超过8192个字符
        if (promtes.stream().mapToInt(String::length).anyMatch(s -> s > 201047)) {
            throw new RuntimeException("请求字符超过8192个字符,最大字符size:" + maxLength);
        }
        return doSend(promtes);
    }

    private static List<String> getPromtes(String promteTepm, List<String> input, List<String> extraInput) {
        List<String> promtes = Lists.newArrayList();
        for (int i = 0; i < input.size(); i++) {
            promtes.add(promteTepm.replace("{输入语句}", input.get(i)).replace("{额外输入}", extraInput.get(i)));
        }
        return promtes;
    }


    private static List<String> doSend(List<String> promtes) {
        // 总共请求的数量
        System.out.println("请求数量：" + promtes.size());

        // 并发请求
        // 创建一个自定义的线程池
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        ExecutorService threadPool = new ThreadPoolExecutor(
                25,
                35,
                1000,
                TimeUnit.MINUTES,

                new ArrayBlockingQueue<>(500),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );


        List<CompletableFuture<String>> completableFutures = promtes.stream()
                .map(po -> CompletableFuture.supplyAsync(() -> {
                    List<Input> inputList = Arrays.asList(new Input("user", po));

                    ChatGPTRequest chatGPTRequest = new ChatGPTRequest(inputList);
                    chatGPTRequest.setRequest_id(UUID.randomUUID().toString());

                    String paramJson = new Gson().toJson(chatGPTRequest);
                    Map<String, String> headers = new HashMap<>();
                    String resultJson = doPostJson("https://test-aiops-api.baijia.com/openai/v1/azure", paramJson, headers);
                    // String resultJson = doPostJson("http://test-aiproxy.baijia.com/azure", paramJson, headers);
                    JSONObject jsonObject = null;
                    try{
                        jsonObject = JSONObject.parseObject(resultJson);
                    } catch (Exception e) {
                        System.out.println("请求失败：" + resultJson);
                        System.out.println("请求失败：" + chatGPTRequest.getRequest_id());
                        return "\n";
                    }

                    Integer code = jsonObject.getInteger("code");
                    if (code != 200) {
                        System.out.println("请求失败：" + resultJson);
                        System.out.println("请求失败：" + chatGPTRequest.getRequest_id());
                        return "\n";
                    } else {
                        System.out.println("请求成功");
                    }
                    return jsonObject.getString("result");
                }, threadPool)) // 使用自定义线程池
                .collect(Collectors.toList());

        // 等待所有请求完成
        CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0])).join();

        // 关闭线程池
        threadPool.shutdown();

        // 获取所有请求结果
        return completableFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    @NotNull
    private static List<String> getPromtes(String promteTepm, List<String> input) {
        List<String> promtes = Lists.newArrayList();
        for (String s : input) {
            promtes.add(promteTepm.replace("{输入语句}", s));
        }
        return promtes;
    }

    public static String doPostJson(String url, String json, Map<String, String> headers) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);

            //塞入header
            if(headers != null){
                for (String key : headers.keySet()) {
                    httpPost.setHeader(key,headers.get(key));
                }
            }

            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return resultString;
    }


    public static void main(String[] args) {
        final String prompt = "你好，我是一个智能机器人，我可以回答你的问题。请问你有什么问题需要我回答吗？";
        doSendPromte(prompt, Arrays.asList("你好", "你是谁"));
    }
}
