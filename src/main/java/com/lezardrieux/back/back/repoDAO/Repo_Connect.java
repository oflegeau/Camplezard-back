package com.lezardrieux.back.back.repoDAO;

import com.lezardrieux.back.back.modelDAO.DAO_Connect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface Repo_Connect extends JpaRepository<DAO_Connect, UUID> {

    Optional<DAO_Connect> findByIdFront(String id);
    Page<DAO_Connect> findAllByRoleGreaterThanEqual(int i, Pageable pageable);

    // USER       0x01                1
    // MEMBER     0x02     2   + USER 3
    // LEADER     0x04     6   + USER 7
    // MANAGER    0x08    14   + USER 15
    // ADMIN      0x10                31
}
