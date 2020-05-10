package waisol.tech.mominholyquranqibladirectionnamesofallahprayertimingstasbih.Model;

/**
 * Created by tehrim on 8/3/2018.
 */

public class DifferenceModel {
    String NamazName, Time, Difference;

    public DifferenceModel(String namazName, String time, String difference) {
        NamazName = namazName;
        Time = time;
        Difference = difference;
    }

    public String getNamazName() {
        return NamazName;
    }

    public String getTime() {
        return Time;
    }

    public String getDifference() {
        return Difference;
    }
}
