package com.whiteday.aiecolink.global.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whiteday.aiecolink.domain.scheduling.model.request.LstmInput;
import com.whiteday.aiecolink.domain.scheduling.model.request.PpoInput;
import com.whiteday.aiecolink.domain.scheduling.model.request.PredictionReq;
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

import java.util.List;
@Slf4j
@Component
@RequiredArgsConstructor
public class AiModelClient {

    private final RestTemplate restTemplate;

    @Value("${ai.endpoint.predict}")
    private String predictUrl;

    public List<SchedulePredictionItem> requestPrediction(float batteryCapacity,List<LstmInput> lstmInputs, List<PpoInput> ppoInputs) throws JsonProcessingException {
        PredictionReq request = PredictionReq.builder()
                .batteryCapacity(batteryCapacity)
                .lstm_input(lstmInputs)
                .ppo_input(ppoInputs)
                .build();

        if (lstmInputs.size() != 24 || ppoInputs.size() != 24) {
            log.error("❌ AI 입력 길이 오류: lstm={}, ppo={}", lstmInputs.size(), ppoInputs.size());
            throw new CustomException(ErrorCode.INVALID_AI_INPUT);
        }

        ObjectMapper mapper = new ObjectMapper();
        log.debug("🔎 AI 요청 JSON:\n{}", mapper.writeValueAsString(request));


        try {

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
