package com.lezardrieux.back.back.repoDAO;

import com.lezardrieux.back.back.modelDAO.DAO_MemberWith;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface Repo_MemberWith extends JpaRepository<DAO_MemberWith, UUID>  {
}
