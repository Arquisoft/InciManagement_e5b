package uo.asw.dbManagement.services;



import uo.asw.dbManagement.model.Agent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    
@Override
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
    Agent agent = new Agent();
    if(agent==null) {
    	throw new UsernameNotFoundException("hola, el usuario no está encontrado");
    }
    	
    
    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    grantedAuthorities.add(new SimpleGrantedAuthority(agent.getRole()));
  
    return  new org.springframework.security.core.userdetails.User(
            agent.getEmail(), agent.getPassword(), grantedAuthorities);
}
}
