package ks46team02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import ks46team02.farm.dto.GoogleFormResult;

public class TestUrl {

	public static void main(String[] args) throws Exception {
List<GoogleFormResult> googleFormResponseList = new ArrayList<GoogleFormResult>();
        
        googleFormResponseList.add(new GoogleFormResult("TEXT", "Token ID", "1111"));
        googleFormResponseList.add(new GoogleFormResult("TEXT", "visitCode", "visit_4"));
        googleFormResponseList.add(new GoogleFormResult("TEXT", "memberId", "farm_id_1"));
        googleFormResponseList.add(new GoogleFormResult("TEXT", "contractCode", "con_1"));
        googleFormResponseList.add(new GoogleFormResult("SCALE", "Unit1. 시장분석", "3"));
        googleFormResponseList.add(new GoogleFormResult("PARAGRAPH_TEXT", "Unit1", "참 잘했어요~"));
    	
        ObjectMapper objectMapper = new ObjectMapper();

        String json = "{\"form_id\":\"1NvfcN8vMEsFuTrSVxVM8VSAXjOM6a5lN5G9-qAGcxeM\",\"form_title\":\"멘토 멘티 평가\",\"results\":[{\"type\":\"TEXT\",\"title\":\"Token ID\",\"response\":\"1111\"},{\"type\":\"TEXT\",\"title\":\"visitCode\",\"response\":\"visit_4\"},{\"type\":\"TEXT\",\"title\":\"memberId\",\"response\":\"farm_id_1\"},{\"type\":\"TEXT\",\"title\":\"contractCode\",\"response\":\"con_1\"},{\"type\":\"SCALE\",\"title\":\"Unit1. 시장분석\",\"response\":\"1\"},{\"type\":\"PARAGRAPH_TEXT\",\"title\":\"Unit1\",\"response\":\"참 잘했어요~\"},{\"type\":\"PARAGRAPH_TEXT\",\"title\":\"Unit2\",\"response\":\"\"},{\"type\":\"PARAGRAPH_TEXT\",\"title\":\"Unit3\",\"response\":\"\"},{\"type\":\"PARAGRAPH_TEXT\",\"title\":\"Unit4\",\"response\":\"\"},{\"type\":\"PARAGRAPH_TEXT\",\"title\":\"Unit5\",\"response\":\"\"},{\"type\":\"PARAGRAPH_TEXT\",\"title\":\"Unit6\",\"response\":\"\"},{\"type\":\"PARAGRAPH_TEXT\",\"title\":\"Unit7\",\"response\":\"\"},{\"type\":\"PARAGRAPH_TEXT\",\"title\":\"Unit8\",\"response\":\"\"},{\"type\":\"PARAGRAPH_TEXT\",\"title\":\"Unit9\",\"response\":\"\"},{\"type\":\"PARAGRAPH_TEXT\",\"title\":\"Unit10\",\"response\":\"\"},{\"type\":\"PARAGRAPH_TEXT\",\"title\":\"Unit11\",\"response\":\"\"},{\"type\":\"PARAGRAPH_TEXT\",\"title\":\"Unit12\",\"response\":\"\"},{\"type\":\"PARAGRAPH_TEXT\",\"title\":\"Unit13\",\"response\":\"\"},{\"type\":\"PARAGRAPH_TEXT\",\"title\":\"Unit14\",\"response\":\"\"},{\"type\":\"PARAGRAPH_TEXT\",\"title\":\"Unit15\",\"response\":\"\"},{\"type\":\"PARAGRAPH_TEXT\",\"title\":\"Unit16\",\"response\":\"\"},{\"type\":\"PARAGRAPH_TEXT\",\"title\":\"Unit17\",\"response\":\"\"},{\"type\":\"PARAGRAPH_TEXT\",\"title\":\"Unit18\",\"response\":\"\"},{\"type\":\"PARAGRAPH_TEXT\",\"title\":\"Unit19\",\"response\":\"\"},{\"type\":\"PARAGRAPH_TEXT\",\"title\":\"Unit20\",\"response\":\"\"},{\"type\":\"PARAGRAPH_TEXT\",\"title\":\"Unit21\",\"response\":\"\"},{\"type\":\"PARAGRAPH_TEXT\",\"title\":\"Unit22\",\"response\":\"\"},{\"type\":\"PARAGRAPH_TEXT\",\"title\":\"Unit23\",\"response\":\"\"},{\"type\":\"PARAGRAPH_TEXT\",\"title\":\"Unit24\",\"response\":\"\"},{\"type\":\"PARAGRAPH_TEXT\",\"title\":\"Unit25\",\"response\":\"\"},{\"type\":\"PARAGRAPH_TEXT\",\"title\":\"Unit26\",\"response\":\"\"}]}";

        System.out.println(json);
        
        URL url = new URL("http://localhost:8088/farm/receiveFormData");
        
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setRequestMethod("POST");
        httpCon.setRequestProperty("Content-Type", "application/json");
        httpCon.setRequestProperty("Accept", "application/json");
        httpCon.setDoOutput(true);
        try(OutputStream os = httpCon.getOutputStream()) {
        	byte[] input = json.getBytes("utf-8");
        	os.write(input, 0, input.length);
        }
        
        try(BufferedReader br = new BufferedReader(
        		  new InputStreamReader(httpCon.getInputStream(), "utf-8"))) {
        		    StringBuilder response = new StringBuilder();
        		    String responseLine = null;
        		    while ((responseLine = br.readLine()) != null) {
        		        response.append(responseLine.trim());
        		    }
        		    System.out.println(response.toString());
        		}
    }
}
