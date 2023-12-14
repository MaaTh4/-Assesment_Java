package JavaProject.At_java.Controller;



import JavaProject.At_java.Exceptions.ResponsePayload;
import JavaProject.At_java.Model.Pokemon;
import JavaProject.At_java.Service.PokemonService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@Log
@RestController
@RequestMapping

public class PokemonController {


    @Autowired
   PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public List<Pokemon> getAll(@RequestParam(required = false, defaultValue = "10") int size,
                                @RequestParam(required = false) Optional<String> name){
        List<Pokemon> pokeMap = pokemonService.getAllPokemon();
        if(name.isPresent()){
            return pokeMap = pokemonService.findByName(name.get());
        }else{
            return pokeMap.subList(0,size);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getPokeById(@PathVariable long id){
        Pokemon pokemon = pokemonService.getPokemonById(id);
        return ResponseEntity.ok(pokemon);
    }

    @PostMapping
    public void create(@RequestBody Pokemon pokemon){
        pokemonService.create(pokemon);
        log.info(String.valueOf(HttpStatus.CREATED));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponsePayload> delete(@PathVariable Long id){
        pokemonService.deleteID(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body((new ResponsePayload("Pokémon successfully deleted")));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponsePayload> update(@PathVariable Long id, @RequestBody Pokemon updated){
        pokemonService.update(id,updated);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new ResponsePayload("Pokémon successfully updated"));
    }

}
