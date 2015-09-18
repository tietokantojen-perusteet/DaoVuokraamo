package tikape.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import tikape.Database;
import tikape.pojo.Pyora;

public class PyoraDao implements Dao<Pyora, String> {

    private Database database;

    public PyoraDao(Database database) {
        this.database = database;
    }

    @Override
    public Pyora findOne(String key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Pyora WHERE rekisterinumero = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        String rekisterinumero = rs.getString("rekisterinumero");
        String merkki = rs.getString("merkki");

        Pyora p = new Pyora(rekisterinumero, merkki);

        rs.close();
        stmt.close();
        connection.close();

        return p;
    }

    @Override
    public List<Pyora> findAll() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Pyora");

        ResultSet rs = stmt.executeQuery();
        List<Pyora> pyorat = new ArrayList<>();
        while (rs.next()) {
            String rekisterinumero = rs.getString("rekisterinumero");
            String merkki = rs.getString("merkki");

            pyorat.add(new Pyora(rekisterinumero, merkki));
        }

        rs.close();
        stmt.close();
        connection.close();

        return pyorat;
    }

    @Override
    public List<Pyora> findAllIn(Collection<String> keys) throws SQLException {
        if (keys.isEmpty()) {
            return new ArrayList<>();
        }

        // Luodaan IN-kyselyä varten paikat, joihin arvot asetetaan -- 
        // toistaiseksi IN-parametrille ei voi antaa suoraan kokoelmaa
        StringBuilder muuttujat = new StringBuilder("?");
        for (int i = 1; i < keys.size(); i++) {
            muuttujat.append(", ?");
        }

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Pyora WHERE rekisterinumero IN (" + muuttujat + ")");
        int laskuri = 1;
        for (String key : keys) {
            stmt.setObject(laskuri, key);
            laskuri++;
        }

        ResultSet rs = stmt.executeQuery();
        List<Pyora> pyorat = new ArrayList<>();
        while (rs.next()) {
            String rekisterinumero = rs.getString("rekisterinumero");
            String merkki = rs.getString("merkki");

            pyorat.add(new Pyora(rekisterinumero, merkki));
        }

        return pyorat;
    }

    @Override
    public void delete(String key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
