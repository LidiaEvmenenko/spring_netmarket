package home11.configs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 //   private final UserService userService;
    private final JwtRequestFilter jwtRequestFilter;
//    @Autowired
//    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      //  log.info("Dao Authentication Provider");
        http.csrf().disable()//дополнительные токены от межсетевых запросов - отключаем
                .authorizeRequests()
             //   .antMatchers("/welcome").permitAll()
                // authenticated() -разрешает доступ всем аутентифицированным пользователям
            //    .antMatchers("/user_info").hasAnyAuthority("READ")
                .antMatchers("/h2-console/**").permitAll()
             //   .antMatchers("/products/**").hasAnyRole("USER", "ADMIN")
            //    .antMatchers("/write").hasAnyAuthority("DELETE", "WRITE")
            //    .antMatchers("/delete").hasAnyAuthority("DELETE")
                //   .antMatchers("/admin/**").hasAnyRole("ADMIN", "SUPERADMIN") // ROLE_ADMIN, ROLE_SUPERADMIN
            //    .antMatchers("/admin/**").hasAnyRole("ADMIN") // ROLE_ADMIN, ROLE_SUPERADMIN
                .anyRequest().permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //никаких сессий храниться не будет
              //  .formLogin()
                .and()
              //  .logout()
             //   .invalidateHttpSession(true)
             //   .deleteCookies("JSESSIONID");
                .headers().frameOptions().disable()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
        //если кто-то пытается влезть в запрещенную область, то получит 401 ошибку(не авторизован)

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
  //  public BCryptPasswordEncoder passwordEncoder() {
    public PasswordEncoder passwordEncoder() {
     //   new BCryptPasswordEncoder().encode(сюда пишем пароль)
     //   new BCryptPasswordEncoder().matches(пришедший пароль в чистом виде, пароль(хеш) из базы); это если надо поменять пароль
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        authenticationProvider.setUserDetailsService(userService);
//        return authenticationProvider;
//    }
@Override
@Bean
public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
}
}

