package com.project.bookingnetic.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class AppConfig {
    private List<Address> address = new ArrayList<Address>();
    private List<User> users = new ArrayList<User>();

    private List<Accommodation> accommodation = new ArrayList<>();
    private List<Image> images = new ArrayList<>();

    private List<Reservation> reservations = new ArrayList<>();

    private ObjectMapper mapper;

    public AppConfig(){
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
    }
    public AppConfig deserializeUser(String file){

        ObjectMapper userMapper = JsonMapper.builder()
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .build();
        userMapper.registerModule(new JavaTimeModule());

        try {
            InputStream usersF = new FileInputStream(file);
            this.users = userMapper.readValue(usersF, new TypeReference<>() {
            });
        }catch(IOException e){
            e.printStackTrace();
        }

        return this;
    }

    public AppConfig deserializeAddress(String file){

        try {
            InputStream addressFile = new FileInputStream(file);
            this.address = mapper.readValue(addressFile, new TypeReference<>() {
            });
        }catch(IOException e){
            e.printStackTrace();
        }

        return this;
    }

    public void serializeImage(String filepath){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        File file = new File(filepath);
        try {
            mapper.writeValue(file, this.images);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AppConfig deserializeAccomm(String file){
        try {
            InputStream accommFile = new FileInputStream(file);
            this.accommodation = mapper.readValue(accommFile, new TypeReference<List<Accommodation>>() {
            });
        }catch(IOException e){
            e.printStackTrace();
        }

        return this;
    }

    public AppConfig deserializeImage(String file){

        try {
            InputStream imageF = new FileInputStream(file);
            this.images = mapper.readValue(imageF, new TypeReference<List<Image>>() {
            });
        }catch(IOException e){
            e.printStackTrace();
        }

        return this;
    }

    public AppConfig deserializeReservation(String file){

        try {
            InputStream reservF = new FileInputStream(file);
            this.reservations = mapper.readValue(reservF, new TypeReference<List<Reservation>>() {
            });
        }catch(IOException e){
            e.printStackTrace();
        }

        return this;
    }

}


