package io.reactivestax;

import io.reactivestax.entity.Address;
import io.reactivestax.repository.AddressDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

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

        context.close();

    }
}
