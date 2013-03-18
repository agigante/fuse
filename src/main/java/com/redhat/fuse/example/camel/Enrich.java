package com.redhat.fuse.example.camel;

import com.redhat.fuse.example.*;
import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.cxf.message.MessageContentsList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enrich {

    private static Logger log = LoggerFactory.getLogger(Enrich.class);
    static List<Customer> customers = new ArrayList<Customer>();
    Random randomGenerator = new Random();

    private void generateCustomer() {
        Customer customer = new Customer();
        customer.setName("Fuse");
        customer.setNumOrders(randomGenerator.nextInt(100));
        customer.setRevenue(randomGenerator.nextInt(10000));
        customer.setType(CustomerType.BUSINESS);
        customer.setTest(BigDecimal.valueOf(100.00));
        customer.getAddress().add("FuseSource Office");
        customers.add(customer);
    }

    public SaveCustomer createCustomer(@Body String name) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setNumOrders(randomGenerator.nextInt(100));
        customer.setRevenue(randomGenerator.nextInt(10000));
        customer.setType(CustomerType.BUSINESS);
        customer.setTest(BigDecimal.valueOf(100.00));
        customer.getAddress().add("FuseSource Office");

        SaveCustomer result = new SaveCustomer();
        result.setCustomer(customer);
        return result;
    }

    public GetAllCustomersResponse getCustomers() {

        GetAllCustomersResponse response = new GetAllCustomersResponse();
        response.getReturn().addAll(customers);
        return response;
    }

    public GetCustomerByNameResponse getCustomerByName(@Body GetCustomerByName cSearched) {

        List<Customer> result = new ArrayList<Customer>();
        // Search for Customer using name as key
        for(Customer c : customers) {
            if (c.getName().equals(cSearched.getName())) {
               result.add(c);
               log.info(">> Customer find !");
               break;
            }
        }

        GetCustomerByNameResponse response = new GetCustomerByNameResponse();
        response.getReturn().addAll(result);

        return response;

    }

    public GetCustomerByNameResponse getCustomerByName2(Exchange ex) {

        MessageContentsList payload = (MessageContentsList) ex.getIn().getBody();
        // String toSearch = (String) payload.get(0);
        GetCustomerByName customerByName = (GetCustomerByName) payload.get(0);
        String cSearch = customerByName.getName();

        List<Customer> result = new ArrayList<Customer>();
        // Search for Customer using name as key
        for(Customer c : customers) {
            if (c.getName().equals(cSearch)) {
                result.add(c);
                log.info(">> Customer find !");
                break;
            }
        }

        GetCustomerByNameResponse response = new GetCustomerByNameResponse();
        response.getReturn().addAll(result);

        return response;

    }

    public Customer saveCustomer(@Body SaveCustomer c) {

        String address = (c.getCustomer().getAddress().get(0) != null) ?  c.getCustomer().getAddress().get(0) : "Unknown address";
        XMLGregorianCalendar birthDate = c.getCustomer().getBirthDate();

        // enrich the customer received from backend data
        Customer customer = new Customer();
        customer.setName(c.getCustomer().getName());
        customer.getAddress().add(address);
        customer.setBirthDate(birthDate);
        customer.setNumOrders(randomGenerator.nextInt(100));
        customer.setRevenue(randomGenerator.nextInt(10000));
        customer.setType(CustomerType.PRIVATE);
        customer.setTest(BigDecimal.valueOf(100.00));
        customers.add(customer);

        log.info(">> Customer created and added in the array.");

        return customer;
    }

    public void checkBody(Exchange ex) {

       Object payload = ex.getIn().getBody();
       log.info(">> Response prepared : " + payload);
    }



}
