package com.study.demo.modules.token.repository;

import com.study.demo.modules.token.model.UserTokenModel;
import com.study.demo.modules.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserTokenRepository extends JpaRepository<UserTokenModel, UUID> {
    Optional<UserTokenModel> findByUser(UserModel user);
}
