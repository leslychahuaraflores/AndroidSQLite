package pe.edu.upeu.registro;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.parameterized.ParametersRunnerFactory;

import java.util.List;

import pe.edu.upeu.registro.bean.Carrito;
import pe.edu.upeu.registro.bean.Person;
import pe.edu.upeu.registro.dao.CarritoDAO;
import pe.edu.upeu.registro.dao.PersonDAO;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("pe.edu.upeu.registro", appContext.getPackageName());

        PersonDAO dao = new PersonDAO(appContext);
        Person person = new Person();
        person.setName("Hernan");
        person.setLastNameF("Vilca");
        person.setLastNameM("Masco");
        dao.savePerson(person);

        List<Person> list = dao.findPersonAll();
        Log.i("list:::",list.size()+"");
        dao.close();
       // assertEquals("Hernan", person.getName());



        CarritoDAO dao1= new CarritoDAO(appContext);
        Carrito carrito= new Carrito();
        carrito.setCodProducto(12);
        carrito.setDesProducto("carrito2");
        carrito.setCantidad(1);
        carrito.setPrecioReal(12554);
        carrito.setTotal(30543);

        dao1.saveCarrito(carrito);

        List<Carrito>list1= dao1.findCarritoAll();

        Log.i("list:::",list1.size()+"");
        dao1.close();


    }



}
