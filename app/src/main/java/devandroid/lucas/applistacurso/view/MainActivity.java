package devandroid.lucas.applistacurso.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import devandroid.lucas.applistacurso.R;
import devandroid.lucas.applistacurso.controller.PessoaController;
import devandroid.lucas.applistacurso.model.Pessoa;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    public static final String NOME_PREFERENCES = "pref_listavip";
    PessoaController controller;
    Pessoa pessoa;
    Pessoa outraPessoa;
    EditText editPrimeiroNome;
    EditText editSobreNomeAluno;
    EditText editNomeDoCurso;
    EditText editTelefoneContato;

    Button btnSalvar;
    Button btnLimpar;
    Button btnFinalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences(NOME_PREFERENCES,0);
        SharedPreferences.Editor listaVip = preferences.edit();

        controller = new PessoaController();
        controller.toString();

        pessoa = new Pessoa();
        pessoa.setPrimeiroNome(preferences.getString("primeiroNome",""));
        pessoa.setSobreNome(preferences.getString("sobreNome",""));
        pessoa.setCursoDesejado(preferences.getString("nomeCurso",""));
        pessoa.setTelefoneContato(preferences.getString("telefoneContato",""));

        editPrimeiroNome = findViewById(R.id.editPrimeiroNome);
        editSobreNomeAluno = findViewById(R.id.editSobreNomeAluno);
        editNomeDoCurso = findViewById(R.id.editNomeDoCurso);
        editTelefoneContato = findViewById(R.id.editTelefoneContato);

        editPrimeiroNome.setText(pessoa.getPrimeiroNome());
        editSobreNomeAluno.setText(pessoa.getSobreNome());
        editNomeDoCurso.setText(pessoa.getCursoDesejado());
        editTelefoneContato.setText(pessoa.getTelefoneContato());

        btnSalvar = findViewById(R.id.btnSalvar);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        btnLimpar.setOnClickListener(v -> {
            editPrimeiroNome.setText("");
            editSobreNomeAluno.setText("");
            editNomeDoCurso.setText("");
            editTelefoneContato.setText("");
        });

        btnFinalizar.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this,"Volte sempre!",Toast.LENGTH_LONG).show();
            finish();
        });

        btnSalvar.setOnClickListener(v -> {

            pessoa.setPrimeiroNome(editPrimeiroNome.getText().toString());
            pessoa.setSobreNome(editSobreNomeAluno.getText().toString());
            pessoa.setCursoDesejado(editNomeDoCurso.getText().toString());
            pessoa.setTelefoneContato(editTelefoneContato.getText().toString());

            Toast.makeText(MainActivity.this,"Salvo! "+pessoa.toString(),Toast.LENGTH_LONG).show();

            listaVip.putString("primeiroNome",pessoa.getPrimeiroNome());
            listaVip.putString("sobreNome",pessoa.getSobreNome());
            listaVip.putString("nomeCurso",pessoa.getCursoDesejado());
            listaVip.putString("telefoneContato",pessoa.getTelefoneContato());
            listaVip.apply();

            controller.salvar(pessoa);
        });


        Log.i("POOAndroid","Objeto pessoa: "+pessoa.toString());
    }
}