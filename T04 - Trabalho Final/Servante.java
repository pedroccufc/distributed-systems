import java.io.*;
import java.rmi.*;
import org.json.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Servante implements InterfaceRemota {

	public File[] listTexts(String directory) throws RemoteException {
		File files = new File(directory);
		File listFiles[] = files.listFiles();
		return listFiles;
	}

	public void createText(JSONObject json) throws RemoteException {

		String titulo = json.getString("titulo");
		String autor = json.getString("autor");
		String conteudo = json.getString("conteudo");
		String data = json.getString("data");

		String texto = titulo+".txt";
		File file = new File("textos/"+texto);

		try{
			file.createNewFile();
		}catch(IOException e){
			System.out.println("Erro na criação do documeto!");
		}

		try{
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("titulo: "+titulo);
			bw.newLine();
			bw.write("autor: "+autor);
			bw.newLine();
			bw.write("conteudo: "+conteudo);
			bw.newLine();
			bw.write("data: "+data);
			bw.flush();
			bw.close();
		}catch(IOException e){
			System.out.println("Erro na edição do texto!");
		}
	}

	public void editText(JSONObject json) throws RemoteException {

		String titulo = json.getString("titulo");
		String autor = json.getString("autor");
		String conteudo = json.getString("conteudo");
		String data = json.getString("data");
		String chave = json.getString("chave");
		String valor = json.getString("valor");

		String texto = titulo+".txt";
		File file = new File("textos/"+texto);

		System.out.println(chave);

		if(chave.equals("titulo")){
			Date dataAtual = new Date(System.currentTimeMillis());
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String dataFormatada = dateFormat.format(dataAtual);
			try{
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("titulo: "+valor);
				bw.newLine();
				bw.write("autor: "+autor);
				bw.newLine();
				bw.write("conteúdo: "+conteudo);
				bw.newLine();
				bw.write("data: "+dataFormatada);
				bw.flush();
				bw.close();
			}catch(IOException e){
				System.out.println("Erro na edição do texto!");
			}
			file.renameTo(new File("textos/"+valor+".txt"));
		}else if(chave.equals("autor")){
			Date dataAtual = new Date(System.currentTimeMillis());
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String dataFormatada = dateFormat.format(dataAtual);
			try{
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("titulo: "+titulo);
				bw.newLine();
				bw.write("autor: "+valor);
				bw.newLine();
				bw.write("conteudo: "+conteudo);
				bw.newLine();
				bw.write("data: "+dataFormatada);
				bw.flush();
				bw.close();
			}catch(IOException e){
				System.out.println("Erro na edição do texto!");
			}
		}else if(chave.equals("conteudo")){
			Date dataAtual = new Date(System.currentTimeMillis());
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String dataFormatada = dateFormat.format(dataAtual);
			try{
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("titulo: "+titulo);
				bw.newLine();
				bw.write("autor: "+autor);
				bw.newLine();
				bw.write("conteudo: "+valor);
				bw.newLine();
				bw.write("data: "+dataFormatada);
				bw.flush();
				bw.close();
			}catch(IOException e){
				System.out.println("Erro na edição do texto!");
			}
		}else{
			//System.out.println(chave);
			System.out.println("ERROR!");
		}
	}

	public String infoText(JSONObject json) throws RemoteException {
		String titulo = json.getString("titulo");
		File file = new File("textos/"+titulo+".txt");
		String linha = "info:\n";
		if(file.exists()){
			try{
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				while(br.ready()){
					linha = linha + br.readLine() + "\n";
				}
				br.close();
				fr.close();
			}catch(IOException e){
				System.out.println("Ei mah, deu certo não ver as info do texto! flw");
			}
		}
		return linha;
	}

	/**
	*	Método para remover arquivo
	**/
	public void deleteText(JSONObject json) throws RemoteException {
		String titulo = json.getString("titulo");
		String texto = "textos/"+titulo+".txt";
		File file = new File(texto);

		if(file.exists()){
			file.delete();
		}else{
			System.out.println("O texto não existe!");
		}
	}
}