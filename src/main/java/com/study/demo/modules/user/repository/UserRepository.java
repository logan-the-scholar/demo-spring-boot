package com.study.demo.modules.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.study.demo.modules.user.model.UserModel;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

    boolean existsByEmail(String email);

    List<UserModel> findByEmail(String email);

    List<UserModel> findBySub(String sub);

}
