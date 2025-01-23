package it.unisa.uniclass.common.security;
import java.security.SecureRandom;

public class PasswordGenerator {

    public static String generatePassword(int length) {
        if (length < 8) {
            throw new IllegalArgumentException("La lunghezza della password deve essere almeno 8 caratteri.");
        }

        // Caratteri suddivisi per tipo
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialChars = "@#$€&%";

        // Insieme completo di caratteri
        String allChars = upperCase + lowerCase + digits + specialChars;

        // StringBuilder per la password
        StringBuilder password = new StringBuilder();

        // SecureRandom per generare numeri casuali sicuri
        SecureRandom random = new SecureRandom();

        // Garantisce che ci sia almeno un carattere di ogni tipo
        password.append(upperCase.charAt(random.nextInt(upperCase.length())));
        password.append(lowerCase.charAt(random.nextInt(lowerCase.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));
        password.append(specialChars.charAt(random.nextInt(specialChars.length())));

        // Riempie il resto della password con caratteri casuali
        for (int i = 4; i < length; i++) {
            password.append(allChars.charAt(random.nextInt(allChars.length())));
        }

        // Mescola i caratteri per evitare una struttura prevedibile
        return shuffleString(password.toString(), random);
    }

    // Funzione per mescolare una stringa
    private static String shuffleString(String input, SecureRandom random) {
        char[] characters = input.toCharArray();
        for (int i = characters.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = characters[i];
            characters[i] = characters[j];
            characters[j] = temp;
        }
        return new String(characters);
    }

    // Metodo main per testare la funzione
    public static void main(String[] args) {
        String password = generatePassword(12); // Genera una password di 12 caratteri
        System.out.println("Password generata: " + password);
    }
}