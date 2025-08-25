package net.engineeringdigest.journalApp.servises;

import net.engineeringdigest.journalApp.entity.BaseEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.BaseEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class BaseEntryServise {

    @Autowired
    public BaseEntryRepository baseEntryRepository;
    @Autowired
    public UserServise userServise;


    public void saveEntry (BaseEntry baseEntry, String userName){
        baseEntry.setDate(LocalDateTime.now());
        User user =userServise.findUserByUserName(userName);
        BaseEntry saved = baseEntryRepository.save(baseEntry);
        user.getJournalEntry().add(saved);
        userServise.saveUser(user);

    }
    public void saveEntry (BaseEntry baseEntry ){
        baseEntryRepository.save(baseEntry);

    }
    public List<BaseEntry> getEntry (){
        return baseEntryRepository.findAll();
    }
    public Optional<BaseEntry> getEntryById (String myId){
        return baseEntryRepository.findById(myId);
    }
    public void deleteById(String myId, String userName){
        User user = userServise.findUserByUserName(userName);
        user.getJournalEntry().removeIf(x -> x.getId().equals(myId));
        baseEntryRepository.deleteById(myId);
        userServise.saveUser(user);


    }


}
