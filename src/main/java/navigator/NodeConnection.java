package navigator;

class NodeConnection {

    private MapNode start;
    private MapNode destination;
    private int distance;
    private int differenceInHeight;
    private boolean usableByCars;


    NodeConnection(MapNode start, MapNode destination, int distance, int differenceInHeight, boolean usableByCars) {
        this.start = start;
        this.destination = destination;
        this.distance = distance;
        this.differenceInHeight = differenceInHeight;
        this.usableByCars = usableByCars;
    }

    MapNode getStart() {
        return start;
    }

    MapNode getDestination() {
        return destination;
    }

    int getDistance() {
        return distance;
    }

    int getDifferenceInHeight() {
        return differenceInHeight;
    }

    boolean isUsableByCars() {
        return usableByCars;
    }
}
