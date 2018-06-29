package za.co.digitalplatoon.invoiceservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.digitalplatoon.invoiceservice.domain.Invoice;

@Repository
public interface InvoiceRepository  extends JpaRepository<Invoice, Long> {
}
