package com.example.myapplication.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.interfaz.inventario.InventarioProductosFragment;
import com.example.myapplication.interfaz.plato.MenuPlatosFragment;
import com.example.myapplication.mundo.Inventario;

import java.util.ArrayList;

public class AdaptadorListaProductos extends  RecyclerView.Adapter<AdaptadorListaProductos.ViewHolder>  implements View.OnClickListener
{

    private final InventarioProductosFragment ventana;
    private ArrayList<Inventario> list;
    private MenuPlatosFragment buscarPlato;
    private Context contexto;
    private static LayoutInflater  inflater = null;
    private View.OnClickListener listener;

    public AdaptadorListaProductos(Context contexto, ArrayList<Inventario> lista, InventarioProductosFragment inventarioProductosFragment)
    {
        this.list=lista;
        this.contexto = contexto;
        inflater = LayoutInflater.from(contexto);
        this.ventana=inventarioProductosFragment;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = inflater.inflate(R.layout.fragment_lista_productos, null);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        // holder.tipoDeOrdenVenta.setText(list.get(position));
     
        // holder.precioDeEnvioVenta.setText(list.get(position).getFactura_precio_envio()+"");

        holder.codigoProducto.setText (this.list.get (position).getIdproductos ()+"");
        holder.producto.setText (this.list.get (position).getNombre ());
        holder.entradas.setText (this.list.get (position).getEntrada_total ()+"");
        holder.salidas.setText (this.list.get (position).getSalida_total ()+"");
        holder.stock.setText (this.list.get (position).getStock ()+"");
        holder.stockMin.setText (this.list.get (position).getStock_minimo ()+"");
        holder.surtir.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                ventana.iniciarSurtirProducto(list.get (position),v);
            }
        });


        holder.verVentas.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                ventana.iniciarVerVentas(list.get (position).getIdproductos (),v);
            }
        });
        holder.VerCompras.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                ventana.iniciarVerCompras(list.get (position).getIdproductos (),v);
            }
        });
        holder.modificar.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                ventana.iniciarModificarProducto(list.get (position),v);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void setFragment(MenuPlatosFragment buscarPlato)
    {
        this.buscarPlato=buscarPlato;
    }

    @Override
    public void onClick(View v)
    {
        if(listener!=null)
        {
            listener.onClick(v);
        }
    }
    public void setOnclickListener(View.OnClickListener listener)
    {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ConstraintLayout item;
        TextView codigoProducto,producto,entradas,salidas,stock,stockMin;
        ImageButton surtir,verVentas,VerCompras,modificar;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            codigoProducto=(TextView) itemView.findViewById(R.id.codigoProducto);
            producto=(TextView) itemView.findViewById(R.id.producto);
            entradas=(TextView) itemView.findViewById(R.id.entradas);
            salidas=(TextView) itemView.findViewById(R.id.salidas);
            stock=(TextView) itemView.findViewById (R.id.stock);
            stockMin=(TextView) itemView.findViewById(R.id.stockMin);
            surtir=(ImageButton) itemView.findViewById(R.id.surtir);
            verVentas=(ImageButton) itemView.findViewById(R.id.verVentas);
            VerCompras=(ImageButton) itemView.findViewById(R.id.VerCompras);
            modificar=(ImageButton) itemView.findViewById(R.id.modificar);
            //item=(ConstraintLayout) itemView.findViewById(R.id.constrain_domicilio);

        }
    }



    public ArrayList<Inventario> getList()
    {
        return this.list;
    }


}