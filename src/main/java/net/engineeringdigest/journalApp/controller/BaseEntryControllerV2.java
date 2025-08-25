package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.BaseEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.servises.BaseEntryServise;
import net.engineeringdigest.journalApp.servises.UserServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/backend")
public class BaseEntryControllerV2 {


    @Autowired
    private  BaseEntryServise baseEntryServise;
    @Autowired
    private UserServise userServise;



    @GetMapping()
    public ResponseEntity<List<BaseEntry>> getAll( ){
        String userName ="amankumar";
        User user = userServise.findUserByUserName(userName);
        List<BaseEntry> all= user.getJournalEntry();
        System.out.println("API Hit");
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<List<BaseEntry>>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("id/{myId}")
    public ResponseEntity<BaseEntry> getById(@PathVariable String
 myId){
        Optional<BaseEntry> baseEntry = baseEntryServise.getEntryById(myId);
        if(baseEntry.isPresent()){
            return new ResponseEntity<BaseEntry>(baseEntry.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<BaseEntry>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("{userName}")
    public ResponseEntity<BaseEntry> createEntry(@RequestBody BaseEntry myEntry,@PathVariable String userName ){
        System.out.println("Data saved");
       try {
           baseEntryServise.saveEntry(myEntry,userName);
           return  new ResponseEntity<BaseEntry>(myEntry, HttpStatus.OK);
       }catch (Exception e){
           return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);

       }

    }
    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<?> deleteById(@PathVariable String
 myId,@PathVariable String userName){
        baseEntryServise.deleteById(myId,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    @PutMapping("id/{userName}/{myId}")
    public ResponseEntity<BaseEntry> updateById(
            @PathVariable String
 myId,
            @RequestBody BaseEntry newEntry,
            @PathVariable String userName){
        try {
            BaseEntry old = baseEntryServise.getEntryById(myId).orElse(null);
            if (old != null) {
                old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
                old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
            }
           baseEntryServise.saveEntry(old);

            return new ResponseEntity<BaseEntry>(old,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }





}
