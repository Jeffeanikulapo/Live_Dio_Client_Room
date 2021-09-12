package com.digital.crud.saladereniao.saladereuniao.controller;

import com.digital.crud.saladereniao.saladereuniao.exception.ResourceNotFoundException;
import com.digital.crud.saladereniao.saladereuniao.model.Room;
import com.digital.crud.saladereniao.saladereuniao.repository.RooRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;


@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class RoomController {

    @Autowired
    private  RooRepository rooRepository;

    @GetMapping("/rooms")
    public  List<Room> getAllRooms(){
        return rooRepository.findAll();
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> getRooByUd(@PathVariable(value = "id")long roomId)
        throws ResourceNotFoundException{
        Room room =  rooRepository.findById(roomId)
                .orElseThrow(()-> new ResourceNotFoundException("Room not Found:: " + roomId));
        return ResponseEntity.ok().body(room);
    }

    @PostMapping("/rooms")
    public Room createRoom (@Valid @RequestBody Room room) {
        return rooRepository.save(room);
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable(value = "id")long roomId,
                                          @Valid @RequestBody Room roomDetails) throws  ResourceNotFoundException{
        Room room = rooRepository.findById(roomId)
                .orElseThrow(()-> new ResourceNotFoundException ("Room not found for this id :: "+ roomId));
                room.setName(roomDetails.getName());
                room.setDate(roomDetails.getDate());
                room.setStartHour(roomDetails.getStartHour());
                room.setEndHour(roomDetails.getEndHour());
                final Room updateRoom = rooRepository.save(room);
                return ResponseEntity.ok(updateRoom);
    }



    @DeleteMapping("/rooms/{id}")
public Map<String, Boolean> deleteRoom(@PathVariable(value = "id")Long roomId)
    throws ResourceNotFoundException{
        Room room = rooRepository.findById(roomId)
                .orElseThrow(()-> new ResourceNotFoundException("Room not found for this id : "+ roomId));
        rooRepository.delete(room);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
