package net.engineeringdigest.journalApp.servises;

import net.engineeringdigest.journalApp.entity.BaseEntry;
import net.engineeringdigest.journalApp.repository.BaseEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BaseEntryServise {

    @Autowired
    public BaseEntryRepository baseEntryRepository;


    public void saveEntry (BaseEntry baseEntry){
         baseEntryRepository.save(baseEntry);
    }
    public List<BaseEntry> getEntry (){
        return baseEntryRepository.findAll();
    }
    public Optional<BaseEntry> getEntryById (String myId){
        return baseEntryRepository.findById(myId);
    }
    public void deleteById(String myId){
        baseEntryRepository.deleteById(myId);
    }


}
