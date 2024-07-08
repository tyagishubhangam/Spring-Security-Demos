package in.krishna.SpringSecurity_login.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfigurations{
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails user1 = User.withUsername("john")
                .password(passwordEncoder().encode("john123"))
                .roles("USER")
                .build();
        UserDetails user2 = User.withUsername("billy")
                .password(passwordEncoder().encode("billy123"))
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("admin123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, user2, admin);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests((authorizeHttpRequests)->
                                authorizeHttpRequests.requestMatchers("/admin/**")
                                        .hasRole("ADMIN")
                                        .requestMatchers("/anonymous/*").anonymous()
                                        .requestMatchers("/login").permitAll()
                                        .requestMatchers("/home").permitAll()
                                        .anyRequest().authenticated()
                        ).formLogin((formLogin)-> formLogin.loginPage("/login")
                        .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/profile",true)
                        .failureUrl("/login?error=true")
                ).httpBasic(Customizer.withDefaults());





        return http.build();
    }
}
