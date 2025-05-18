package com.whiteday.aiecolink.domain.scheduling.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PredictionReq {
    private List<LstmInput> lstm_input;
    private List<PpoInput> ppo_input;
}
