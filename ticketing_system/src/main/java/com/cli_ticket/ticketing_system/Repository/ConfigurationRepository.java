package com.cli_ticket.ticketing_system.Repository;

import com.cli_ticket.ticketing_system.Entity.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
}
