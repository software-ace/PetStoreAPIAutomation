package api.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Pet {
    int id;
    PetCategory category;
    String name;
    List<PetTag> tags;
    Enums.PetStatus status;


    //status
}
