package com.lezardrieux.back.back.repoDAO;

import com.lezardrieux.back.back.modelDAO.DAO_ConnectAccess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface Repo_ConnectAccess extends JpaRepository<DAO_ConnectAccess, String> {

    DAO_ConnectAccess findByConnect_Id(UUID s);
}
