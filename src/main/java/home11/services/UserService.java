package home11.services;

import home11.dto.RegistrationRequest;
import home11.model.Role;
import home11.model.User;
import home11.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public Optional<User> getUserByUsername(String username){
        Optional<User> user = findByUsername(username);
        if(user.isPresent()){
            return user;
//            throw  new LoginAlreadyExistsException(String.format("Login '%s' already exists", username));
        }
        return null;
    }
//    public void save(User user){
//     //   userRepository.insertU(user.getUsername(), user.getPassword(), user.getEmail());
//     //   userRepository.saveAndFlush(user);
//        userRepository.save(user);
//    }
    public void insert(Long user_id, Long role_id){
        userRepository.insertQ(user_id, role_id);
    }

    @Transactional
    public void registerNewUserAccount(RegistrationRequest  regRequest){// throws EmailExistsException {

        userRepository.insertU(regRequest.getUsername(), passwordEncoder.encode(regRequest.getPassword()),
                regRequest.getEmail());
        Optional<User> user = findByUsername(regRequest.getUsername());
////                    .orElseThrow(()-> new ResourceNotFoundException("User username = "+ regRequest.getUsername() +" not found"));
        Optional<Role> role = roleService.findByName("ROLE_USER");
////                .orElseThrow(() -> new ResourceNotFoundException("Role name = ROLE_USER not found")));
            userRepository.insertQ(user.get().getId(), role.get().getId());
    }
}
