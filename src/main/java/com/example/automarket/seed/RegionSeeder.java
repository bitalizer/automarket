package com.example.automarket.seed;

import com.example.automarket.domain.model.Region;
import com.example.automarket.repository.RegionRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
@Order(2)
public class RegionSeeder implements CommandLineRunner {


    private final RegionRepository regionRepository;
    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {
        log.info("Seeding regions");

        List<String> regionNames = readJsonDataFromFile();

        for (String regionName : regionNames) {
            Region region = new Region(regionName);
            regionRepository.save(region);
        }
    }

    public Resource loadResource(String location) {
        return resourceLoader.getResource(location);
    }

    private List<String> readJsonDataFromFile() throws IOException {

        Resource resource = loadResource("classpath:data/regions.json");

        File file = resource.getFile();

        return objectMapper.readValue(file, new TypeReference<>() {
        });
    }
}
