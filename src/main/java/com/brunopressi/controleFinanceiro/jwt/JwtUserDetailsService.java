package com.brunopressi.controleFinanceiro.jwt;

import com.brunopressi.controleFinanceiro.entities.Usuario;
import com.brunopressi.controleFinanceiro.entities.enums.Role;
import com.brunopressi.controleFinanceiro.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.buscarPorEmail(email);
        return new JwtUserDetails(usuario);
    }

    public JwtToken getTokenAuthenticated(String email) {
        Usuario usuario = usuarioService.buscarPorEmail(email);
        return JwtUtils.createToken(email, usuario.getRole().name().substring("ROLE_".length()));
    }
}
