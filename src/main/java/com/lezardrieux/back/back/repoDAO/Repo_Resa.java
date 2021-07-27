package com.lezardrieux.back.back.repoDAO;

import com.lezardrieux.back.back.modelDAO.DAO_Resa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface Repo_Resa extends JpaRepository<DAO_Resa, UUID> {
}
