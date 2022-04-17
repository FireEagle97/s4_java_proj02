package ewallet.java.proj02.javafx;

import ewallet.java.proj02.WalletController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PaymentPanel {

        private VBox vbPayment;
        private TextField tfCardNumberInput;
        private TextField tfPayCardAmount;
        private TextField tfPayCash;
        private TextField tfAddCash;

        private WalletController walletC;

    public PaymentPanel(WalletController walletC, Label lblCashDisplay) {
        this.walletC = walletC;

        this.vbPayment = new VBox();
        this.tfCardNumberInput = new TextField();
        this.tfPayCardAmount = new TextField();
        this.tfPayCash = new TextField();
        this.tfAddCash = new TextField();
        this.tfPayCardAmount.setPromptText("Amount to pay ($): ");
        this.tfPayCash.setPromptText("Amount to pay ($)");
        this.tfAddCash.setPromptText("Amount to add ($): ");
        this.tfCardNumberInput.setPromptText("Enter Card Number");


        Button btnCardPay = new Button("Pay with Card");
        Button btnCashPay = new Button("Pay with Cash");
        Button btnAddCash = new Button("Add Cash");

        Label lblError = new Label("<Warning text>");
        Label lblNotification = new Label("<Notification text eg. 50% limit reached>");
        HBox hbCardPayment = new HBox(this.tfCardNumberInput, this.tfPayCardAmount, btnCardPay);
        HBox hbCashPayment = new HBox(this.tfPayCash, btnCashPay);
        HBox hbAddCashBox = new HBox(this.tfAddCash, btnAddCash);

        this.vbPayment.getChildren().addAll(lblCashDisplay, hbAddCashBox, hbCardPayment,
                hbCashPayment, lblError, lblNotification);

        btnAddCash.setOnAction(e -> this.walletC.handleAddCash(this));
    }

    public VBox getVbPayment() {
        return this.vbPayment;
    }


    public String getAddCashInput() {
        return this.tfAddCash.getText();
    }


}
