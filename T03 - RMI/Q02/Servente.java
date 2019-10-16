import java.io.*;
import java.nio.channels.FileChannel;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servente extends UnicastRemoteObject implements InterfaceRemota {

    public Servente() throws RemoteException{
        super();
    }

    public String getStatus() throws RemoteException {
        return "Ok";
    }

    // ########################## 1 - Listar lembretes ######################################
    public File[] getListLembretes(String name) throws RemoteException {
        File files = new File(name);
        File listFiles[] = files.listFiles();
        return listFiles;
    }
    
    // ########################## 2 - Criar novo lembrete ######################################
    public boolean criarLembrete(String name) throws RemoteException {
        boolean ret = false;
        String arquivo = name+".txt";
        File files = new File("LEMBRETES/"+arquivo);
       
        try {
            files.createNewFile();
            ret = true;
        } catch (IOException e) {
            System.out.println("Erro na criação do lembrete da data "+arquivo);
        }    
        return ret;
    }

    // ########################## 3 - Detalhar lembrete ######################################
    public boolean detalharLembrete(String name) throws RemoteException {
        boolean ret = false;
            String arquivo = name+".txt";
            File teste = new File("LEMBRETES/"+arquivo);
            if (teste.exists()) {
                try {
                    FileReader fr = new FileReader(teste);
                    BufferedReader br = new BufferedReader(fr);
                    while (br.ready()) {                        
                        String linha = br.readLine();
                        System.out.println(linha);
                    }
                    br.close();
                    fr.close();
                    ret = true;
                } catch (IOException ex) {
                    System.out.println("Erro na leitura do lembrete da data "+arquivo);
                }
            }else{
            	System.out.println("O lembrete da data "+arquivo+" não existe!");
            }
        return ret;    
    }

    // ########################## 4 - Editar lembrete ######################################
    public boolean editarLembrete(String name, String conteudo) throws RemoteException {
        boolean ret = false;

        String arquivo = name+".txt";
        String cont = conteudo;
        File teste = new File("LEMBRETES/"+arquivo);

        if (teste.exists()) {
            try {
                FileWriter fw = new FileWriter(teste);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(cont);
                bw.newLine();
                
                bw.close();
                fw.close();

                ret = true;
            } catch (IOException ex) {
                System.out.println("Erro na edição do lembrete da data "+arquivo);
            }
        }else{
            System.out.println("O lembrete da data "+arquivo+" não existe!");
        }
    	return ret;     
    }

    // ########################## 5 - Apagar lembrete #####################################
    public boolean apagarLembrete(String name) throws RemoteException {
        boolean ret = false;
        String arquivo = name+".txt";
        File files = new File("LEMBRETES/"+arquivo);
        
            if (files.exists()) {
                files.delete();
                ret = true;  
            }else{
                System.out.println("O lembrete da data "+arquivo+" não existe!");
            }
          
        return ret;    
    }

}