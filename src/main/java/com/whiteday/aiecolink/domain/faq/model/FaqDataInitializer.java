package com.whiteday.aiecolink.domain.faq.model;

import com.whiteday.aiecolink.domain.faq.repository.FaqRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FaqDataInitializer implements CommandLineRunner {

    private final FaqRepository faqRepository;

    @Override
    public void run(String... args) {
        if (faqRepository.count() == 0) {
            faqRepository.saveAll(List.of(
                    Faq.builder()
                            .question("회원가입은 어떻게 하나요?")
                            .answer("회원가입은 이메일 인증 후 가능합니다.")
                            .createdAt(LocalDateTime.now())
                            .build(),

                    Faq.builder()
                            .question("비밀번호를 잊어버렸어요.")
                            .answer("비밀번호 찾기 기능을 이용해 주세요.")
                            .createdAt(LocalDateTime.now())
                            .build()
            ));
        }
    }
}
