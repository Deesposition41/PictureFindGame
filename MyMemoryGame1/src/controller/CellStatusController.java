package controller;

import Exceptions.UsageOfInactiveCellException;
import model.Cell;
import model.CellStatus;

public class CellStatusController {

    public static void swap(Cell cell){
        CellStatus currentStatus = cell.getStatus();
        if(currentStatus == CellStatus.INACTIVE){
            throw new UsageOfInactiveCellException();
        }
        if(currentStatus == CellStatus.CLOSSED){
            cell.setStatus(CellStatus.OPENED);
        } else{
            cell.setStatus(CellStatus.CLOSSED);
        }
    }

    public static void makeInaktive(Cell cell){
        cell.setStatus(CellStatus.INACTIVE);
    }

}
