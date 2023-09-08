package com.example.automarket.seed;

import com.example.automarket.domain.json.BrandModelJson;
import com.example.automarket.domain.model.VehicleBrand;
import com.example.automarket.domain.model.VehicleModel;
import com.example.automarket.repository.VehicleBrandRepository;
import com.example.automarket.repository.VehicleModelRepository;
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
@Order(1)
public class VehicleDataSeeder implements CommandLineRunner {

	private final VehicleBrandRepository brandRepository;

	private final VehicleModelRepository modelRepository;

	private final ObjectMapper objectMapper;

	private final ResourceLoader resourceLoader;

	@Override
	public void run(String... args) throws Exception {
		log.info("Seeding vehicle data");
		List<BrandModelJson> brandModelList = readJsonDataFromFile();

		for (BrandModelJson brandModelJson : brandModelList) {
			VehicleBrand brand = new VehicleBrand();

			brand.setName(brandModelJson.getBrand());
			brandRepository.save(brand);

			for (String model : brandModelJson.getModels()) {
				VehicleModel vehicleModel = new VehicleModel();
				vehicleModel.setName(model);
				vehicleModel.setBrand(brand);
				modelRepository.save(vehicleModel);
			}
		}
	}

	public Resource loadResource(String location) {
		return resourceLoader.getResource(location);
	}

	private List<BrandModelJson> readJsonDataFromFile() throws IOException {

		Resource resource = loadResource("classpath:data/brand_model.json");

		File file = resource.getFile();

		return objectMapper.readValue(file, new TypeReference<>() {
		});
	}

}