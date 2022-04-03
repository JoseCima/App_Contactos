package com.example.userspjosecima

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class AdaptadorGridView(var contexto: Context, items:ArrayList<Contacto>): BaseAdapter() {

    var items:ArrayList<Contacto>? =null

    //Almacenar los datos que se van a mostrar en el ListView
    var copiaItems:ArrayList<Contacto>? = null
    init {
        //No tienen la misma informacion de memoria pero si de contenido
        this.items = ArrayList(items)//Haciendo una copia nueva de la informacion
        this.copiaItems = items
    }


    override fun getCount(): Int {
        //regresar el numero de elementos de mi lista
        return this.items?.count()!!
    }

    fun addItem(item:Contacto){
        copiaItems?.add(item)
        items = ArrayList(copiaItems)
        notifyDataSetChanged()
    }

    fun removeItem(index:Int){
        copiaItems?.removeAt(index)
        items = ArrayList(copiaItems)
        notifyDataSetChanged()
    }
    fun updateItem(index:Int, newItem:Contacto){
        copiaItems?.set(index, newItem)
        items = ArrayList(copiaItems)
        notifyDataSetChanged()
    }
    //Codigo para actualizar las vistas despues de realizar una operacion
    fun actualizarVistaGrid(){
        items?.clear()
        items= ArrayList(copiaItems)
    }

    fun filtrarGrid(string: String){
        items?.clear()

        if(string.isEmpty()){//Validar cuando el usuario no ha ingresado ningun caracter
            items = ArrayList(copiaItems)
            notifyDataSetChanged()
            return

        }


        var busqueda = string
        busqueda = busqueda.lowercase()

        //Haciendo un recorrido por todos los elementos
        for (item in copiaItems!!){//Doble signo de ! para obtener el contenido
            val nombre = item.nombre.lowercase()//Siempre lo vamos a evaluar en minusculas

            if(nombre.contains(busqueda)){
                items?.add(item)
            }

        }

        notifyDataSetChanged()

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
            vista = LayoutInflater.from(contexto).inflate(R.layout.template_contacto_grid, null)

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
        var nombre: TextView? = null
        var foto: ImageView? = null
        var empresa: TextView? = null

        init {
            nombre = vista.findViewById<TextView>(R.id.gridNombre)
            empresa = vista.findViewById<TextView>(R.id.gridEmpresa)
            foto = vista.findViewById<ImageView>(R.id.gridFoto)

        }
    }






}