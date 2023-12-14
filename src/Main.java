import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите арифметическое выражение: ");
        String input = scanner.nextLine();
        try {
            String result = calc(input);
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static String calc(String input) throws Exception {
        Map<Character, Integer> romanNumerals = new HashMap<>();
        romanNumerals.put('I', 1);
        romanNumerals.put('V', 5);
        romanNumerals.put('X', 10);
        romanNumerals.put('L', 50);
        romanNumerals.put('C', 100);

        boolean isRoman = false;
        if (input.contains("I") || input.contains("V") || input.contains("X") || input.contains("L") || input.contains("C")) {
            isRoman = true;
        }

        String[] parts = input.split(" ");
        if (parts.length != 3) {
            throw new Exception("Некорректное выражение");
        }
        String operator = parts[1];

        int num1;
        int num2;
        try {
            num1 = Integer.parseInt(parts[0]);
            num2 = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            throw new Exception("Некорректные числа");
        }

        if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) {
            throw new Exception("Введенные числа должны быть от 1 до 10");
        }

        int result;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            default:
                throw new Exception("Некорректный оператор");
        }

        if (isRoman) {
            if (result <= 0) {
                throw new Exception("Результат работы с римскими числами должен быть положительным");
            }
            return convertToRoman(result);
        }
        return String.valueOf(result);
    }

    public static String convertToRoman(int number) {
        Map<Integer, String> romanNumerals = new HashMap<>();
        romanNumerals.put(1, "I");
        romanNumerals.put(4, "IV");
        romanNumerals.put(5, "V");
        romanNumerals.put(9, "IX");
        romanNumerals.put(10, "X");
        romanNumerals.put(40, "XL");
        romanNumerals.put(50, "L");
        romanNumerals.put(90, "XC");
        romanNumerals.put(100, "C");

        int[] arabicNumbers = {100, 90, 50, 40, 10, 9, 5, 4, 1};

        StringBuilder roman = new StringBuilder();

        for (int i = 0; i < arabicNumbers.length; i++) {
            int arabic = arabicNumbers[i];
            while (number >= arabic) {
                roman.append(romanNumerals.get(arabic));
                number -= arabic;
            }
        }

        return roman.toString();
    }
}