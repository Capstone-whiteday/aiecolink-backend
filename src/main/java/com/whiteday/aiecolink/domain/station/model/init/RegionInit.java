package com.whiteday.aiecolink.domain.station.model.init;

import com.whiteday.aiecolink.domain.station.model.entity.Region;
import com.whiteday.aiecolink.domain.station.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegionInit implements CommandLineRunner {

    final RegionRepository regionRepository;

    @Override
    public void run(String... args) {
        if (regionRepository.count() == 0) {
            regionRepository.save(new Region(11L, "서울특별시"));
            regionRepository.save(new Region(21L, "부산광역시"));
            regionRepository.save(new Region(22L, "대구광역시"));
            regionRepository.save(new Region(23L, "인천광역시"));
            regionRepository.save(new Region(24L, "광주광역시"));
            regionRepository.save(new Region(25L, "대전광역시"));
            regionRepository.save(new Region(26L, "울산광역시"));
            regionRepository.save(new Region(29L, "세종특별자치시"));
            regionRepository.save(new Region(31L, "경기도"));
            regionRepository.save(new Region(32L, "강원도"));
            regionRepository.save(new Region(33L, "충청북도"));
            regionRepository.save(new Region(34L, "충청남도"));
            regionRepository.save(new Region(35L, "전라북도"));
            regionRepository.save(new Region(36L, "전라남도"));
            regionRepository.save(new Region(37L, "경상북도"));
            regionRepository.save(new Region(38L, "경상남도"));
            regionRepository.save(new Region(39L, "제주특별자치도"));
        }
    }
}
