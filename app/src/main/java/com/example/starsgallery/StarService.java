package com.example.starsgallery;

import java.util.ArrayList;
import java.util.List;

public class StarService implements IDao<Star> {

    private List<Star> stars;
    private static StarService instance;

    private StarService() {
        stars = new ArrayList<>();
        seed();
    }

    public static StarService getInstance() {
        if (instance == null) instance = new StarService();
        return instance;
    }

    private void seed() {
        stars.add(new Star("Emma Watson",
                "https://upload.wikimedia.org/wikipedia/commons/7/7f/Emma_Watson_2013.jpg",
                4.5f));

        stars.add(new Star("Tom Cruise",
                "https://upload.wikimedia.org/wikipedia/commons/3/33/Tom_Cruise_by_Gage_Skidmore_2.jpg",
                4.2f));

        stars.add(new Star("Scarlett Johansson",
                "https://upload.wikimedia.org/wikipedia/commons/6/60/Scarlett_Johansson_2010.jpg",
                4.7f));

        stars.add(new Star("Leonardo DiCaprio",
                "https://upload.wikimedia.org/wikipedia/commons/4/46/Leonardo_Dicaprio_Cannes_2019.jpg",
                4.8f));

        stars.add(new Star("Natalie Portman",
                "https://upload.wikimedia.org/wikipedia/commons/0/0e/Natalie_Portman_2018.jpg",
                4.6f));

        stars.add(new Star("Brad Pitt",
                "https://upload.wikimedia.org/wikipedia/commons/4/4c/Brad_Pitt_2019_by_Glenn_Francis.jpg",
                4.3f));

        stars.add(new Star("Angelina Jolie",
                "https://upload.wikimedia.org/wikipedia/commons/a/ad/Angelina_Jolie_2_June_2014_%28cropped%29.jpg",
                4.4f));

        stars.add(new Star("Will Smith",
                "https://upload.wikimedia.org/wikipedia/commons/3/3f/Will_Smith_in_2018.jpg",
                4.1f));

        stars.add(new Star("Margot Robbie",
                "https://upload.wikimedia.org/wikipedia/commons/8/8a/Margot_Robbie_2023.jpg",
                4.6f));

        stars.add(new Star("Johnny Depp",
                "https://upload.wikimedia.org/wikipedia/commons/9/91/Johnny_Depp_-_SDCC_2015.jpg",
                4.0f));
    }

    @Override
    public boolean create(Star o) { return stars.add(o); }

    @Override
    public boolean update(Star o) {
        for (Star s : stars) {
            if (s.getId() == o.getId()) {
                s.setName(o.getName());
                s.setImg(o.getImg());
                s.setStar(o.getStar());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Star o) { return stars.remove(o); }

    @Override
    public Star findById(int id) {
        for (Star s : stars)
            if (s.getId() == id) return s;
        return null;
    }

    @Override
    public List<Star> findAll() { return stars; }
}