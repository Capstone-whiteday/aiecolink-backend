package com.whiteday.aiecolink.global.client;

import com.whiteday.aiecolink.domain.scheduling.model.request.LstmInput;
import com.whiteday.aiecolink.domain.scheduling.model.request.PpoInput;
import com.whiteday.aiecolink.domain.scheduling.model.request.PredictionReq;
import com.whiteday.aiecolink.domain.scheduling.model.request.PredictionRequest;
import com.whiteday.aiecolink.global.error.CustomException;
import com.whiteday.aiecolink.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AiModelClient {

    private final RestTemplate restTemplate;

    @Value("${ai.endpoint.predict}")
    private String predictUrl;

    public List<SchedulePredictionItem> requestPrediction(Long stationId, LocalDate date, List<LstmInput> lstmInputs, List<PpoInput> ppoInputs) {
        PredictionReq request = PredictionReq.builder()
                .lstm_input(lstmInputs)
                .ppo_input(ppoInputs)
                .build();

        try {
            ResponseEntity<SchedulePredictionItem[]> response = restTemplate.postForEntity(
                    predictUrl, request, SchedulePredictionItem[].class
            );
            return Arrays.asList(response.getBody());
        } catch (Exception e) {
            throw new CustomException(ErrorCode.AI_PREDICT_FAIL);
        }
    }
}
