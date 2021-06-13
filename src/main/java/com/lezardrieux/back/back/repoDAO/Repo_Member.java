package com.lezardrieux.back.back.repoDAO;


import com.lezardrieux.back.back.modelDAO.DAO_Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface Repo_Member extends JpaRepository<DAO_Member, UUID> {

    Page<DAO_Member> findAll( Pageable pageable);
    Page<DAO_Member> findAllByNationEquals(int nation,  Pageable pageable);
}
