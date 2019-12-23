package model;

public class Cell {

    private CellStatus status;
    private int value;

    public Cell(CellStatus status, int value) {
        this.status = status;
        this.value = value;
    }

    public Cell(int value){
        this(CellStatus.CLOSSED, value);
    }

    public CellStatus getStatus() {
        return status;
    }

    public int getValue() {
        return value;
    }

    public void setStatus(CellStatus status) {
        this.status = status;
    }
}
