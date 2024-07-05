package vascopanigi.u5_w3_d5.enitites;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String description;
    private String location;
    private LocalDate date;
    private int max_participants;

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private User user;

    public Event(String description, String location, LocalDate date, int max_participants) {
        this.description = description;
        this.location = location;
        this.date = date;
        this.max_participants = max_participants;
    }

    public Event(int max_participants, String location, String description) {
        this.max_participants = max_participants;
        this.location = location;
        this.description = description;
    }
}
