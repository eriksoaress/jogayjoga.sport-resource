package jogayjoga.sport;



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.NonNull;

@Service
public class SportService {

    @Autowired
    private SportRepository sportRepository;

    public Sport create(Sport in){
        return sportRepository.save(new SportModel(in)).to();
    }

    @Cacheable(key = "#id", value = "sport", unless = "#result == null")
    public SportOut read(@NonNull String id) {
        System.out.println("Reading sport id: " + id);
        return SportParser.to(sportRepository.findById(id).map(model -> model.to()).orElse(null));
    }

    public void delete(String id) {
        sportRepository.deleteById(id);
    }

    public SportOut update(String id, SportIn in) {
        Sport sport = sportRepository.findById(id).map(SportModel::to).orElse(null);
        if (sport == null) {
            // Handle the case when the sport is not found
            throw new RuntimeException("Sport not found");
        }
        sport.name(in.name()); // Fluent setter
        SportModel updatedModel = sportRepository.save(new SportModel(sport));

        // Convert the updated Sport object to a SportOut object and return it
        return SportParser.to(updatedModel.to());
    }

    public List<SportOut> readAll() {
        List<SportModel> sportModels = new ArrayList<>();
        sportRepository.findAll().forEach(sportModels::add);

        List<Sport> sports = sportModels.stream()
            .map(SportModel::to)
            .collect(Collectors.toList());

        if (sports.isEmpty()) {
            return null;
        }

        // converte sports para List<SportOut>
        List<SportOut> SportOuts = new ArrayList<>();
        for (Sport sport : sports) {
            SportOuts.add(SportParser.to(sport));
        }

        return SportOuts;
    }
    

}