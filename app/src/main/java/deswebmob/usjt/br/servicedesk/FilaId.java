package deswebmob.usjt.br.servicedesk;

/**
 * Created by arqdsis on 21/03/2018.
 */

public enum FilaId {
   DESKTOP(1000, "Desktop","7desktops.png"),
   TELEFONIA(1001,"Telefonia", "6telefonia.png");

    public int numero;
    public String nome;
    public String icone;

    FilaId(int numero, String nome, String icone){
        this.numero = numero;
        this.nome = nome;
        this.icone = icone;
    }
}
