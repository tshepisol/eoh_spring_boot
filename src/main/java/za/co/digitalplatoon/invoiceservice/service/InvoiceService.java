package za.co.digitalplatoon.invoiceservice.service;

import za.co.digitalplatoon.invoiceservice.domain.Invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {

    List<Invoice> findAllInvoices();

    Optional<Invoice> getInvoice(Long invoiceId);

    Invoice saveInvoice(Invoice invoice);

}
