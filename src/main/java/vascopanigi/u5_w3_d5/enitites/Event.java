package vascopanigi.u5_w3_d5.enitites;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "eventi")
public class Event {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String description;

    private String location;
    private int max_participants;

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private User user;

    public Event(String description, String location, int max_participants) {
        this.description = description;
        this.location = location;
        this.max_participants = max_participants;
    }
}
