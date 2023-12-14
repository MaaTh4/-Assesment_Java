package JavaProject.At_java.Util;


import JavaProject.At_java.Model.Pokemon;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Log
@Slf4j
public class PokemonUtil {

//    public final String apiURL = " https://pokeapi.co/api/v2/";
//
//    public Pokemon getPokemon(Long id) {

//        try {
//            HttpRequest request = HttpRequest.newBuilder().GET().version(HttpClient.Version.HTTP_2).uri(new URI(apiURL + id)).build();
//
//            HttpClient client = HttpClient.newBuilder().build();
//            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//            ObjectMapper objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
//
//            Pokemon pokemon = objectMapper.readValue(httpResponse.body(), Pokemon.class);
//
//            if (httpResponse.statusCode() == 200) {
//
//                log.info("Request reponse:  " + httpResponse.statusCode());
//            }
//
//
//            Long ID = pokemon.getID();
//            String pokeName = pokemon.getPokemon_name();
//            List<String> pokeSkills = GetPokeSKills(pokemon);
//
//            Pokemon pokemon1 = new Pokemon((Long) id, pokeName, pokeSkills);
//
//
//            return pokemon1;
//
//        }catch (URISyntaxException | IOException | InterruptedException e){
//            throw new RuntimeException();
//        } finally {
//            log.info("App is working");
//        }
//    }
//
//    private List<String> GetPokeSKills(Pokemon pokemon) {
//        List<String> abilitys = new LinkedList<>();
//        for (int i = 0; i < pokemon.getPokeSkills().size(); i++) {
//            Object skillTypes = pokemon.getPokeSkills().get(i);
//
//            if (skillTypes instanceof Map) {
//                Map<String, Object> skillsTypeList = (Map<String, Object>) skillTypes;
//                Object skillTypeList = skillsTypeList.get("ability");
//                if (skillTypeList instanceof Map) {
//                    Object skillName = ((Map<String, Object>) skillsTypeList).get("name");
//                    abilitys.add(skillName.toString());
//                }
//            }
//        }
//        return abilitys;
//    }




    public final String apiURL = "https://pokeapi.co/api/v2/pokemon/";

    public Pokemon getPokemon(Long id) {
        try {
            HttpRequest request = HttpRequest.newBuilder().GET().uri(new URI(apiURL + id)).build();

            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            log.info("Request response: " + statusCode);

            ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
            Pokemon pokeInfos = mapper.readValue(response.body(), Pokemon.class);

            List<String> skill = getPokeSkills(pokeInfos);
            int height = pokeInfos.getHeight();
            String name = pokeInfos.getPokemon_name();


            return new Pokemon(id, name, skill, height);

        }catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private List<String> getPokeSkills(Pokemon pokemon) {
        List<String> skills = new LinkedList<>();

        if (pokemon != null && pokemon.getPokeSkills() != null) {
            for (Object types : pokemon.getPokeSkills()) {
                if (types instanceof Map) {
                    Map<String, Object> typesList = (Map<String, Object>) types;
                    Object typeList = typesList.get("ability");

                    if (typeList instanceof Map) {
                        Map<String, Object> skillList = (Map<String, Object>) typeList;
                        Object skillName = skillList.get("name");

                        if (skillName != null) {
                            skills.add(skillName.toString());
                        }
                    }
                }
            }
        }

        return skills;
    }
}