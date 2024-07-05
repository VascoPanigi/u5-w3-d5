package vascopanigi.u5_w3_d5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vascopanigi.u5_w3_d5.enitites.User;
import vascopanigi.u5_w3_d5.exceptions.BadRequestException;
import vascopanigi.u5_w3_d5.exceptions.NotFoundException;
import vascopanigi.u5_w3_d5.payloads.NewUserDTO;
import vascopanigi.u5_w3_d5.repositories.UserRepository;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcrypt;

    public Page<User> getAllUsers(int pageNum, int pageSize, String sortBy){
        if(pageSize>50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(sortBy));
        return userRepository.findAll(pageable);
    }

    public User save(NewUserDTO body) {
        this.userRepository.findByEmail(body.email()).ifPresent(
                user -> {
                    throw new BadRequestException("This email address is already in use.");
                }
        );
        User newUser = new User(body.name(), body.surname(), body.email(), bcrypt.encode(body.password()));
        return userRepository.save(newUser);
    }

    public User findById(UUID userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException(userId));
    }

    public User findByEmail(String userEmail){
        return userRepository.findByEmail(userEmail).orElseThrow(() -> new NotFoundException("User with email: " + userEmail + " not found! :("));
    }

    public void findByIdAndDelete(UUID userId) {
        User found = this.findById(userId);
        this.userRepository.delete(found);
    }

}
