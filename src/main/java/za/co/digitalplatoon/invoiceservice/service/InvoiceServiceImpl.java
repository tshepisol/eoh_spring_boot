package za.co.digitalplatoon.invoiceservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.digitalplatoon.invoiceservice.dao.InvoiceRepository;
import za.co.digitalplatoon.invoiceservice.domain.Invoice;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;


    @Override
    public List<Invoice> findAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public Optional<Invoice> getInvoice(Long invoiceId) {
        return invoiceRepository.findById(invoiceId);
    }

    @Override
    public Invoice saveInvoice(Invoice invoice) {

        return  invoiceRepository.save(invoice);
    }
}
