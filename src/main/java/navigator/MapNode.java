package navigator;


import java.util.ArrayList;

class MapNode {

    private Location location;
    private ArrayList<NodeConnection> connections = new ArrayList<>();
    private boolean isVisited = false;
    private MapNode bestNavigationPredecessor;
    private double bestNavigationTime = Double.MAX_VALUE;

    MapNode(Location location) {
        this.location = location;
    }

    void addConnection(NodeConnection newConnection) {
        connections.add(newConnection);
    }

    Location getLocation() {
        return location;
    }

    ArrayList<NodeConnection> getConnections() {
        return connections;
    }

    void visit() {
        isVisited = true;
    }

    boolean hasNotBeenVisited() {
        return !isVisited;
    }

    double getBestNavigationTime() {
        return bestNavigationTime;
    }

    void setBestNavigationTime(double bestNavigationTime) {
        this.bestNavigationTime = bestNavigationTime;
    }

    MapNode getBestNavigationPredecessor() {
        return bestNavigationPredecessor;
    }

    void setBestNavigationPredecessor(MapNode bestNavigationPredecessor) {
        this.bestNavigationPredecessor = bestNavigationPredecessor;
    }

    boolean hasBestNavigationPredecessor() {
        return bestNavigationPredecessor != null;
    }
}
