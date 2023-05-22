package ks46team02.admin.service;

import jakarta.servlet.http.HttpSession;
import ks46team02.admin.controller.AdminController;
import org.json.JSONObject;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class ApiExamRomanService {

    private static final String CLIENT_ID = "AxY7WiYuNxH4nSC9dDyP";
    private static final String CLIENT_SECRET = "LgTf3I2eeL";

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    public String getRomanization(HttpSession session) {
        String encodedName = (String) session.getAttribute("sessionName");

        String apiUrl = "https://openapi.naver.com/v1/krdict/romanization?query=" + encodedName;

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", CLIENT_ID);
        headers.set("X-Naver-Client-Secret", CLIENT_SECRET);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String result = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class).getBody();


        JSONObject jsonObject = new JSONObject(result);
        JSONArray aResultArray = jsonObject.getJSONArray("aResult");
        JSONObject aResultObject = aResultArray.getJSONObject(0);
        JSONArray aItemsArray = aResultObject.getJSONArray("aItems");

        String topName = "";
        int topScore = Integer.MIN_VALUE;

        for (int i = 0; i < aItemsArray.length(); i++) {
            JSONObject item = aItemsArray.getJSONObject(i);
            String name = item.getString("name");
            int score = item.getInt("score");
            if (score > topScore) {
                topScore = score;
                topName = name;
            }
        }

        return topName;
    }
}