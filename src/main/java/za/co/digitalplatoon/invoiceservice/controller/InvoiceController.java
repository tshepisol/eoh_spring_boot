package za.co.digitalplatoon.invoiceservice.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import za.co.digitalplatoon.invoiceservice.domain.Invoice;
import za.co.digitalplatoon.invoiceservice.error.InvoiceErrorType;
import za.co.digitalplatoon.invoiceservice.service.InvoiceService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/invoices")
public class InvoiceController  {

    public static  final Logger log = LoggerFactory.getLogger(InvoiceController.class);

    @Autowired
    protected  InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<List<Invoice>> viewAllInvoice(){
        List<Invoice> invoices =  invoiceService.findAllInvoices();

        if(invoices.isEmpty()){
            return  new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(invoices, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> viewInvoice(@PathVariable("id") Long invoiceId){

        log.info("retrieve invoice id: {}", invoiceId);

        Optional<Invoice> invoiceOptional = invoiceService.getInvoice(invoiceId);

       if(invoiceOptional.isPresent()){
           return new ResponseEntity(invoiceOptional.get(), HttpStatus.OK);
       }

        log.warn("Invoice with id [] not found", invoiceId);

        return new ResponseEntity(new InvoiceErrorType("Invoice with id "+invoiceId+" not found"), HttpStatus.NOT_FOUND);
    }


    @PostMapping
    public ResponseEntity<?> addInvoice(@Valid @RequestBody Invoice invoice, UriComponentsBuilder uriComponentsBuilder){

        log.info("creating invoice: {}", invoice);

        if(invoice.getId() != null && invoiceService.getInvoice(invoice.getId()).isPresent()){
            log.warn("unable to add invoice. invoice already exist: {}", invoice);
            return new ResponseEntity(new InvoiceErrorType("Unable to create. Invoice exist: "+ invoice), HttpStatus.BAD_REQUEST);
        }

        invoice = invoiceService.saveInvoice(invoice);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uriComponentsBuilder.path("/invoices/{id}").buildAndExpand(invoice.getId()).toUri());

        return new ResponseEntity<String>(httpHeaders, HttpStatus.CREATED);
    }


}
