package com.savelife.ambulance.client;

import com.savelife.ambulance.dto.hospital.HospitalDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "hospital", url = "http://localhost:8081/v1")
public interface HospitalFeing {

    @GetMapping(value = "/hospital/{id}")
    HospitalDTO searchHospital(@PathVariable Long id);

}
