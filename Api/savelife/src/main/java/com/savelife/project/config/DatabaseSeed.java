package com.savelife.project.config;

import java.util.Arrays;
import java.util.List;

import com.savelife.project.dto.hospital.RegistryHospital;
import com.savelife.project.entities.*;
import com.savelife.project.services.AmbulanceService;
import com.savelife.project.services.HospitalService;
import com.savelife.project.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DatabaseSeed implements CommandLineRunner {

    private final UserService userService;
    private final HospitalService hospitalService;
    private final AmbulanceService ambulanceService;

    public DatabaseSeed(UserService userService, HospitalService hospitalService, AmbulanceService ambulanceService) {
        this.userService = userService;
        this.hospitalService = hospitalService;
        this.ambulanceService = ambulanceService;
    }

    @Override
    public void run(String... args) throws Exception {


        userService.saveUser(
            new UserModel(
                    null,
                    "admin",
                    20,
                    "11960719823",
                    "admin@savelife.com.br",
                    "administrador",
                    null,
                    null,
                    "890000085774482",
                    "09087245800",
                    Arrays.asList(new Role(Roles.ROLE_ADMIN))
            )
        );

        userService.saveUser(
                new UserModel(
                        null,
                        "usuário",
                        20,
                        "11964563987",
                        "usuario@savelife.com.br",
                        "user",
                        null,
                        "SulAmérica",
                        "890000085784482",
                        "09087245900",
                        Arrays.asList(new Role(Roles.ROLE_USER))
                )
        );

        userService.saveUser(
                new UserModel(
                        null,
                        "Albert",
                        20,
                        "11960719423",
                        "albert@savelife.com.br",
                        "albert",
                        null,
                        null,
                        "890000085974482",
                        "09097245800",
                        Arrays.asList(new Role(Roles.ROLE_HOSPITAL))
                )
        );

        userService.saveUser(
                new UserModel(
                        null,
                        "jose",
                        20,
                        "11960719820",
                        "jose@savelife.com.br",
                        "jose",
                        null,
                        "SulAmérica",
                        "890005085774482",
                        "09087245700",
                        Arrays.asList(new Role(Roles.ROLE_AMBULANCE))
                )
        );

        ambulanceService.saveAmbulance(
                new Ambulance(
                        null,
                        "AYF3310",
                        false,
                        Arrays.asList(new Phone(null, "1140028922", null, null)),
                        null,
                        null
                )
        );

        ambulanceService.saveAmbulance(
                new Ambulance(
                        null,
                        "AEJ4433",
                        false,
                        Arrays.asList(new Phone(null, "11960719820", null, null)),
                        null,
                        null
                )
        );

        ambulanceService.saveAmbulance(
                new Ambulance(
                        null,
                        "HJE4455",
                        false,
                        Arrays.asList(new Phone(null, "1140028922", null, null)),
                        null,
                        null
                )
        );

        hospitalService.saveHospital(
            new RegistryHospital(
                    "Hospital Israelita Albert Einstein",
                    new Address("Av. Albert Einstein 627/701","Morumbi", "05652-900", "São Paulo", "São Paulo"),
                    Arrays.asList(new Phone(null, "1140028922", null, null)),
                    Arrays.asList(1L),
                    Arrays.asList("albert@savelife.com.br")
            )
        );

        hospitalService.saveHospital(
                new RegistryHospital(
                        "Hospital Samaritano",
                        new Address("R. Conselheiro Brotero 1486","Higienópolis", "01232-010", "São Paulo", "São Paulo"),
                        Arrays.asList(new Phone(null, "1140028633", null, null)),
                        Arrays.asList(2L, 3L),
                        List.of()
                )
        );


    }
    
}
