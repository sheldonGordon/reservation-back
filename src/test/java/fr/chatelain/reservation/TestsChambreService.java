package fr.chatelain.reservation;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import fr.chatelain.reservation.model.ChambreService;
import fr.chatelain.reservation.model.FactoryReservation;
import fr.chatelain.reservation.model.dto.ChambreServiceDto;
import fr.chatelain.reservation.service.ChambreServiceService;


@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class TestsChambreService {
	
	@LocalServerPort
    int randomServerPort;

	@Autowired
    private ChambreServiceService chambreServiceService;
	
	@Test
	public void saveChambreService() throws URISyntaxException {

		RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:"+randomServerPort+"/api/chambreServices?libelle=MonService";
        URI uri = new URI(baseUrl);


		HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");      
 
        /*HttpEntity<Employee> request = new HttpEntity<>(employee, headers);*/
         
        ResponseEntity<String> result = restTemplate.postForEntity(uri, null, String.class);
         
        //Verify request succeed
        Assertions.assertEquals(200, result.getStatusCodeValue());
	}

	@Test
	public void deleteChambreService() {
		String libelle="monLibelle";
		ChambreService chambreService = FactoryReservation.getInstanceChambreService(libelle);

        chambreServiceService.save(chambreService);

        ModelMapper modelMapper = new ModelMapper();
        ChambreServiceDto maDto = modelMapper.map(chambreService, ChambreServiceDto.class);

		chambreServiceService.deleteById(maDto.getId());
		
        //Verify request succeed
        Assertions.assertEquals(libelle, maDto.getLibelleService());
	}

	@Test
	public void getChambreService() {
		String libelle="monLibelle";
		ChambreService chambreService = FactoryReservation.getInstanceChambreService(libelle);

        chambreServiceService.save(chambreService);

        ModelMapper modelMapper = new ModelMapper();
        ChambreServiceDto maDto = modelMapper.map(chambreService, ChambreServiceDto.class);

		chambreServiceService.deleteById(maDto.getId());

		ChambreService chambreServiceNull = chambreServiceService.getById(maDto.getId());
        //Verify request succeed
        Assertions.assertEquals(null, chambreServiceNull);
	}
}
