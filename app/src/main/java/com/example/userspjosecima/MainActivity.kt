package com.example.userspjosecima

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import androidx.appcompat.widget.Toolbar;

class MainActivity : AppCompatActivity() {

    var lista:ListView? = null
    var adaptador:AdaptadorLisView? = null

    //Transfromando en un objeto estatico
    companion object{
        var contactos:ArrayList<Contacto>? = null
        //Kotlin no elimina su contenido  al menos al flujo de nav de nuestra app, puede ser invocado directamente sin crear uns instancia

        fun agregarContacto(contacto:Contacto){
            contactos?.add(contacto)
        }

        fun obtenerContacto(index:Int):Contacto{

            return contactos?.get(index)!!
        }

        fun eliminarContacto(index:Int){
            contactos?.removeAt(index)
        }

        fun actualizarContacto(index:Int, nuevoContacto:Contacto){
            contactos?.set(index, nuevoContacto)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //anidando nuestro toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)
        //Lo anterior solo corresponde a agregar la barra del toolbar

        contactos = ArrayList()
        contactos?.add(Contacto("Jose Guillermo", "Cima Tamay", "Costoso", 25, 70.0F, "Ciudad de FCP", "9831319990","josegcima@gmail.com", foto = R.drawable.foto_01))
        lista = findViewById<ListView>(R.id.lista)
        adaptador = AdaptadorLisView(this, contactos!!)

        lista?.adapter = adaptador

        lista?.setOnItemClickListener{parent, view, position, id ->
            val intent = Intent(this, detalle::class.java)
            intent.putExtra("ID", position.toString())
            startActivity(intent)
        }
    }

    //me permite aderir mis elementos xml a la pantalla

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item?.itemId){
            R.id.iNuevo ->{
                val intent = Intent(this, Nuevo::class.java)
                startActivity(intent)
                return true
            }

            else ->(return super.onOptionsItemSelected(item))
        }
    }


    override fun onResume() {
        super.onResume()

        adaptador?.notifyDataSetChanged()

    }
}