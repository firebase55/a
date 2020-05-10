package waisol.tech.mominholyquranqibladirectionnamesofallahprayertimingstasbih.Model;

/**
 * Created by tehrim on 8/3/2018.
 */

public class NamazModel {
    String NamazName, Time, Date, gregorianDate;
    Boolean Adda, Qaza;
    boolean isCurrentNamaz;

    public NamazModel(String NamazName, String Time, String date, String gregorianDate, Boolean adda, Boolean qaza, boolean isCurrentNamaz) {
        this.NamazName = NamazName;
        this.Time = Time;
        this.Date = date;
        this.gregorianDate = gregorianDate;
        this.Adda = adda;
        this.Qaza = qaza;
        this.isCurrentNamaz = isCurrentNamaz;

    }

    public boolean isCurrentNamaz() {
        return isCurrentNamaz;
    }

    public void setCurrentNamaz(boolean currentNamaz) {
        isCurrentNamaz = currentNamaz;
    }

    @Override
    public String toString() {
        return "NamazModel{" +
                "NamazName='" + NamazName + '\'' +
                ", Time='" + Time + '\'' +
                ", Date='" + Date + '\'' +
                ", Adda=" + Adda +
                ", Qaza=" + Qaza +
                '}';
    }

    public String getGregorianDate() {
        return gregorianDate;
    }

    public void setGregorianDate(String gregorianDate) {
        this.gregorianDate = gregorianDate;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getNamazName() {
        return NamazName;
    }

    public void setNamazName(String namazName) {
        NamazName = namazName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Boolean getAdda() {
        return Adda;
    }

    public void setAdda(Boolean adda) {
        Adda = adda;
    }

    public Boolean getQaza() {
        return Qaza;
    }

    public void setQaza(Boolean qaza) {
        Qaza = qaza;
    }
}
