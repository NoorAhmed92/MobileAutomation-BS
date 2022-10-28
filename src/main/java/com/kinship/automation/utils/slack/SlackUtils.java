package com.kinship.automation.utils.slack;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kinship.automation.constants.Constants;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class SlackUtils {

    private static void buildMessage(SlackMessage message) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(Constants.slackWebhookUrl);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(message);
            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            client.execute(httpPost);
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessage(String username, String message){
    	SlackMessage slackMessage = new SlackMessage();
    	slackMessage.setUsername(username);
    	slackMessage.setText(message);
        SlackUtils.buildMessage(slackMessage);
    }
}
