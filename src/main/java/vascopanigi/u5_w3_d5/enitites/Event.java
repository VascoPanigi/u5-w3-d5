package vascopanigi.u5_w3_d5.enitites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Setter
@Getter
public class Event {
    //titolo, descrizione, data, luogo e numero di posti
    // disponibili, l’evento inoltre deve avere un riferimento
    // al creatore di esso. Gli organizzatori devono anche poter
    // modificare ed eventualmente eliminare eventi

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

}
