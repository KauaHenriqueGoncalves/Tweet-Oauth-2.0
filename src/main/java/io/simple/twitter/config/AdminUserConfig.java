package io.simple.twitter.config;

import io.simple.twitter.entities.Role;
import io.simple.twitter.entities.User;
import io.simple.twitter.repository.RoleRepository;
import io.simple.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Role admin = roleRepository.findByName(Role.Values.ADMIN.name());

        Optional<User> userAdmin = userRepository.findByName("admin");

        userAdmin.ifPresentOrElse(
                (user) -> System.out.println("Admin já existe!"),
                () -> {
                    User user = new User();
                    user.setName("admin");
                    user.setPassword(passwordEncoder.encode("123"));
                    user.setRoles(Set.of(admin));
                    userRepository.save(user);
                }
        );

    }
}
