import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CheckoutServiceTest {
    private CheckoutService checkoutService;

    @BeforeEach
    public void createCheckout() {
        checkoutService = new CheckoutService();
    }

    @Test
    public void Test1() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            checkoutService.generateRentalAgreement("JAKR", LocalDate.parse("2015-09-03"), 5, 101);
        });
    }

    @Test
    public void Test2() {
        RentalAgreement rentalAgreement =
                checkoutService.generateRentalAgreement("LADW", LocalDate.parse("2020-07-02"), 3, 10);
        rentalAgreement.PrintAgreement();

        Assertions.assertEquals(BigDecimal.valueOf(3.98), rentalAgreement.getPreDiscountCharge());
        Assertions.assertEquals(BigDecimal.valueOf(3.58), rentalAgreement.getFinalCharge());
    }

    @Test
    public void Test3() {
        RentalAgreement rentalAgreement =
                checkoutService.generateRentalAgreement("CHNS", LocalDate.parse("2015-07-02"), 5, 25);
        rentalAgreement.PrintAgreement();

        Assertions.assertEquals(BigDecimal.valueOf(4.47), rentalAgreement.getPreDiscountCharge());
        Assertions.assertEquals(BigDecimal.valueOf(3.35), rentalAgreement.getFinalCharge());
    }

    @Test
    public void Test4() {
        RentalAgreement rentalAgreement =
                checkoutService.generateRentalAgreement("JAKD", LocalDate.parse("2015-09-03"), 6, 0);
        rentalAgreement.PrintAgreement();

        Assertions.assertEquals(BigDecimal.valueOf(8.97), rentalAgreement.getPreDiscountCharge());
        Assertions.assertEquals(BigDecimal.valueOf(8.97), rentalAgreement.getFinalCharge());
    }

    @Test
    public void Test5() {
        RentalAgreement rentalAgreement =
                checkoutService.generateRentalAgreement("JAKR", LocalDate.parse("2015-07-02"), 9, 0);

        rentalAgreement.PrintAgreement();

        Assertions.assertEquals(BigDecimal.valueOf(14.95), rentalAgreement.getPreDiscountCharge());
        Assertions.assertEquals(BigDecimal.valueOf(14.95), rentalAgreement.getFinalCharge());
    }


    @Test
    public void Test6() {
        RentalAgreement rentalAgreement =
                checkoutService.generateRentalAgreement("JAKR", LocalDate.parse("2020-07-02"), 4, 50);

        rentalAgreement.PrintAgreement();

        Assertions.assertEquals(BigDecimal.valueOf(2.99), rentalAgreement.getPreDiscountCharge());
        Assertions.assertEquals(BigDecimal.valueOf(1.49), rentalAgreement.getFinalCharge());
    }

}
