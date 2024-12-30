package io.reactivestax;

import io.reactivestax.repository.AddressDAO;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;


class TableCreatorServiceTest {

    @Test
    void testTableCreation(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        TableCreatorService tableCreatorService = context.getBean(TableCreatorService.class);
        tableCreatorService.createSequencesAndTables();
    }

}