package deswebmob.usjt.br.servicedesk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ListarChamadosActivity extends Activity {
    public static final String DESCRICAO = "br.usjt.deswebmob.servicedesk.descricao";

    private List<Chamado> lista;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_chamados);

        activity = this;
        Intent intent = getIntent();
        String chave = intent.getStringExtra(MainActivity.NOME);

        lista = buscaChamados(chave);
        /*ArrayAdapter <Chamado> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);*/
        ChamadoAdapter<Chamado> adapter =
                new ChamadoAdapter<>(this, lista);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent (ListarChamadosActivity.this, DetalheChamadoActivity.class);
                intent.putExtra(DESCRICAO, lista.get(position));
                startActivity(intent);
            }
        });
    }

    public List<Chamado> geraListaChamados(){
        List<Chamado> lista = new LinkedList<>();
        Chamado c1 = new Chamado(1, new Date(), null, "aberto",
                "Desktops: Computador da secretária quebrado.",
                    new Fila(FilaId.DESKTOP.numero,
                            FilaId.DESKTOP.icone,
                            FilaId.DESKTOP.nome));
        lista.add(c1);

        Chamado c2 = new Chamado(2, new Date(), new Date(),"aberto",
                "Telefonia: Telefone não funciona.",
                new Fila(FilaId.TELEFONIA.numero,
                        FilaId.TELEFONIA.icone,
                        FilaId.TELEFONIA.nome));
        lista.add(c2);

        /*
        lista.add("Redes: Manutenção no proxy.");
        lista.add("Servidores: Lentidão generalizada.");
        lista.add("Novos Projetos: CRM");
        lista.add("Manutenção Sistema ERP: atualizar versão.");
        lista.add("Novos Projetos: Rede MPLS");
        lista.add("Manutenção Sistema de Vendas: incluir pipeline.");
        lista.add("Manutenção Sistema ERP: erro contábil");
        lista.add("Novos Projetos: Gestão de Orçamento");
        lista.add("Novos Projetos: Big Data");
        lista.add("Manoel de Barros");
        lista.add("Redes: Internet com lentidão");
        lista.add("Novos Projetos: Chatbot");
        lista.add("Desktops: troca de senha");
        lista.add("Desktops: falha no Windows");
        lista.add("Novos Projetos: ITIL V3");
        lista.add("Telefonia: liberar celular");
        lista.add("Telefonia: mover ramal");
        lista.add("Redes: ponto com defeito");
        lista.add("Novos Projetos: ferramenta EMM");
        */
        return lista;
    }

    public List <Chamado> buscaChamados (String chave){
        List <Chamado> lista = geraListaChamados();
        if (chave == null || chave.length() <=0)
            return lista;

        List <Chamado> subLista = new ArrayList<>();
        for (Chamado chamado:lista){
            if(chamado.getDescricao().toLowerCase().contains(chave.toLowerCase())){
                subLista.add(chamado);
            }
        }
        return subLista;
    }
}
