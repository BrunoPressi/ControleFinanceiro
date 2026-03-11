package com.brunopressi.controleFinanceiro.jwt;

import com.brunopressi.controleFinanceiro.entities.Usuario;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class JwtUserDetails extends User {

    private Usuario usuario;

    public JwtUserDetails(Usuario usuario) {
        super(usuario.getEmail(), usuario.getSenha(), AuthorityUtils.createAuthorityList(usuario.getRole().name()));
        this.usuario = usuario;
    }

    public Long getId() {
        return usuario.getId();
    }

    public String getRole() {
        return usuario.getRole().name();
    }

    public Usuario getUsuario() { return usuario; }

}
