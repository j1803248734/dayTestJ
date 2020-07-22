package redblack;

public enum Color {

    RED("red"),
    BLACK("black");

    private String color;
    private Color(String color)
    {
        this.color = color;
    }

    @Override
    public String toString()
    {
        return color;
    }
}
