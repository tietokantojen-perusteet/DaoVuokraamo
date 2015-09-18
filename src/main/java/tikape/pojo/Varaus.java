package tikape.pojo;

import java.sql.Timestamp;

public class Varaus {

    private Integer varaustunnus;
    private Pyora pyora;
    private Varaaja varaaja;
    private Timestamp varausAlkaa;
    private Timestamp varausLoppuu;

    public Varaus(Integer varaustunnus, Timestamp varausAlkaa, Timestamp varausLoppuu) {
        this.varaustunnus = varaustunnus;
        this.varausAlkaa = varausAlkaa;
        this.varausLoppuu = varausLoppuu;
    }

    public Varaus(Integer varaustunnus, Pyora pyora, Varaaja varaaja, Timestamp varausAlkaa, Timestamp varausLoppuu) {
        this(varaustunnus, varausAlkaa, varausLoppuu);
        this.pyora = pyora;
        this.varaaja = varaaja;
    }

    public Integer getVaraustunnus() {
        return varaustunnus;
    }

    public void setVaraustunnus(Integer varaustunnus) {
        this.varaustunnus = varaustunnus;
    }

    public Pyora getPyora() {
        return pyora;
    }

    public void setPyora(Pyora pyora) {
        this.pyora = pyora;
    }

    public Varaaja getVaraaja() {
        return varaaja;
    }

    public void setVaraaja(Varaaja varaaja) {
        this.varaaja = varaaja;
    }

    public Timestamp getVarausAlkaa() {
        return varausAlkaa;
    }

    public void setVarausAlkaa(Timestamp varausAlkaa) {
        this.varausAlkaa = varausAlkaa;
    }

    public Timestamp getVarausLoppuu() {
        return varausLoppuu;
    }

    public void setVarausLoppuu(Timestamp varausLoppuu) {
        this.varausLoppuu = varausLoppuu;
    }

}
