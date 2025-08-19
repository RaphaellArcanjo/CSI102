import model.BolsaMonitoria;
import service.CadastroService;
import service.MonitoriaService;
import service.RegraDeNegocioException;
import service.RelatorioService;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static final CadastroService cadastroService = new CadastroService();
    private static final MonitoriaService monitoriaService = new MonitoriaService();
    private static final RelatorioService relatorioService = new RelatorioService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int opcao = -1;
        while (opcao != 0) {
            exibirMenu();
            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consome a quebra de linha

                switch (opcao) {
                    case 1:
                        cadastrarDepartamento();
                        break;
                    case 2:
                        cadastrarDisciplina();
                        break;
                    case 3:
                        cadastrarProfessor();
                        break;
                    case 4:
                        cadastrarAluno();
                        break;
                    case 5:
                        solicitarBolsa();
                        break;
                    case 6:
                        cadastrarHorarioAtendimento();
                        break;
                    case 7:
                        consultarMonitoriaPorAluno();
                        break;
                    case 8:
                        emitirRelatorioHorarios();
                        break;
                    case 9:
                        emitirRelatorioAlunoProfessor();
                        break;
                    case 0:
                        System.out.println("Saindo do sistema... Até logo!");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Por favor, digite um número válido para a opção.");
                scanner.nextLine(); // Limpa o buffer do scanner
                opcao = -1; // Reseta a opção para continuar no loop
            }
            pressioneEnterParaContinuar();
        }
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n--- Sistema de Gestão de Monitorias (ICEA/UFOP) ---");
        System.out.println("=============== MENU DO ADMINISTRADOR ===============");
        System.out.println("1. Cadastrar Departamento");
        System.out.println("2. Cadastrar Disciplina");
        System.out.println("3. Cadastrar Professor");
        System.out.println("4. Cadastrar Aluno");
        System.out.println("-----------------------------------------------------");
        System.out.println("5. Solicitar Bolsa de Monitoria");
        System.out.println("6. Cadastrar Horário de Atendimento");
        System.out.println("7. Consultar Monitoria por Aluno");
        System.out.println("-----------------------------------------------------");
        System.out.println("8. Relatório: Horários por Disciplina");
        System.out.println("9. Relatório: Aluno/Professor por Disciplina");
        System.out.println("-----------------------------------------------------");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarDepartamento() {
        System.out.println("\n--- Cadastro de Departamento ---");
        System.out.print("Nome do Departamento: ");
        String nome = scanner.nextLine();
        System.out.print("Sigla do Departamento: ");
        String sigla = scanner.nextLine();
        cadastroService.cadastrarDepartamento(nome, sigla);
        System.out.println("Departamento '" + sigla + "' cadastrado com sucesso!");
    }

    private static void cadastrarDisciplina() {
        System.out.println("\n--- Cadastro de Disciplina ---");
        try {
            System.out.print("Código da Disciplina: ");
            String codigo = scanner.nextLine();
            System.out.print("Nome da Disciplina: ");
            String nome = scanner.nextLine();
            System.out.print("Carga Horária: ");
            int cargaHoraria = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Sigla do Departamento: ");
            String siglaDepto = scanner.nextLine();
            cadastroService.cadastrarDisciplina(codigo, nome, cargaHoraria, siglaDepto);
            System.out.println("Disciplina '" + nome + "' cadastrada com sucesso!");
        } catch (RegraDeNegocioException e) {
            System.out.println("Erro no cadastro: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Erro: A carga horária deve ser um número inteiro.");
            scanner.nextLine(); // Limpa o buffer
        }
    }

    private static void cadastrarProfessor() {
        System.out.println("\n--- Cadastro de Professor ---");
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("SIAPE: ");
            String siape = scanner.nextLine();
            System.out.print("Sigla do Departamento: ");
            String siglaDepto = scanner.nextLine();
            cadastroService.cadastrarProfessor(nome, email, cpf, siape, siglaDepto);
            System.out.println("Professor '" + nome + "' cadastrado com sucesso!");
        } catch (RegraDeNegocioException e) {
            System.out.println("Erro no cadastro: " + e.getMessage());
        }
    }

    private static void cadastrarAluno() {
        System.out.println("\n--- Cadastro de Aluno ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();
        System.out.print("Curso: ");
        String curso = scanner.nextLine();
        cadastroService.cadastrarAluno(nome, email, cpf, matricula, curso);
        System.out.println("Aluno '" + nome + "' cadastrado com sucesso!");
    }

    private static void solicitarBolsa() {
        System.out.println("\n--- Solicitar Bolsa de Monitoria ---");
        try {
            System.out.print("Código da Disciplina: ");
            String codDisciplina = scanner.nextLine();
            System.out.print("SIAPE do Professor Orientador: ");
            String siape = scanner.nextLine();
            System.out.print("Matrícula do Aluno Monitor: ");
            String matricula = scanner.nextLine();
            System.out.print("Tipo da Bolsa (Remunerada/Voluntaria): ");
            String tipoBolsa = scanner.nextLine();
            System.out.print("Semestre (ex: 2025/1): ");
            String semestre = scanner.nextLine();
            monitoriaService.solicitarBolsa(codDisciplina, siape, matricula, tipoBolsa, semestre);
            System.out.println("Bolsa solicitada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao solicitar bolsa: " + e.getMessage());
        }
    }

    private static void cadastrarHorarioAtendimento() {
        System.out.println("\n--- Cadastrar Horário de Atendimento ---");
        try {
            System.out.print("Código da Disciplina da monitoria: ");
            String codDisciplina = scanner.nextLine();
            System.out.print("Horário de Atendimento (ex: Seg 14h-16h): ");
            String horario = scanner.nextLine();
            System.out.print("Local de Atendimento: ");
            String local = scanner.nextLine();
            monitoriaService.cadastrarHorarioAtendimento(codDisciplina, horario, local);
            System.out.println("Horário cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar horário: " + e.getMessage());
        }
    }

    private static void consultarMonitoriaPorAluno() {
        System.out.println("\n--- Consultar Monitoria por Aluno ---");
        System.out.print("Digite a matrícula do aluno: ");
        String matricula = scanner.nextLine();
        Optional<BolsaMonitoria> bolsaOpt = monitoriaService.consultarMonitoriaPorAluno(matricula);
        if (bolsaOpt.isPresent()) {
            System.out.println("Monitoria encontrada:");
            System.out.println(bolsaOpt.get().toString());
        } else {
            System.out.println("Nenhuma monitoria encontrada para o aluno com a matrícula '" + matricula + "'.");
        }
    }

    private static void emitirRelatorioHorarios() {
        System.out.println(relatorioService.gerarRelatorioHorariosPorDisciplina());
    }

    private static void emitirRelatorioAlunoProfessor() {
        System.out.println(relatorioService.gerarRelatorioAlunoProfessorPorDisciplina());
    }

    private static void pressioneEnterParaContinuar() {
        System.out.println("\nPressione Enter para continuar...");
        try {
            System.in.read();
            // Limpa o restante do buffer, caso algo tenha sido digitado antes do Enter
            while (System.in.available() > 0) {
                System.in.read();
            }
        } catch (Exception e) {
            // Ignora a exceção
        }
    }



}
