package io.reactivestax;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



class TableCreatorServiceTest {

    @Test
    void testTableCreation(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        TableCreatorService tableCreatorService = context.getBean(TableCreatorService.class);
        tableCreatorService.createSequencesAndTables();
    }

}