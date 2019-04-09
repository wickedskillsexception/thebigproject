package com.siit.thebigproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;


    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);


//        auth.inMemoryAuthentication()
//                .withUser("admin").password("admin").roles("ADMIN")
//        .and()
//                .withUser("user").password("user").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/", "/file").permitAll()

//                .antMatchers("/user").hasRole("ADMIN")
                .antMatchers("/user/add").access("hasRole('ADMIN')")
//                .antMatchers("/user/edit").hasRole("ADMIN")
//                .antMatchers("/user/delete").hasRole("ADMIN")
//                .antMatchers("/user/save").hasRole("ADMIN")
//
//                .antMatchers("/ingredient").permitAll()
//                .antMatchers("/ingredient/add").hasRole("ADMIN")
//                .antMatchers("/ingredient/edit").hasRole("ADMIN")
//                .antMatchers("/ingredient/delete").hasRole("ADMIN")
//                .antMatchers("/ingredient/save").hasRole("ADMIN")
//
//                .antMatchers("/recipe").permitAll()
//                .antMatchers("/recipe/add").hasRole("ADMIN")
//                .antMatchers("/recipe/edit").hasRole("ADMIN")
//                .antMatchers("/recipe/delete").hasRole("ADMIN")
//                .antMatchers("/recipe`/save").hasRole("ADMIN")
//
//                .antMatchers("/fridgeIngredient").hasRole("ADMIN")
//                .antMatchers("/recipeIngredient").hasRole("ADMIN")


                .anyRequest().authenticated()

                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/login")
                .permitAll()


                .and()
                .logout().logoutSuccessUrl("/").permitAll()
                .and().exceptionHandling()
                .accessDeniedPage("/access-denied");


    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/ext-img/**");
    }
}
