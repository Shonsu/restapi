package pl.shonsu.authserver.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.shonsu.authserver.user.model.UserPrincipal;
import pl.shonsu.authserver.user.repository.UserRepository;
import pl.shonsu.authserver.user.entity.User;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found for username: " + username));
        return new UserPrincipal(user);
    }
}
