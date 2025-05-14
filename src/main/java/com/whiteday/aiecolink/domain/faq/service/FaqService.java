package com.whiteday.aiecolink.domain.faq.service;

import com.whiteday.aiecolink.domain.faq.model.Faq;
import com.whiteday.aiecolink.domain.faq.repository.FaqRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqRepository faqRepository;

    public List<Faq> getAllFaqs() {
        return faqRepository.findAll();
    }

    public Faq getFaqById(Long id) {
        return faqRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 FAQ가 존재하지 않습니다. id=" + id));
    }
}
