package za.co.digitalplatoon.invoiceservice.controller;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import za.co.digitalplatoon.invoiceservice.domain.Invoice;
import za.co.digitalplatoon.invoiceservice.service.InvoiceService;

import java.util.ArrayList;
import java.util.List;

public class InvoiceControllerTest {

    Mockery mockery;
    InvoiceService invoiceService;

    InvoiceController invoiceController = new InvoiceController();


    @Before
    public void init(){
        mockery = new Mockery(){{

        }};

        invoiceService = mockery.mock(InvoiceService.class);
        invoiceController.invoiceService = invoiceService;

    }

    @Test
    public void viewInvoiceTest() {

        mockery.checking( new Expectations(){{
            atLeast(1).of(invoiceService).findAllInvoices();
             will(returnValue(getInvoices()));
        }});

        ResponseEntity<List<Invoice>> listResponseEntity = invoiceController.viewAllInvoice();
        Assert.assertNotNull(listResponseEntity);
        Assert.assertEquals(listResponseEntity.getBody().size(), 1);

        mockery.assertIsSatisfied();

    }

    private List<Invoice> getInvoices(){
        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(new Invoice());

        return invoiceList;
    }


}