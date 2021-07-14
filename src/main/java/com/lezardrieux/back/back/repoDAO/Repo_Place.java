package com.lezardrieux.back.back.repoDAO;

import com.lezardrieux.back.back.modelDAO.DAO_Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface Repo_Place extends JpaRepository<DAO_Place, UUID>  {

    List<DAO_Place> findAllByZone(int zone);
    List<DAO_Place> findAllByStatus(int status);
}
