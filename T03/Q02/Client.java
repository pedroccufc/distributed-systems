import java.io.File;
import java.net.MalformedURLException;
import java.rmi.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    public static void main(String[] args) {
        System.setSecurityManager(new SecurityManager());        
        InterfaceRemota ir = null;
        Scanner scanner = new Scanner(System.in);
        try {            
            System.out.println(Arrays.toString(Naming.list("ServerJM")));
            
            ir = (InterfaceRemota) Naming.lookup("ServerJM");
            
            if(!ir.getStatus().equals("Ok")){
                System.out.println("Servidor não encontrado!");
                System.exit(0);
            }
            
            System.out.println("Servidor encontrado!");
            
            while(true){
            	System.out.println("Exemplo: 03_07_1996");
                System.out.println("------- Calendário -------");
				System.out.println("1 - Listar lembretes");
                System.out.println("2 - Criar novo lembrete");
                System.out.println("3 - Detalhar lembrete");
                System.out.println("4 - Editar lembrete");
                System.out.println("5 - Apagar lembrete");
                System.out.println("6 - Sair");
                
                int opcao = scanner.nextInt();
                
                switch (opcao){
                	// Listar lembretes
                    case 1:
                        System.out.println("----------");
                        for(File file : ir.getListLembretes("LEMBRETES/")){
                            System.out.println(file.getName());
                        }
                        System.out.println("----------");
                        break;
                        
                    // Criar novo lembrete
                    case 2:
                        System.out.println("Digite a data do lembrete: ");
                        String dataLembrete = scanner.next();
                        
                        if (ir.criarLembrete(dataLembrete)) {
                            System.out.println("Lembrete criado!");
                        }
                        System.out.println("Lembrar de: ");
                        String conteudo = scanner.next();
                        ir.editarLembrete(dataLembrete, conteudo);
                        break;
                    
                    // Detalhar lembrete
                    case 3:
                       System.out.println("Digite a data do lembrete: ");
                        String detalhar = scanner.next();
                        
                        if (ir.detalharLembrete(detalhar)) {
                            System.out.println("\n"+"Fim da leitura!");
                        }
                        break;

                    // Editar lembrete
                    case 4:
                        
                        System.out.println("Digite a data do lembrete: ");
                        String editar = scanner.next();
                        
                        if (ir.detalharLembrete(editar)) {
                            System.out.println("\n"+"Fim da leitura!");
                        }else{
                            System.out.println("ERRO NA LEITURA DO ARQUIVO!");
                        }
                        
                        System.out.println("\n"+"Digite o novo lembrete desejado para a data "+editar);
                        String novoConteudo = scanner.next();
                        
                        if (ir.editarLembrete(editar, novoConteudo)) {
                            System.out.println("Lembrete da data "+editar+" editada!");
                        }else{
                            System.out.println("ERRO NA EDIÇÃO DO ARQUIVO!");
                        }
                        break;
                    
                    // Apagar lembrete
                    case 5:
                        System.out.println("Digite a data do lembrete: ");
                        String deletar = scanner.next();
                        if (ir.apagarLembrete(deletar)) {
                            System.out.println("O lembrete da data "+deletar+" foi deletada!");
                        }else{
                            System.out.println("ERRO NA OPERAÇÃO!");
                        }
                        break;

                    // Sair
                    case 6:
                        System.exit(0);
                        break;
                }
            }
        } catch (MalformedURLException | NotBoundException | RemoteException e){
            System.out.println("Server não encontrado");
        }
    }
}