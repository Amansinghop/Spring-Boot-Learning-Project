package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.baseEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/backend")
public class baseController {

    Map<String , baseEntry> baseData =new HashMap<>();
    @GetMapping
    public List<baseEntry> getAll(){
        return new ArrayList<>(baseData.values());
    }
    @GetMapping("id/{myId}")
    public baseEntry getByd(@PathVariable String myId){
        return baseData.get(myId);
    }
    @PostMapping
    public Boolean createEntry(@RequestBody baseEntry myEntry ){
        baseData.put(myEntry.getId(),myEntry);

        return true;
    }
    @DeleteMapping("id/{myId}")
    public Boolean deleteById(@PathVariable String myId){
        baseData.remove(myId);
        return true;

    }
    @PutMapping("id/{myId}")
    public baseEntry updateById(@PathVariable String myId,@RequestBody baseEntry myEntry){
        return baseData.put(myId,myEntry);
    }





}
