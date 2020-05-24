package fr.axa.editique.users.constante;


public enum Field {
    USER_ID("USER_ID"),USER_FIRST_NAME("USER_FIRST_NAME");
    private String value;

    Field(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
