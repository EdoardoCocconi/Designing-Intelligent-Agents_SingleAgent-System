package uk.ac.nott.cs.g53dia.agent;

import uk.ac.nott.cs.g53dia.library.*;

import java.util.HashMap;
import java.util.Map;

import static sun.security.pkcs11.wrapper.PKCS11Constants.TRUE;

public class ExploredMap {

    public Map<Point, Cell> map;

    public ExploredMap(DemoLitterAgent agent) {
        this.map = new HashMap<Point, Cell>();
        agent.isMapCreated = TRUE;
    }

    public void Update(Cell[][] view) {

        for (Cell[] row : view) {
            for (Cell cell : row) {
                this.map.put(cell.getPoint(), cell);
            }
        }

    }


    public Cell[][] getView(Point pos, int size) {
        Cell[][] res = new Cell[size * 2 + 1][size * 2 + 1];
        for (int x = pos.getX() - size; x <= pos.getX() + size; x++) {
            for (int y = pos.getY() - size; y <= pos.getY() + size; y++) {
                int i = x - (pos.getX() - size);
                int j = (2 * size) - (y - (pos.getY() - size));
                res[i][j] = map.get(new Point(x, y));
            }
        }
        return res;
    }

}
