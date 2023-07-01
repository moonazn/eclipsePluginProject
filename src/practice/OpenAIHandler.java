package practice;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



public class OpenAIHandler extends AbstractHandler {
	

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {

        return null;
    }
    
    public String callOpenAPI(String selectedText) throws ExecutionException {
        // OpenAI API 인증 정보
        String apiKey = "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";

        // 요청할 엔드포인트 URL
        String apiUrl = "https://api.openai.com/v1/chat/completions";

        // 요청 본문 데이터 설정
        String requestBody = "{\"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"user\", \"content\": \"" + selectedText + "\"}], \"max_tokens\": 100}";

        // HttpClient 생성
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // HTTP POST 요청 생성
            HttpPost request = new HttpPost(apiUrl);

            // HTTP 헤더 설정
            request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey);
            request.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());

            // 요청 본문 설정
            StringEntity entity = new StringEntity(requestBody, ContentType.APPLICATION_JSON);
            request.setEntity(entity);

            // API 호출 및 응답 처리
            HttpResponse response = httpClient.execute(request);
            HttpEntity responseEntity = response.getEntity();
            
            // 응답 본문 출력
            if (responseEntity != null) {
                String responseBody = EntityUtils.toString(responseEntity);

                // JSON 파싱
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(responseBody);

                // 특정 데이터 추출 (예시: 첫 번째 메시지의 내용)
                JsonNode messageNode = jsonNode.get("choices").get(0).get("message");
                String content = messageNode.get("content").asText();

                
                //System.out.println(responseBody);
                return content; // 응답 본문을 반환합니다.
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 예외 처리 로직 작성
        }

        return null;
    }
}

