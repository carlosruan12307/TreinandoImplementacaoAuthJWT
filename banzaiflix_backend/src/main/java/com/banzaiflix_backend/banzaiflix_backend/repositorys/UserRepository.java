package com.banzaiflix_backend.banzaiflix_backend.repositorys;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banzaiflix_backend.banzaiflix_backend.models.Usuarios.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel,UUID> {
    
}
