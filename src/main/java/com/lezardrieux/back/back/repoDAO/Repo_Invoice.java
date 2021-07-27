package com.lezardrieux.back.back.repoDAO;

import com.lezardrieux.back.back.modelDAO.DAO_Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface Repo_Invoice extends JpaRepository<DAO_Invoice, Long> {
}
