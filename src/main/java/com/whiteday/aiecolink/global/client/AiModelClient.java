package com.whiteday.aiecolink.global.client;

import com.whiteday.aiecolink.domain.scheduling.model.request.LstmInput;
import com.whiteday.aiecolink.domain.scheduling.model.request.PpoInput;
import com.whiteday.aiecolink.domain.scheduling.model.request.PredictionReq;
import com.whiteday.aiecolink.domain.scheduling.model.request.PredictionRequest;
import com.whiteday.aiecolink.global.error.CustomException;
import com.whiteday.aiecolink.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
@Slf4j
@Component
@RequiredArgsConstructor
public class AiModelClient {

    private final RestTemplate restTemplate;

    @Value("${ai.endpoint.predict}")
    private String predictUrl;

    public List<SchedulePredictionItem> requestPrediction(List<LstmInput> lstmInputs, List<PpoInput> ppoInputs) {
        PredictionReq request = PredictionReq.builder()
                .lstm_input(lstmInputs)
                .ppo_input(ppoInputs)
                .build();

        try {
            // Example: Sending HTTP request to AI model
            ResponseEntity<SchedulePredictionRes> response = restTemplate.postForEntity(
                    predictUrl, request, SchedulePredictionRes.class
            );
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody().getResult();
            } else {
                throw new CustomException(ErrorCode.AI_PREDICT_FAIL,
                        "AI 모델 서비스에서 실패 응답을 받았습니다. " + response.getStatusCode());
            }
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("AI 모델 요청 실패: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
            throw new CustomException(ErrorCode.AI_PREDICT_FAIL,
                    "AI 모델 요청 실패: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
        } catch (ResourceAccessException e) {
            log.error("AI 모델 요청 시간 초과: {}", e.getMessage(), e);
            throw new CustomException(ErrorCode.AI_PREDICT_FAIL,
                    "AI 모델 요청 시간 초과: " + e.getMessage());
        } catch (Exception e) {
            log.error("AI 모델 요청 중 알 수 없는 오류 발생: {}", e.getMessage(), e);
            throw new CustomException();
        }
    }
}
