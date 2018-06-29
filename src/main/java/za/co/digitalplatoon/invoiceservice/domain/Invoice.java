package za.co.digitalplatoon.invoiceservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "invoice")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    @NotBlank(message = "Client information required")
    private String client;

    @NotNull(message = "VAT registration required")
    private Long vatReg;

    @NotNull(message = "Invoice Date required")
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date invoiceDate;


    @Valid
    @NotNull(message = "Line Item required")
    @Size(min = 1, message = "Atleast 1 lineItem required")

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "invoice_id")
    private List<LineItem> lineItems;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Long getVatReg() {
        return vatReg;
    }

    public void setVatReg(Long vatReg) {
        this.vatReg = vatReg;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    //TODO get further details on how method should be implemented
    @JsonIgnore
    public BigDecimal getSubTotal(){
        throw new RuntimeException("No implementation");
    }

    //TODO get further details on how method should be implemented
    @JsonIgnore
    public BigDecimal getTotal(){
        throw new RuntimeException("No implementation");
    }

    //TODO get further details on how method should be implemented
    @JsonIgnore
    public BigDecimal getVat(){
        throw new RuntimeException("No implementation");
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", client='" + client + '\'' +
                ", vatReg=" + vatReg +
                ", invoiceDate=" + invoiceDate +
                '}';
    }
}
