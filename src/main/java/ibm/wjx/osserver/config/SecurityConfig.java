package ibm.wjx.osserver.config;

import ibm.wjx.osserver.web.security.TokenBasedAuthenticationEntryPoint;
import ibm.wjx.osserver.web.security.TokenBasedAuthenticationFilter;
import ibm.wjx.osserver.web.security.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

/**
 * Create Date: 1/16/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenBasedAuthenticationFilter authenticationFilter;
    private final UserDetailsService userService;
    private final TokenBasedAuthenticationEntryPoint customEntryPoint;


    @Autowired
    public SecurityConfig(TokenBasedAuthenticationFilter authenticationFilter, UserDetailsService userService, TokenBasedAuthenticationEntryPoint customEntryPoint) {
        this.authenticationFilter = authenticationFilter;
        this.userService = userService;
        this.customEntryPoint = customEntryPoint;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .regexMatchers(HttpMethod.GET, "/token.*").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                .and().exceptionHandling().authenticationEntryPoint(customEntryPoint)
                .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean(initMethod = "init")
    public TokenManager tokenManager() {
        return new TokenManager();
    }
}
