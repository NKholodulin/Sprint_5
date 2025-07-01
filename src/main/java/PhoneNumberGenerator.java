import java.util.Random;

public class PhoneNumberGenerator {
    public String generatePhoneNumber() {
        Random rand = new Random();

        // Выбираем, начинаем ли с '8' или '+7'
        boolean startWithPlusSeven = rand.nextBoolean();

        String phoneNumber;

        if (startWithPlusSeven) {
            // Начинается с '+7'
            StringBuilder numberBuilder = new StringBuilder("+7");
            // Генерируем 10 случайных цифр
            for (int i = 0; i < 10; i++) {
                int digit = rand.nextInt(10);
                numberBuilder.append(digit);
            }
            phoneNumber = numberBuilder.toString();
        } else {
            // Начинается с '8'
            StringBuilder numberBuilder = new StringBuilder("8");
            // Генерируем 10 случайных цифр
            for (int i = 0; i < 10; i++) {
                int digit = rand.nextInt(10);
                numberBuilder.append(digit);
            }
            phoneNumber = numberBuilder.toString();
        }
        return phoneNumber;
    }
}