package manager;

import models.Contact;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderContact {
    @DataProvider
    public Iterator<Object[]> contactSuccess(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .name("Harry")
                .lastName("Potter")
                .phone("343456783234")
                .email("harry@gmail.com")
                .address("Hogwards")
                .description("All fields")
                .build()});

        list.add(new Object[]{Contact.builder()
                .name("Tony")
                .lastName("Motter")
                .phone("343456783234")
                .email("Tony@gmail.com")
                .address("Haifa")
                .build()});


        return list.iterator();

    }
}
