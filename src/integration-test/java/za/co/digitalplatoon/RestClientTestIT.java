package za.co.digitalplatoon;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import za.co.digitalplatoon.invoiceservice.domain.Invoice;
import za.co.digitalplatoon.invoiceservice.domain.LineItem;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 *   once the application deployed
 *  - integration test - application can be invoked by client application
 *
 */
public class RestClientTestIT {

    private  static final String invoice_URL = "http://localhost:8080/invoices";


    @Test
    public void createInvoiceTest(){

        Invoice invoice = getInvoice();
        RestTemplate restTemplate = new RestTemplate();
        URI uri = restTemplate.postForLocation(invoice_URL, invoice, Invoice.class);

        Assert.assertNotNull(uri.toASCIIString());

        System.out.println("URL to retrieve Invoice:"+uri.toASCIIString());

    }

    @Test(expected = HttpClientErrorException.class)
    public void beanValidationTest(){

        Invoice invoice = getInvoice();
        invoice.setClient(null);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(invoice_URL, invoice, Invoice.class);
    }


    private Invoice getInvoice(){
        Invoice invoice = new Invoice();
        invoice.setClient("Tshepiso");
        invoice.setInvoiceDate(new Date());
        invoice.setVatReg(15L);
        invoice.setLineItems(new ArrayList<>());

        LineItem lineItem = new LineItem();
        lineItem.setDescription("TODO TODO");
        lineItem.setQuality(1L);
        lineItem.setUnitPrice(new BigDecimal(123));
        invoice.getLineItems().add(lineItem);

        return invoice;
    }
}
