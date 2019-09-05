package navigator;

import java.util.ArrayList;

class Route {

    private int length;
    private int uphillHeightChange;
    private double estimatedTime;
    private ArrayList<MapNode> wayPoints;


    Route(int length, int uphillHeightChange, double estimatedTime, ArrayList<MapNode> wayPoints) {
        this.length = length;
        this.uphillHeightChange = uphillHeightChange;
        this.estimatedTime = estimatedTime;
        this.wayPoints = wayPoints;
    }

    double getEstimatedTime() {
        return estimatedTime;
    }

    int getUphillHeightChange() {
        return uphillHeightChange;
    }

    int getLength() {
        return length;
    }

    ArrayList<MapNode> getWayPoints() {
        return wayPoints;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("_____________________________\n");
        builder.append("Length: ").append(length).append("\n");
        builder.append("HeightChange: ").append(uphillHeightChange).append("\n");
        builder.append("Estimated time: ").append(Math.round(estimatedTime/60)).append("\n");
        builder.append("\n");
        builder.append("Way points:\n");
        for (MapNode node : wayPoints) {
            builder.append(node.getLocation().name()).append("\n");
        }
        builder.append("_____________________________\n");
        return builder.toString();
    }
}
