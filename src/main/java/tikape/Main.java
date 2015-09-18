package tikape;

import tikape.pojo.Varaus;
import tikape.dao.VarausDao;
import tikape.dao.PyoraDao;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("org.sqlite.JDBC", "jdbc:sqlite:vuokraamo.db");
        PyoraDao pyoraDao = new PyoraDao(database);
        VarausDao varausDao = new VarausDao(database, pyoraDao);

        for (Varaus varaus : varausDao.findAll()) {
            System.out.println(varaus.getPyora().getRekisterinumero() + " -> " + varaus.getVarausAlkaa());
        }

    }
}
