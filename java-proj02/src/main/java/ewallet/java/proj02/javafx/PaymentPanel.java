package ewallet.java.proj02.javafx;

import ewallet.java.proj02.WalletController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Representing a panel containing all payment fields to make payments within this wallet.
 */
public class PaymentPanel {

        private VBox vbPayment;
        private TextField tfCardNumberInput;
        private TextField tfPayCardAmount;
        private TextField tfPayCash;
        private TextField tfAddCash;
        private Label lblError;
        private WalletController walletC;

    public PaymentPanel(WalletController walletC, Label lblCashDisplay) {
        this.walletC = walletC;
        this.vbPayment = new VBox();
        this.vbPayment.setSpacing(5);
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

        this.lblError = new Label();
        this.lblError.setStyle("-fx-background-color: #ff0000");
        Label lblNotification = new Label("<Notification text eg. 50% limit reached>");
        //Label lblNotification = new Label(CreditCardView.update());
        HBox hbCardPayment = new HBox(this.tfCardNumberInput, this.tfPayCardAmount);
        VBox vbCardPayment = new VBox(hbCardPayment,btnCardPay);
        VBox vbCashPayment = new VBox(this.tfPayCash, btnCashPay);
        VBox vbAddCashBox = new VBox(this.tfAddCash, btnAddCash);
        hbCardPayment.setSpacing(20);
        vbCardPayment.setSpacing(10);
        vbCashPayment.setSpacing(10);
        vbAddCashBox.setSpacing(10);

        this.vbPayment.getChildren().addAll(lblCashDisplay, vbAddCashBox, vbCardPayment,
                vbCashPayment, lblError, lblNotification);

        btnAddCash.setOnAction(evt  -> {
            try {
                this.walletC.handleAddCash(this);
            } catch (NumberFormatException exc) {
                setErrorMessage("Invalid input(s) given");
            }

        });
        btnCashPay.setOnAction(evt -> {
            try {
                this.walletC.handlePayWithCash(this);
            } catch (NumberFormatException exc) {
                setErrorMessage("Invalid input(s) given");
            }
        });
        btnCardPay.setOnAction(evt -> {
            try {
                this.walletC.handlePayWithCard(this);
            } catch (NumberFormatException exc) {
                setErrorMessage("Invalid input(s) given");
            }
        });
    }

    public VBox getVbPayment() {
        return this.vbPayment;
    }

    public void setErrorMessage(String message) {
        this.lblError.setText(message);
    }

    public String getAddCashInput() {
        return this.tfAddCash.getText();
    }

    public String getPayCashInput() {
        return this.tfPayCash.getText();
    }

    public String getPayCardInput() {
        return this.tfPayCardAmount.getText();
    }

    public String getCardNumberInput() {
        return  this.tfCardNumberInput.getText();
    }

}
