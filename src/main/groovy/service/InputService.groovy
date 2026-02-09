package service

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Scanner

class InputService {
    private Scanner scanner

    InputService() {
        this.scanner = new Scanner(System.in)
    }


    String lerTexto(String mensagem) {
        print mensagem + ": "
        return scanner.nextLine()
    }


    int lerInteiro(String mensagem) {
        while (true) {
            try {
                print mensagem + ": "
                return Integer.parseInt(scanner.nextLine())
            } catch (NumberFormatException e) {
                println "❌ Erro: Digite um número válido."
            }
        }
    }


    LocalDate lerData(String mensagem) {
        while (true) {
            try {
                String input = lerTexto(mensagem + " (AAAA-MM-DD)")
                return LocalDate.parse(input)
            } catch (DateTimeParseException e) {
                println "❌ Erro: Data inválida. Use o formato Ano-Mês-Dia (ex: 1990-12-31)."
            }
        }
    }


    List<String> lerListaSeparadaPorVirgula(String mensagem) {
        String input = lerTexto(mensagem + " (separe por vírgula)")
        if (input.trim().isEmpty()) return []
        return input.split(",").collect { it.trim() }
    }
}