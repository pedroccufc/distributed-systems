public class Emoji {
/*
   alt 1 -- ☺
    alt 11 -- ♂
    alt 12 -- ♀
    alt 3 -- ♥
    alt 241 -- ± 
*/    
    public String retornaEmoji(String plchave){
        String emoji = plchave;
        if(plchave.contains("feliz")){
            emoji = emoji.replaceAll("feliz", "☺");
        }
        if(plchave.contains("homem")){
            emoji = emoji.replaceAll("homem", "♂");
        }
        if(plchave.contains("mulher")){
            emoji = emoji.replaceAll("mulher", "♀");
        }
        if(plchave.contains("amor")){
            emoji = emoji.replaceAll("amor", "♥");
        }
        if(plchave.contains("mais ou menos")){
            emoji = emoji.replaceAll("mais ou menos", "±");
        }
        return emoji;
    }
}
