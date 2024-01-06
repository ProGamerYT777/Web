package ru.skypro.lessons.springboot.web.dto;

public class PositionDTO {

    private int id;
    private String name;

    public PositionDTO() {
    }

    public PositionDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PositionDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
