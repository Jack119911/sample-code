package navigator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

class Navigator {

    private static final double DISTANCE_TIME_MOD_WITH_CAR = 0.1;
    private static final int DISTANCE_TIME_MOD_NO_CAR = 1;
    private static final int UP_HILL_HEIGHT_CHANGE_TIME_MOD_NO_CAR = 2;
    private boolean byCar;

    private ArrayList<MapNode> nodesToVisit;

    Route getFastestRoute(NavigationMap map, Location start, Location destination, boolean byCar) {
        initData(byCar);
        initStartNode(map, start);
        return getBestRoute(map.getMapNode(destination));
    }

    private void initData(boolean byCar) {
        this.byCar = byCar;
        nodesToVisit = new ArrayList<>();
    }

    private void initStartNode(NavigationMap map, Location start) {
        MapNode startNode = map.getMapNode(start);
        startNode.setBestNavigationTime(0);
        nodesToVisit.add(startNode);
    }

    private Route getBestRoute(MapNode destination) {
        runNodeExpansionTillDestination(destination);
        return composeRouteToNode(destination);
    }

    private void runNodeExpansionTillDestination(MapNode destination) {
        while (!nodesToVisit.isEmpty()) {
            MapNode nodeToExpand = getNextNode();
            if (nodeToExpand == destination) {
                return;
            } else {
                expandNode(nodeToExpand);
            }
        }
    }

    private void expandNode(MapNode node) {
        for (NodeConnection connection : node.getConnections()) {
            if (byCar && !connection.isUsableByCars()) {
                continue;
            }
            updateDestinationDistance(connection);
            updateNodesToVisit(connection.getDestination());
        }
    }

    private void updateDestinationDistance(NodeConnection connection) {
        if (connection.getDestination().hasNotBeenVisited()) {
            setDistanceForDestination(connection);
        }
    }

    private void updateNodesToVisit(MapNode node) {
        if (!nodesToVisit.contains(node) && node.hasNotBeenVisited()) {
            nodesToVisit.add(node);
        }
    }

    private MapNode getNextNode() {
        MapNode nodeWithMinimalDistance = getNodeWithMinimalDistance();
        updateNodeVisited(nodeWithMinimalDistance);
        return nodeWithMinimalDistance;
    }

    private MapNode getNodeWithMinimalDistance() {
        MapNode nodeWithMinimalDistance = nodesToVisit.get(0);
        for (MapNode currentNode : nodesToVisit) {
            if (currentNode.getBestNavigationTime() < nodeWithMinimalDistance.getBestNavigationTime()) {
                nodeWithMinimalDistance = currentNode;
            }
        }
        return nodeWithMinimalDistance;
    }

    private void updateNodeVisited(MapNode nodeWithMinimalDistance) {
        nodeWithMinimalDistance.visit();
        nodesToVisit.remove(nodeWithMinimalDistance);
    }

    private void setDistanceForDestination(NodeConnection connection) {
        double newTimeToConnectionDestination = getNewTimeToConnectionDestination(connection);
        MapNode destinationNode = connection.getDestination();
        if (newTimeToConnectionDestination < destinationNode.getBestNavigationTime()) {
            destinationNode.setBestNavigationTime(newTimeToConnectionDestination);
            destinationNode.setBestNavigationPredecessor(connection.getStart());
        }
    }

    private double getNewTimeToConnectionDestination(NodeConnection connection) {
        double timeToConnectionStartNode = connection.getStart().getBestNavigationTime();
        double timeForConnection = calculateTime(connection);
        return timeToConnectionStartNode + timeForConnection;
    }

    private double calculateTime(NodeConnection connection) {
        if (byCar) {
            return calculateTimeWithCar(connection);
        } else {
            return calculateTimeWithoutCar(connection);
        }

    }

    private double calculateTimeWithCar(NodeConnection connection) {
        return connection.getDistance() * DISTANCE_TIME_MOD_WITH_CAR;
    }

    private int calculateTimeWithoutCar(NodeConnection connection) {
        int distance = connection.getDistance();
        int upHillHeightChange = Math.max(connection.getDifferenceInHeight(), 0);
        return distance * DISTANCE_TIME_MOD_NO_CAR + upHillHeightChange * UP_HILL_HEIGHT_CHANGE_TIME_MOD_NO_CAR;
    }

    private Route composeRouteToNode(MapNode node) {
        int completeDistance = 0;
        int completeUpHillHeightChange = 0;
        ArrayList<MapNode> allWayPoints = new ArrayList<>();
        allWayPoints.add(node);

        MapNode currentNode = node;
        while(currentNode.hasBestNavigationPredecessor()) {
            MapNode predecessor = currentNode.getBestNavigationPredecessor();

            allWayPoints.add(predecessor);
            NodeConnection connection = getConnectionToNode(predecessor, currentNode);
            completeDistance += connection.getDistance();
            completeUpHillHeightChange += Math.max(connection.getDifferenceInHeight(), 0);

            currentNode = predecessor;
        }
        Collections.reverse(allWayPoints);
        return new Route(completeDistance, completeUpHillHeightChange, node.getBestNavigationTime(), allWayPoints);
    }

    private NodeConnection getConnectionToNode(MapNode origin, MapNode destination) {
        for (NodeConnection connection : origin.getConnections()) {
            if (connection.getDestination() == destination) {
                return connection;
            }
        }
        throw new NoSuchElementException("No connection to this destination");
    }

}
