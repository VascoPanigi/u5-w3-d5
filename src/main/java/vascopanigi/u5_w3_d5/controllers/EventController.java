package vascopanigi.u5_w3_d5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vascopanigi.u5_w3_d5.enitites.Event;
import vascopanigi.u5_w3_d5.enitites.User;
import vascopanigi.u5_w3_d5.exceptions.BadRequestException;
import vascopanigi.u5_w3_d5.payloads.NewEventDTO;
import vascopanigi.u5_w3_d5.payloads.NewEventResponseDTO;
import vascopanigi.u5_w3_d5.payloads.NewUserDTO;
import vascopanigi.u5_w3_d5.payloads.NewUserResponseDTO;
import vascopanigi.u5_w3_d5.services.EventService;

import java.util.UUID;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping
    public Page<Event> getAllEvents(@RequestParam(defaultValue = "0") int pageNum, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "id") String sortBy){
        return this.eventService.getAllEvents(pageNum, pageSize, sortBy);
    }

    @GetMapping("/{eventId}")
    public Event getEvent(@AuthenticationPrincipal @PathVariable UUID eventId){
        return this.eventService.findById(eventId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('EVENT_ORGANIZER')")
    public NewEventResponseDTO save(@RequestBody @Validated NewEventDTO body, BindingResult validationResult){
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        return new NewEventResponseDTO(this.eventService.save(body).getId());
    }

    @DeleteMapping("/{eventId}")
    @PreAuthorize("hasAuthority('EVENT_ORGANIZER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@AuthenticationPrincipal @PathVariable UUID eventId){
        this.eventService.findByIdAndDelete(eventId);
    }
}
