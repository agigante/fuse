package com.redhat.fuse.example;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.6.0
 * 2013-03-13T15:12:23.612-03:00
 * Generated source version: 2.6.0
 * 
 */
@WebService(targetNamespace = "http://example.fuse.redhat.com/", name = "CustomerService")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface CustomerService {

    @WebResult(name = "getAllCustomersResponse", targetNamespace = "http://example.fuse.redhat.com/", partName = "parameters")
    @WebMethod(action = "http://example.fuse.redhat.com/getAllCustomers")
    public GetAllCustomersResponse getAllCustomers() throws NotAuthorizedUserFault;

    @WebResult(name = "saveCustomerResponse", targetNamespace = "http://example.fuse.redhat.com/", partName = "parameters")
    @WebMethod(action = "http://example.fuse.redhat.com/saveCustomer")
    public SaveCustomer saveCustomer(
        @WebParam(partName = "parameters", name = "saveCustomer", targetNamespace = "http://example.fuse.redhat.com/")
        SaveCustomer parameters
    ) throws NotAuthorizedUserFault;

    @WebResult(name = "getCustomerByNameResponse", targetNamespace = "http://example.fuse.redhat.com/", partName = "parameters")
    @WebMethod(action = "http://example.fuse.redhat.com/getCustomerByName")
    public GetCustomerByNameResponse getCustomerByName(
        @WebParam(partName = "parameters", name = "getCustomerByName", targetNamespace = "http://example.fuse.redhat.com/")
        GetCustomerByName parameters
    ) throws NotAuthorizedUserFault, NoSuchCustomerFault;
}
