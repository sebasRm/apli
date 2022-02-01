package com.example.myapplication.interfaz;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.example.myapplication.interfaz.inventario.InvenarioVentasFragment;
import com.example.myapplication.interfaz.inventario.InventarioProductosFragment;
import com.example.myapplication.interfaz.pedido.PedidosFragment;
import com.example.myapplication.interfaz.plato.PlatosFragment;


public class HomeFragment extends Fragment {
  PlatosFragment platosFragment;
    PedidosFragment pedidosFragment;

    ConstraintLayout constrain_menu,constrain_mesas,constrain_domicilios,constrain_inventario;

    private String rolUsuario;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home, container, false);

        constrain_menu =root.findViewById(R.id.constrain_menu);
        constrain_mesas =root.findViewById(R.id.constrain_mesas);
        constrain_domicilios =root.findViewById(R.id.constrain_domicilios);
        constrain_inventario =root.findViewById(R.id.constrain_inventario);

      //  constrain_menu.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_transition));



        recuperarPreferencias();

        CardView agregarPedido = root.findViewById(R.id.botonAgregar);
        agregarPedido.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.left_transition));
        agregarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment pedidosFragment = new PedidosEnMesaFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, pedidosFragment);
                transaction.addToBackStack(null);

                // Commit a la transacción
                transaction.commit();
            }
        });

        CardView pedidos = root.findViewById(R.id.botonPedidos);
        pedidos.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.left_transition));
        pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Fragment platosFragment = new PedidosEnDomiciliosFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.slide_right, R.anim.slide_left);
                transaction.replace(R.id.nav_host_fragment, platosFragment);
                transaction.addToBackStack(null);
                // Commit a la transacción
                transaction.commit();

            }
        });


        CardView platos = root.findViewById(R.id.botonPlatos);
        platos.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.left_transition));
        platos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rolUsuario.equals("Admin"))
                {
                    Fragment platosFragment = new PlatosFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.slide_right, R.anim.slide_left);
                    transaction.replace(R.id.nav_host_fragment, platosFragment);
                    transaction.addToBackStack(null);
                    // Commit a la transacción
                    transaction.commit();
                }
                else
                {
                    Toast.makeText(getContext(), "No tiene permisos para acceder a Menu", Toast.LENGTH_SHORT).show();
                }
            }
        });



        CardView inventario = root.findViewById(R.id.botonInventario);
        inventario.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.left_transition));

        inventario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rolUsuario.equals("Admin"))
                {
                    Fragment inventarioProductosFragment = new InventarioProductosFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.nav_host_fragment, inventarioProductosFragment);
                    transaction.addToBackStack(null);

                    // Commit a la transacción
                    transaction.commit();
                }
                else
                {
                    Toast.makeText(getContext(), "No tiene permisos para acceder a Inventario", Toast.LENGTH_SHORT).show();
                }
            }
        });



        CardView ventas = root.findViewById(R.id.boton_ventas);
        ventas.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.left_transition));
        ventas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rolUsuario.equals("Admin"))
                {
                    Fragment invenarioVentasFragment = new InvenarioVentasFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.nav_host_fragment, invenarioVentasFragment);
                    transaction.addToBackStack(null);

                    // Commit a la transacción
                    transaction.commit();
                }
                else
                {
                    Toast.makeText(getContext(), "No tiene permisos para acceder a Ventas", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return root;
    }







    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void recuperarPreferencias()
    {
        SharedPreferences preferences= getContext ().getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        boolean sesion=preferences.getBoolean("sesion",false);
        if(sesion)
        {
            this.rolUsuario=preferences.getString("rol", "No hay nada");
        }
    }

}