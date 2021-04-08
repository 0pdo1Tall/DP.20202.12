package entity.payment;

import org.w3c.dom.CDATASection;

public class CreditCardFactory extends PaymentMethodFactory{

    private static CreditCardFactory creditCardFactory;
    private CreditCardFactory(){};
    public static CreditCardFactory getInstance(){
        if(creditCardFactory == null) creditCardFactory = new CreditCardFactory();
        return creditCardFactory;
    }

    public CreditCard creatMethod(String cardCode, String owner, String dateExpired, int cvvCode)
    {
        return new CreditCard(cardCode,owner, dateExpired,cvvCode);
    }

    // Credit Card Method Here
}
