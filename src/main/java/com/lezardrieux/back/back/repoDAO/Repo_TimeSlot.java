package com.lezardrieux.back.back.repoDAO;

import com.lezardrieux.back.back.modelDAO.DAO_TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repo_TimeSlot extends JpaRepository<DAO_TimeSlot, Long>  {
}
