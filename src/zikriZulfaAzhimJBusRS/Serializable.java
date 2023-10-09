package zikriZulfaAzhimJBusRS;


import java.util.HashMap;

/**
 * Write a description of class Serializable here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Serializable implements Comparable<Serializable> {
    public final int id;
    private static HashMap<Class<?>, Integer> mapCounter = new HashMap<>();

    protected Serializable() {

        Class<? extends Serializable> kelas = this.getClass();

        int nextId = mapCounter.getOrDefault(kelas, -1) + 1;
        mapCounter.put(kelas, nextId);

        this.id = nextId;
    }

    public static Integer setLastAssignedId(Class<?> kelas, int value) {

        return mapCounter.put(kelas, value);
    }

    public static Integer getLastAssignedId(Class<?> kelas) {

        return mapCounter.getOrDefault(kelas, 0);
    }

    public boolean equals(Serializable serializable) {
        if (id == serializable.id) return true;
        else return false;
    }

    public boolean equals(Object obj) {
        Serializable serializable = (Serializable) obj;
        if (obj instanceof Serializable && id == serializable.id) {
            return true;
        } else {
            return false;
        }

    }

    public int compareTo(Serializable other) {

        return Integer.compare(this.id, other.id);
    }
}
