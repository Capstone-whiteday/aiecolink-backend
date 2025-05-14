package com.whiteday.aiecolink.domain.faq.repository;

import com.whiteday.aiecolink.domain.faq.model.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqRepository extends JpaRepository<Faq, Long> {
}
