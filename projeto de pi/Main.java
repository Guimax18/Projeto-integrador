import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Classe que representa um funcionário
class Funcionario {
    private int id;
    private String nome;
    private String cargo;
    private String rgf;
    private String rg;
    private String cpf;

    public Funcionario(int id, String nome, String cargo, String rgf, String rg, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.rgf = rgf;
        this.rg = rg;
        this.cpf = cpf;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCargo() {
        return cargo;
    }

    public String getrgf() {
        return rgf;
    }

    public String getrg() {
        return rg;
    }

    public String getcpf() {
        return cpf;
    }
}

// Classe que representa uma empresa solicitante
class Empresa {
    private int id;
    private String CNPJ;
    private String nome;
    private String endereco;
    private String ie;
    private String CEP;

    public Empresa(int id, String CNPJ, String nome, String endereco,  String ie, String CEP) {
        this.id = id;
        this.CNPJ = CNPJ;
        this.nome = nome;
        this.endereco = endereco;
        this.ie = ie;
        this.CEP = CEP;
    }

    public int getId() {
        return id;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getie(){
        return ie;
    }

    public String getCEP(){
        return CEP;
    }
}

// Classe principal que contém a interface gráfica com os menus
public class Main {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/empresa_portaria";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "max123";
    private static List<Funcionario> funcionarios = new ArrayList<>();
    private static List<Empresa> empresas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        conectarBancoDeDados();
        exibirMenuPrincipal();
    }

    private static void conectarBancoDeDados() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Conexão bem-sucedida com o banco de dados!");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
    private static void exibirMenuPrincipal() {
        int opcao;

        do {
            System.out.println("----- MENU PRINCIPAL -----");
            System.out.println("1. Cadastrar funcionário");
            System.out.println("2. Cadastrar empresa solicitante");
            System.out.println("3. Listar funcionários");
            System.out.println("4. Listar empresas solicitantes");
            System.out.println("5. Atualizar funcionário");
            System.out.println("6. Atualizar empresa solicitante");
            System.out.println("7. Excluir funcionário");
            System.out.println("8. Excluir empresa solicitante");
            System.out.println("9. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    cadastrarFuncionario();
                    break;
                case 2:
                    cadastrarEmpresa();
                    break;
                case 3:
                    listarFuncionarios();
                    break;
                case 4:
                    listarEmpresas();
                    break;
                case 5:
                    atualizarFuncionario();
                    break;
                case 6:
                    atualizarEmpresa();
                    break;
                case 7:
                    excluirFuncionario();
                    break;
                case 8:
                    excluirEmpresa();
                    break;
                case 9:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

            System.out.println();
        } while (opcao != 9);
    }

    private static void cadastrarFuncionario() {
        System.out.println("----- CADASTRO DE FUNCIONÁRIO -----");
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();
        System.out.print("RGF: ");
        String rgf = scanner.nextLine();
        System.out.print("RG: ");
        String rg = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO funcionarios (id, nome, cargo) VALUES (?, ?, ?)")) {
            statement.setInt(1, id);
            statement.setString(2, nome);
            statement.setString(3, cargo);
            statement.setString(4, rgf);
            statement.setString(5, rg);
            statement.setString(6, cpf);

            System.out.println("Funcionário cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar funcionário: " + e.getMessage());
        }
    }


    private static void cadastrarEmpresa() {
        System.out.println("----- CADASTRO DE EMPRESA SOLICITANTE -----");
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner
        System.out.print("CNPJ: ");
        String CNPJ = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Ie: ");
        String ie = scanner.nextLine();
        System.out.print("CEP: ");
        String CEP = scanner.nextLine();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO empresas_solicitantes (id, nome, endereco) VALUES (?, ?, ?)")) {
            statement.setInt(1, id);
            statement.setString(2, CNPJ);
            statement.setString(3, nome);
            statement.setString(3, endereco);
            statement.setString(3, ie);
            statement.setString(3, CEP);
            
            System.out.println("Empresa cadastrada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar empresa: " + e.getMessage());
        }
    }


    private static void listarFuncionarios() {
        System.out.println("----- LISTA DE FUNCIONÁRIOS -----");
        if (funcionarios.isEmpty()) {
            System.out.println("Não há funcionários cadastrados.");
        } else {
            for (Funcionario funcionario : funcionarios) {
                System.out.println("ID: " + funcionario.getId());
                System.out.println("Nome: " + funcionario.getNome());
                System.out.println("Cargo: " + funcionario.getCargo());
                System.out.println("RGF: " + funcionario.getrgf());
                System.out.println("RG: " + funcionario.getrg());
                System.out.println("CPF: " + funcionario.getcpf());

                try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM funcionarios");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String cargo = resultSet.getString("cargo");
                String rgf = resultSet.getString("rgf");
                String rg = resultSet.getString("rg");
                String cpf = resultSet.getString("cpf");

                System.out.println("ID: " + id);
                System.out.println("Nome: " + nome);
                System.out.println("Cargo: " + cargo);
                System.out.println("RGF: " + rgf);
                System.out.println("RG: " + rg);
                System.out.println("CPF: " + cpf);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar funcionários: " + e.getMessage());
        }
    }
            }
        }
    

    private static void listarEmpresas() {
        System.out.println("----- LISTA DE EMPRESAS SOLICITANTES -----");
        if (empresas.isEmpty()) {
            System.out.println("Não há empresas cadastradas.");
        } else {
            for (Empresa empresa : empresas) {
                System.out.println("ID: " + empresa.getId());
                System.out.println("Nome: " + empresa.getNome());
                System.out.println("Endereço: " + empresa.getEndereco());
                System.out.println("IE: " + empresa.getie());
                System.out.println("CEP: " + empresa.getCEP());
                System.out.println("CNPJ: " + empresa.getCNPJ());
            }
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM empresas_solicitantes");
            ResultSet resultSet = statement.executeQuery()) {
           while (resultSet.next()) {
               int id = resultSet.getInt("id");
               String CNPJ = resultSet.getString("CNPJ");
               String nome = resultSet.getString("nome");
               String endereco = resultSet.getString("endereco");
               String ie = resultSet.getString("IE");
               String CEP = resultSet.getString("CEP");

               System.out.println("ID: " + id);
               System.out.println("CNPJ: " + CNPJ);
               System.out.println("Nome: " + nome);
               System.out.println("Endereço: " + endereco);
               System.out.println("IE: " + ie);
               System.out.println("CEP: " + CEP);
           }
       } catch (SQLException e) {
           System.out.println("Erro ao listar empresas: " + e.getMessage());
       }
   }
        }

    private static void atualizarFuncionario() {
        System.out.println("----- ATUALIZAÇÃO DE FUNCIONÁRIO -----");
        System.out.print("Digite o ID do funcionário que deseja atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        Funcionario funcionario = buscarFuncionarioPorId(id);
        if (funcionario == null) {
            System.out.println("Funcionário não encontrado.");
            return;
        }

        System.out.print("Novo nome (atual: " + funcionario.getNome() + "): ");
        String novoNome = scanner.nextLine();
        System.out.print("Novo cargo (atual: " + funcionario.getCargo() + "): ");
        String novoCargo = scanner.nextLine();
        System.out.print("Novo RGF (atual: " + funcionario.getrgf() + "): ");
        String novorgf = scanner.nextLine();
        System.out.print("Novo RG (atual: " + funcionario.getrg() + "): ");
        String novorg = scanner.nextLine();
        System.out.print("Novo CPF (atual: " + funcionario.getcpf() + "): ");
        String novocpf = scanner.nextLine();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("UPDATE funcionarios SET nome = ?, cargo = ? WHERE id = ?")) {
            statement.setString(1, novoNome);
            statement.setString(2, novoCargo);
            statement.setInt(3, id);
            statement.setString(4, novorgf);
            statement.setString(5, novorg);
            statement.setString(6, novocpf);

            System.out.println("Funcionário atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar funcionário: " + e.getMessage());
        }
    }

    private static void atualizarEmpresa() {
        System.out.println("----- ATUALIZAÇÃO DE EMPRESA SOLICITANTE -----");
        System.out.print("Digite o ID da empresa que deseja atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        Empresa empresa = buscarEmpresaPorId(id);
        if (empresa == null) {
            System.out.println("Empresa não encontrada.");
            return;
        }
        System.out.print("Novo nome (atual: " + empresa.getCNPJ() + "): ");
        String novoCNPJ = scanner.nextLine();
        System.out.print("Novo nome (atual: " + empresa.getNome() + "): ");
        String novoNome = scanner.nextLine();
        System.out.print("Novo endereço (atual: " + empresa.getEndereco() + "): ");
        String novoEndereco = scanner.nextLine();
        System.out.print("Novo endereço (atual: " + empresa.getie() + "): ");
        String novoie = scanner.nextLine();
        System.out.print("Novo endereço (atual: " + empresa.getCEP() + "): ");
        String novoCEP = scanner.nextLine();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("UPDATE empresas_solicitantes SET nome = ?, endereco = ? WHERE id = ?")) {
            statement.setString(1, novoCNPJ);
            statement.setString(2, novoNome);
            statement.setString(3, novoEndereco);
            statement.setInt(4, id);
            statement.setString(5, novoie);
            statement.setString(6, novoCEP);

            System.out.println("Empresa atualizada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar empresa: " + e.getMessage());
        }
    }

    private static void excluirFuncionario() {
        System.out.println("----- EXCLUSÃO DE FUNCIONÁRIO -----");
        System.out.print("Digite o ID do funcionário que deseja excluir: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        Funcionario funcionario = buscarFuncionarioPorId(id);
        if (funcionario == null) {
            System.out.println("Funcionário não encontrado.");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM funcionarios WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();

            System.out.println("Funcionário excluído com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir funcionário: " + e.getMessage());
        }
    }
    private static void excluirEmpresa() {
        System.out.println("----- EXCLUSÃO DE EMPRESA SOLICITANTE -----");
        System.out.print("Digite o ID da empresa que deseja excluir: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        Empresa empresa = buscarEmpresaPorId(id);
        if (empresa == null) {
            System.out.println("Empresa não encontrada.");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        PreparedStatement statement = connection.prepareStatement("DELETE FROM empresas_solicitantes WHERE id = ?")) {
       statement.setInt(1, id);
       statement.executeUpdate();

       System.out.println("Empresa excluída com sucesso!");
   } catch (SQLException e) {
       System.out.println("Erro ao excluir empresa: " + e.getMessage());
   }
}

    private static Funcionario buscarFuncionarioPorId(int id) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getId() == id) {
                return funcionario;
            }
        }
        return null;
    }

    private static Empresa buscarEmpresaPorId(int id) {
        for (Empresa empresa : empresas) {
            if (empresa.getId() == id) {
                return empresa;
            }
        }
        return null;
    }
}
