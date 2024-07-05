package vascopanigi.u5_w3_d5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vascopanigi.u5_w3_d5.enitites.Event;
import vascopanigi.u5_w3_d5.enitites.User;
import vascopanigi.u5_w3_d5.exceptions.BadRequestException;
import vascopanigi.u5_w3_d5.exceptions.NotFoundException;
import vascopanigi.u5_w3_d5.payloads.NewEventDTO;
import vascopanigi.u5_w3_d5.payloads.NewUserDTO;
import vascopanigi.u5_w3_d5.repositories.EventRepository;
import vascopanigi.u5_w3_d5.repositories.UserRepository;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    public Page<Event> getAllEvents(int pageNum, int pageSize, String sortBy){
        if(pageSize>50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(sortBy));
        return eventRepository.findAll(pageable);
    }

    public Event save(NewEventDTO body) {
//        Event newEvent = new Event(body.description(), body.location(), body.date(), body.capacity());
        Event newEvent = new Event(body.capacity(), body.location(), body.description()  );
        Random rndm = new Random();
        newEvent.setDate(LocalDate.now().plusDays(rndm.nextInt(1,100)));

        return eventRepository.save(newEvent);
    }

    public Event findById(UUID eventId) {
        return this.eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException(eventId));
    }

    public void findByIdAndDelete(UUID eventId) {
        Event found = this.findById(eventId);
        this.eventRepository.delete(found);
    }
}
