package com.banzaiflix_backend.banzaiflix_backend.models.Usuarios;

import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;

import com.banzaiflix_backend.banzaiflix_backend.Enums.RoleEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "role")
public class RoleModel implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Enumerated(EnumType.STRING)
    private RoleEnum name;
    @Override
    public String getAuthority() {
        // TODO Auto-generated method stub
        return this.name.toString();
    }

}
