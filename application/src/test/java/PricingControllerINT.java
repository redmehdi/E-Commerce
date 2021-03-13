import com.jayway.restassured.RestAssured;
import com.red.one.controller.dtos.PricingDto;
import com.red.one.entities.PricingEntity;
import com.red.one.repositories.PricingRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
public class PricingControllerINT {

    @LocalServerPort
    int serverPort;

    @Autowired
    private PricingRepository repository;

    private static final String POKE_HEIGHT_PATH = "/pokes/height";

    private static final String POKE_WEIGHT_PATH = "/pokes/weight";

    private static final String POKE_BASE_EXPERIENCE_PATH = "/pokes/base-experience";

    @Before
    public void onBefore() throws Exception {
        setUp();
    }

    public void setUp() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void testHeight_whenNoData_shouldReturnSize() {

        RestAssured.given().port(serverPort)
                .when()
                .get(POKE_HEIGHT_PATH)
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("height", Matchers.hasSize(0));
    }

    @Test
    public void testWeight_whenNoData_shouldReturnSize() {

        RestAssured.given().port(serverPort)
                .when()
                .get(POKE_WEIGHT_PATH)
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("weight", Matchers.hasSize(0));
    }

    @Test
    public void testHeight_whenExistData_shouldReturnSize() {

        PokeCharacterEntity pokeCharacterEntity1 = new PokeCharacterEntity("name1", 56, 34, 23, null);
        PricingEntity pokeSpeciesEntity1 = new PricingEntity("red", "Red1", "test", "eee", pokeCharacterEntity1);
        pokeCharacterEntity1.setSpecies(pokeSpeciesEntity1);

        PokeCharacterEntity pokeCharacterEntity2 = new PokeCharacterEntity("name1", 56, 34, 23, null);
        PricingEntity pokeSpeciesEntity2 = new PricingEntity("red", "Red1", "test", "eee", pokeCharacterEntity2);
        pokeCharacterEntity2.setSpecies(pokeSpeciesEntity2);

        repository.save(pokeSpeciesEntity1);
        repository.save(pokeSpeciesEntity2);

        RestAssured.given().port(serverPort)
                .when()
                .get(POKE_HEIGHT_PATH)
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("height", Matchers.hasSize(2));
    }

    @Test
    public void testWeight_whenExistData_shouldReturnSize() {

        PokeCharacterEntity pokeCharacterEntity1 = new PokeCharacterEntity("name1", 56, 34, 23, null);
        PricingEntity pokeSpeciesEntity1 = new PricingEntity("red", "Red1", "test", "eee", pokeCharacterEntity1);
        pokeCharacterEntity1.setSpecies(pokeSpeciesEntity1);

        PokeCharacterEntity pokeCharacterEntity2 = new PokeCharacterEntity("name1", 56, 34, 23, null);
        PricingEntity pokeSpeciesEntity2 = new PricingEntity("red", "Red1", "test", "eee", pokeCharacterEntity2);
        pokeCharacterEntity2.setSpecies(pokeSpeciesEntity2);

        repository.save(pokeSpeciesEntity1);
        repository.save(pokeSpeciesEntity2);

        RestAssured.given().port(serverPort)
                .when()
                .get(POKE_WEIGHT_PATH)
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("weight", Matchers.hasSize(2));
    }

    @Test
    public void testHeight_whenExistData_shouldReturnTop5Height() {

        PokeCharacterEntity pokeCharacterEntity1 = new PokeCharacterEntity("name1", 56, 100, 23, null);
        PricingEntity pokeSpeciesEntity1 = new PricingEntity("red", "Red1", "test", "eee", pokeCharacterEntity1);
        pokeCharacterEntity1.setSpecies(pokeSpeciesEntity1);

        PokeCharacterEntity pokeCharacterEntity2 = new PokeCharacterEntity("name1", 56, 88, 23, null);
        PricingEntity pokeSpeciesEntity2 = new PricingEntity("red", "Red1", "test", "eee", pokeCharacterEntity2);
        pokeCharacterEntity2.setSpecies(pokeSpeciesEntity2);

        PokeCharacterEntity pokeCharacterEntity3 = new PokeCharacterEntity("name1", 56, 66, 23, null);
        PricingEntity pokeSpeciesEntity3 = new PricingEntity("red", "Red1", "test", "eee", pokeCharacterEntity3);
        pokeCharacterEntity3.setSpecies(pokeSpeciesEntity3);

        PokeCharacterEntity pokeCharacterEntity4 = new PokeCharacterEntity("name1", 56, 44, 23, null);
        PricingEntity pokeSpeciesEntity4 = new PricingEntity("red", "Red1", "test", "eee", pokeCharacterEntity4);
        pokeCharacterEntity4.setSpecies(pokeSpeciesEntity4);

        PokeCharacterEntity pokeCharacterEntity5 = new PokeCharacterEntity("name1", 56, 1030, 23, null);
        PricingEntity pokeSpeciesEntity5 = new PricingEntity("red", "Red1", "test", "eee", pokeCharacterEntity5);
        pokeCharacterEntity5.setSpecies(pokeSpeciesEntity5);

        //small one is number 6 item
        final int smallestHeight = 8;
        PokeCharacterEntity pokeCharacterSmallestOne = new PokeCharacterEntity("name1", 56, smallestHeight, 23, null);
        PricingEntity pokeSpeciesEntity6 = new PricingEntity("red", "Red1", "test", "eee", pokeCharacterSmallestOne);
        pokeCharacterSmallestOne.setSpecies(pokeSpeciesEntity6);

        repository.save(pokeSpeciesEntity1);
        repository.save(pokeSpeciesEntity2);
        repository.save(pokeSpeciesEntity3);
        repository.save(pokeSpeciesEntity4);
        repository.save(pokeSpeciesEntity5);
        repository.save(pokeSpeciesEntity6);


        PricingDto[] pokeSpecies = RestAssured.given().port(serverPort)
                .when()
                .get(POKE_HEIGHT_PATH)
                .as(PricingDto[].class);

        assertEqualTop5(smallestHeight, pokeSpecies);
    }

    @Test
    public void testWeight_whenExistData_shouldReturnTop5Height() {

        PokeCharacterEntity pokeCharacterEntity1 = new PokeCharacterEntity("name1", 467, 100, 576, null);
        PricingEntity pokeSpeciesEntity1 = new PricingEntity("red", "Red1", "test", "eee", pokeCharacterEntity1);
        pokeCharacterEntity1.setSpecies(pokeSpeciesEntity1);

        PokeCharacterEntity pokeCharacterEntity2 = new PokeCharacterEntity("name1", 56, 88, 11, null);
        PricingEntity pokeSpeciesEntity2 = new PricingEntity("red", "Red1", "test", "eee", pokeCharacterEntity2);
        pokeCharacterEntity2.setSpecies(pokeSpeciesEntity2);

        PokeCharacterEntity pokeCharacterEntity3 = new PokeCharacterEntity("name1", 11, 66, 22, null);
        PricingEntity pokeSpeciesEntity3 = new PricingEntity("red", "Red1", "test", "eee", pokeCharacterEntity3);
        pokeCharacterEntity3.setSpecies(pokeSpeciesEntity3);

        PokeCharacterEntity pokeCharacterEntity4 = new PokeCharacterEntity("name1", 22, 44, 33, null);
        PricingEntity pokeSpeciesEntity4 = new PricingEntity("red", "Red1", "test", "eee", pokeCharacterEntity4);
        pokeCharacterEntity4.setSpecies(pokeSpeciesEntity4);

        PokeCharacterEntity pokeCharacterEntity5 = new PokeCharacterEntity("name1", 33, 1030, 23, null);
        PricingEntity pokeSpeciesEntity5 = new PricingEntity("red", "Red1", "test", "eee", pokeCharacterEntity5);
        pokeCharacterEntity5.setSpecies(pokeSpeciesEntity5);

        //small one is number 6 item
        final int smallestWeight = 8;
        PokeCharacterEntity pokeCharacterSmallestOne = new PokeCharacterEntity("name1", 22, 44, smallestWeight, null);
        PricingEntity pokeSpeciesEntity6 = new PricingEntity("red", "Red1", "test", "eee", pokeCharacterSmallestOne);
        pokeCharacterSmallestOne.setSpecies(pokeSpeciesEntity6);

        repository.save(pokeSpeciesEntity1);
        repository.save(pokeSpeciesEntity2);
        repository.save(pokeSpeciesEntity3);
        repository.save(pokeSpeciesEntity4);
        repository.save(pokeSpeciesEntity5);
        repository.save(pokeSpeciesEntity6);


        PricingDto[] pokeSpecies = RestAssured.given().port(serverPort)
                .when()
                .get(POKE_WEIGHT_PATH)
                .as(PricingDto[].class);

        assertEqualTop5(smallestWeight, pokeSpecies);
    }

    @Test
    public void testBaseExperience_whenExistData_shouldReturnTop5Height() {

        PokeCharacterEntity pokeCharacterEntity1 = new PokeCharacterEntity("name1", 467, 100, 576, null);
        PricingEntity pokeSpeciesEntity1 = new PricingEntity("red", "Red1", "test", "eee", pokeCharacterEntity1);
        pokeCharacterEntity1.setSpecies(pokeSpeciesEntity1);

        PokeCharacterEntity pokeCharacterEntity2 = new PokeCharacterEntity("name1", 56, 88, 11, null);
        PricingEntity pokeSpeciesEntity2 = new PricingEntity("red", "Red1", "test", "eee", pokeCharacterEntity2);
        pokeCharacterEntity2.setSpecies(pokeSpeciesEntity2);

        PokeCharacterEntity pokeCharacterEntity3 = new PokeCharacterEntity("name1", 11, 66, 22, null);
        PricingEntity pokeSpeciesEntity3 = new PricingEntity("red", "Red1", "test", "eee", pokeCharacterEntity3);
        pokeCharacterEntity3.setSpecies(pokeSpeciesEntity3);

        PokeCharacterEntity pokeCharacterEntity4 = new PokeCharacterEntity("name1", 546, 44, 33, null);
        PricingEntity pokeSpeciesEntity4 = new PricingEntity("red", "Red1", "test", "eee", pokeCharacterEntity4);
        pokeCharacterEntity4.setSpecies(pokeSpeciesEntity4);

        PokeCharacterEntity pokeCharacterEntity5 = new PokeCharacterEntity("name1", 33, 1030, 23, null);
        PricingEntity pokeSpeciesEntity5 = new PricingEntity("red", "Red1", "test", "eee", pokeCharacterEntity5);
        pokeCharacterEntity5.setSpecies(pokeSpeciesEntity5);

        //small one is number 6 item
        final int smallestBaseExperience = 8;
        PokeCharacterEntity pokeCharacterSmallestOne = new PokeCharacterEntity("name1", smallestBaseExperience, 44, 44, null);
        PricingEntity pokeSpeciesEntity6 = new PricingEntity("red", "Red1", "test", "eee", pokeCharacterSmallestOne);
        pokeCharacterSmallestOne.setSpecies(pokeSpeciesEntity6);

        repository.save(pokeSpeciesEntity1);
        repository.save(pokeSpeciesEntity2);
        repository.save(pokeSpeciesEntity3);
        repository.save(pokeSpeciesEntity4);
        repository.save(pokeSpeciesEntity5);
        repository.save(pokeSpeciesEntity6);


        PricingDto[] pokeSpecies = RestAssured.given().port(serverPort)
                .when()
                .get(POKE_BASE_EXPERIENCE_PATH)
                .as(PricingDto[].class);

        assertEqualTop5(smallestBaseExperience, pokeSpecies);
    }


    private void assertEqualTop5(int smallestHeight, PricingDto[] pokeSpecies) {
        for (int i = 0; i < pokeSpecies.length; i++) {
            final PokeCharacterDto pokeCharacter = pokeSpecies[i].getPokeCharacter();
            Assertions.assertNotNull(pokeCharacter);
            Assertions.assertNotEquals(pokeCharacter.getHeight(), smallestHeight);
        }
    }
}
