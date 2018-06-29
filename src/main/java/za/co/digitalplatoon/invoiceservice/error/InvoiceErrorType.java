package za.co.digitalplatoon.invoiceservice.error;

public class InvoiceErrorType {
    private String errorMessage;

    public InvoiceErrorType(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
