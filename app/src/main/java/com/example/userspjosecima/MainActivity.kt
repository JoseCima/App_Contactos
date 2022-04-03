package com.example.userspjosecima

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.GridView
import android.widget.ListView
import android.widget.Switch
import android.widget.ViewSwitcher
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar;

class MainActivity : AppCompatActivity() {

    var lista:ListView? = null
    var grid:GridView? = null


    var viewSwitcher:ViewSwitcher? = null

    //Transfromando en un objeto estatico
    companion object{
        var contactos:ArrayList<Contacto>? = null
        //Kotlin no elimina su contenido  al menos al flujo de nav de nuestra app, puede ser invocado directamente sin crear uns instancia

        var adaptador:AdaptadorLisView? = null

        var adaptadorGrid:AdaptadorGridView? = null

        //Operaciones en Listview
        fun agregarContacto(contacto:Contacto){
            adaptador?.addItem(contacto)

            adaptadorGrid?.actualizarVistaGrid()

        }

        fun obtenerContacto(index:Int):Contacto{

            return adaptador?.getItem(index)!! as Contacto

        }
        fun obtenerContactoGrid(index:Int):Contacto{

            return adaptadorGrid?.getItem(index)!! as Contacto

        }

        fun eliminarContacto(index:Int){
            adaptador?.removeItem(index)

            adaptadorGrid?.actualizarVistaGrid()
        }


        fun actualizarContacto(index:Int, nuevoContacto:Contacto){
            adaptador?.updateItem(index, nuevoContacto)

            adaptadorGrid?.actualizarVistaGrid()

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
        contactos?.add(Contacto("Lupis", "Cima", "Costoso", 25, 70.0F, "Ciudad de FCP", "9831319990","josegcima@gmail.com", foto = R.drawable.foto_02))
        contactos?.add(Contacto("Lucy", "Ake", "Costoso", 25, 70.0F, "Ciudad de FCP", "9831319990","josegcima@gmail.com", foto = R.drawable.foto_03))
        contactos?.add(Contacto("Jose Angelo", "Cima Ake", "Costoso", 25, 70.0F, "Ciudad de FCP", "9831319990","josegcima@gmail.com", foto = R.drawable.foto_04))
        contactos?.add(Contacto("Carmen", "Tamay", "Costoso", 25, 70.0F, "Ciudad de FCP", "9831319990","josegcima@gmail.com", foto = R.drawable.foto_05))
        contactos?.add(Contacto("Diego", "Cima", "Costoso", 25, 70.0F, "Ciudad de FCP", "9831319990","josegcima@gmail.com", foto = R.drawable.foto_06))

        lista = findViewById<ListView>(R.id.lista)
        grid = findViewById<GridView>(R.id.grid)
        adaptador = AdaptadorLisView(this, contactos!!)
        adaptadorGrid = AdaptadorGridView(this, contactos!!)



        viewSwitcher = findViewById(R.id.viewSwitcher)

        lista?.adapter = adaptador
        grid?.adapter = adaptadorGrid

        lista?.setOnItemClickListener{parent, view, position, id ->
            val intent = Intent(this, detalle::class.java)
            intent.putExtra("ID", position.toString())
            startActivity(intent)
        }

        grid?.setOnItemClickListener{parent, view, position, id ->
            val intent = Intent(this, detalle::class.java)
            intent.putExtra("ID", position.toString())
            startActivity(intent)
        }
    }

    //me permite aderir mis elementos xml a la pantalla

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)

        //Buscar en listView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val itemBusqueda = menu?.findItem(R.id.searchView)
        val searchView = itemBusqueda?.actionView as SearchView



        val itemSwitch = menu?.findItem(R.id.switchView)
        itemSwitch?.setActionView(R.layout.switch_item)
        val switchView = itemSwitch.actionView.findViewById<Switch>(R.id.sCambiarVista)


        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = "Buscar contacto.."

        searchView.setOnQueryTextFocusChangeListener{ v, hasFocus ->
            //Preparar los datos

        }
        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                //Filtrar
                adaptador?.filtrar(newText!!)
                adaptadorGrid?.filtrarGrid(newText!!)
                return true
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                //Filtrar
                return true
            }
        })


        switchView?.setOnCheckedChangeListener { buttonView, inChecked ->


            viewSwitcher?.showNext()

        }
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
        adaptadorGrid?.notifyDataSetChanged()



    }

}