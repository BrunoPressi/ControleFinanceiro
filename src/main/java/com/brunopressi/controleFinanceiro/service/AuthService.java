package com.brunopressi.controleFinanceiro.service;

import com.brunopressi.controleFinanceiro.entities.dto.usuarioDTO.AuthDTO;
import com.brunopressi.controleFinanceiro.exception.BadCredentialsException;
import com.brunopressi.controleFinanceiro.jwt.JwtToken;
import com.brunopressi.controleFinanceiro.jwt.JwtUserDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtUserDetailsService jwtUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    public JwtToken auth(@Valid AuthDTO authDTO) {

        try {
            usuarioService.buscarPorEmail(authDTO.getEmail());

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(authDTO.getEmail(), authDTO.getSenha());

            authenticationManager.authenticate(authenticationToken);
            JwtToken token = jwtUserDetailsService.getTokenAuthenticated(authDTO.getEmail());

            return token;
        }
        catch (AuthenticationException e) {
            log.warn("Bad Credentials for email: {}", authDTO.getEmail());
            throw new BadCredentialsException(String.format("Senha inválida para o email %s", authDTO.getEmail()));
        }
    }
}
