package ca.cmpt213.database.server.models;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

/**
 * SuperhumanCollection - class for collecting superhumans in one place and
 * manipulating with their data
 */
public class SuperhumanCollection extends ArrayList<Superhuman> {

    private ArrayList<Superhuman> superhumans = new ArrayList<>();
    private Stack<Long> deletedFree = new Stack<>();

    public boolean addSuperhuman(Superhuman newSuperhuman) {

        if (!deletedFree.isEmpty())
            newSuperhuman.setSid(Integer.parseInt(Long.toString(deletedFree.pop())));
        else {
            boolean found = false;
            int id = 0;
            while (!found) {
                boolean exists = false;

                for (Superhuman superhuman : superhumans)
                    if (superhuman.getSid() == id)
                        exists = true;

                if (!exists) {
                    found = true;
                    newSuperhuman.setSid(id);
                    break;
                }

                id++;
            }
        }

        superhumans.add(newSuperhuman);

        Gson gsonWrite = new GsonBuilder().setPrettyPrinting().create();
        String jsonFile = "src/main/java/ca/cmpt213/database/server/data/superhuman.json";

        try (FileWriter writer = new FileWriter(jsonFile)) {
            gsonWrite.toJson(superhumans, writer);
        } catch (JsonIOException e) {
            return false;
        } catch (IOException e) {
            return false;
        }

        return true;

    }

    public Superhuman getSuperhuman(long sid) {

        if (superhumans.size() == 0 || sid < 0)
            return null;

        for (Superhuman superhuman : superhumans)
            if (superhuman.getSid() == sid)
                return superhuman;

        return null;

    }

    public Superhuman deleteSuperhuman(long sid) {

        Superhuman deleted = null;

        if (superhumans.size() == 0 || sid < 0)
            return null;

        for (Superhuman superhuman : superhumans) {
            if (superhuman.getSid() == sid) {
                superhumans.remove(superhuman);
                deletedFree.push(sid);

                deleted = superhuman;

                break;
            }
        }

        if (deleted != null) {
            Gson gsonWrite = new GsonBuilder().setPrettyPrinting().create();
            String jsonFile = "src/main/java/ca/cmpt213/database/server/data/superhuman.json";

            try (FileWriter writer = new FileWriter(jsonFile)) {
                gsonWrite.toJson(superhumans, writer);
            } catch (JsonIOException e) {
                return null;
            } catch (IOException e) {
                return null;
            }
        }

        return deleted;

    }

    // Getters & Setters
    public ArrayList<Superhuman> getSuperhumans() {
        return superhumans;
    }

    public void setSuperhumans(ArrayList<Superhuman> superhumans) {
        this.superhumans = superhumans;
    }

}
