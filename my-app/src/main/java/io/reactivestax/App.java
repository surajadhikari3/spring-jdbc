package io.reactivestax;

import io.reactivestax.entity.Address;
import io.reactivestax.repository.AddressDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 */

@Slf4j
public class App {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        AddressDAO addressDAO = context.getBean(AddressDAO.class);
        addressDAO.createAddress(
                Address.builder()
                        .street("Downes")
                        .city("Toronto")
                        .state("Ontario")
                        .zipcode("M5Y")
                        .build()
        );

        addressDAO.createAddressWithNamedTemplate(
                Address.builder()
                        .street("QueeensWay")
                        .city("Mississauga")
                        .state("Ontario")
                        .zipcode("L6T")
                        .build()
        );

        addressDAO.getAllAddress().forEach(System.out::println);

        Address addressWithNamedTemplate = addressDAO.getAddressWithNamedTemplate(3);
        log.debug("address", addressWithNamedTemplate);


        context.close();

    }
}
