package an.poliakov.example2;

public enum TypeAnimal{
    PEACEFUL("Не бойся этого животного"),
    DENGEROUS("Беги пока не съели!");

    private String description;

    private TypeAnimal(String description) {
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
