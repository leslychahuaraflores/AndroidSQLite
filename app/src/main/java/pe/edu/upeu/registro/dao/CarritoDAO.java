package pe.edu.upeu.registro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upeu.registro.bean.Carrito;
import pe.edu.upeu.registro.bean.Person;
import pe.edu.upeu.registro.util.Commons;

/**
 * Created by Alumnos on 02/05/2017.
 */

public class CarritoDAO extends SQLiteOpenHelper {

    private static final int VERSION=1;

    public CarritoDAO(Context context) {
        super(context, Commons.DATABASE, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String ddl="CREATE TABLE Carrito(" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " codProducto INTEGER," +
                " desProducto TEXT," +
                " cantidad INTEGER," +
                " precioReal INTEGER," +
                " total INTEGER," +
                ");";
        db.execSQL(ddl);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String ddl = "DROP TABLE IF EXISTS Carrito";
        db.execSQL(ddl);
        this.onCreate(db);
    }

    public void saveCarrito(Carrito carrito){
        ContentValues values=new ContentValues();
        values.put("codProducto",carrito.getCodProducto());
        values.put("desProducto",carrito.getDesProducto());
        values.put("cantidad",carrito.getCantidad());
        values.put("precioReal",carrito.getPrecioReal());
        values.put("total",carrito.getTotal());


        getWritableDatabase().insert("Carrito",null,values);
    }

    public void updateCarrito(Carrito carrito){
        ContentValues values = new ContentValues();
        values.put("codProducto", carrito.getCodProducto());
        values.put("desProducto", carrito.getDesProducto());
        values.put("cantidad", carrito.getCantidad());
        values.put("precioReal", carrito.getPrecioReal());
        values.put("total", carrito.getTotal());

        getWritableDatabase().update("Carrito",values,"id="+carrito.getId(),null);
    }

    public void deleteCarrito(Carrito id){
        String where = "id = "+id;
        getWritableDatabase().delete("Carrito",where,null);

    }


    public List<Carrito> findCarritoAll() {
        List<Carrito> listCarrito = new ArrayList<Carrito>();
        String columns[]={"id","codProducto","desProducto","cantidad","precioReal","total"};
        String where=null;
        Cursor cursor = getReadableDatabase().query("Carrito",columns,where,null,null,null,null);
        Carrito carrito=null;
        while (cursor.moveToNext()){
            carrito = new Carrito();
            carrito.setId(cursor.getLong(0));
            carrito.setCodProducto(cursor.getInt(1));
            carrito.setDesProducto(cursor.getString(2));
            carrito.setCantidad(cursor.getInt(3));
            carrito.setPrecioReal(cursor.getInt(4));
            carrito.setTotal(cursor.getInt(5));
            listCarrito.add(carrito);
        }
        return listCarrito;
    }
    public Carrito findCarritoById(Long id){
        String column[]={"id","codProducto","desProducto","cantidad","precioReal","total"};
        String where = "id = "+id;

        Carrito carrito=null;
        Cursor cursor = getReadableDatabase().query("Carrito",column,where,null,null,null,null);
        if(cursor.moveToFirst()){
            carrito=new Carrito();
            carrito.setId(cursor.getLong(0));
            carrito.setCodProducto(cursor.getInt(1));
            carrito.setDesProducto(cursor.getString(2));
            carrito.setCantidad(cursor.getInt(3));
            carrito.setPrecioReal(cursor.getInt(4));
            carrito.setTotal(cursor.getInt(5));
        }
        cursor.close();
        return carrito;
    }


}
