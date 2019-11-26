package dao;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import webprog.model.Hallgato;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class DAOJSON {
    File file;
    ObjectMapper mapper;

    public DAOJSON(String filepath) throws IOException {
        this.file = new File(filepath);
        this.mapper= new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        if(!file.exists()){
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write("[]");
            writer.close();

        }
    }

    public Collection<Hallgato> readAllHallgato() throws IOException {
        Collection<Hallgato> result = new ArrayList<>();
        result= mapper.readValue(file,
                new TypeReference<ArrayList<Hallgato>>(){});
        return result;
    }

    public void addHallgato(Hallgato hallgato) throws IOException, DuplikaltHallgato {
        try {
            readByNeptunKod(hallgato.getNeptunKod());
        } catch (HallgatoNincs hallgatoNincs) {
            Collection<Hallgato> hallgatok = readAllHallgato();
            hallgatok.add(hallgato);
            mapper.writeValue(file, hallgatok);
            return;
        }
        throw new DuplikaltHallgato(hallgato.getNeptunKod());
    }

    public Hallgato readByNeptunKod(String neptunKod) throws IOException,
            HallgatoNincs {
        Collection<Hallgato> hallgatok = readAllHallgato();
        for(Hallgato h: hallgatok){
            if(h.getNeptunKod().equalsIgnoreCase(neptunKod)){
                return h;
            }
        }
        throw new HallgatoNincs();
    }
}
