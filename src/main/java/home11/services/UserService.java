package home11.services;

import home11.entity.Role;
import home11.entity.User;
import home11.exceptions.ResourceNotFoundException;
import home11.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    @Transactional
    public void updateUserBalance(Long id, Double itog){
        Optional<User> user = Optional.ofNullable(userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User id = " + id + " not found")));
        if (user.get().getBalance() >= itog){
            Double newBalance = user.get().getBalance() - itog;
            userRepository.updateUserBalance(newBalance, id);
        }
    }

//    public Optional<User> findByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
//    }
//
//    public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
//        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//    }
//
//    public Optional<User> getUserByUsername(String username){
//        Optional<User> user = findByUsername(username);
//        if(user.isPresent()){
//            return user;
//        }
//        return null;
//    }
//
//    public void insert(Long user_id, Long role_id){
//        userRepository.insertQ(user_id, role_id);
//    }
//
//    @Transactional
//    public void registerNewUserAccount(RegistrationRequest  regRequest){
//        userRepository.insertU(regRequest.getUsername(), passwordEncoder.encode(regRequest.getPassword()),
//                regRequest.getEmail());
//        Optional<User> user = findByUsername(regRequest.getUsername());
//        Optional<Role> role = roleService.findByName("ROLE_USER");
//            userRepository.insertQ(user.get().getId(), role.get().getId());
//    }
}
