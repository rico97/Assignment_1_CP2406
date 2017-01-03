/**
 * Created by Rico on 1/3/2017.
 * Andrico Cahyadi
 * 13232840
 * jc340272
 */
public class Card {
    private String name;
    private float hardness;
    private float specificGravity;
    private String cleavage;
    private String crystalAbundance;
    private String ecoValue;

    Card(String nm, float hard, float speGra, String cleav, String cryAbu, String ecoV)
    {
        name = nm;
        hardness = hard;
        specificGravity = speGra;
        cleavage = cleav;
        crystalAbundance = cryAbu;
        ecoValue = ecoV;
    }

    public String getName() {
        return name;
    }

    public float getHardness() {
        return hardness;
    }

    public float getSpecificGravity() {
        return specificGravity;
    }

    public String getCleavage() {
        return cleavage;
    }

    public String getCrystalAbundance() {
        return crystalAbundance;
    }

    public String getEcoValue() {
        return ecoValue;
    }
}
