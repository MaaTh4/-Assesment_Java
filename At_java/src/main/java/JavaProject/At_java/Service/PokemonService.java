package JavaProject.At_java.Service;


import JavaProject.At_java.Exceptions.ResourceNotFoundException;
import JavaProject.At_java.Model.Pokemon;
import JavaProject.At_java.Util.PokemonUtil;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log
@Service
public class PokemonService {

        private final Map<Long, Pokemon> pokeMap = initPokemon();
        private long lastId = pokeMap.size();

    private Map<Long, Pokemon> initPokemon() {
        Map<Long, Pokemon> pokeMap = new HashMap<>();
        PokemonUtil pokeUtil = new PokemonUtil();

        for (long i = 1; i <= 151; i++) {
            Pokemon pokemon = pokeUtil.getPokemon(i);
            pokeMap.put(i, pokemon);
        }

        return pokeMap;
    }
        public void create(Pokemon pokemon) {
            Long id = ++this.lastId;
            pokemon.setID(id);
            pokeMap.put(id,pokemon);
        }

        public List<Pokemon> getAllPokemon() {
            return pokeMap.values().stream().toList();
        }

        public Pokemon getPokemonById(Long id) {
            return pokeMap.get(id);

        }

        public void update(long id, Pokemon updated) {
            updated.setID(id);
            pokeMap.replace(id,updated);
        }


        public void deleteID(long id) {
            Pokemon pokemon = pokeMap.remove(id);
            if (pokemon == null){
                try {
                    throw new ResourceNotFoundException("This pokemon ID does not exist");
                } catch (ResourceNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        public List<Pokemon> findByName(String name) {
            List<Pokemon> all = getAllPokemon();
            return all.stream().filter(pokemon -> pokemon.getPokemon_name().startsWith(name)).toList();
        }
    }
