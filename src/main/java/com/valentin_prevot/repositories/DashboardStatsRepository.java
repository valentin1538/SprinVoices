package com.valentin_prevot.invoice_app.repositories;

import com.valentin_prevot.invoice_app.models.DashboardStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DashboardStatsRepository extends JpaRepository<DashboardStats, Long> {
}