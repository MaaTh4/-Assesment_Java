package JavaProject.At_java.Model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {

    @JsonProperty("id")
    public Long ID;

    @JsonProperty("name")
    public String Pokemon_name;

    @JsonProperty("PokemonSkills")
    public List pokeSkills;

    @JsonProperty("Height")
    public int Height;

    public Pokemon(){}

    public Pokemon(Long Id, String pokeName, List skills, int height){

        this.ID = Id;
        this.Pokemon_name = pokeName;
        this.pokeSkills = skills;
        this.Height = height;
    }
}
