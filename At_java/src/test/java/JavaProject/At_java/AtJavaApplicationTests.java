package JavaProject.At_java;

import JavaProject.At_java.Model.Pokemon;
import JavaProject.At_java.Service.PokemonService;
import JavaProject.At_java.Util.PokemonUtil;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Log
@SpringBootTest
class AtJavaApplicationTests {

	@Test
void getPokemonTest(){
		PokemonUtil pokemonUtil = new PokemonUtil();
		Pokemon pokemon = pokemonUtil.getPokemon(1L);
		log.info("This pokemon is the " + pokemon);
	}
@Test
	void amountTest(){
	PokemonService pokemonService = new PokemonService();

		log.info("The amount of pokemons are "+pokemonService.getAllPokemon());
	assertEquals(151,pokemonService.getAllPokemon().size());
}
	@Test
	void getIdTest(){
		PokemonService	pokemonService = new PokemonService();
		log.info(""+pokemonService.getPokemonById(54L));
	}
}
