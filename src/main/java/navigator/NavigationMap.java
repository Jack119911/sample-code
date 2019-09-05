package navigator;

import java.util.ArrayList;
import java.util.NoSuchElementException;

class NavigationMap {

    private ArrayList<MapNode> mapNodes = new ArrayList<>();

    NavigationMap() {
        createNodes();
        addConnections();
    }

    private void createNodes() {
        mapNodes.add(new MapNode(Location.CASTLE));
        mapNodes.add(new MapNode(Location.GARDEN));
        mapNodes.add(new MapNode(Location.CHURCH));
        mapNodes.add(new MapNode(Location.RIVER));
        mapNodes.add(new MapNode(Location.RESTAURANT));
        mapNodes.add(new MapNode(Location.SEE));
    }

    private void addConnections() {
        addConnection(Location.CASTLE, Location.GARDEN, 500, -50, false);
        addConnection(Location.GARDEN, Location.CASTLE, 500, 50, false);
        addConnection(Location.CASTLE, Location.RIVER, 2000, -100, true);
        addConnection(Location.RIVER, Location.CASTLE, 2000, 100, true);
        addConnection(Location.GARDEN, Location.CHURCH, 200, -30, true);
        addConnection(Location.CHURCH, Location.GARDEN, 200, 30, true);
        addConnection(Location.CHURCH, Location.RIVER, 300, -20, true);
        addConnection(Location.RIVER, Location.CHURCH, 300, 20, true);
        addConnection(Location.RIVER, Location.RESTAURANT, 700, 10, true);
        addConnection(Location.RESTAURANT, Location.RIVER, 700, -10, true);
        addConnection(Location.RESTAURANT, Location.SEE, 600, -20, true);
        addConnection(Location.SEE, Location.RESTAURANT, 600, 20, true);
        addConnection(Location.SEE, Location.RIVER, 1000, 10, true);
        addConnection(Location.RIVER, Location.SEE, 1000, -10, true);
    }

    private void addConnection(Location start, Location end, int distance, int differenceInHeight, boolean usableByCars) {
        MapNode startNode = getMapNode(start);
        MapNode endNode = getMapNode(end);
        NodeConnection newConnection = new NodeConnection(startNode, endNode, distance, differenceInHeight, usableByCars);
        startNode.addConnection(newConnection);
    }

    MapNode getMapNode(Location location) {
        for (MapNode node : mapNodes) {
            if (node.getLocation() == location) {
                return node;
            }
        }
        throw new NoSuchElementException("This Map does not contains a node with this location");
    }

}
