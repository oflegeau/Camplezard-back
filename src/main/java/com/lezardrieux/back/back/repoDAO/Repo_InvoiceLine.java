package com.lezardrieux.back.back.repoDAO;

import com.lezardrieux.back.back.modelDAO.DAO_InvoiceLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repo_InvoiceLine extends JpaRepository<DAO_InvoiceLine, Long> {
}
