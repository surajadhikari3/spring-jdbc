package io.reactivestax;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TableCreatorService {

    private final JdbcTemplate jdbcTemplate;

    public TableCreatorService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createSequencesAndTables() {
        // Create Sequences
        jdbcTemplate.execute("CREATE SEQUENCE IF NOT EXISTS seq_addresses START 1;");


        jdbcTemplate.execute("""
                    CREATE TABLE IF NOT EXISTS addresses (
                        id INT PRIMARY KEY DEFAULT nextval('seq_addresses'),
                        street VARCHAR(100),
                        city VARCHAR(50),
                        state VARCHAR(50),
                        zipcode VARCHAR(10)
                    );
                """);


        log.debug("Sequences and tables created successfully.");
    }
}
