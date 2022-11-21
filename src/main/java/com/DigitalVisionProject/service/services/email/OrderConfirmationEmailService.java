package com.DigitalVisionProject.service.services.email;

import com.DigitalVisionProject.service.dtos.PaymentDTO;
import com.DigitalVisionProject.service.models.*;
import com.DigitalVisionProject.service.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderConfirmationEmailService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderedProductRepository orderedProductRepository;
    private final EmailSenderService emailSenderService;
    private final PaymentRepository paymentRepository;

    private final UserRepository userRepository;

    @Autowired
    public OrderConfirmationEmailService(ProductRepository productRepository,
                                         OrderRepository orderRepository,
                                         OrderedProductRepository orderedProductRepository,
                                         EmailSenderService emailSenderService,
                                         PaymentRepository paymentRepository,
                                         UserRepository userRepository){
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderedProductRepository = orderedProductRepository;
        this.emailSenderService = emailSenderService;
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
    }

    public void sendOrderConfirmationEmail(PaymentDTO payment){
        String body = buildOrderConfirmationEmailBody(payment);
        String subject = "Digital Vision: Order Confirmation";
        User user = userRepository.getReferenceById(payment.getUserId());
        emailSenderService.send(subject, user.getEmail(), body);
    }

    public String buildOrderConfirmationEmailBody(PaymentDTO payment){
        String body = "";
        String header = addHeaderToEmailBody();
        List<OrderedProduct> orderedProducts = new ArrayList<>();
        payment.getOrderIds().forEach(
                orderId -> {
                    Order order = orderRepository.getReferenceById(orderId);
                    OrderedProduct orderedProduct = orderedProductRepository.getReferenceById(order.getOrderProductId());
                    orderedProducts.add(orderedProduct);
                });
        User user = userRepository.getReferenceById(payment.getUserId());
        Payment actualPayment = paymentRepository.getReferenceById(payment.getPaymentId());
        String invoiceDetails = addInvoiceNoAndDateOfPurchaseIoToEmailBody(actualPayment.getInvoiceNumber(),
                actualPayment.getPaymentDate(), user.getName());
        String productsInInvoice = addProductsIoInvoiceToEmailBody(orderedProducts, payment.getDeliveryCharge());
        String totalAmount = addTotalAmountInInvoiceToEmailBody(payment.getTotal());
        return body + header + invoiceDetails + productsInInvoice + totalAmount;
    }

    public String addHeaderToEmailBody(){
        return "<div style=\"margin: 0; padding: 0; font-family: Helvetica, Arial, sans-serif;box-sizing: border-box;font-size: 14px;\">\n" +
                "<table style=\"background-color: #f6f6f6; width: 100%;\">\n" +
                "<tbody>\n" +"<tr>\n" +
                "<td>\n" +"</td>\n" +
                "<td style=\"display: block !important;max-width: 600px !important;margin: 0 auto !important;clear: both !important;\" width=\"600\">\n" +
                "<div style=\"max-width: 600px;margin: 0 auto;display: block;padding: 20px;\">\n" +
                "<table style=\"background: #fff; border: 1px solid #e9e9e9;border-radius: 3px;\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "<tbody>\n" +"<tr>\n" +
                "<td style=\"padding: 20px; text-align: center;vertical-align: top;\">\n" +
                "<table style=\"vertical-align: top;\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "<tbody>\n" +"<tr>\n" +
                "<td style=\"padding: 0 0 20px;vertical-align: top;\">\n" +
                "<h4>\n" +"Thank you for your purchase at Digital Vision."+"</h2>\n" +
                "<h4>\n" +"Your order has been confirmed!"+"</h4>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td style=\"padding: 0 0 20px;vertical-align: top;\">\n" +
                "<table style=\"margin: 40px auto; text-align: left; width: 80%;\">\n" +
                "<tbody>\n" +"<tr>\n";
    }

    public String addInvoiceNoAndDateOfPurchaseIoToEmailBody(UUID invoiceNumber, LocalDate date, String name){
        return "<td>\n"+ name +"<br>\n"+ invoiceNumber +"<br>\n" +date+"</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td style=\"vertical-align: top;\">\n" +
                "<table style=\"width: 100%; border-top: #eee 1px solid; border-top: 2px solid #333; border-bottom: 2px solid #333;font-weight: 700;\" cellpadding=\"0\" cellspacing=\"0\">\n"
            + "<tbody>\n";
    }

    public String addProductsIoInvoiceToEmailBody(List<OrderedProduct> orderedProducts, double deliveryFee){
        String productRecords = "";
        for (OrderedProduct orderedProduct: orderedProducts) {
            Product product = productRepository.getReferenceById(orderedProduct.getProductId());
            String productRecord = "<tr>\n" +
                    "<td>\n" + product.getTitle() +"</td>\n" +
                    "<td style=\"text-align: right;vertical-align: top;\">\n" +"$"+ product.getPrice()+"</td>\n" +
                    "</tr>\n";
            return productRecords + productRecord;
        }
        String deliveryFeeRecord =  "<tr>\n" +
                "<td>\n" + "Delivery Fee" +"</td>\n" +
                "<td style=\"text-align: right;vertical-align: top;\">\n" +"$"+ deliveryFee+"</td>\n" +
                "</tr>\n";
        return productRecords + deliveryFeeRecord;
    }

    public String addTotalAmountInInvoiceToEmailBody(double totalAmount){

        return "<tr style=\"border-top: 2px solid #333;border-bottom: 2px solid #333;font-weight: 700;\">\n" +
                "<td style=\"text-align: right;vertical-align: top;\" width=\"80%\">\n" +"Total"+"</td>\n" +
                "<td style=\"text-align: right;vertical-align: top;\">\n" +"$"+ totalAmount+"</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +"</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +"</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +"</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</div>\n" +
                "</td>\n" +
                "<td>\n" +"</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +"</table>\n" +
                "</div>\n" ;
    }


}
