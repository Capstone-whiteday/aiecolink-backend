package com.whiteday.aiecolink.domain.scheduling.repository;

import com.whiteday.aiecolink.domain.scheduling.model.entity.TouHourly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TouHourlyRepository extends JpaRepository<TouHourly, Long> {
    // JpaRepository를 상속받아 기본적인 CRUD 메서드를 사용할 수 있습니다.
    // 추가적인 쿼리 메서드가 필요하다면 여기에 정의할 수 있습니다.
}
