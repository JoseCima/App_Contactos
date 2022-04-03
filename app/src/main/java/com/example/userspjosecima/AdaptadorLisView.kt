package com.example.userspjosecima

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

import org.w3c.dom.Text

class AdaptadorLisView(var contexto:Context, items:ArrayList<Contacto>):BaseAdapter() {


     var items:ArrayList<Contacto>? =null
    //Almacenar los datos que se van a mostrar en el ListView
    init {
        this.items = items
    }


    override fun getCount(): Int {
        //regresar el numero de elementos de mi lista
        return this.items?.count()!!
    }

    override fun getItem(position: Int): Any {
        //Obteniendo y regresando los objetos
        return this.items?.get(position)!!
    }

    override fun getItemId(position: Int): Long {
        //Obteniendo cada elemento por su ID.
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {


        var viewHolder:ViewHolder? = null
        var vista:View? = convertView

        if( vista == null){
            vista = LayoutInflater.from(contexto).inflate(R.layout.template_contacto, null)

            viewHolder = ViewHolder(vista)
            vista.tag = viewHolder

        }else{

            viewHolder = vista.tag as? ViewHolder
        }

        var item = getItem(position) as Contacto

        //Asignacion de valores a alementos graficos
        viewHolder?.nombre?.text = item.nombre + " " + item.apellidos
        viewHolder?.empresa?.text = item.empresa
        viewHolder?.foto?.setImageResource(item.foto)

        return vista!!
    }

    //definiendo viewholder
    private class ViewHolder(vista:View){
        var nombre:TextView? = null
        var foto:ImageView? = null
        var empresa:TextView? = null

        init {
            nombre = vista.findViewById<TextView>(R.id.idNombre)
            empresa = vista.findViewById<TextView>(R.id.idDesc)
            foto = vista.findViewById<ImageView>(R.id.idFoto)

        }
    }

}