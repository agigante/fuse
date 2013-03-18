package com.redhat.fuse.example.camel;

import org.apache.camel.CamelAuthorizationException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class FromWStoCustomerService extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("cxf:bean:WS").id("cxf-to-client-pojo")

                .choice()
                .when().simple("${in.header.SOAPAction} contains 'getCustomerByName'")
                .log(">>> We will search a Customer")
                .beanRef("enrich", "getCustomerByName")

                .when().simple("${in.header.SOAPAction} contains 'saveCustomer'")
                .log(">>> We will save a Customer")
                .beanRef("enrich", "saveCustomer")

                .when().simple("${in.header.SOAPAction} contains 'getAllCustomers'")
                .log(">>> We will get all Customers")
                .beanRef("enrich", "getCustomers")
        ;


    }
}