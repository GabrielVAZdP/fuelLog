package com.example.fuellog.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.fuellog.Classes.Modelos.Abastecimento;
import com.example.fuellog.Classes.Modelos.Consumo;
import com.example.fuellog.R;
import com.example.fuellog.databinding.FragmentAbastecimentoBinding;

public class RegistrarAbastecimento extends Fragment {

    private FragmentAbastecimentoBinding binding;
    private Abastecimento abastecimento;
    private Consumo consumo;

    private EditText editTextKm;
    private EditText editTextValor;
    private EditText editTextLitros;
    private TextView textPorcentagem;
    private TextView consumoCalculado;
    private Switch switchCombustivel;
    private CheckBox checkBoxTanqueCheio;
    private Spinner spinnerPorcentagem;
    private String opcaoSelecionada;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        abastecimento = new Abastecimento(getContext());
        consumo = new Consumo(getContext());
        binding = FragmentAbastecimentoBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextKm = view.findViewById(R.id.editTextKm);
        editTextValor = view.findViewById(R.id.editTextValor);
        editTextLitros = view.findViewById(R.id.editTextLitros);
        textPorcentagem = view.findViewById(R.id.textPorcentagem);
        switchCombustivel = view.findViewById(R.id.switchCombustivel);
        checkBoxTanqueCheio = view.findViewById(R.id.checkBoxTanqueCheio);
        spinnerPorcentagem = view.findViewById(R.id.spinnerOpcoes);
        consumoCalculado = view.findViewById(R.id.consumoCalculado);

        binding.checkBoxTanqueCheio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBoxTanqueCheio.isChecked()) {
                    abastecimento.setTanqueCheio(1);
                    abastecimento.setPercentualTanque(100);
                    spinnerPorcentagem.setVisibility(View.INVISIBLE);
                    textPorcentagem.setVisibility(View.INVISIBLE);
                } else {
                    abastecimento.setTanqueCheio(0);
                    spinnerPorcentagem.setVisibility(View.VISIBLE);
                    textPorcentagem.setVisibility(View.VISIBLE);

                }
            }
        });

        binding.switchCombustivel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switchCombustivel.isChecked()) {
                    switchCombustivel.setText("Gasolina");
                } else {
                    switchCombustivel.setText("Etanol");
                }
            }
        });

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (verifyCampos()) {
                    abastecimento.setIdUsuario(1);
                    abastecimento.setIdVeiculo(1);
                    abastecimento.setKmAtual(Integer.valueOf(editTextKm.getText().toString()));
                    abastecimento.setValor(Double.valueOf(editTextValor.getText().toString()));
                    abastecimento.setQuantidadeLitros(Integer.valueOf(editTextLitros.getText().toString()));
                    abastecimento.setTipoCombustivel(switchCombustivel.isChecked() ? "G" : "E");

                    if (abastecimento.getTanqueCheio() == 0) {
                        abastecimento.setPercentualTanque(Integer.valueOf(opcaoSelecionada));
                    }

                    if (verifyCampos()) {
                        abastecimento.registrarAbastecimento(abastecimento);
                        Toast.makeText(getContext(), "ABASTECIMENTO REGISTRADO COM SUCESSO", Toast.LENGTH_LONG).show();
                        consumoCalculado.setText("Consumo Atual: "+ String.valueOf(consumo.calcularConsumo()) + " Km/L");
                        resetCampos();
                    }
                }


            }
        });

        spinnerPorcentagem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                opcaoSelecionada = parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                opcaoSelecionada = "0";
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void resetCampos() {
        editTextKm.setText("");
        editTextValor.setText("");
        editTextLitros.setText("");
        checkBoxTanqueCheio.setChecked(false);
        spinnerPorcentagem.setSelection(0);
    }

    private boolean verifyCampos() {

        if (!checkBoxTanqueCheio.isChecked() && opcaoSelecionada.equals("0")) {
            Toast.makeText(getContext(), "A porcentagem do tanque não pode ser 0", Toast.LENGTH_SHORT).show();
            return false;
        } else if (editTextKm.getText().toString().equals("") || editTextValor.getText().toString().equals("") || editTextLitros.getText().toString().equals("")) {
            Toast.makeText(getContext(), "Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT).show();
            return false;
        } else if (Integer.valueOf(editTextKm.getText().toString()) < abastecimento.kmAnteriorAbastecimento()) {
            Toast.makeText(getContext(), "A kilometragem do veículo não pode ser menor do que a do abastecimento anterior", Toast.LENGTH_SHORT).show();
            return false;
        } else if (Integer.valueOf(editTextLitros.getText().toString()) == 0 || Integer.valueOf(editTextLitros.getText().toString()) > abastecimento.tamTanque()) {
            Toast.makeText(getContext(), "A quantidade de litros abastecida não pode ser 0, nem maior do que o tanque do veículo", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

}