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
                System.out.println(responseBody);
                return responseBody; // 응답 본문을 반환합니다.
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 예외 처리 로직 작성
        }

        return null;
    }
}

