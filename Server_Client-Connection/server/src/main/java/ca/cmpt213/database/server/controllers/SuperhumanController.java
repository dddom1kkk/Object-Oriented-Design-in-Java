package ca.cmpt213.database.server.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import ca.cmpt213.database.server.models.Superhuman;
import ca.cmpt213.database.server.models.SuperhumanCollection;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * SuperhumanController - class for REST API methods manipulations
 */
@RestController
public class SuperhumanController {

    private SuperhumanCollection superhumans = new SuperhumanCollection();

    @GetMapping("/api/superhuman/all")
    public ArrayList<Superhuman> getAllSuperhumans() {

        System.out.println("GET /person/all");

        return superhumans.getSuperhumans();

    }

    @GetMapping("api/superhuman/{sid}")
    public Superhuman getSuperhuman(@PathVariable long sid, HttpServletResponse response) {

        System.out.println("GET /api/superhuman/" + sid);

        Superhuman findSuperhuman = superhumans.getSuperhuman(sid);

        if (findSuperhuman == null)
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

        return findSuperhuman;

    }

    @PostMapping("/api/superhuman/add")
    public Superhuman postMethodName(@RequestBody Superhuman newSuperhuman, HttpServletResponse response) {

        System.out.println("POST /api/superhuman/add");
        boolean responseCode = superhumans.addSuperhuman(newSuperhuman);

        if (responseCode == true)
            response.setStatus(HttpServletResponse.SC_CREATED);
        else
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        return newSuperhuman;

    }

    @DeleteMapping("/api/superhuman/{sid}")
    public void deleteSuperhuman(@PathVariable long sid, HttpServletResponse response) {

        System.out.println("DELETE /api/superhuman/" + sid);

        Superhuman findSuperhuman = superhumans.deleteSuperhuman(sid);

        if (findSuperhuman == null)
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        else
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);

    }

    @PostConstruct
    public void init() {

        Gson gsonRead = new Gson();
        String jsonFile = "src/main/java/ca/cmpt213/database/server/data/superhuman.json";

        try (Reader reader = new FileReader(jsonFile)) {
            superhumans.setSuperhumans(gsonRead.fromJson(reader, superhumans.getClass()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
