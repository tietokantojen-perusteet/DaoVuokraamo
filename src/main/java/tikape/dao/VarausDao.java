package tikape.dao;

import java.sql.*;
import java.util.*;
import tikape.Database;
import tikape.pojo.Pyora;
import tikape.pojo.Varaus;

public class VarausDao implements Dao<Varaus, Integer> {

    private Database database;
    private Dao<Pyora, String> pyoraDao;

    public VarausDao(Database database, Dao<Pyora, String> pyoraDao) {
        this.database = database;
        this.pyoraDao = pyoraDao;
    }

    @Override
    public Varaus findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Varaus WHERE varaustunnus = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer varaustunnus = rs.getInt("varaustunnus");
        Timestamp alku = rs.getTimestamp("varaus_alkaa");
        Timestamp loppu = rs.getTimestamp("varaus_loppuu");

        Varaus v = new Varaus(varaustunnus, alku, loppu);

        Integer varaaja = rs.getInt("varaaja");
        String pyora = rs.getString("pyora");

        rs.close();
        stmt.close();
        connection.close();

        v.setPyora(this.pyoraDao.findOne(pyora));

        return v;
    }

    @Override
    public List<Varaus> findAll() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Varaus");
        ResultSet rs = stmt.executeQuery();

        Map<String, List<Varaus>> varaustenPyorat = new HashMap<>();

        List<Varaus> varaukset = new ArrayList<>();

        while (rs.next()) {

            Integer varaustunnus = rs.getInt("varaustunnus");
            Timestamp alku = rs.getTimestamp("varaus_alkaa");
            Timestamp loppu = rs.getTimestamp("varaus_loppuu");

            Varaus v = new Varaus(varaustunnus, alku, loppu);
            varaukset.add(v);

            String pyora = rs.getString("pyora");

            if (!varaustenPyorat.containsKey(pyora)) {
                varaustenPyorat.put(pyora, new ArrayList<>());
            }
            varaustenPyorat.get(pyora).add(v);
        }

        rs.close();
        stmt.close();
        connection.close();

        for (Pyora pyora : this.pyoraDao.findAllIn(varaustenPyorat.keySet())) {
            for (Varaus varaus : varaustenPyorat.get(pyora.getRekisterinumero())) {
                varaus.setPyora(pyora);
            }
        }

        return varaukset;
    }

    @Override
    public List<Varaus> findAllIn(Collection<Integer> keys) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
