package pl.shonsu.authserver.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.shonsu.authserver.user.entity.Role;
import pl.shonsu.authserver.user.entity.User;
import pl.shonsu.authserver.user.model.UserPrincipal;
import pl.shonsu.authserver.user.repository.RoleRepository;
import pl.shonsu.authserver.user.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
//    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println(username);
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + username);
//            return new User(
//                    " ", " ", true, true, true, true,
//                            roleRepository.findByName("ROLE_USER"));
        } else {
            //roleRepository.findByName("ROLE_USER");
            Set<Role> roles = roleRepository.findByUsersId(user.getId());
            roles = roles.stream().map(r->new Role(r.getId(), r.getName(),null)).collect(Collectors.toSet());
            return new UserPrincipal(
                    new User(null, user.getUsername(), user.getPassword(), user.getStatus(), roles));
        }

//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User Not Found for username: " + username));
       // return new UserPrincipal(user);
    }

    public void saveDefaultUser() {
        //userRepository.findByUsername("Shonsu");
        User user = new User();
        user.setUsername("Shonsu");
        user.setPassword(passwordEncoder.encode("Test123$"));
        user.setStatus("ACTIVE");
        user.setRoles(Set.of(new Role(null, "USER")));
        userRepository.save(user);
    }
}
