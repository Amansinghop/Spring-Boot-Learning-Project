package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.BaseEntry;
import net.engineeringdigest.journalApp.servises.BaseEntryServise;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/backend")
public class BaseEntryControllerV2 {


    @Autowired
    private  BaseEntryServise baseEntryServise;

    @GetMapping
    public ResponseEntity<List<BaseEntry>> getAll(){

        List<BaseEntry> baseEntry= baseEntryServise.getEntry();
        if(baseEntry!=null && !baseEntry.isEmpty()){
            return new ResponseEntity<List<BaseEntry>>(baseEntry,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("id/{myId}")
    public ResponseEntity<BaseEntry> getById(@PathVariable String myId){
        Optional<BaseEntry> baseEntry = baseEntryServise.getEntryById(myId);
        if(baseEntry.isPresent()){
            return new ResponseEntity<BaseEntry>(baseEntry.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<BaseEntry>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<BaseEntry> createEntry(@RequestBody BaseEntry myEntry ){
       try {
           myEntry.setDate(LocalDateTime.now());
           baseEntryServise.saveEntry(myEntry);
           return  new ResponseEntity<BaseEntry>(myEntry, HttpStatus.OK);
       }catch (Exception e){
           return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);

       }

    }
    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteById(@PathVariable String myId){
        baseEntryServise.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    @PutMapping("id/{myId}")
    public ResponseEntity<BaseEntry> updateById(@PathVariable String myId,@RequestBody BaseEntry newEntry){
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
